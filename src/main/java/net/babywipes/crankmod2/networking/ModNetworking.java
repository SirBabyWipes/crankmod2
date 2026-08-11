package net.babywipes.crankmod2.networking;

import com.google.common.graph.Network;

import net.babywipes.crankmod2.sounds.SpeakerState;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;

public class ModNetworking {
    public static void initalizeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ClientboundSpeakerPayload.TYPE, (payload, context) -> {
            SpeakerState packet = payload.state();
            SoundManager soundManager = Minecraft.getInstance().getSoundManager();
            var instance = NetworkingStatics.speakerInstances.get(Integer.valueOf(packet.id));
            if (instance == null) {
                instance = NetworkingStatics.getNewSpeakerInstance();
                NetworkingStatics.speakerInstances.put(Integer.valueOf(packet.id), instance);
            }

            if (packet.start) {
                soundManager.play(instance);
                instance.setVolume(packet.volume);
                return;
            }

            if (packet.end) {
                soundManager.stop(instance);
                return;
            }

            if (packet.playing) {
                //if (!soundManager.isActive(instance)) {
                //    System.out.println("DONE");
                //}
                instance.setVolume(packet.volume);
                return;
            }
        });
    }

    public static void initalizeServer() {
        PayloadTypeRegistry.clientboundPlay().register(ClientboundSpeakerPayload.TYPE, ClientboundSpeakerPayload.CODEC);
    }
}
