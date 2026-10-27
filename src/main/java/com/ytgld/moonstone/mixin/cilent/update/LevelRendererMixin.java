package com.ytgld.moonstone.mixin.cilent.update;


import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.renderpearl.api.buffers.GpuBufferSlice;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.render.light.LightFrameGraph;
import com.ytgld.moonstone.render.light.LightRenders;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Final
    @Shadow
    private LevelTargetBundle targets;
    @Inject(
            method = "addMainPass",
            at = @At("RETURN")
    )
    private void chest_light$afterAddMainPass(
            FrameGraphBuilder frame, FeatureRenderDispatcher.PreparedFrame featureFrame, GpuBufferSlice terrainFog, ChunkSectionsToRender chunkSectionsToRender, boolean consistentDepthRequired, CallbackInfo ci
    ) {
        if (HandlerClient.isShowRenderLight()) {
            LightFrameGraph.addPostPass(frame, targets);
            HandlerClient.setShowRenderLight(false);
        }
    }
    @Inject(
            method = "submitFeatures",
            at = @At("HEAD")
    )
    private void chest_light$begin(
            LevelRenderState levelRenderState, SubmitNodeCollector submitNodeCollector, boolean renderOutline, CallbackInfo ci
    ) {
        LightRenders.clearLights();
    }
    @Inject(
            method = "submitFeatures",
            at = @At("RETURN")
    )
    private void chest_light$return(
            LevelRenderState levelRenderState, SubmitNodeCollector submitNodeCollector, boolean renderOutline, CallbackInfo ci
    ) {
        CameraRenderState camera = levelRenderState.cameraRenderState;
        LightRenders.uploadLights(
                camera.pos.x, camera.pos.y, camera.pos.z,
                camera.viewRotationMatrix
        );
    }
}
