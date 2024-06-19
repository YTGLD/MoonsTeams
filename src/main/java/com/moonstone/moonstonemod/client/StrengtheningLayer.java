package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
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
import net.minecraft.world.item.ItemDisplayContext;
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
        if (entity instanceof LivingEntity living) {

            if (Handler.hascurio(living, Items.the_heart.get())) {
                matrices.pushPose();

                the_heart(matrices,
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

        }
    }

    @SuppressWarnings("unchecked")
    public void the_heart(@NotNull PoseStack matrices,
                          @NotNull MultiBufferSource vertexConsumers,
                          int light,
                          @NotNull Entity entity,
                          float limbAngle, float limbDistance,
                          float tickDelta, float animationProgress,
                          float headYaw, float headPitch) {

        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if (render instanceof LivingEntityRenderer) {
            matrices.translate(0, 0, 0);
            matrices.scale(1, 1, 1);
            matrices.mulPose(Axis.ZP.rotationDegrees(180));



            Minecraft.getInstance().getItemRenderer().renderStatic(
                    Items.the_heart_image.get().getDefaultInstance(),
                    ItemDisplayContext.GROUND,
                    light,
                    OverlayTexture.NO_OVERLAY,
                    matrices,
                    vertexConsumers,
                    entity.level(),
                    0);
        }

    }


}
