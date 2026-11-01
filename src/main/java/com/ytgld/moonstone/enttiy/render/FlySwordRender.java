package com.ytgld.moonstone.enttiy.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.FlySword;
import com.ytgld.moonstone.enttiy.state.FlySwordState;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class FlySwordRender extends EntityRenderer<FlySword, FlySwordState> {
    public FlySwordRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(FlySword entity, Frustum culler, double camX, double camY, double camZ, float partialTicks) {
        return true;
    }

    @Override
    public void extractRenderState(FlySword entity, FlySwordState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.xRot = entity.getXRot(partialTick);
        reusedState.yRot = entity.getYRot(partialTick);
        reusedState.entity = entity;
        reusedState.partialTick = partialTick;
    }
    public void submit(FlySwordState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        var entity = state.entity;
        double x = Mth.lerp(state.partialTick, entity.xOld, entity.getX());
        double y = Mth.lerp(state.partialTick, entity.yOld, entity.getY());
        double z = Mth.lerp(state.partialTick, entity.zOld, entity.getZ());
        poseStack.pushPose();
        poseStack.translate(entity.getX()-x, entity.getY()-y,entity.getZ() -z);
        submitNodeCollector.submitCustomGeometry(poseStack, MRender.colorOutline, (pose, bufferSource) -> {
            HandlerClient.addTrail(pose, entity,entity, bufferSource,0.25f,0.66f,1f,0.1f);
        });
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public FlySwordState createRenderState() {
        return new FlySwordState();
    }
}

