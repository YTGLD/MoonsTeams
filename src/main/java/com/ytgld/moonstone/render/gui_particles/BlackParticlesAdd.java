package com.ytgld.moonstone.render.gui_particles;

import java.util.HashMap;
import java.util.Map;

public class BlackParticlesAdd {

    private static final Map<BlackKey, BlackState> STATES = new HashMap<>();
    private static int time = 0;

    public static final int KEEP_ALIVE = 10;

    public static void tick() {
        time += 2;
        for (BlackState s : STATES.values()) {
            s.lifeTime =  s.lifeTime + 1;
            if (time - s.lastSeenTick <= KEEP_ALIVE) {
            } else {
                s.blurAlpha = Math.max(0, s.blurAlpha - s.downAlpha);
                s.alpha = Math.max(0, s.alpha - s.downAlpha);
            }
        }

        STATES.entrySet().removeIf(e -> e.getValue().alpha <= 0);
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