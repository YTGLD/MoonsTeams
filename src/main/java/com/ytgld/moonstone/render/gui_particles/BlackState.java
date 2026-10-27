package com.ytgld.moonstone.render.gui_particles;

public class BlackState {
    public int alpha;
    public int lifeTime = 0;
    public int lastSeenTick;
    public int blurAlpha;

    public final int screenX;
    public final int screenY;
    public final BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline;
    public final int downAlpha;


    public BlackState(int alpha, int lastSeenTick, int x, int y, BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline) {
        this.alpha = alpha;
        this.lastSeenTick = lastSeenTick;
        this.screenX = x;
        this.screenY = y;
        this.imageColorAndRenderPipeline = imageColorAndRenderPipeline;
        this.downAlpha = 30;
        this.blurAlpha = alpha / 10;
    }
    public BlackState(int alpha, int lastSeenTick, int x, int y, BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline,int downAlpha) {
        this.alpha = alpha;
        this.lastSeenTick = lastSeenTick;
        this.screenX = x;
        this.screenY = y;
        this.imageColorAndRenderPipeline = imageColorAndRenderPipeline;
        this.downAlpha = downAlpha;
        this.blurAlpha = alpha / 10;
    }
    public BlackState(int alpha, int lastSeenTick, int x, int y, BlackKey.ImageColorAndRenderPipeline imageColorAndRenderPipeline,int downAlpha,int blurAlpha) {
        this.alpha = alpha;
        this.lastSeenTick = lastSeenTick;
        this.screenX = x;
        this.screenY = y;
        this.imageColorAndRenderPipeline = imageColorAndRenderPipeline;
        this.downAlpha = downAlpha;
        this.blurAlpha = blurAlpha;
    }
}