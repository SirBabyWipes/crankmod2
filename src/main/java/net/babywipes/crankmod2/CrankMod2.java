package net.babywipes.crankmod2;

import net.babywipes.crankmod2.creativemodetab.ModCreativeModeTabs;
import net.babywipes.crankmod2.entity.ModEntityTypes;
import net.babywipes.crankmod2.item.ModItems;
import net.babywipes.crankmod2.sounds.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrankMod2 implements ModInitializer {
	public static final String MOD_ID = "crankmod2";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
        ModSounds.initalize();
		ModEntityTypes.registerModEntityTypes();
	}
}

