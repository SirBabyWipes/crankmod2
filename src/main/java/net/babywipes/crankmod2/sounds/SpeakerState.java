package net.babywipes.crankmod2.sounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class SpeakerState {
    public boolean start = false;
    public float volume = 0.0f;
    public boolean end = false;
    public boolean playing = false;

    public SpeakerState(boolean start, float volume, boolean end, boolean playing) {
        this.start = start;
        this.volume = volume;
        this.end = end;
        this.playing = playing;
    }

    public static final StreamCodec<ByteBuf, SpeakerState> STREAM_CODEC = new StreamCodec<ByteBuf,SpeakerState>() {
        public SpeakerState decode(final ByteBuf input) {
            return new SpeakerState(input.readBoolean(), input.readFloat(), input.readBoolean(), input.readBoolean());
        }

        public void encode(final ByteBuf output, final SpeakerState value) {
            output.writeBoolean(value.start);
            output.writeFloat(value.volume);
            output.writeBoolean(value.end);
            output.writeBoolean(value.playing);
        }
    };
}
