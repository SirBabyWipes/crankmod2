package net.babywipes.crankmod2;

import net.babywipes.crankmod2.networking.ModNetworking;
import net.babywipes.crankmod2.networking.NetworkingStatics;
import net.fabricmc.api.ClientModInitializer;

public class CrankMod2Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NetworkingStatics.initalize();
        ModNetworking.initalizeClient();
    }
}
