package com.moonstone.moonstonemod.entity.c;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.cell_giant;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WardenRenderer;
import net.minecraft.client.renderer.entity.layers.WardenEmissiveLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.warden.Warden;

public class CellZombieG  extends MobRenderer<cell_giant, GModel<cell_giant>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/cell_giant.png");
    private static final ResourceLocation BIOLUMINESCENT_LAYER_TEXTURE = new ResourceLocation("textures/entity/warden/warden_bioluminescent_layer.png");
    private static final ResourceLocation HEART_TEXTURE = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/hearth.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = new ResourceLocation("textures/entity/warden/warden_pulsating_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = new ResourceLocation("textures/entity/warden/warden_pulsating_spots_2.png");

    public CellZombieG(EntityRendererProvider.Context p_234787_) {
        super(p_234787_, new GModel<>(p_234787_.bakeLayer(ModelLayers.WARDEN)), 0.9F);
        this.addLayer(new GEmissiveLay<>(this, BIOLUMINESCENT_LAYER_TEXTURE, (p_234809_, p_234810_, p_234811_) -> {
            return 1.0F;
        }, GModel::getBioluminescentLayerModelParts));
        this.addLayer(new GEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_1, (p_234805_, p_234806_, p_234807_) -> {
            return Math.max(0.0F, Mth.cos(p_234807_ * 0.045F) * 0.25F);
        }, GModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new GEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_2, (p_234801_, p_234802_, p_234803_) -> {
            return Math.max(0.0F, Mth.cos(p_234803_ * 0.045F + (float)Math.PI) * 0.25F);
        }, GModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new GEmissiveLay<>(this, TEXTURE, (p_234797_, p_234798_, p_234799_) -> {
            return p_234797_.getTendrilAnimation(p_234798_);
        }, GModel::getTendrilsLayerModelParts));
        this.addLayer(new GEmissiveLay<>(this, HEART_TEXTURE, (p_234793_, p_234794_, p_234795_) -> {
            return p_234793_.getHeartAnimation(p_234794_);
        }, GModel::getHeartLayerModelParts));
    }

    public ResourceLocation getTextureLocation(cell_giant p_234791_) {
        return TEXTURE;
    }
}
