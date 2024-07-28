package com.moonstone.moonstonemod.client.glow;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class Glow {

    public static void blit(float ra,PoseStack guiGraphics, ResourceLocation p_282034_, int p_283671_, int p_282377_, int p_282058_, int p_281939_, float p_282285_, float p_283199_, int p_282186_, int p_282322_, int p_282481_, int p_281887_, float r, float g, float b, float a) {
        blit( ra,guiGraphics, p_282034_, p_283671_, p_283671_ + p_282058_, p_282377_, p_282377_ + p_281939_, 0, p_282186_, p_282322_, p_282285_, p_283199_, p_282481_, p_281887_, r, g, b, a);
    }

    public static void blit(float ra,PoseStack guiGraphics, ResourceLocation p_283272_, int p_283605_, int p_281879_, float p_282809_, float p_282942_, int p_281922_, int p_282385_, int p_282596_, int p_281699_, float r, float g, float b, float a) {
        blit( ra,guiGraphics, p_283272_, p_283605_, p_281879_, p_281922_, p_282385_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_, r, g, b, a);

    }

    private static void blit(float ra,PoseStack guiGraphics, ResourceLocation p_282639_, int p_282732_, int p_283541_, int p_281760_, int p_283298_, int p_283429_, int p_282193_, int p_281980_, float p_282660_, float p_281522_, int p_282315_, int p_281436_, float r, float g, float b, float a) {
        blit( ra,guiGraphics, p_282639_, p_282732_, p_283541_, p_281760_, p_283298_, p_283429_, (p_282660_ + 0.0F) / (float) p_282315_, (p_282660_ + (float) p_282193_) / (float) p_282315_, (p_281522_ + 0.0F) / (float) p_281436_, (p_281522_ + (float) p_281980_) / (float) p_281436_, r, g, b, a);
    }

    private static void blit(float ra,PoseStack guiGraphics, ResourceLocation texture, int startX, int endX, int startY, int endY, int zLevel, float u0, float u1, float v0, float v1, float r, float g, float b, float a) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.enableBlend();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        guiGraphics.pushPose();
        guiGraphics.translate((startX + endX) / 2.0F, (startY + endY) / 2.0F, 0); // 移动到中心点
        guiGraphics.mulPose(Axis.ZP.rotation(ra)); // 绕Z轴旋转
        guiGraphics.translate(-(startX + endX) / 2.0F, -(startY + endY) / 2.0F, 0); // 移回原点
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);

        bufferbuilder.vertex(guiGraphics.last().pose(), (float) startX, (float) startY, (float) zLevel).uv2(240).color(r, g, b, a).uv(u0, v0).endVertex();
        bufferbuilder.vertex(guiGraphics.last().pose(), (float) startX, (float) endY, (float) zLevel).uv2(240).color(r, g, b, a).uv(u0, v1).endVertex();
        bufferbuilder.vertex(guiGraphics.last().pose(), (float) endX, (float) endY, (float) zLevel).uv2(240).color(r, g, b, a).uv(u1, v1).endVertex();
        bufferbuilder.vertex(guiGraphics.last().pose(), (float) endX, (float) startY, (float) zLevel).uv2(240).color(r, g, b, a).uv(u1, v0).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
        guiGraphics.popPose();
    }
}
