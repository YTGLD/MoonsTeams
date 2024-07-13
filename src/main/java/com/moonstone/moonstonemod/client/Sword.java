package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.entity.sword;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class Sword {
    public Sword(@NotNull PoseStack matrices,
                     @NotNull MultiBufferSource vertexConsumers,
                     int light,
                     @NotNull Entity entity) {
        if (entity instanceof sword sword){

            if (sword.age > 20){
                {
                    float size = sword.age;
                    size /= 30;
                    float s = -240;
                    float s1 = 240;

                    float alp = 100;
                    alp -= (float) sword.age;
                    if (alp > 10){
                        alp -= sword.age* 0.5f;
                    }
                    if (alp < 0 ){
                        alp = 0;
                    }
                    alp /= 100;

                    matrices.pushPose();
                    renderCircle(matrices, vertexConsumers, 240, 0, 0,size,alp,s,s1, entity);
                    matrices.popPose();
                }
                {
                    float size = sword.age;
                    size /= 50;
                    float s = 240;
                    float s1 = -240;

                    float alp = 100;
                    alp -= (float) (sword.age);
                    if (alp > 10){
                        alp -=sword.age* 0.85f;
                    }
                    if (alp < 0 ){
                        alp = 0;
                    }
                    alp /= 100;

                    matrices.pushPose();
                    renderCircle2(matrices, vertexConsumers, 240, 0, 0,size,alp,s,s1, entity);
                    matrices.popPose();
                }
            }
        }


    }


    public void renderCircle(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,float radius,float alp, float s,float s1,Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64;
        poseStack.translate(0, 0, 0);
        poseStack.mulPose(Axis.XP.rotationDegrees(s));
        poseStack.mulPose(Axis.ZP.rotationDegrees(s1));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color((float) 255 /255, (float) 250 /255, (float) 250 /255, alp).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color((float) 255 /255, (float) 222 /255, (float) 173 /255, alp).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }

    public void renderCircle2(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float radius,float alp,float s,float s1,Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        poseStack.translate(0, 0, 0);
        poseStack.mulPose(Axis.XP.rotationDegrees(s));
        poseStack.mulPose(Axis.ZP.rotationDegrees(s1));


        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.vertex(poseStack.last().pose(), x1, y1, 0).color((float) 255 /255, (float) 222 /255, (float) 173 /255, alp).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(), x2, y2, 0).color((float) 255 /255, (float) 250 /255, (float) 250 /255, alp).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(1, 0, 0).endVertex();
        }
    }



}