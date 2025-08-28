package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class BlackCube {
    public BlackCube(@NotNull PoseStack matrices,
                     @NotNull MultiBufferSource vertexConsumers,
                     int light,
                     @NotNull Entity entity)
    {
        {

            matrices.pushPose();
            renderCircle(matrices, vertexConsumers, light, 0, 0, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle2(matrices, vertexConsumers, light, 0, 0, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle3(matrices, vertexConsumers, light, 0, 0, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle4(matrices, vertexConsumers, light, 0, 0, entity);
            matrices.popPose();
        }
    }


    public void renderCircle(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 1; // 圆的半径
        poseStack.translate(0 ,0 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (90+(Math.sin((double) entity.tickCount / 25) * 50))));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }
    public void renderCircle2(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.75f; // 圆的半径
        poseStack.translate(0 ,-0.1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (90  + (Math.sin((double) entity.tickCount / 20) * 40))));


        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }
    public void renderCircle3(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.5f; // 圆的半径
        poseStack.translate(0 ,0.1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (90  - (Math.sin((double) entity.tickCount /15) * 30))));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }
    public void renderCircle4(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.3f; // 圆的半径
        poseStack.translate(0 ,0 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (90  + (Math.sin((double) entity.tickCount /10) * 20))));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color(0, 0, 0, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }

}
