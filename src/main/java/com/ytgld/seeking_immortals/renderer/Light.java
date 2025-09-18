package com.ytgld.seeking_immortals.renderer;

public class Light {

    public static class ARGB {
        public static int color(int alpha, int red, int green, int blue) {
            return (alpha << 24) | (red << 16) | (green << 8) | blue;
        }
    }

    public static float u(double offset, float size) {
        return (float) (-offset / 2.0 / size + 0.5);
    }

    public static float v(double offset, float size) {
        return (float) (-offset / 2.0 / size + 0.5);
    }

}
