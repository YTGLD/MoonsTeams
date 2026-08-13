package com.ytgld.moonstone.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.outline.MFramebufferBlack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;

public class MRender extends RenderType {

    protected static final RenderStateShard.OutputStateShard setOutputState = new RenderStateShard.OutputStateShard("set", () -> {
        if (Minecraft.getInstance().levelRenderer instanceof MFramebufferBlack ilevelRender) {
            if (ilevelRender.moonstone$render_black() != null) {
                ilevelRender.moonstone$render_black() .copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
                ilevelRender.moonstone$render_black() .bindWrite(false);
            }
        }
    }, () -> {
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    });
    public MRender(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
        super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
    }

    public static final RenderType theRenderTypeNotOutline = create("blood",
            DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS,
            1536, false, false,
            RenderType.CompositeState.builder().setShaderState(RENDERTYPE_END_GATEWAY_SHADER)
                    .setTextureState(MultiTextureStateShard.builder().add(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"),
                            false, false).add(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"), false,
                            false).build()).createCompositeState(false));

    public static final RenderType theRenderTypeOutline = create("blood_outline",
            DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS,
            1536, false, false,
            RenderType.CompositeState.builder().setShaderState(RENDERTYPE_END_GATEWAY_SHADER)
                    .setOutputState(setOutputState)
                    .setTextureState(MultiTextureStateShard.builder().add(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"),
                            false, false).add(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"textures/necr_image.png"), false,
                            false).build()).createCompositeState(false));
}
