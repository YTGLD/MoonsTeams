package com.ytgld.moonstone.render.gui_particles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.MGuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Vector2f;

import java.util.Map;

public class BlackParticlesRenderer {
    public static void onRenderGui(GuiGraphics guiGraphics , PoseStack stack) {
        for (Map.Entry<BlackKey, BlackState> entry : BlackParticlesAdd.all().entrySet()) {
            BlackState state = entry.getValue();
            stack.pushPose();
            addBlackLight(guiGraphics,stack,state.screenX,state.screenY,state);
            stack.popPose();
        }
    }
    private static void  addBlackLight(GuiGraphics guiGraphics,PoseStack pose,int x, int y,BlackState state){
        int alpha = state.alpha;
        int size = state.imageColorAndRenderPipeline.size();
        ResourceLocation identifier = state.imageColorAndRenderPipeline.identifier();

        boolean rot = state.imageColorAndRenderPipeline.rot();
        addCom((state.lifeTime), guiGraphics, pose, x, y,state,alpha,size,identifier,true,rot);
        addCom((state.lifeTime), guiGraphics, pose, x, y,state,state.blurAlpha,size * 2,ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,
                "textures/item_glowing/all.png"),false,false);
    }

    private static void addCom(
            float deltaTime,
            GuiGraphics guiGraphics,
            PoseStack pose,
            int x,
            int y,
            BlackState state,
            int alpha,
            int size,
            ResourceLocation image,
            boolean downSize,
            boolean canRotate
    ) {

        float partialTick =
                Minecraft.getInstance()
                        .getTimer()
                        .getGameTimeDeltaPartialTick(false);

        Vector2f position =
                state.imageColorAndRenderPipeline.position();

        Vector2f previous =
                state.previousPosition;

        float px = x + Mth.lerp(
                partialTick,
                previous.x,
                position.x
        );

        float py = y + Mth.lerp(
                partialTick,
                previous.y,
                position.y
        );




        BlackKey.ColorImage color = state.imageColorAndRenderPipeline.color();

        if (downSize) {
            size = (int) (size * alpha / 255f);
        }

        pose.pushPose();

        pose.translate(px, py,0);

        if (canRotate) {
            pose.mulPose(Axis.ZN.rotationDegrees(deltaTime * 5));
        }

        pose.translate(-px, -py,0);

        pose.translate(px - size / 2f, py - size / 2f,0);

        new MGuiGraphics.GUI(state.imageColorAndRenderPipeline.shadowImage().shaderInstanceSupplier(),
                state.imageColorAndRenderPipeline.shadowImage().light()).blit(guiGraphics,image,
                0,
                0,
                0,
                0,
                size,
                size,
                size,
                size,
                Light.ARGB.color(
                        alpha,
                        color.r(),
                        color.g(),
                        color.b()
                )
        );

        pose.popPose();
    }
}