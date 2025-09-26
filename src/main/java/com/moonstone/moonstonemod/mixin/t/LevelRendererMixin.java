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
	@Inject(method = "renderDebug", at = @At("HEAD"))
	public void renderDebugHook(PoseStack poseStack, MultiBufferSource buffer, Camera camera, CallbackInfo ci) {
		onPreRenderDebug();
	}

}
