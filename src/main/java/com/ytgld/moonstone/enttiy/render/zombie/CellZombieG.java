package com.ytgld.moonstone.enttiy.render.zombie;


import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.CellGiant;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CellZombieG  extends MobRenderer<CellGiant, GModel<CellGiant>> {
    private static final ResourceLocation TEXTURE =  ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/cell_giant.png");
    private static final ResourceLocation HEART_TEXTURE = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/hearth.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/cell_giant_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/cell_giant_spots_2.png");

    public CellZombieG(EntityRendererProvider.Context p_234787_) {
        super(p_234787_, new GModel<>(p_234787_.bakeLayer(ModelLayers.WARDEN)), 0.9F);

        this.addLayer(new GEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_1, (p_234805_, p_234806_, p_234807_) -> {
            return Math.max(0.0F, Mth.cos(p_234807_ * 0.045F) * 0.25F);
        }, GModel::getPulsatingSpotsLayerModelParts,null));
        this.addLayer(new GEmissiveLay<>(this, PULSATING_SPOTS_TEXTURE_2, (p_234801_, p_234802_, p_234803_) -> {
            return Math.max(0.0F, Mth.cos(p_234803_ * 0.045F + (float)Math.PI) * 0.25F);
        }, GModel::getPulsatingSpotsLayerModelParts,null));
        this.addLayer(new GEmissiveLay<>(this, HEART_TEXTURE, (p_234793_, p_234794_, p_234795_) -> {
            return p_234793_.getHeartAnimation(p_234794_);
        }, GModel::getHeartLayerModelParts, MRender.BEACON_BEAM.apply(HEART_TEXTURE,true)));
    }

    public ResourceLocation getTextureLocation(CellGiant p_234791_) {
        return TEXTURE;
    }
}
