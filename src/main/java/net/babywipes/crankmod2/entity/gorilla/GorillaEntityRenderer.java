package net.babywipes.crankmod2.entity.gorilla;

import net.babywipes.crankmod2.CrankMod2;
import net.babywipes.crankmod2.client.ModEntityModelLayers;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;


public class GorillaEntityRenderer extends MobRenderer<GorillaEntity, GorillaEntityRenderState, GorillaEntityModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, "textures/entity/gorilla.png");

    public GorillaEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GorillaEntityModel(context.bakeLayer(ModEntityModelLayers.GORILLA)), 0.375f); // 0.375 shadow radius
    }


    @Override
    public GorillaEntityRenderState createRenderState() {
        return new GorillaEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(GorillaEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(GorillaEntity entity, GorillaEntityRenderState state, float tickProgress) {
        super.extractRenderState(entity, state, tickProgress);
        state.chestPoundAnimationState.copyFrom((entity.poundChestAnimationState));
    }
}