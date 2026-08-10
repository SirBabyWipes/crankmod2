package net.babywipes.crankmod2.entity.gorilla;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.babywipes.crankmod2.CrankMod2;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

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

	public GorillaEntityModel(ModelPart root) {
        super(root);
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
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition partdefinition = modelData.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 14.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -24.0F, 16.0F, 14.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 40).addBox(-6.0F, -16.0F, -10.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(84, 0).addBox(-4.0F, -6.0F, -12.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -16.0F));

		PartDefinition r_arm = body.addOrReplaceChild("r_arm", CubeListBuilder.create().texOffs(80, 62).addBox(-8.0F, -8.0F, -2.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -6.0F, -22.0F));

		PartDefinition r_foreArm = r_arm.addOrReplaceChild("r_foreArm", CubeListBuilder.create().texOffs(0, 68).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 0.0F));

		PartDefinition l_arm = body.addOrReplaceChild("l_arm", CubeListBuilder.create().texOffs(80, 82).addBox(0.0F, -8.0F, -2.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -6.0F, -22.0F));

		PartDefinition l_foreArm = l_arm.addOrReplaceChild("l_foreArm", CubeListBuilder.create().texOffs(80, 40).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 0.0F));

		PartDefinition r_leg = body.addOrReplaceChild("r_leg", CubeListBuilder.create().texOffs(48, 40).addBox(-8.0F, -2.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -2.0F, -2.0F));

		PartDefinition l_leg = body.addOrReplaceChild("l_leg", CubeListBuilder.create().texOffs(48, 64).addBox(0.0F, -2.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -2.0F, -2.0F));

		return LayerDefinition.create(modelData, 128, 128);
	}
}