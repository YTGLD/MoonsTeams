package com.ytgld.moonstone.mixin.cilent;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.outline.MFramebufferBlack;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.PostChain;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.io.IOException;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin implements MFramebufferBlack {
    @Unique
    @Nullable
    private RenderTarget moonstone1_21_1$renderTarget;
    @Unique
    @Nullable
    private PostChain moonstone1_21_1$postChain;
    @Shadow
    @Final
    private Minecraft minecraft;
    @Inject(method = "initOutline()V",
            at = @At("TAIL"))
    private void initOutline(CallbackInfo ci) {

        if (moonstone1_21_1$postChain != null) {
            moonstone1_21_1$postChain.close();
        }
        try {
            this.moonstone1_21_1$postChain = new PostChain(this.minecraft.getTextureManager(), this.minecraft.getResourceManager(), this.minecraft.getMainRenderTarget(), Moonstone.POST_Blood);
            this.moonstone1_21_1$postChain.resize(this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
            this.moonstone1_21_1$renderTarget = this.moonstone1_21_1$postChain.getTempTarget("final");
        } catch (IOException | JsonSyntaxException var3) {
            this.moonstone1_21_1$postChain = null;
            this.moonstone1_21_1$renderTarget = null;
            System.out.println("moonstone1_21_1$postChain");
            System.out.println("moonstone1_21_1$renderTarget");
        }

    }
    @Inject(method = "renderLevel",
            at = @At(
                    value = "TAIL"
            ))
    private void doEntityOutline(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        if (HandlerClient.showOutline) {
            if (this.moonstone1_21_1$renderTarget != null) {
                RenderSystem.enableBlend();
                RenderSystem.enableDepthTest();
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                if (moonstone1_21_1$renderTarget != null) {
                    moonstone1_21_1$renderTarget.blitToScreen(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight(), false);
                    moonstone1_21_1$renderTarget.clear(Minecraft.ON_OSX);
                    minecraft.getMainRenderTarget().bindWrite(false);
                    HandlerClient.showOutline = false;
                }
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
            }

        }

    }

    @Inject(method = "resize(II)V",
            at = @At("TAIL"))
    private void resize(int width, int height, CallbackInfo ci) {
        if (this.moonstone1_21_1$postChain != null) {
            this.moonstone1_21_1$postChain.resize(width, height);
        }
    }

    @Inject(method = "close",
            at = @At("TAIL"))
    private void close(CallbackInfo ci) {
        if (this.moonstone1_21_1$postChain != null) {
            this.moonstone1_21_1$postChain.close();
        }
    }
    @Inject(method = "renderLevel(Lnet/minecraft/client/DeltaTracker;ZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OutlineBufferSource;endOutlineBatch()V",
                    shift = At.Shift.BEFORE
            ))
    private void renderLevel2(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        if (HandlerClient.showOutline) {
            if (this.moonstone1_21_1$postChain != null) {
                this.moonstone1_21_1$postChain.process(deltaTracker.getGameTimeDeltaTicks());
                this.minecraft.getMainRenderTarget().bindWrite(false);
            }
        }
    }
    @Inject(method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBuffers;bufferSource()Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;",
                    shift = At.Shift.BEFORE
            ))
    private void renderLevel2RETURN(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        if (HandlerClient.showOutline) {
            if (this.moonstone1_21_1$renderTarget != null) {
                this.moonstone1_21_1$renderTarget.clear(Minecraft.ON_OSX);
                minecraft.getMainRenderTarget().bindWrite(false);
            }
        }
    }

    @Override
    public RenderTarget moonstone$render_black() {
        return moonstone1_21_1$renderTarget;
    }
}
