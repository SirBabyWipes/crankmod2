package net.babywipes.crankmod2.networking;

import net.babywipes.crankmod2.CrankMod2;
import net.babywipes.crankmod2.sounds.SpeakerState;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ClientboundSpeakerPayload(SpeakerState state) implements CustomPacketPayload {
    public static final Identifier SPEAKER_STATE_ID = Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, "speaker_state");

	public static final CustomPacketPayload.Type<ClientboundSpeakerPayload> TYPE =
        new CustomPacketPayload.Type<>(SPEAKER_STATE_ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSpeakerPayload> 
        CODEC = StreamCodec.composite(SpeakerState.STREAM_CODEC, ClientboundSpeakerPayload::state, ClientboundSpeakerPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
