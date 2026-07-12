package com.ytgld.moonstone.render;

import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.outline.MFramebufferBlack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

import java.util.Optional;

import static com.mojang.blaze3d.platform.SourceFactor.ONE;
import static com.mojang.blaze3d.platform.SourceFactor.SRC_ALPHA;
import static net.minecraft.client.renderer.RenderPipelines.*;

public class MRender {
    public static final OutputTarget outline2 = new OutputTarget("set_outline2", () -> {
        LevelRenderer rendertarget = Minecraft.getInstance().levelRenderer;
        if (rendertarget instanceof MFramebufferBlack framebuffer){
            if (framebuffer.moonstone$render_black()!=null) {
                GlStateManager._enableDepthTest();
                framebuffer.moonstone$render_black().copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
                return framebuffer.moonstone$render_black();
            }
        }
        return Minecraft.getInstance().getMainRenderTarget();
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
    public static  RenderType outline = RenderType.create(
            "lightning", RenderSetup.builder(RenderPipeline.builder(MATRICES_FOG_SNIPPET).withLocation("pipeline/lightning")
                    .withVertexShader("core/rendertype_lightning").withFragmentShader("core/rendertype_lightning").
                    withColorTargetState(new ColorTargetState(new BlendFunction(
                            SRC_ALPHA,
                            DestFactor.ONE,
                            ONE,
                            DestFactor.ZERO))).withVertexFormat(DefaultVertexFormat.POSITION_COLOR,
                            VertexFormat.Mode.QUADS)
                    .withDepthStencilState(DepthStencilState.DEFAULT).build()
            ).setOutputTarget(outline2).sortOnUpload().createRenderSetup()
    );
    public static RenderType notOutline = RenderType.create(
            "lightning", RenderSetup.builder(RenderPipeline.builder(MATRICES_FOG_SNIPPET).withLocation("pipeline/lightning")
                    .withVertexShader("core/rendertype_lightning").withFragmentShader("core/rendertype_lightning").
                    withColorTargetState(new ColorTargetState(new BlendFunction(
                            SRC_ALPHA,
                            DestFactor.ONE,
                            ONE,
                            DestFactor.ZERO))).withVertexFormat(DefaultVertexFormat.POSITION_COLOR,
                            VertexFormat.Mode.QUADS)
                    .withDepthStencilState(DepthStencilState.DEFAULT).build()
      ).setOutputTarget(OutputTarget.WEATHER_TARGET).sortOnUpload().createRenderSetup()
    );

    public static class RenderPs {

        public static final RenderPipeline ENTITY_OUTLINE_BLIT =
                RenderPipeline.builder()
                        .withLocation("pipeline/entity_outline_blit")
                        .withVertexShader("core/screenquad")
                        .withFragmentShader("core/blit_screen")
                        .withSampler("InSampler")
                        .withColorTargetState(new ColorTargetState(Optional.of(new BlendFunction(
                                SRC_ALPHA,
                                DestFactor.ONE,
                                ONE,
                                DestFactor.ZERO
                        )), 7))
                        .withVertexFormat(DefaultVertexFormat.EMPTY, VertexFormat.Mode.TRIANGLES)
                        .build();

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
