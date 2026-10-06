package com.ytgld.moonstone.render.gui_particles;

import org.joml.Vector2f;

import java.util.HashMap;
import java.util.Map;

public class BlackParticlesAdd {

    private static final Map<BlackKey, BlackState> STATES = new HashMap<>();
    private static int time = 0;
    public static void tick() {
        time++;
        for (BlackState state : STATES.values()) {
            state.lifeTime =  state.lifeTime + 1;
            if (!(time - state.lastSeenTick <= 5)){
                state.blurAlpha = Math.max(0, state.blurAlpha - state.downAlpha);
                state.alpha = Math.max(0, state.alpha - state.downAlpha);
            }
            state.lifeTime++;
            state.rotation += 0.05f;
            Vector2f position =
                    state.imageColorAndRenderPipeline.position();

            Vector2f previous =
                    state.previousPosition;

            previous.set(position);

            Vector2f velocity =
                    state.imageColorAndRenderPipeline.velocity();

            Vector2f acceleration =
                    state.imageColorAndRenderPipeline.acceleration();

            velocity = new Vector2f(velocity.x * 60,velocity.y * 60);

            velocity.add(acceleration);
            position.add(velocity);
        }

        STATES.entrySet().removeIf(e -> {
            return e.getValue().alpha <= 0 || STATES.size() > 800;
        });
    }

    public static void markSeen(int x, int y, BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline) {
        BlackKey key = new BlackKey(x, y,imageColorAndRenderPipeline);
        STATES.computeIfAbsent(key,
                k -> new BlackState(imageColorAndRenderPipeline.color().a(), time, x, y,imageColorAndRenderPipeline)
        ).lastSeenTick = time;
    }

    public static void markSeen(int x, int y, BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline,int downAlpha) {
        BlackKey key = new BlackKey(x, y,imageColorAndRenderPipeline);
        STATES.computeIfAbsent(key,
                k -> new BlackState(imageColorAndRenderPipeline.color().a(), BlackParticlesAdd.time, x, y,imageColorAndRenderPipeline,downAlpha)
        ).lastSeenTick = BlackParticlesAdd.time;
    }

    public static void markSeen(int x, int y, BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline,int downAlpha,int blurAlpha) {
        BlackKey key = new BlackKey(x, y,imageColorAndRenderPipeline);
        STATES.computeIfAbsent(key,
                k -> new BlackState(imageColorAndRenderPipeline.color().a(), BlackParticlesAdd.time, x, y,imageColorAndRenderPipeline,downAlpha,blurAlpha)
        ).lastSeenTick = BlackParticlesAdd.time;
    }
    public static Map<BlackKey, BlackState> all() {
        return STATES;
    }
}