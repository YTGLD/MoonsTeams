package com.ytgld.moonstone.enttiy.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.enttiy.AttackBlood;
import com.ytgld.moonstone.enttiy.state.AttackBloodsState;
import com.ytgld.moonstone.enttiy.state.AttackBloodsState;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class AttackBloodsRender extends EntityRenderer<@NotNull AttackBlood, AttackBloodsState> {
    public AttackBloodsRender(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public boolean shouldRender(AttackBlood livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public @NotNull AttackBloodsState createRenderState() {
        return new AttackBloodsState();
    }

    @Override
    public void submit(AttackBloodsState renderState, PoseStack modelView , SubmitNodeCollector collector, CameraRenderState camera) {
        AttackBlood entity = renderState.entity;

        submitOther(renderState, modelView, collector, camera);
    }
    public void submitOther(AttackBloodsState renderState, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        HandlerClient.showOutline = true;
        HandlerClient.doPass = true;

        AttackBlood entity = renderState.entity;
        double x = Mth.lerp(renderState.partialTick, entity.xOld, entity.getX());
        double y = Mth.lerp(renderState.partialTick, entity.yOld, entity.getY());
        double z = Mth.lerp(renderState.partialTick, entity.zOld, entity.getZ());
        poseStack.pushPose();
        poseStack.translate(entity.getX()-x, entity.getY()-y,entity.getZ() -z);
        collector.submitCustomGeometry(poseStack, MRender.renderTypeOutline, (pose, bufferSource) -> {
            setT(pose, entity, bufferSource);
        });
        if (entity.canSee) {
            collector.submitCustomGeometry(poseStack, MRender.renderTypeOutline, (pose, bufferSource) -> {
                renderSphere1(pose, bufferSource, 0, 0.1f);
            });
        }

        collector.submitCustomGeometry(poseStack, MRender.renderType, (pose, bufferSource) -> {
            setT(pose, entity, bufferSource);
        });
        if (entity.canSee) {
            collector.submitCustomGeometry(poseStack, MRender.renderType, (pose, bufferSource) -> {
                renderSphere1(pose, bufferSource, 0, 0.1f);
            });
        }
        poseStack.popPose();
    }


    private void setT(PoseStack.Pose matrices, AttackBlood entity, VertexConsumer vertexConsumers) {
        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());
            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());
            renderBloodW(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, Light.ARGB.color((int) (alpha * 255),255,0,0),0.1f * alpha);
            renderBloodW(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, Light.ARGB.color((int) (alpha * 255),255,0,0),0.1f * alpha);
        }
    }
    public static void renderBloodW(PoseStack.Pose poseStack, VertexConsumer vertexConsumer, Vec3 start, Vec3 end, int a, float r) {
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * r;
            double z1 = Math.sin(angle1) * r;
            double x2 = Math.cos(angle2) * r;
            double z2 = Math.sin(angle2) * r;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }


    private static void addSquare(VertexConsumer vertexConsumer, PoseStack.Pose poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, int c) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack, (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(c)
                .setUv2(255, 255)
                .setNormal(poseStack,0, 0, 1);

        vertexConsumer.addVertex(poseStack, (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(c)
                .setUv2(255, 255)
                .setNormal(poseStack,0, 0, 1);

        vertexConsumer.addVertex(poseStack, (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(c)
                .setUv2(255, 255)
                .setNormal(poseStack,0, 0, 1);

        vertexConsumer.addVertex(poseStack, (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(c)
                .setUv2(255, 255)
                .setNormal(poseStack,0, 0, 1);
    }
    public void renderSphere1(@NotNull PoseStack.Pose matrices, @NotNull VertexConsumer vertexConsumer, int light, float s ) {

        int stacks = 10; // 垂直方向的分割数
        int slices = 10; // 水平方向的分割数
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);
                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);
                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);
                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.addVertex(matrices, x0, y0, z0).setColor(1.0f, 0, 0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(matrices,1, 0, 0);
                vertexConsumer.addVertex(matrices, x1, y1, z1).setColor(1.0f, 0, 0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(matrices,1, 0, 0);
                vertexConsumer.addVertex(matrices, x2, y2, z2).setColor(1.0f, 0, 0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(matrices,1, 0, 0);
                vertexConsumer.addVertex(matrices, x3, y3, z3).setColor(1.0f, 0, 0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(matrices,1, 0, 0);
            }
        }
    }
    @Override
    public void extractRenderState(AttackBlood entity, AttackBloodsState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.entity = entity;
        reusedState.partialTick = partialTick;
    }
}




