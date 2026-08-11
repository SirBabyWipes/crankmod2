package net.babywipes.crankmod2.sounds;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public abstract class AbstractDynamicSoundInstance extends AbstractTickableSoundInstance {
    protected int currentTick = 0;

    protected AbstractDynamicSoundInstance(SoundEvent soundEvent, SoundSource soundCategory) {
        super(soundEvent, soundCategory, SoundInstance.createUnseededRandom());

        this.volume = 1.0f;
    }

    @Override
    public void tick() {
        //check for sound source null
        this.currentTick++;
    }

    public void decreaseVolume() {
        this.volume = this.volume - 0.1f;
    }

    public void setVolume(float val) {
        this.volume = val;
    }
}
