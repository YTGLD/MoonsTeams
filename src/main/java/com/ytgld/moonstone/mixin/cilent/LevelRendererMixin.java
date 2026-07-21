package com.ytgld.moonstone.mixin.cilent;

import com.mojang.blaze3d.GpuFormat;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.framegraph.FramePass;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.resource.ResourceHandle;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.MRender;
import com.ytgld.moonstone.render.outline.BlackFramebufferSets;
import com.ytgld.moonstone.render.outline.MFramebufferBlack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.util.profiling.ProfilerFiller;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin implements MFramebufferBlack {
    @Unique
    private RenderTarget moonstone$renderTarget_black;
    @Unique
    private final BlackFramebufferSets moonstone$defaultFramebufferSets_black = new BlackFramebufferSets();

    @Override
    public RenderTarget moonstone$render_black() {
        return moonstone$renderTarget_black;
    }

    @Inject(method = "close", at = @At(value = "RETURN"))
    private void  moonstone$close(CallbackInfo ci) {
        if (moonstone$renderTarget_black != null) {
            moonstone$renderTarget_black.destroyBuffers();
        }
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void  moonstone$loadEntityOutlinePostProcessor(CallbackInfo ci) {
        this.moonstone$renderTarget_black = new TextureTarget(
                "Entity Outline For Black", Minecraft.getInstance().getWindow().getWidth(),
                Minecraft.getInstance().getWindow().getHeight(),true, GpuFormat.RGBA8_UNORM);
    }

    @Inject(method = "doEntityOutline", at = @At(value = "RETURN"))
    private void  moonstone$drawEntityOutlinesFramebuffer(CallbackInfo ci) {
        if (Minecraft.getInstance().gameRenderer.mainRenderTarget().getColorTextureView() != null) {
            if (HandlerClient.showOutline) {
                moonstone$blitAndBlendToTexture(
                        Minecraft.getInstance().gameRenderer.mainRenderTarget().getColorTextureView(),
                        Minecraft.getInstance().gameRenderer.mainRenderTarget().getDepthTextureView(),
                        moonstone$renderTarget_black);
                HandlerClient.showOutline = false;
            }
        }
    }

    @Inject(method = "resize", at = @At(value = "RETURN"))
    private void  moonstone$onResized(int width, int height, CallbackInfo ci) {
        if (this.moonstone$renderTarget_black != null) {
            this.moonstone$renderTarget_black.resize(width, height);
        }
    }

    @Inject(method = "addMainPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher$PreparedFrame;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lnet/minecraft/client/renderer/state/level/LevelRenderState;Lnet/minecraft/util/profiling/ProfilerFiller;Lnet/minecraft/client/renderer/chunk/ChunkSectionsToRender;Lorg/joml/Matrix4fc;)V", at = @At(value = "RETURN"))
    private void  moonstone$renderMainHEAD(FrameGraphBuilder frame, FeatureRenderDispatcher.PreparedFrame featureFrame, GpuBufferSlice terrainFog, LevelRenderState levelRenderState, ProfilerFiller profiler, ChunkSectionsToRender chunkSectionsToRender, Matrix4fc modelViewMatrix, CallbackInfo ci) {
        FramePass framepass = frame.addPass(Moonstone.MODID);
        if (this.moonstone$defaultFramebufferSets_black.entityOutlineFramebuffer != null) {
            this.moonstone$defaultFramebufferSets_black.entityOutlineFramebuffer =
                    framepass.readsAndWrites(this.moonstone$defaultFramebufferSets_black.entityOutlineFramebuffer);
        }
    }

    @Inject(method = "addMainPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher$PreparedFrame;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lnet/minecraft/client/renderer/state/level/LevelRenderState;Lnet/minecraft/util/profiling/ProfilerFiller;Lnet/minecraft/client/renderer/chunk/ChunkSectionsToRender;Lorg/joml/Matrix4fc;)V", at = @At(value = "RETURN"))
    private void  moonstone$renderMain(FrameGraphBuilder frame, FeatureRenderDispatcher.PreparedFrame featureFrame, GpuBufferSlice terrainFog, LevelRenderState levelRenderState, ProfilerFiller profiler, ChunkSectionsToRender chunkSectionsToRender, Matrix4fc modelViewMatrix, CallbackInfo ci) {
        if (this.moonstone$renderTarget_black != null) {
            this.moonstone$defaultFramebufferSets_black.entityOutlineFramebuffer =
                    frame.importExternal("main", this.moonstone$renderTarget_black);
        }
    }

    @Inject(method = "addMainPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher$PreparedFrame;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lnet/minecraft/client/renderer/state/level/LevelRenderState;Lnet/minecraft/util/profiling/ProfilerFiller;Lnet/minecraft/client/renderer/chunk/ChunkSectionsToRender;Lorg/joml/Matrix4fc;)V", at = @At(value = "RETURN"))
    private void  moonstone$renderMain2INVOKE_ASSIGN(FrameGraphBuilder frame, FeatureRenderDispatcher.PreparedFrame featureFrame, GpuBufferSlice terrainFog, LevelRenderState levelRenderState, ProfilerFiller profiler, ChunkSectionsToRender chunkSectionsToRender, Matrix4fc modelViewMatrix, CallbackInfo ci) {
        ResourceHandle<RenderTarget> handle4 = this.moonstone$defaultFramebufferSets_black.entityOutlineFramebuffer;
        if (handle4 != null) {
            RenderTarget rendertarget = handle4.get();
            if (rendertarget.getColorTexture() != null && rendertarget.getDepthTexture() != null) {
                RenderSystem.getDevice()
                        .createCommandEncoder()
                        .clearColorAndDepthTextures(rendertarget.getColorTexture(),
                                new Vector4f(0,0,0,0), rendertarget.getDepthTexture(), 0.0);
            }
        }
    }

    @Inject(method = "addMainPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher$PreparedFrame;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lnet/minecraft/client/renderer/state/level/LevelRenderState;Lnet/minecraft/util/profiling/ProfilerFiller;Lnet/minecraft/client/renderer/chunk/ChunkSectionsToRender;Lorg/joml/Matrix4fc;)V", at = @At(value = "RETURN"))
    private void  moonstone$renderMains(FrameGraphBuilder frame, FeatureRenderDispatcher.PreparedFrame featureFrame, GpuBufferSlice terrainFog, LevelRenderState levelRenderState, ProfilerFiller profiler, ChunkSectionsToRender chunkSectionsToRender, Matrix4fc modelViewMatrix, CallbackInfo ci) {
        int i = Minecraft.getInstance().gameRenderer.mainRenderTarget().width;
        int j = Minecraft.getInstance().gameRenderer.mainRenderTarget().height;

        PostChain postchain1 = Minecraft.getInstance().getShaderManager().getPostChain(Moonstone.POST_BLACK,
                Set.of(BlackFramebufferSets.MAIN, BlackFramebufferSets.ENTITY_OUTLINE));
        if (postchain1 != null) {
            if (HandlerClient.doPass) {
                postchain1.addToFrame(frame, i, j, this.moonstone$defaultFramebufferSets_black);
                HandlerClient.doPass = false;
            }
        }
    }
    @Unique
    public void moonstone$blitAndBlendToTexture(GpuTextureView output, GpuTextureView outputDepth, RenderTarget renderTarget) {
        RenderSystem.assertOnRenderThread();

        try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Blit render target", output, Optional.empty(), outputDepth, OptionalDouble.empty())) {
            renderPass.setPipeline(MRender.RenderPs.ENTITY_OUTLINE_BLIT);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.bindTexture("InSampler", renderTarget.getColorTextureView(), RenderSystem.getSamplerCache().getClampToEdge(FilterMode.NEAREST));
            renderPass.draw(3, 1, 0, 0);
        }

    }
}
