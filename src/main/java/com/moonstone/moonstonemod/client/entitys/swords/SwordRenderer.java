package com.moonstone.moonstonemod.client.entitys.swords;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.other.flysword;
import com.moonstone.moonstonemod.entity.other.suddenrain;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SwordRenderer <T extends ThrowableItemProjectile> extends EntityRenderer<T> {
    public SwordRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    public void render(T p_113839_, float p_113840_, float p_113841_, PoseStack p_113842_, MultiBufferSource p_113843_, int p_113844_) {

        p_113842_.pushPose();

        setTRed(p_113842_,p_113839_,p_113843_);
        setT(p_113842_,p_113839_,p_113843_);
        p_113842_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_113841_, p_113839_.yRotO, p_113839_.getYRot()) - 90.0F));
        p_113842_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_113841_, p_113839_.xRotO, p_113839_.getXRot())));

        p_113842_.mulPose(Axis.XP.rotationDegrees(45.0F));
        p_113842_.scale(0.05625F, 0.05625F, 0.05625F);
        p_113842_.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = p_113843_.getBuffer(RenderType.entityCutout(this.getTextureLocation(p_113839_)));

        PoseStack.Pose posestack$pose = p_113842_.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, p_113844_);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, p_113844_);

        for(int j = 0; j < 4; ++j) {
            p_113842_.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex(matrix4f, matrix3f, vertexconsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, p_113844_);
            this.vertex(matrix4f, matrix3f, vertexconsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, p_113844_);
            this.vertex(matrix4f, matrix3f, vertexconsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, p_113844_);
            this.vertex(matrix4f, matrix3f, vertexconsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, p_113844_);
        }

        p_113842_.popPose();
        super.render(p_113839_, p_113840_, p_113841_, p_113842_, p_113843_, p_113844_);
    }
    private void setT(PoseStack matrices,
                      T flyswords,
                      MultiBufferSource vertexConsumers)
    {
        if (flyswords instanceof flysword entity) {
            matrices.pushPose();

            matrices.mulPose(Axis.ZP.rotationDegrees(0));
            matrices.mulPose(Axis.YP.rotationDegrees(0));
            matrices.mulPose(Axis.XP.rotationDegrees(0));

            for (int i = 1; i < entity.getTrailPositions().size(); i++) {
                Vec3 prevPos = entity.getTrailPositions().get(i - 1);
                Vec3 currPos = entity.getTrailPositions().get(i);
                Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
                Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

                float alpha = (float) (i) / (float) (entity.getTrailPositions().size());

                Handler.renderSword(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderType.lightning(), 0.0375f);
            }
            matrices.popPose();
        }
    }
    private void setTRed(PoseStack matrices,
                         T flyswords,
                         MultiBufferSource vertexConsumers)
    {
        if (flyswords instanceof suddenrain entity) {
            matrices.pushPose();

            matrices.mulPose(Axis.ZP.rotationDegrees(0));
            matrices.mulPose(Axis.YP.rotationDegrees(0));
            matrices.mulPose(Axis.XP.rotationDegrees(0));

            for (int i = 1; i < entity.getTrailPositions().size(); i++) {
                Vec3 prevPos = entity.getTrailPositions().get(i - 1);
                Vec3 currPos = entity.getTrailPositions().get(i);
                Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
                Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

                float alpha = (float) (i) / (float) (entity.getTrailPositions().size());

                Handler.renderBlood(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderType.lightning(), 0.0375f);
            }
            matrices.popPose();
        }
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(T p_114482_) {
        return new ResourceLocation(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }

    public void vertex(Matrix4f p_254392_, Matrix3f p_254011_, VertexConsumer p_253902_, int p_254058_, int p_254338_, int p_254196_, float p_254003_, float p_254165_, int p_253982_, int p_254037_, int p_254038_, int p_254271_) {
        p_253902_.vertex(p_254392_, (float)p_254058_, (float)p_254338_, (float)p_254196_).color(255, 255, 255, 255).uv(p_254003_, p_254165_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_254271_).normal(p_254011_, (float)p_253982_, (float)p_254038_, (float)p_254037_).endVertex();
    }
}
