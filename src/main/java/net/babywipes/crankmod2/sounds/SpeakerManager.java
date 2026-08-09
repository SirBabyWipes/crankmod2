package net.babywipes.crankmod2.sounds;

import java.util.HashMap;

import net.babywipes.crankmod2.networking.ClientboundSpeakerPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class SpeakerManager {
    public record SpeakerPlayingInstance(int entityId, String playerName) {}

    private static HashMap<Integer, SpeakerPlayingInstance> instanceMap = new HashMap<>();
    public static void initalize() {
        ServerTickEvents.END_SERVER_TICK.register((server) -> {
            for (var instance : instanceMap.values()) {
                ServerPlayer holder = server.getPlayerList().getPlayerByName(instance.playerName()); 
                int entityId = holder.asLivingEntity().getId();
                for (var player : PlayerLookup.all(server)) {
                    float volume = calculateSoundVolume((float)(holder.position().distanceTo(player.position())), 20f);
                    var payload = new ClientboundSpeakerPayload(new SpeakerState(false, volume, false, true, entityId));

                    ServerPlayNetworking.send(player, payload);
                }
            }
            //server.getPlayerList().getPlayerByName(name);
        });
    }    

    private static float calculateSoundVolume(float distance, float maxDistance) {
        if (distance > maxDistance) { distance = maxDistance; }
        return ((distance - 0f) / (-maxDistance)) + 1f;
    }

    public static void startPlaying(String playerName, int entityId, Level level) {
        if (instanceMap.get(entityId) != null) {
            return;
        }

        var payload = new ClientboundSpeakerPayload(new SpeakerState(true, 0.0f, false, false, entityId));
        for (ServerPlayer player : PlayerLookup.level((ServerLevel) level)) {
            ServerPlayNetworking.send(player, payload);
        }

        var instance = new SpeakerPlayingInstance(entityId, playerName);
        instanceMap.put(Integer.valueOf(entityId), instance);
    }
}
