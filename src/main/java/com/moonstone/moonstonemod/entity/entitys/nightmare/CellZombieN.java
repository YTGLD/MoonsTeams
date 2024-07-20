package com.moonstone.moonstonemod.entity.entitys.nightmare;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.nightmare_giant;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CellZombieN extends MobRenderer<nightmare_giant, NModel<nightmare_giant>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/nightmare_giant.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/nig_boot.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/nig_boot_2.png");

    public CellZombieN(EntityRendererProvider.Context p_234787_) {
        super(p_234787_, new NModel<>(p_234787_.bakeLayer(ModelLayers.WARDEN)), 0.9F);
        this.addLayer(new NEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_1, (p_234805_, p_234806_, p_234807_) -> {
            return Math.max(0.0F, Mth.cos(p_234807_ * 0.045F) * 0.25F);
        }, NModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new NEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_2, (p_234801_, p_234802_, p_234803_) -> {
            return Math.max(0.0F, Mth.cos(p_234803_ * 0.045F + (float)Math.PI) * 0.25F);
        }, NModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new NEmissiveLay<>(this, TEXTURE, (p_234797_, p_234798_, p_234799_) -> {
            return p_234797_.getTendrilAnimation(p_234798_);
        }, NModel::getTendrilsLayerModelParts));
    }

    public ResourceLocation getTextureLocation(nightmare_giant p_234791_) {
        return TEXTURE;
    }
}
