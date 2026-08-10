package net.babywipes.crankmod2.client;

import net.babywipes.crankmod2.CrankMod2;
import net.babywipes.crankmod2.entity.gorilla.GorillaEntityModel;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModEntityModelLayers {
    public static final ModelLayerLocation GORILLA = createMain("net.babywipes.crankmod2.entity.gorilla.gorilla");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, name), "main");
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(ModEntityModelLayers.GORILLA, GorillaEntityModel::getTexturedModelData);
    }
}