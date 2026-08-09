package net.babywipes.crankmod2.networking;

import net.babywipes.crankmod2.sounds.SpeakerState;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.Minecraft;

public class ModNetworking {
    public static void initalizeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ClientboundSpeakerPayload.TYPE, (payload, context) -> {
            SpeakerState packet = payload.state();
            if (packet.start) {
                Minecraft.getInstance().getSoundManager().play(NetworkingStatics.speakerInstance);
                NetworkingStatics.speakerInstance.setVolume(packet.volume);
                return;
            }

            if (packet.playing) {
                NetworkingStatics.speakerInstance.setVolume(packet.volume);
                return;
            }

            if (packet.end) {
                Minecraft.getInstance().getSoundManager().stop(NetworkingStatics.speakerInstance);
                return;
            }
        });
    }

    public static void initalizeServer() {
        PayloadTypeRegistry.clientboundPlay().register(ClientboundSpeakerPayload.TYPE, ClientboundSpeakerPayload.CODEC);
    }
}
