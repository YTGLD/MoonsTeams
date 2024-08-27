package com.moonstone.moonstonemod.client.glow;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class Glow {

    public static void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers) {
        float radius = 50;
        int stacks = 20;
        int slices = 20;
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.gui());
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = radius * (float) Math.cos(phi0);
                float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = radius * (float) Math.cos(phi0);
                float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = radius * (float) Math.cos(phi1);
                float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = radius * (float) Math.cos(phi1);
                float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.vertex(matrices.last().pose(), x0, y0, z0).color(1.0f, 0, 1.0f, 0.25f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(240).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x1, y1, z1).color(1.0f, 0, 1.0f, 0.25f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(240).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x2, y2, z2).color(1.0f, 0, 1.0f, 0.25f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(240).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x3, y3, z3).color(1.0f, 0, 1.0f, 0.25f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(240).normal(1, 0, 0).endVertex();
            }
        }
    }
    public static void blit(VertexConsumer vertexConsumer, PoseStack guiGraphics, float r, float g, float b, float a) {
        Matrix4f matrix = guiGraphics.last().pose();
        float centerX = 0.0f;
        float centerY = 0.0f;
        float radius = 50.0f;
        int segments = 360;

        for (int i = 0; i <= segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = centerX + radius * (float) Math.cos(angle1);
            float y1 = centerY + radius * (float) Math.sin(angle1);
            float x2 = centerX + radius * (float) Math.cos(angle2);
            float y2 = centerY + radius * (float) Math.sin(angle2);

            float topR = r;
            float topG = g;
            float topB = b;
            float topA = a;

            float bottomR = 1;
            float bottomG = 0;
            float bottomB = 0;
            float bottomA = 0;

            vertexConsumer.vertex(matrix, centerX, centerY, 0)
                    .color(topR, topG, topB, topA)
                    .endVertex();
            vertexConsumer.vertex(matrix, x1, y1, 0)
                    .color(bottomR, bottomG, bottomB, bottomA)
                    .endVertex();
            vertexConsumer.vertex(matrix, x2, y2, 0)
                    .color(bottomR, bottomG, bottomB, bottomA)
                    .endVertex();
        }
    }
}