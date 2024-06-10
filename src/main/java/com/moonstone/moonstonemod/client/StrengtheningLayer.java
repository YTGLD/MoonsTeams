package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
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

            if (living.getPersistentData().getBoolean(AllEvent.Gorillas)) {
                matrices.pushPose();
                //noinspection unchecked
                EntityModel<LivingEntity> s = this.getParentModel();
              //  Gorilla(living, matrices, vertexConsumers, light);
                matrices.popPose();
            }


        }
    }

    @SuppressWarnings("unchecked")
    public void Gorilla(final LivingEntity entity,
                        PoseStack matrices,
                              MultiBufferSource vertexConsumers,
                              int light) {

        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if (render instanceof LivingEntityRenderer) {
            if (render instanceof LivingEntityRenderer) {
                LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>
                        livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
                EntityModel<LivingEntity> entityModel = livingRenderer.getModel();

                matrices.translate(0, -entity.getEyeHeight()*1.5,0);
                matrices.scale(0.45f,0.45f,0.45f);
                matrices.mulPose(Axis.ZP.rotationDegrees(180));
                matrices.mulPose(Axis.YP.rotationDegrees(180- (float) entity.tickCount*3));
               // Minecraft.getInstance().getItemRenderer().renderStatic(Items.test_image.get().getDefaultInstance(), ItemDisplayContext.GROUND, (int) (120+((float)120*Math.sin( entity.tickCount))), OverlayTexture.NO_OVERLAY, matrices, vertexConsumers, entity.level(), 0);




            }
        }
    }


}
