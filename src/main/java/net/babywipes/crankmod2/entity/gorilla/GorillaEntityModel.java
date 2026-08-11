package net.babywipes.crankmod2.entity.gorilla;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GorillaEntityModel extends EntityModel<GorillaEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart r_arm;
	private final ModelPart r_foreArm;
	private final ModelPart l_arm;
	private final ModelPart l_foreArm;
	private final ModelPart r_leg;
	private final ModelPart l_leg;

	private final KeyframeAnimation poundChest;

	public GorillaEntityModel(ModelPart root) {
        super(root);
		this.poundChest = GorillaAnimations.poundChest.bake(root);

        this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.r_arm = this.body.getChild("r_arm");
		this.r_foreArm = this.r_arm.getChild("r_foreArm");
		this.l_arm = this.body.getChild("l_arm");
		this.l_foreArm = this.l_arm.getChild("l_foreArm");
		this.r_leg = this.body.getChild("r_leg");
		this.l_leg = this.body.getChild("l_leg");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.4333F, 6.3559F, -2.3873F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -8.0F, -20.0F, 16.0F, 14.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 5.6441F, 11.6536F, -0.4363F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 40).addBox(-12.0F, -16.0F, -5.0F, 12.0F, 16.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(84, 0).addBox(-10.0F, -6.0F, -8.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(86, 27).addBox(-12.0F, -16.0F, -6.0F, 12.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -8.3559F, -4.3464F));

		PartDefinition r_arm = body.addOrReplaceChild("r_arm", CubeListBuilder.create().texOffs(78, 62).addBox(-8.0F, -4.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -4.3559F, -4.3464F));

		PartDefinition r_foreArm = r_arm.addOrReplaceChild("r_foreArm", CubeListBuilder.create().texOffs(0, 67).addBox(-3.0F, 0.0F, -5.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 8.0F, 1.0F));

		PartDefinition l_arm = body.addOrReplaceChild("l_arm", CubeListBuilder.create().texOffs(78, 82).addBox(0.0F, -5.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -3.3559F, -4.3464F));

		PartDefinition l_foreArm = l_arm.addOrReplaceChild("l_foreArm", CubeListBuilder.create().texOffs(78, 40).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 7.0F, 0.0F));

		PartDefinition r_leg = body.addOrReplaceChild("r_leg", CubeListBuilder.create().texOffs(46, 40).addBox(-8.0F, -4.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 5.6441F, 13.6536F));

		PartDefinition l_leg = body.addOrReplaceChild("l_leg", CubeListBuilder.create().texOffs(46, 64).addBox(0.0F, -4.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 5.6441F, 13.6536F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
	@Override
	public void setupAnim(GorillaEntityRenderState state) {
		super.setupAnim(state);
		if (state.chestPoundAnimationState.isStarted()) {
			this.poundChest.apply(state.chestPoundAnimationState, state.ageInTicks);
		} else {
			float limbSwingAmplitude = state.walkAnimationSpeed;
			float limbSwingAnimationProgress = state.walkAnimationPos;
			this.r_arm.xRot = Mth.cos(limbSwingAnimationProgress * 0.4f + Mth.PI) * 1f * limbSwingAmplitude;
			this.l_arm.xRot = Mth.cos(limbSwingAnimationProgress * 0.4f) * 1f * limbSwingAmplitude;
			this.l_leg.xRot = Mth.cos(limbSwingAnimationProgress * 0.4f + Mth.PI) * 1.4f * limbSwingAmplitude;
			this.r_leg.xRot = Mth.cos(limbSwingAnimationProgress * 0.4f) * 1.4f * limbSwingAmplitude;
		}
	}
}