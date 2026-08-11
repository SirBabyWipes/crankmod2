package net.babywipes.crankmod2;

import net.babywipes.crankmod2.client.ModEntityModelLayers;
import net.babywipes.crankmod2.entity.ModEntityTypes;
import net.babywipes.crankmod2.entity.gorilla.GorillaEntityRenderer;
import net.babywipes.crankmod2.networking.ModNetworking;
import net.babywipes.crankmod2.networking.NetworkingStatics;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class CrankMod2Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NetworkingStatics.initalize();
        ModNetworking.initalizeClient();
		ModEntityTypes.registerModEntityTypes();
		ModEntityModelLayers.registerModelLayers();
		EntityRenderers.register(ModEntityTypes.GORILLA, GorillaEntityRenderer::new);
    }
}
