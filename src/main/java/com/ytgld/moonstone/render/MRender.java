package com.ytgld.moonstone.render;

import com.mojang.blaze3d.pipeline.*;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.renderpearl.api.GpuFormat;
import com.mojang.renderpearl.api.pipeline.*;
import com.mojang.renderpearl.backend.opengl.GlStateManager;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.outline.MFramebufferBlack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.Identifier;

import java.util.Optional;

import static com.mojang.renderpearl.api.pipeline.BlendFactor.*;
import static net.minecraft.client.renderer.RenderPipelines.*;

public class MRender {

    public static final RenderPipeline.Snippet GUI_TEXTURED_SNIPPET =
            RenderPipeline.builder(GLOBALS_SNIPPET).
                    withBindGroupLayout(BindGroupLayouts.PROJECTION)
                    .withVertexShader(Identifier.fromNamespaceAndPath(
                            Moonstone.MODID, "core/vows"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(
                            Moonstone.MODID, "core/vows"))
                    .withBindGroupLayout(BindGroupLayouts.SAMPLER0).withColorTargetState(new ColorTargetState(new BlendFunction(
                            SRC_ALPHA,
                            ONE,
                            ONE,
                            ZERO))).withBindGroupLayout(BindGroupLayouts.DYNAMIC_TRANSFORMS) .withBindGroupLayout(BindGroupLayout.builder().withUniform(
                            "ChestVowsGlobals", UniformType.UNIFORM_BUFFER).build())
                    .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR).withPrimitiveTopology(PrimitiveTopology.QUADS).buildSnippet();

    public static final RenderPipeline VowGlow =
            (RenderPipeline.builder(GUI_TEXTURED_SNIPPET).withColorTargetState(new ColorTargetState(new BlendFunction(
                            SRC_ALPHA,
                            ONE,
                            ONE,
                            ZERO))).
                    withLocation(Identifier.fromNamespaceAndPath(Moonstone.MODID,"pipeline/vows_glow")).build());


    public static RenderType colorOutline = RenderType.create(
            "lightning", RenderSetup.builder(RenderPipeline.builder(MATRICES_FOG_SNIPPET).withLocation("pipeline/lightning")
                            .withVertexShader("core/rendertype_lightning").withFragmentShader("core/rendertype_lightning")
                            .withColorTargetState(new ColorTargetState(BlendFunction.LIGHTNING))
                            .withPrimitiveTopology(PrimitiveTopology.QUADS)
                            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR).withCull(false).build())
                    .sortOnUpload().createRenderSetup()
    );


    public static class RenderPs {
        public static final RenderPipeline.Snippet  GUI_TEXTURED_SNIPPET = RenderPipeline.builder(GLOBALS_SNIPPET).
                withBindGroupLayout(BindGroupLayouts.PROJECTION).
                withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color")
                .withBindGroupLayout(BindGroupLayouts.SAMPLER0).withColorTargetState(new ColorTargetState(new BlendFunction(
                        SRC_ALPHA,
                        ONE,
                        ONE,
                        ZERO))).withBindGroupLayout(BindGroupLayouts.DYNAMIC_TRANSFORMS)
                .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR).withPrimitiveTopology(PrimitiveTopology.QUADS).buildSnippet();

        public static final RenderPipeline GUI_TEXTURED =
                (RenderPipeline.builder(GUI_TEXTURED_SNIPPET).
                        withLocation("pipeline/gui_textured").withBindGroupLayout(BindGroupLayouts.DYNAMIC_TRANSFORMS).build());


        public static final RenderPipeline ENTITY_OUTLINE_BLIT =
                (RenderPipeline.builder(GLOBALS_SNIPPET).withLocation("pipeline/entity_outline_blit").
                        withVertexShader("core/screenquad").withFragmentShader("core/blit_screen").
                        withBindGroupLayout(BindGroupLayouts.IN_SAMPLER).withColorTargetState(new
                                ColorTargetState(Optional.of(new BlendFunction(
                                SRC_ALPHA,
                                ONE,
                                ONE,
                                ZERO)),
                                GpuFormat.RGBA8_UNORM, 7)).withPrimitiveTopology(PrimitiveTopology.TRIANGLES).build());
    }
}
