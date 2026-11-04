package com.ytgld.moonstone.utils.dout;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.utils.RenderObjects;
import com.ytgld.moonstone.utils.WorldRenderObject;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class WrathHeavenRenderObject extends WorldRenderObject {
    public int color = 0xffffffff;
    public float size = 1;
    public boolean hasDown;
    public WrathHeavenRenderObject(Vec3 position,int color,float size,boolean hasDown) {
        super(position);
        this.color = color;
        this.size = size;
        this.maxTime = 500;
        this.hasDown = hasDown;
    }
    public int newAlpha = 0;

    @Override
    public void clientTick() {
        super.clientTick();

        if (age > maxTime * 0.7f) {
            if (newAlpha > 0) {
                newAlpha -= 5;
            }
        }else {
            if (newAlpha < 150) {
                newAlpha += 15;
            }
        }
    }

    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera, float partialTick) {
        Vec3 cameraPos = camera.pos;
        poseStack.pushPose();
        poseStack.translate(
                position.x - cameraPos.x,
                position.y - cameraPos.y,
                position.z - cameraPos.z
        );
        doRender(poseStack, collector, partialTick);
        doRender(poseStack, collector, partialTick);
        poseStack.popPose();
    }
    private void doRender(PoseStack poseStack, SubmitNodeCollector collector, float partialTick){

        poseStack.pushPose();
        poseStack.rotate(Axis.YP.rotationDegrees((age + partialTick) / 5f));
        renderAll(poseStack, collector,Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/render_object/wrath_heaven_2.png"));

        poseStack.popPose();


        poseStack.pushPose();
        poseStack.rotate(Axis.YP.rotationDegrees(-(age + partialTick) / 5f));
        renderAll(poseStack, collector,Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/render_object/wrath_heaven_1.png"));
        poseStack.popPose();
    }
    private void renderAll(PoseStack poseStack, SubmitNodeCollector collector, Identifier identifier){
        int as = (color >> 24) & 0xFF;
        int rs = (color >> 16) & 0xFF;
        int gs = (color >> 8) & 0xFF;
        int bs = color & 0xFF;
        poseStack.pushPose();

        Quaternionf quaternionf = new Quaternionf();
        quaternionf.transform(new Vector3d(0,1,0));
        quaternionf.rotateX(-(float)Math.PI / 2);
        poseStack.rotate(quaternionf);


        render(poseStack,collector,size,
                RenderObjects.renderTypeFunctionLive.apply(
                        identifier
                ),
                Light.ARGB.color(newAlpha,rs,gs,bs));

        render(poseStack,collector,size,
                RenderObjects.renderTypeFunctionLiveOutline.apply(
                        identifier
                ),
                Light.ARGB.color(newAlpha,rs,gs,bs));

        poseStack.popPose();
    }
    private void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, float size, RenderType renderType, int color){
        poseStack.pushPose();
        submitNodeCollector.submitCustomGeometry(poseStack, renderType,
                (pose, vc) -> {
                    Matrix4f mat = pose.pose();
                    vc.addVertex(mat, -size, -size, 0)
                            .setColor(color)
                            .setUv(0, 1)
                            .setOverlay(OverlayTexture.NO_OVERLAY)
                            .setUv2(255,255)
                            .setNormal(0, 0, 1);

                    vc.addVertex(mat, size, -size, 0)
                            .setColor(color)
                            .setUv(1, 1)
                            .setOverlay(OverlayTexture.NO_OVERLAY)
                            .setUv2(255,255)
                            .setNormal(0, 0, 1);

                    vc.addVertex(mat, size, size, 0)
                            .setColor(color)
                            .setUv(1, 0)
                            .setOverlay(OverlayTexture.NO_OVERLAY)
                            .setUv2(255,255)
                            .setNormal(0, 0, 1);

                    vc.addVertex(mat, -size, size, 0)
                            .setColor(color)
                            .setUv(0, 0)
                            .setOverlay(OverlayTexture.NO_OVERLAY)
                            .setUv2(255,255)
                            .setNormal(0, 0, 1);
                });
        poseStack.popPose();
    }
}
