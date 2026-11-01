package com.ytgld.moonstone;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.other.ITrail;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class HandlerClient {
    public static boolean showOutline = false;
    public static boolean doPass = false;
    private static boolean showRenderLight = false;

    public static void addTrail(PoseStack.Pose matrices,
                                ITrail trailEntity,
                                Entity entity,
                                VertexConsumer vertexConsumers,
                                float r, float g, float b,float w) {

        Vec3 cameraPos = Minecraft.getInstance().gameRenderer.mainCamera().position();

        for (int i = 1; i < trailEntity.trailPositions().size(); i++) {
            Vec3 prevPos = trailEntity.trailPositions().get(i - 1);
            Vec3 currPos = trailEntity.trailPositions().get(i);

            Vec3 adjustedPrevPos = new Vec3(
                    prevPos.x - entity.getX(),
                    prevPos.y - entity.getY(),
                    prevPos.z - entity.getZ()
            );
            Vec3 adjustedCurrPos = new Vec3(
                    currPos.x - entity.getX(),
                    currPos.y - entity.getY(),
                    currPos.z - entity.getZ()
            );
            float alpha = (float) i / trailEntity.trailPositions().size();

            Vec3 dir = adjustedCurrPos.subtract(adjustedPrevPos).normalize();
            Vec3 toCamera = cameraPos.subtract(
                    entity.getX() + adjustedPrevPos.x,
                    entity.getY() + adjustedPrevPos.y,
                    entity.getZ() + adjustedPrevPos.z
            ).normalize();

            Vec3 side = dir.cross(toCamera).normalize().scale(alpha * w);

            Vec3 p0 = adjustedPrevPos.add(side);
            Vec3 p1 = adjustedPrevPos.subtract(side);
            Vec3 p2 = adjustedCurrPos.subtract(side);
            Vec3 p3 = adjustedCurrPos.add(side);

            vertexConsumers.addVertex(matrices, (float) p0.x, (float) p0.y, (float) p0.z)
                    .setColor(r, g, b, alpha).setUv(0, 0);

            vertexConsumers.addVertex(matrices, (float) p1.x, (float) p1.y, (float) p1.z)
                    .setColor(r, g, b, alpha).setUv(1, 0);

            vertexConsumers.addVertex(matrices, (float) p2.x, (float) p2.y, (float) p2.z)
                    .setColor(r, g, b, alpha).setUv(1, 1);

            vertexConsumers.addVertex(matrices, (float) p3.x, (float) p3.y, (float) p3.z)
                    .setColor(r, g, b, alpha).setUv(0, 1);
        }
    }

    public static boolean isShowRenderLight() {
        return showRenderLight;
    }

    public static void setShowRenderLight(boolean showRenderLight) {
        HandlerClient.showRenderLight = showRenderLight;
    }
}
