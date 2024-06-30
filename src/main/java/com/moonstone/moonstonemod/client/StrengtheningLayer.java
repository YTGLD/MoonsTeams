package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.cell_zombie;
import com.moonstone.moonstonemod.entity.nightmare_entity;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
        if (entity instanceof nightmare_entity) {


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
            matrices.translate(0, 0, 0);
            float s = (float) Math.sin((double) entity.tickCount / 22.5);
            s /= 5;
            s += 1;
            matrices.scale(s/2, s/2, s/2);


            Minecraft.getInstance().getItemRenderer().renderStatic(
                    Items.gorillacake.get().getDefaultInstance(),
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
