package com.ytgld.moonstone.render.light;

import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.framegraph.FramePass;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.RenderTargetDescriptor;
import com.mojang.blaze3d.resource.ResourceHandle;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.GpuFormat;
import com.mojang.renderpearl.api.commands.RenderPass;
import com.mojang.renderpearl.api.textures.FilterMode;
import net.minecraft.client.renderer.LevelTargetBundle;

import java.util.Optional;
import java.util.OptionalDouble;
public final class LightFrameGraph {
    private LightFrameGraph() {
    }

    public static void addPostPass(FrameGraphBuilder frame, LevelTargetBundle targets) {
        ResourceHandle<RenderTarget> main = targets.main;
        FramePass lightPass = frame.addPass("Moonstone Light Post");
        lightPass.reads(main);
        ResourceHandle<RenderTarget> lightTarget = lightPass.createsInternal("Moonstone Light Target", new RenderTargetDescriptor(main.get().width, main.get().height, new RenderTargetDescriptor.TextureProperties(null, GpuFormat.RGBA8_UNORM), null));
        lightPass.executes(() -> {
            RenderTarget source = main.get();
            RenderTarget destination = lightTarget.get();
            if (destination.getColorTextureView() != null && RenderSystem.getProjectionMatrixBuffer() != null) {
                try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() ->
                                "Moonstone Light Post", destination.getColorTextureView(), Optional.empty(),
                        null, OptionalDouble.empty())) {

                    renderPass.setPipeline(RenderSystem.getCompiledPipeline(LightRenders.COLORED_LIGHT_POST));
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("Projection", RenderSystem.getProjectionMatrixBuffer());
                    renderPass.setUniform("MainSampler", source.getColorTextureView(), RenderSystem.getSamplerCache().getClampToEdge(FilterMode.LINEAR));
                    renderPass.setUniform("DepthSampler", source.getDepthTextureView(), RenderSystem.getSamplerCache().getClampToEdge(FilterMode.NEAREST));
                    renderPass.setUniform("ColoredLights", LightRenders.getColoredLightsBuffer());
                    renderPass.draw(3, 1, 0, 0);

                }
            }
        });
        FramePass copyPass = frame.addPass("Moonstone Light Copy");
        copyPass.reads(lightTarget);
        ResourceHandle<RenderTarget> newMain = copyPass.readsAndWrites(main);
        copyPass.executes(() -> {
            RenderTarget source = lightTarget.get();
            RenderTarget destination = newMain.get();
            destination.copyColorFrom(source);
        });
        targets.replace(LevelTargetBundle.MAIN_TARGET_ID, newMain);
    }
}