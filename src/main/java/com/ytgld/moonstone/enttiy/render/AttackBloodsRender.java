package com.ytgld.moonstone.enttiy.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.enttiy.AttackBlood;
import com.ytgld.moonstone.enttiy.state.AttackBloodsState;
import com.ytgld.moonstone.render.MRender;
import com.ytgld.moonstone.render.light.LightRenders;
import net.minecraft.client.Minecraft;
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
    public boolean shouldRender(AttackBlood livingEntity, Frustum camera, double camX, double camY, double camZ, float partialTicks) {
        return true;
    }

    @Override
    public @NotNull AttackBloodsState createRenderState() {
        return new AttackBloodsState();
    }

    @Override
    public void submit(AttackBloodsState renderState, PoseStack modelView , SubmitNodeCollector collector, CameraRenderState camera) {
        HandlerClient.setShowRenderLight(true);
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
        collector.submitCustomGeometry(poseStack, MRender.colorOutline, (pose, bufferSource) -> {
            addTrail(pose, entity, bufferSource,1,0,0,0.1f);
        });
        if (entity.posPre.distanceTo(entity.posPost) > 0.01f) {
            LightRenders.addLight(
                    entity.position().x,entity.position().y,entity.position().z,
                    8,
                    0.8f,0,0,
                    1);
            collector.submitCustomGeometry(poseStack, MRender.renderType, (pose, bufferSource) -> {
                renderSphere1(pose, bufferSource, 0, 0.1f);
            });
        }
        poseStack.popPose();
    }


    public static void addTrail(PoseStack.Pose matrices,
                                AttackBlood entity,
                                VertexConsumer vertexConsumers,
                                float r, float g, float b,float w) {

        Vec3 cameraPos = Minecraft.getInstance().gameRenderer.mainCamera().position();

        for (int i = 1; i < entity.trailPositions.size(); i++) {
            Vec3 prevPos = entity.trailPositions.get(i - 1);
            Vec3 currPos = entity.trailPositions.get(i);

            Vec3 adjustedPrevPos = new Vec3(
                    prevPos.x - entity.getX(),
                    prevPos.y - entity.getY(),
                    prevPos.z - entity.getZ()
            );
            Vec3 adjustedCurrPos = new Vec3(
                    currPos.x - entity.getX(),
                    currPos.y - entity.getY(),
                    currPos.z - entity.getZ()
            );
            float alpha = (float) i / entity.trailPositions.size();

            Vec3 dir = adjustedCurrPos.subtract(adjustedPrevPos).normalize();
            Vec3 toCamera = cameraPos.subtract(
                    entity.getX() + adjustedPrevPos.x,
                    entity.getY() + adjustedPrevPos.y,
                    entity.getZ() + adjustedPrevPos.z
            ).normalize();

            Vec3 side = dir.cross(toCamera).normalize().scale(alpha * w);

            Vec3 p0 = adjustedPrevPos.add(side);
            Vec3 p1 = adjustedPrevPos.subtract(side);
            Vec3 p2 = adjustedCurrPos.subtract(side);
            Vec3 p3 = adjustedCurrPos.add(side);

            vertexConsumers.addVertex(matrices, (float) p0.x, (float) p0.y, (float) p0.z)
                    .setColor(r, g, b, alpha).setUv(0, 0);

            vertexConsumers.addVertex(matrices, (float) p1.x, (float) p1.y, (float) p1.z)
                    .setColor(r, g, b, alpha).setUv(1, 0);

            vertexConsumers.addVertex(matrices, (float) p2.x, (float) p2.y, (float) p2.z)
                    .setColor(r, g, b, alpha).setUv(1, 1);

            vertexConsumers.addVertex(matrices, (float) p3.x, (float) p3.y, (float) p3.z)
                    .setColor(r, g, b, alpha).setUv(0, 1);
        }
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
        reusedState.canLook = entity.canSee;
    }
}




