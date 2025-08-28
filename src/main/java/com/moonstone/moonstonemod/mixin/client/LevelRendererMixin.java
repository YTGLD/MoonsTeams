package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "initOutline()V",
            at = @At("TAIL"))
    private void initOutline(CallbackInfo ci) {
        MoonPost.onInitializeOutline(minecraft);
    }
    @Inject(method = "resize(II)V",
            at = @At("TAIL"))
    private void resize(int x, int y, CallbackInfo ci) {
        MoonPost.resize(x, y);
    }


    @Inject(method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBuffers;bufferSource()Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;",
                    shift = At.Shift.BEFORE
            ))
    private void renderLevel1(PoseStack p_109600_, float p_109601_, long p_109602_, boolean p_109603_, Camera p_109604_, GameRenderer p_109605_, LightTexture p_109606_, Matrix4f p_254120_, CallbackInfo ci) {
        MoonPost.clearAndBindWrite(this.minecraft.getMainRenderTarget());
    }

    @Inject(method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OutlineBufferSource;endOutlineBatch()V",
                    shift = At.Shift.BEFORE
            ))
    private void renderLevel2(PoseStack p_109600_, float p_109601_, long p_109602_, boolean p_109603_, Camera p_109604_, GameRenderer p_109605_, LightTexture p_109606_, Matrix4f p_254120_, CallbackInfo ci) {
        MoonPost.processEffects(this.minecraft.getMainRenderTarget());
    }

    @Inject(method = "renderLevel",
            at = @At(
                    value = "TAIL"
            ))
    private void renderLevel3(PoseStack p_109600_, float p_109601_, long p_109602_, boolean p_109603_, Camera p_109604_, GameRenderer p_109605_, LightTexture p_109606_, Matrix4f p_254120_, CallbackInfo ci) {
        MoonPost.blitEffects(minecraft);
    }
}
