package com.ytgld.moonstone.enttiy.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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
    private final SwordModel model;

    public FlySwordRender(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SwordModel(context.bakeLayer(ModelLayers.ARROW));
    }

    @Override
    public boolean shouldRender(FlySword entity, Frustum culler, double camX, double camY, double camZ) {
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
        submitNodeCollector.submitCustomGeometry(poseStack, MRender.lines, (pose, bufferSource) -> {
            setT2(pose, entity, bufferSource);
        });
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public FlySwordState createRenderState() {
        return new FlySwordState();
    }
    private void setT2(PoseStack.Pose matrices,
                       FlySword entity,
                       VertexConsumer vertexConsumers)
    {
        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());
            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());
            alpha *= 255;

            int color = Light.ARGB.color(255,150,200,255);
            int as = (color >> 24) & 0xFF;
            int rs = (color >> 16) & 0xFF;
            int gs = (color >> 8) & 0xFF;
            int bs = color & 0xFF;

            addSquare(vertexConsumers,matrices,adjustedCurrPos, adjustedPrevPos, Light.ARGB.color((int) alpha,rs,gs,bs));
        }
    }

    private static void addSquare(VertexConsumer vertexConsumer, PoseStack.Pose poseStack, Vec3 s, Vec3 e, int colorS) {
        vertexConsumer.addVertex(poseStack, (float) s.x, (float) s.y, (float) s.z)
                .setColor(colorS)
                .setUv2(255, 255)
                .setNormal(1,0,1)
                .setLight(255)
                .setOverlay(NO_OVERLAY)
                .setLineWidth(3);

        vertexConsumer.addVertex(poseStack, (float) e.x, (float) e.y, (float) e.z)
                .setColor(colorS)
                .setUv2(255, 255)
                .setNormal(1,0,1)
                .setLight(255)
                .setOverlay(NO_OVERLAY)
                .setLineWidth(3);
    }


    public class SwordModel extends EntityModel<FlySwordState> {
        public SwordModel(ModelPart root) {
            super(root, RenderTypes::entityCutoutCull);
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();
            root.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(-11.0F, 0.0F, 0.0F, ((float)Math.PI / 4F), 0.0F, 0.0F).withScale(0.8F));
            CubeListBuilder cross = CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, CubeDeformation.NONE, 1.0F, 0.8F);
            root.addOrReplaceChild("cross_1", cross, PartPose.rotation(((float)Math.PI / 4F), 0.0F, 0.0F));
            root.addOrReplaceChild("cross_2", cross, PartPose.rotation(2.3561945F, 0.0F, 0.0F));
            return LayerDefinition.create(mesh.transformed((pose) -> pose.scaled(0.9F)), 32, 32);
        }

        public void setupAnim(FlySwordState state) {
            super.setupAnim(state);
            if (state.shake > 0.0F) {
                float pow = -Mth.sin((double)(state.shake * 3.0F)) * state.shake;
                ModelPart var10000 = this.root;
                var10000.zRot += pow * ((float)Math.PI / 180F);
            }

        }
    }

}

