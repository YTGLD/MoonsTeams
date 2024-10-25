package com.moonstone.moonstonemod.entity.client.blood;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_owner;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BloodOrbOwner extends EntityRenderer<blood_orb_owner> {
    public BloodOrbOwner(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public void render(blood_orb_owner p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        renderSphere1s(poseStack,bufferSource,240,0.5f,p_entity);
        setT(poseStack, p_entity, bufferSource);

        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
    private void setT(PoseStack matrices,
                      blood_orb_owner entity,
                      MultiBufferSource vertexConsumers)
    {
        matrices.pushPose();

        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            Handler.renderColor(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderType.lightning(),0.3f,128,0,128);
        }
        matrices.popPose();
    }
    public void renderSphere1s(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s,blood_orb_owner p_entity) {
        int stacks = 15; // 垂直方向的分割数
        int slices = 15; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.ging());
        matrices.pushPose();

        matrices.mulPose(Axis.ZP.rotationDegrees(p_entity.tickCount*20));
        matrices.mulPose(Axis.YP.rotationDegrees(p_entity.tickCount*20));
        matrices.mulPose(Axis.XP.rotationDegrees(p_entity.tickCount*20));
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

                vertexConsumer.vertex(matrices.last().pose(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
            }
        }
        matrices.popPose();
    }
    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s, blood_orb_owner entity,float partialTick) {
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        matrices.pushPose();

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.ging());
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            // 在顶部添加孔洞，phi值小于某个阈值则跳过
            if (phi0 < Math.PI / 6) continue; // 调整孔洞的大小

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

                vertexConsumer.vertex(matrices.last().pose(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
            }
        }
        matrices.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(blood_orb_owner p_114482_) {
        return new  ResourceLocation(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}
