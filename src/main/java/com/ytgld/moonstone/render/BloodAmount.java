package com.ytgld.moonstone.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.event.NewEvent;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import static com.ytgld.moonstone.item.ms.blood.TwistedAmout.MaxSword;


public class BloodAmount  implements ICurioRenderer {
    @Override
    public <S extends LivingEntityRenderState, M extends EntityModel<? super S>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, S renderState, RenderLayerParent<S, M> renderLayerParent, EntityRendererProvider.Context context, float yRotation, float xRotation) {
        if (stack.get(DataReg.tag)!=null) {
            stack.get(DataReg.tag).getInt(MaxSword).ifPresent((a)->{
                render(a,poseStack,submitNodeCollector, MRender.renderTypeOutline);
                render(a,poseStack,submitNodeCollector, MRender.renderType);
            });
        }
    }
    public void render(int value, PoseStack poseStack, SubmitNodeCollector collector, RenderType renderType){
        poseStack.pushPose();
        if (value > 0) {
            HandlerClient.doPass = true;
            HandlerClient.showOutline = true;
            collector.submitCustomGeometry(poseStack, renderType, (pose, vertexConsumers) -> {
                pose.rotate(Axis.YN.rotation(NewEvent.time / 20));
                pose.translate((float) 0, (float) 0.07, (float) (0.7 * 1.5));
                renderSphere1(pose, vertexConsumers, 255, 0.17f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 1) {
             collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                 matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 22));
                 matrices.translate((float) 0, (float) 0, (float) (0.5f * 1.5));
                 renderSphere1(matrices, vertexConsumers, 255, 0.14f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 2) {
             collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                 matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 10));
                 matrices.translate((float) 0, (float) 0.2, (float) (0.45 * 1.5));
                 renderSphere1(matrices, vertexConsumers, 255, 0.1f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 3) {
            collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 21));
                matrices.translate((float) 0, (float) 0.22, (float) (0.23 * 1.5));
                renderSphere1(matrices, vertexConsumers, 255, 0.075f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 4) {
                collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                    matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 8));
                    matrices.translate((float) 0, (float) 0.25, (float) (0.55 * 1.5));
                    renderSphere1(matrices, vertexConsumers, 255, 0.12f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 5) {
                collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                    matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 6));
                    matrices.translate((float) 0, (float) -0.05, (float) (0.58 * 1.5));
                    renderSphere1(matrices, vertexConsumers, 255, 0.1f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 6) {
                collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                    matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 15));
                    matrices.translate((float) 0, (float) 0.11, (float) (0.6 * 1.5));
                    renderSphere1(matrices, vertexConsumers, 255, 0.15f);
            });
        }
        poseStack.popPose();


        poseStack.pushPose();
        if (value > 7) {
                collector.submitCustomGeometry(poseStack, renderType, (matrices, vertexConsumers) -> {
                    matrices.rotate(Axis.YN.rotation((float) NewEvent.time / 20));
                    matrices.translate((float) 0, (float) 0.11, (float) (0.4 * 1.5));
                    renderSphere1(matrices, vertexConsumers, 255, 0.22f);
            });
        }
        poseStack.popPose();


    }
    public BloodAmount() {

    }
    private void renderSphere1(@NotNull PoseStack.Pose matrices, @NotNull VertexConsumer vertexConsumer, int light, float s) {
        float radius = s; // 球体的半径
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = radius * (float) Math.cos(phi0);
                float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = radius * (float) Math.cos(phi0);
                float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = radius * (float) Math.cos(phi1);
                float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = radius * (float) Math.cos(phi1);
                float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.addVertex(matrices, x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices, x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices, x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices, x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
            }
        }
    }
}
