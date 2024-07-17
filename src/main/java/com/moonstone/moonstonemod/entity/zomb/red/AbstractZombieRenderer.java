package com.moonstone.moonstonemod.entity.zomb.red;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractZombieRenderer<T extends com.moonstone.moonstonemod.entity.cell_zombie, M extends ZombieModel<T>> extends HumanoidMobRenderer<T, M> {
    private static final ResourceLocation ZOMBIE_LOCATION = new ResourceLocation("textures/entity/zombie/zombie.png");

    protected AbstractZombieRenderer(EntityRendererProvider.Context p_173910_, M p_173911_, M p_173912_, M p_173913_) {
        super(p_173910_, p_173911_, 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, p_173912_, p_173913_, p_173910_.getModelManager()));
    }

    public ResourceLocation getTextureLocation(com.moonstone.moonstonemod.entity.cell_zombie p_113771_) {
        return ZOMBIE_LOCATION;
    }

    protected boolean isShaking(T p_113773_) {
        return super.isShaking(p_113773_);
    }
}