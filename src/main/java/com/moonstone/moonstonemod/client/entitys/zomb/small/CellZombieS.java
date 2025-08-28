package com.moonstone.moonstonemod.client.entitys.zomb.small;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.necora.small_zombie;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CellZombieS extends MobRenderer<small_zombie, SModel<small_zombie>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/cell_giant.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/cell_giant_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/cell_giant_spots_2.png");

    public CellZombieS(EntityRendererProvider.Context p_234787_) {
        super(p_234787_, new SModel<>(p_234787_.bakeLayer(ModelLayers.WARDEN)), 0.9F);

        this.addLayer(new SEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_1, (p_234805_, p_234806_, p_234807_) -> {
            return Math.max(0.0F, Mth.cos(p_234807_ * 0.045F) * 0.25F);
        }, SModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new SEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_2, (p_234801_, p_234802_, p_234803_) -> {
            return Math.max(0.0F, Mth.cos(p_234803_ * 0.045F + (float)Math.PI) * 0.25F);
        }, SModel::getPulsatingSpotsLayerModelParts));
    }

    public ResourceLocation getTextureLocation(small_zombie p_234791_) {
        return TEXTURE;
    }

    @Override
    protected void scale(small_zombie p_115314_, PoseStack p_115315_, float p_115316_) {
        p_115315_.scale(0.33f,0.33f,0.33f);
    }
}
