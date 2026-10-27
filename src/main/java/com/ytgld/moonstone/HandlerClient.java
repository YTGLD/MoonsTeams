package com.ytgld.moonstone;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class HandlerClient {
    public static boolean showOutline = false;
    public static boolean doPass = false;
    private static boolean showRenderLight = false;

    public static boolean isShowRenderLight() {
        return showRenderLight;
    }

    public static void setShowRenderLight(boolean showRenderLight) {
        HandlerClient.showRenderLight = showRenderLight;
    }
}
