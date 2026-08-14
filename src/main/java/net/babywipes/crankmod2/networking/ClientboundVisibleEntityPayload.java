package net.babywipes.crankmod2.networking;

import net.babywipes.crankmod2.CrankMod2;
import net.babywipes.crankmod2.entity.VisibleEntityState;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ClientboundVisibleEntityPayload(VisibleEntityState state) implements CustomPacketPayload {
    public static final Identifier STATE_ID = Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, "visible_entity_state");

    public static final CustomPacketPayload.Type<ClientboundVisibleEntityPayload> TYPE =
        new CustomPacketPayload.Type<>(STATE_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundVisibleEntityPayload> CODEC =
        StreamCodec.composite(VisibleEntityState.STREAM_CODEC, ClientboundVisibleEntityPayload::state, ClientboundVisibleEntityPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
