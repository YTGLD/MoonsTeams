package com.moonstone.moonstonemod.client.entitys.blood;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.bloodvruis.blood_bat;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BloodBatRenderer extends MobRenderer<blood_bat, BloodBatModel> {
    private static final ResourceLocation BAT_LOCATION = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/blood_bat.png");

    public BloodBatRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new BloodBatModel(p_173929_.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    public ResourceLocation getTextureLocation(blood_bat p_113876_) {
        return BAT_LOCATION;
    }

    protected void scale(blood_bat p_113878_, PoseStack p_113879_, float p_113880_) {
        p_113879_.scale(0.7f, 0.7f, 0.7f);
    }

    protected void setupRotations(blood_bat p_113882_, PoseStack p_113883_, float p_113884_, float p_113885_, float p_113886_) {
        {
            p_113883_.translate(0.0F, Mth.cos(p_113884_ * 0.3F) * 0.1F, 0.0F);
        }

        super.setupRotations(p_113882_, p_113883_, p_113884_, p_113885_, p_113886_);
    }
}
