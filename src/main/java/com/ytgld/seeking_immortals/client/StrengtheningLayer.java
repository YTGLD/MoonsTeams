package com.ytgld.seeking_immortals.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class StrengtheningLayer<T extends LivingEntity, M extends EntityModel<T>> extends  RenderLayer {
    public StrengtheningLayer(RenderLayerParent<T, M> p_117346_) {
        //noinspection unchecked
        super(p_117346_);
    }
    @Override
    public void render(@NotNull PoseStack matrices,
                       @NotNull MultiBufferSource vertexConsumers,
                       int light,
                       @NotNull Entity entity,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {
//        if (entity instanceof Player player) {
//            {
//                matrices.pushPose();
//
//                double x = -80;//向下或者向上
//                double y = 45;//向下或者向上
//                double z = 45;//向外的角度
//
//                matrices.mulPose(Axis.XN.rotationDegrees((float) x));
//                matrices.mulPose(Axis.YN.rotationDegrees((float) y));
//                matrices.mulPose(Axis.ZN.rotationDegrees((float) z));
//                matrices.translate(1,1.25,1);
//                matrices.scale(0.125f,2,0.125f);
//                new SevenSword(matrices,vertexConsumers);
//
//                matrices.popPose();
//            }
//            {
//                matrices.pushPose();
//
//                double x = -60;//向下或者向上
//                double y = 35;//向下或者向上
//                double z = 45;//向外的角度
//
//                matrices.mulPose(Axis.XN.rotationDegrees((float) x));
//                matrices.mulPose(Axis.YN.rotationDegrees((float) y));
//                matrices.mulPose(Axis.ZN.rotationDegrees((float) z));
//                matrices.translate(1,1.25,1);
//
//                matrices.scale(0.125f,1.75f,0.125f);
//                new SevenSword(matrices,vertexConsumers);
//
//                matrices.popPose();
//            }
//            {
//                matrices.pushPose();
//
//                double x = -40;//向下或者向上
//                double y = 0;//向下或者向上
//                double z = 45;//向外的角度
//
//                matrices.mulPose(Axis.XN.rotationDegrees((float) x));
//                matrices.mulPose(Axis.YN.rotationDegrees((float) y));
//                matrices.mulPose(Axis.ZN.rotationDegrees((float) z));
//                matrices.translate(1,1.25,1);
//
//                matrices.scale(0.125f,1.25f,0.125f);
//                new SevenSword(matrices,vertexConsumers);
//
//                matrices.popPose();
//            }
//        }
    }
}
