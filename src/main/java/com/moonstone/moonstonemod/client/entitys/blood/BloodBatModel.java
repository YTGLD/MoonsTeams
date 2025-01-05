package com.moonstone.moonstonemod.client.entitys.blood;

import com.moonstone.moonstonemod.entity.bloodvruis.blood_bat;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class BloodBatModel  extends HierarchicalModel<blood_bat> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingTip;
    private final ModelPart leftWingTip;

    public BloodBatModel(ModelPart p_170427_) {
        this.root = p_170427_;
        this.head = p_170427_.getChild("head");
        this.body = p_170427_.getChild("body");
        this.rightWing = this.body.getChild("right_wing");
        this.rightWingTip = this.rightWing.getChild("right_wing_tip");
        this.leftWing = this.body.getChild("left_wing");
        this.leftWingTip = this.leftWing.getChild("left_wing_tip");
    }

    public ModelPart root() {
        return this.root;
    }

    public void setupAnim(blood_bat p_102200_, float p_102201_, float p_102202_, float p_102203_, float p_102204_, float p_102205_) {
       {
            this.head.xRot = p_102205_ * ((float)Math.PI / 180F);
            this.head.yRot = p_102204_ * ((float)Math.PI / 180F);
            this.head.zRot = 0.0F;
            this.head.setPos(0.0F, 0.0F, 0.0F);
            this.rightWing.setPos(0.0F, 0.0F, 0.0F);
            this.leftWing.setPos(0.0F, 0.0F, 0.0F);
            this.body.xRot = ((float)Math.PI / 4F) + Mth.cos(p_102203_ * 0.1F) * 0.15F;
            this.body.yRot = 0.0F;
            this.rightWing.yRot = Mth.cos(p_102203_ * 74.48451F * ((float)Math.PI / 180F)) * (float)Math.PI * 0.25F;
            this.leftWing.yRot = -this.rightWing.yRot;
            this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
            this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
        }

    }
}
