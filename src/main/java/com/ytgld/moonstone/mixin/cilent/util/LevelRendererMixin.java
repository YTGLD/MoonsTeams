package com.ytgld.moonstone.mixin.cilent.util;


import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.moonstone.utils.RenderObjectManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Inject(method = "submitEntities", at = @At(value = "RETURN"))
    private void close(PoseStack poseStack, LevelRenderState levelRenderState, SubmitNodeCollector output, CallbackInfo ci) {
        float deltaPartialTick = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false);
        RenderObjectManager.render(poseStack,output,levelRenderState.cameraRenderState,deltaPartialTick);
    }
}
