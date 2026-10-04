package com.ytgld.moonstone.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import org.jspecify.annotations.Nullable;

public final class MoonstoneTimeVowsGlobals {
    private static @Nullable GpuBuffer gpuBuffer;

    public static void setGpuBuffer(GpuBuffer buffer) {
        gpuBuffer = buffer;
    }

    public static @Nullable GpuBuffer getGpuBuffer() {
        return gpuBuffer;
    }

}