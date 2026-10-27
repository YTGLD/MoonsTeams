package com.ytgld.moonstone.utils;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.renderpearl.api.pipeline.*;
import com.ytgld.moonstone.Moonstone;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

import static com.mojang.renderpearl.api.pipeline.BlendFactor.*;
import static net.minecraft.client.renderer.RenderPipelines.MATRICES_FOG_LIGHT_DIR_SNIPPET;

public class RenderObjects {
    public static final ColorTargetState vColorTargetState = new ColorTargetState(new BlendFunction(SRC_ALPHA, ONE, ONE, ZERO));

    private static final RenderPipeline itemLive = RenderPipeline.builder(
            RenderPipeline.builder(RenderPipeline.builder(MATRICES_FOG_LIGHT_DIR_SNIPPET).
                            withVertexShader(Identifier.fromNamespaceAndPath(Moonstone.MODID,"core/item"))
                            .withFragmentShader(Identifier.fromNamespaceAndPath(Moonstone.MODID,"core/item"))
                            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER1_SAMPLER2)
                            .withCull(false)
                            .withVertexBinding(0, DefaultVertexFormat.ENTITY).withPrimitiveTopology(PrimitiveTopology.QUADS).
                            withDepthStencilState(new DepthStencilState(CompareOp.GREATER_THAN_OR_EQUAL, false))
                            .withColorTargetState(vColorTargetState)
                            .buildSnippet())
                    .withLocation(Identifier.fromNamespaceAndPath(Moonstone.MODID,"item"))
                    .buildSnippet()).withLocation(Identifier.fromNamespaceAndPath(Moonstone.MODID,"item")).withShaderDefine("ALPHA_CUTOUT", 0.1F).build();

    public static final Function<Identifier, RenderType> renderTypeFunctionLive = Util.memoize((texture) -> {
        RenderSetup state = RenderSetup.builder(itemLive)
                .withTexture("Sampler0", texture)
                .useLightmap()
                .useOverlay()
                .affectsCrumbling()
                .createRenderSetup();
        return RenderType.create("item", state);
    });
    public static final Function<Identifier, RenderType> renderTypeFunctionLiveOutline = Util.memoize((texture) -> {
        RenderSetup state = RenderSetup.builder(itemLive)
                .withTexture("Sampler0", texture)
                .useLightmap()
                .useOverlay()
                .affectsCrumbling()
                .createRenderSetup();
        return RenderType.create("item", state);
    });
}
