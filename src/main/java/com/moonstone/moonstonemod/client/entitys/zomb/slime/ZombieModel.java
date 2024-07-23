package com.moonstone.moonstonemod.client.entitys.zomb.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.entity.necora.cell_zombie;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

public class ZombieModel <T extends cell_zombie> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart arms;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public ZombieModel(ModelPart p_170688_) {
        this.root = p_170688_;
        this.head = root.getChild("head");
        this.arms = root.getChild("arms");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
    }

    public @NotNull ModelPart root() {
        return this.root;
    }

    public void setupAnim(T p_102928_, float p_102929_, float p_102930_, float p_102931_, float p_102932_, float p_102933_) {
        this.head.yRot = p_102932_ * ((float)Math.PI / 180F);
        this.head.xRot = p_102933_ * ((float)Math.PI / 180F);
        if (this.riding) {
            this.rightArm.xRot = (-(float)Math.PI / 5F);
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = (-(float)Math.PI / 5F);
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = ((float)Math.PI / 10F);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        } else {
            this.rightArm.xRot = Mth.cos(p_102929_ * 0.6662F + (float)Math.PI) * 2.0F * p_102930_ * 0.5F;
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = Mth.cos(p_102929_ * 0.6662F) * 2.0F * p_102930_ * 1;
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = Mth.cos(p_102929_ * 0.6662F) * 4 * p_102930_ * 0.5f;
            this.rightLeg.yRot = 0.0F;
            this.rightLeg.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(p_102929_ * 0.6662F + (float)Math.PI) * 4 * p_102930_ * 0.5f;
            this.leftLeg.yRot = 0.0F;
            this.leftLeg.zRot = 0.0F;
        }

        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, false, this.attackTime, p_102931_);

    }

    private ModelPart getArm(HumanoidArm p_102923_) {
        return p_102923_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public ModelPart getHead() {
        return this.head;
    }

    public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
        this.getArm(p_102925_).translateAndRotate(p_102926_);
    }
}
