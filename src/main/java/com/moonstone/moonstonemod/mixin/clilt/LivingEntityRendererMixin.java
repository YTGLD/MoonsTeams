package com.moonstone.moonstonemod.mixin.clilt;

import com.moonstone.moonstonemod.client.StrengtheningLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected LivingEntityRendererMixin(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }
    @Shadow public abstract boolean addLayer(RenderLayer<T, M> p_115327_);
    @Inject(at = @At("RETURN"), method = "<init>")
    public void init(EntityRendererProvider.Context context, M model, float Radius, CallbackInfo info) {
        addLayer(new StrengtheningLayer<>((LivingEntityRenderer<?, ?>) (Object) this));
    }
}
