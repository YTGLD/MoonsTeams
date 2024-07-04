package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class StrengtheningLayer<T extends LivingEntity, M extends EntityModel<T>> extends  RenderLayer {
    public StrengtheningLayer(RenderLayerParent<T, M> p_117346_) {
        //noinspection unchecked
        super(p_117346_);
    }
    /**
        matrices.translate( 左右 , 上下 , 前后 );
     **/


    @Override
    public void render(@NotNull PoseStack matrices,
                       @NotNull MultiBufferSource vertexConsumers,
                       int light,
                       @NotNull Entity entity,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch)
    {
        if (entity instanceof Player player) {

            if (Handler.hascurio(player,Items.nightmare_heart.get())) {
                {
                    matrices.pushPose();
                    Nig(matrices,
                            vertexConsumers,
                            light,
                            entity,
                            limbAngle,
                            limbDistance,
                            tickDelta,
                            animationProgress,
                            headYaw,
                            headPitch);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    Nig3(matrices,
                            vertexConsumers,
                            light,
                            entity,
                            limbAngle,
                            limbDistance,
                            tickDelta,
                            animationProgress,
                            headYaw,
                            headPitch);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    this.renderCircle3(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    this.renderCircle4(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    this.renderCircle6(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    this.renderCircle5(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    this.renderCircle7(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                    matrices.popPose();
                }
                {
                    matrices.pushPose();
                    this.renderCircle8(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                    matrices.popPose();
                }
            }
        }
    }
    public void Nig(@NotNull PoseStack matrices,
                          @NotNull MultiBufferSource vertexConsumers,
                          int light,
                          @NotNull Entity entity,
                          float limbAngle, float limbDistance,
                          float tickDelta, float animationProgress,
                          float headYaw, float headPitch) {

        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if (render instanceof LivingEntityRenderer) {
            this.renderCircle(matrices,vertexConsumers,light,0,0,entity);
        }

    }

    public void Nig3(@NotNull PoseStack matrices,
                     @NotNull MultiBufferSource vertexConsumers,
                     int light,
                     @NotNull Entity entity,
                     float limbAngle, float limbDistance,
                     float tickDelta, float animationProgress,
                     float headYaw, float headPitch) {

        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if (render instanceof LivingEntityRenderer) {
            this.renderCircle2(matrices,vertexConsumers,light,0,0,entity);
        }
    }
    public void renderCircle(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.5F; // 圆的半径
        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 1.289)));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color(0.8F, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color(0.8F, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }
    public void renderCircle2(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.25F; // 圆的半径
        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (-entity.tickCount * 1.3)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-entity.tickCount * 2.289)));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }
    public void renderCircle3(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 16; // 垂直段数
        int horizontalSegments = 16; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.last().pose(), x1, y1, z1).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();

                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x4, y4, z4).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            }
        }
    }
    public void renderCircle6(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 4; // 垂直段数
        int horizontalSegments = 4; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.last().pose(), x1, y1, z1).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();

                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x4, y4, z4).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            }
        }
    }
    public void renderCircle5(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 8; // 垂直段数
        int horizontalSegments = 8; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.last().pose(), x1, y1, z1).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();

                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x4, y4, z4).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            }
        }
    }
    public void renderCircle4(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 22; // 垂直段数
        int horizontalSegments = 22; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.last().pose(), x1, y1, z1).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();

                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x4, y4, z4).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            }
        }
    }
    public void renderCircle7(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 24; // 垂直段数
        int horizontalSegments = 24; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.last().pose(), x1, y1, z1).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();

                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x4, y4, z4).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            }
        }
    }
    public void renderCircle8(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 2; // 垂直段数
        int horizontalSegments = 2; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.last().pose(), x1, y1, z1).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();

                vertexConsumer.vertex(poseStack.last().pose(), x2, y2, z2).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x3, y3, z3).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(poseStack.last().pose(), x4, y4, z4).color(1, 0.3F, 0.5F, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            }
        }
    }
}
