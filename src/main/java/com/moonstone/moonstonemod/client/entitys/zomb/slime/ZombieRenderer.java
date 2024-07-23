package com.moonstone.moonstonemod.client.entitys.zomb.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.necora.cell_slime;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;

public class ZombieRenderer<T extends cell_slime> extends MobRenderer<T, ZombieModel<T>> {
    private static final ResourceLocation PILLAGER = new ResourceLocation(MoonStoneMod.MODID,"textures/entity/cell_slime.png");

    public ZombieRenderer(EntityRendererProvider.Context context) {
        super(context,new ZombieModel<>(context.bakeLayer(ModelLayers.PILLAGER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));

    }

    protected void scale(T p_115983_, PoseStack p_115984_, float p_115985_) {
        float f1 = (float)p_115983_.getSize();
        p_115984_.scale(0.5f+(0.5f * f1 /8), 0.5f+(0.5f * f1 /8), 0.5f+(0.5f * f1 /8));
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return PILLAGER;
    }
}