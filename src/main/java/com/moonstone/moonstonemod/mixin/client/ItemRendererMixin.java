package com.moonstone.moonstonemod.mixin.client;

import com.all.IGUILight;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.seeking_immortals.MGuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Inject(at = @At("RETURN"), method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V")
    public void init(ItemStack p_115144_, ItemDisplayContext p_270188_, boolean p_115146_, PoseStack poseStack, MultiBufferSource multiBufferSource, int x, int y, BakedModel p_115151_, CallbackInfo ci) {
    }
    @Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V")
    public void initHEAD(ItemStack p_115144_, ItemDisplayContext p_270188_, boolean p_115146_, PoseStack poseStack, MultiBufferSource multiBufferSource, int x, int y, BakedModel p_115151_, CallbackInfo ci) {
    }
}
