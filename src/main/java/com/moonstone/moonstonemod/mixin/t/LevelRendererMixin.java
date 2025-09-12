package com.moonstone.moonstonemod.mixin.t;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.tbl.client.handler.ShaderHandler;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.*;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

import static com.moonstone.tbl.client.handler.ShaderHandler.*;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

	@Shadow @Nullable private PostChain transparencyChain;

	@Inject(method = "renderDebug", at = @At("HEAD"))
	public void renderDebugHook(PoseStack poseStack, MultiBufferSource buffer, Camera camera, CallbackInfo ci) {
		onPreRenderDebug(poseStack, buffer, camera);
	}


	@Inject(method = "renderChunkLayer", at = @At("HEAD"))
	public void translucentPatcherStart(RenderType renderType, PoseStack p_172995_, double p_172996_, double p_172997_, double p_172998_, Matrix4f p_254039_, CallbackInfo ci) {
		if (renderType != RenderType.translucent() || this.transparencyChain != null) return;
		onPreTranslucentBatch();
	}

	@Inject(method = "renderChunkLayer", at = @At("RETURN"))
	public void translucentPatcherEnd(RenderType renderType, PoseStack p_172995_, double p_172996_, double p_172997_, double p_172998_, Matrix4f p_254039_, CallbackInfo ci) {
		if (renderType != RenderType.translucent() || this.transparencyChain != null) return;
		onPostTranslucentBatch();
	}
}
