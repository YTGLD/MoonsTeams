package com.moonstone.moonstonemod.mixin.t;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.tbl.client.handler.ShaderHandler;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Inject(method = "resize", at = @At("TAIL"))
	private void resizeBuffers(int width, int height, CallbackInfo ci) {
		ShaderHandler.resize(width, height);
	}
	@Inject(method = "renderLevel", at = @At("TAIL"))
	public void renderLevel(float f, long p_109091_, PoseStack p_109092_, CallbackInfo ci) {
	}

}
