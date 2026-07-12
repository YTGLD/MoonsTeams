package com.ytgld.moonstone.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import static com.mojang.blaze3d.platform.SourceFactor.ONE;
import static com.mojang.blaze3d.platform.SourceFactor.SRC_ALPHA;
import static net.minecraft.client.renderer.RenderPipelines.MATRICES_PROJECTION_SNIPPET;

public class MRender {

    public static class RenderPs {

        public static final RenderPipeline GUI_TEXTURED =
                (RenderPipeline.builder(RenderPipeline.builder(MATRICES_PROJECTION_SNIPPET).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withColorTargetState(new ColorTargetState(
                                new BlendFunction(
                                        SRC_ALPHA,
                                        DestFactor.ONE,
                                        ONE,
                                        DestFactor.ZERO
                                )
                        )).withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS).buildSnippet()).
                        withLocation("pipeline/gui_textured").build());

    }
}
