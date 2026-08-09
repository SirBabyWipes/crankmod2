package net.babywipes.crankmod2.sounds;

import net.babywipes.crankmod2.CrankMod2;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent RIP = registerSound("rip");

    public static SoundEvent registerSound(String id) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, id),
                SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, id)));
    }

    public static void initalize() {
        CrankMod2.LOGGER.info("REGISTERING " + CrankMod2.MOD_ID + " SOUNDS");
    }
}
