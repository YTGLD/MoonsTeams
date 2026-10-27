package com.ytgld.moonstone.render.gui_particles;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2fStack;
import org.joml.Vector2f;

import java.util.Map;

public class BlackParticlesRenderer {
    public static void onRenderGui(GuiGraphicsExtractor guiGraphics ,Matrix3x2fStack stack) {
        for (Map.Entry<BlackKey, BlackState> entry : BlackParticlesAdd.all().entrySet()) {
            BlackState state = entry.getValue();
            stack.pushMatrix();
            addBlackLight(guiGraphics,stack,state.screenX,state.screenY,state);
            stack.popMatrix();
        }
    }
    private static void  addBlackLight(GuiGraphicsExtractor guiGraphics,Matrix3x2fStack pose,int x, int y,BlackState state){
        int alpha = state.alpha;
        int size = state.imageColorAndRenderPipeline.size();
        Identifier identifier = state.imageColorAndRenderPipeline.identifier();

        boolean rot = state.imageColorAndRenderPipeline.rot();
        addCom((state.lifeTime), guiGraphics, pose, x, y,state,alpha,size,identifier,true,rot);
        addCom((state.lifeTime), guiGraphics, pose, x, y,state,state.blurAlpha,size * 2,Identifier.fromNamespaceAndPath(Moonstone.MODID,
                "textures/item_glowing/all.png"),false,false);
    }

    private static void addCom(
            float deltaTime,
            GuiGraphicsExtractor guiGraphics,
            Matrix3x2fStack pose,
            int x,
            int y,
            BlackState state,
            int alpha,
            int size,
            Identifier image,
            boolean downSize,
            boolean canRotate
    ) {
        BlackKey.ColorImage color = state.imageColorAndRenderPipeline.color();

        if (downSize) {
            size = (int) (size * alpha / 255f);
        }

        Vector2f position = state.imageColorAndRenderPipeline.position();
        Vector2f velocity = state.imageColorAndRenderPipeline.velocity();
        Vector2f acceleration = state.imageColorAndRenderPipeline.acceleration();

        velocity.fma(deltaTime, acceleration);
        position.fma(deltaTime, velocity);
        float px = x + position.x;
        float py = y + position.y;

        pose.pushMatrix();

        pose.translate(px, py);

        if (canRotate) {
            pose.rotate(deltaTime / 10f);
        }

        pose.translate(-px, -py);

        pose.translate(px - size / 2f, py - size / 2f);

        guiGraphics.blit(
                state.imageColorAndRenderPipeline.renderPipeline(),
                image,
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

        pose.popMatrix();
    }
}