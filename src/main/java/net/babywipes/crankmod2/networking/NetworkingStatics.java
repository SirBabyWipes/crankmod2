package net.babywipes.crankmod2.networking;

import net.babywipes.crankmod2.sounds.ModSounds;
import net.babywipes.crankmod2.sounds.SpeakerSoundInstance;
import net.minecraft.sounds.SoundSource;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NetworkingStatics {
    public static final SpeakerSoundInstance speakerInstance = new SpeakerSoundInstance(ModSounds.RIP, SoundSource.MUSIC);
    public static void initalize() {
        return; 
    }
}
