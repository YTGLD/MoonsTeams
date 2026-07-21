package com.ytgld.moonstone.render;

import com.mojang.blaze3d.GpuFormat;
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.outline.MFramebufferBlack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

import java.util.Optional;

import static com.mojang.blaze3d.platform.BlendFactor.*;
import static net.minecraft.client.renderer.RenderPipelines.*;

public class MRender {
    public static final OutputTarget outline2 = new OutputTarget("set_outline2", () -> {
        LevelRenderer rendertarget = Minecraft.getInstance().levelRenderer;
        if (rendertarget instanceof MFramebufferBlack framebuffer){
            if (framebuffer.moonstone$render_black()!=null) {
                GlStateManager._enableDepthTest();
                framebuffer.moonstone$render_black().copyDepthFrom(Minecraft.getInstance().gameRenderer.mainRenderTarget());
                return framebuffer.moonstone$render_black();
            }
        }
        return Minecraft.getInstance().gameRenderer.mainRenderTarget();
    });
    public static  RenderType renderTypeOutline = RenderType.create(
            "end_gateway",
            RenderSetup.builder(RenderPipeline.builder(END_PORTAL_SNIPPET).withLocation("pipeline/end_gateway")
                    .withCull(false).withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT)).withShaderDefine("PORTAL_LAYERS", 16).build())
                    .withTexture("Sampler0", Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"))
                    .withTexture("Sampler1", Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"))
                    .setOutputTarget(outline2)

                    .createRenderSetup()
    );
    public static  RenderType renderType = RenderType.create(
            "end_gateway",
            RenderSetup.builder(RenderPipeline.builder(END_PORTAL_SNIPPET).withLocation("pipeline/end_gateway")
                            .withCull(false).withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT)).withShaderDefine("PORTAL_LAYERS", 16).build())
                    .withTexture("Sampler0", Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"))
                    .withTexture("Sampler1", Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"))
                    .createRenderSetup()
    );
    public static final RenderType lines = RenderType.create(
            "lines",
            RenderSetup.builder(RenderPipeline.builder(LINES_SNIPPET).withColorTargetState(new ColorTargetState(new BlendFunction(
                            SRC_ALPHA,
                            ONE,
                            ONE,
                            ZERO
                    ))).withLocation("pipeline/lines").build())
                    .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
                    .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                    .createRenderSetup()
    );

    public static class RenderPs {

        public static final RenderPipeline ENTITY_OUTLINE_BLIT =
                RenderPipeline.builder(new RenderPipeline.Snippet[]{GLOBALS_SNIPPET}).withLocation("pipeline/entity_outline_blit").withVertexShader("core/screenquad").
                        withFragmentShader("core/blit_screen")
                        .withBindGroupLayout(BindGroupLayouts.IN_SAMPLER).
                        withColorTargetState(new ColorTargetState(Optional.of(new BlendFunction(
                                SRC_ALPHA,
                                ONE,
                                ONE,
                                ZERO
                        )), GpuFormat.RGBA8_UNORM, 7)).withPrimitiveTopology(PrimitiveTopology.TRIANGLES).build();

        public static final RenderPipeline.Snippet GUI_TEXTURED_SNIPPET = RenderPipeline.builder(GLOBALS_SNIPPET).withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withBindGroupLayout(BindGroupLayouts.SAMPLER0).withColorTargetState(new ColorTargetState(new BlendFunction(
                SRC_ALPHA,
                ONE,
                ONE,
                ZERO
        ))).withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR).withPrimitiveTopology(PrimitiveTopology.QUADS).buildSnippet();
        public static final RenderPipeline GUI_TEXTURED = (RenderPipeline.builder(GUI_TEXTURED_SNIPPET)
                .withLocation("pipeline/gui_textured").build());

    }
}
