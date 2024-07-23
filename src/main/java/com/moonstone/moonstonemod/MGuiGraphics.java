package com.moonstone.moonstonemod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class MGuiGraphics {

    public static void blit(GuiGraphics guiGraphics, ResourceLocation p_282034_, float p_283671_, float p_282377_, float p_282058_, float p_281939_, float p_282285_, float p_283199_, float p_282186_, float p_282322_, float p_282481_, float p_281887_, float r, float g, float b, float a) {
        blit(guiGraphics, p_282034_, p_283671_, p_283671_ + p_282058_, p_282377_, p_282377_ + p_281939_, 0, p_282186_, p_282322_, p_282285_, p_283199_, p_282481_, p_281887_, r, g, b, a);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation p_283272_, float p_283605_, float p_281879_, float p_282809_, float p_282942_, float p_281922_, float p_282385_, float p_282596_, float p_281699_, float r, float g, float b, float a) {
        blit(guiGraphics, p_283272_, p_283605_, p_281879_, p_281922_, p_282385_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_, r, g, b, a);
    }

    private static void blit(GuiGraphics guiGraphics, ResourceLocation p_282639_, float p_282732_, float p_283541_, float p_281760_, float p_283298_, float p_283429_, float p_282193_, float p_281980_, float p_282660_, float p_281522_, float p_282315_, float p_281436_, float r, float g, float b, float a) {
        blit(guiGraphics, p_282639_, p_282732_, p_283541_, p_281760_, p_283298_, p_283429_, (p_282660_ + 0.0F) / (float) p_282315_, (p_282660_ + (float) p_282193_) / (float) p_282315_, (p_281522_ + 0.0F) / (float) p_281436_, (p_281522_ + (float) p_281980_) / (float) p_281436_, r, g, b, a);
    }

    private static void blit(GuiGraphics guiGraphics, ResourceLocation texture, float startX, float endX, float startY, float endY, float zLevel, float u0, float u1, float v0, float v1, float r, float g, float b, float a) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.enableBlend();
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferbuilder.vertex(matrix4f, (float) startX, (float) startY, (float) zLevel).color(r, g, b, a).uv(u0, v0).endVertex();
        bufferbuilder.vertex(matrix4f, (float) startX, (float) endY, (float) zLevel).color(r, g, b, a).uv(u0, v1).endVertex();
        bufferbuilder.vertex(matrix4f, (float) endX, (float) endY, (float) zLevel).color(r, g, b, a).uv(u1, v1).endVertex();
        bufferbuilder.vertex(matrix4f, (float) endX, (float) startY, (float) zLevel).color(r, g, b, a).uv(u1, v0).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
    }

}
