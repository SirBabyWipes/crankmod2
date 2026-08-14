package net.babywipes.crankmod2.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class VisibleEntityState {
    public int entityId;
    public boolean visible;

    public VisibleEntityState(int entityId, boolean visible) {
        this.entityId = entityId;
        this.visible = visible;
    }

    public static final StreamCodec<ByteBuf, VisibleEntityState> STREAM_CODEC = new StreamCodec<ByteBuf,VisibleEntityState>() {
        public VisibleEntityState decode(final ByteBuf input) {
            return new VisibleEntityState(input.readInt(), input.readBoolean());
        }

        public void encode(final ByteBuf output, final VisibleEntityState value) {
            output.writeInt(value.entityId);
            output.writeBoolean(value.visible);
        }
    }; 
}
