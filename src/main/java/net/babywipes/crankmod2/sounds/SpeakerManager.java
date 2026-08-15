package net.babywipes.crankmod2.sounds;

import java.util.HashMap;
import java.util.Stack;

import net.babywipes.crankmod2.networking.ClientboundSpeakerPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpeakerManager {
    public record SpeakerPlayingInstance(int entityId, String playerName) {}

    private static Stack<Integer> removeStack = new Stack<>();
    private static HashMap<Integer, SpeakerPlayingInstance> instanceMap = new HashMap<>();
    public static void initalize() {
        ServerTickEvents.END_SERVER_TICK.register((server) -> {
            instanceMap.forEach((id, instance) -> {
                ServerPlayer holder = server.getPlayerList().getPlayerByName(instance.playerName()); 
                if (holder == null) {
                    removeStack.push(id);
                    return; 
                }

                Vec3 holderPos = holder.position();
                int entityId = holder.asLivingEntity().getId();
                for (var player : PlayerLookup.all(server)) {
                    if (player == null) { continue; }
                    float volume = calculateSoundVolume((float)(holderPos.distanceTo(player.position())), 20f);
                    var payload = new ClientboundSpeakerPayload(new SpeakerState(false, volume, false, true, entityId));

                    ServerPlayNetworking.send(player, payload);
                }

            });

            removeStack.forEach((id) -> {
                instanceMap.remove(id);
                for (var player : PlayerLookup.all(server)) {
                    if (player == null) { continue; }
                    var payload = new ClientboundSpeakerPayload(new SpeakerState(false, 0.0f, true, false, id));

                    ServerPlayNetworking.send(player, payload);
                }
            });
            removeStack.clear();
        });
    }    

    private static float calculateSoundVolume(float distance, float maxDistance) {
        distance = Math.min(distance, maxDistance);
        return (float)Math.pow((double)((distance - 0f) / (-maxDistance)) + 1f, 2d);
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
