package com.ytgld.moonstone.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import com.mojang.blaze3d.systems.RenderSystem;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

public class MoonstoneSettingsUniform implements AutoCloseable {
    public static final int UBO_SIZE = new Std140SizeCalculator().putFloat().get();
    private final GpuBuffer buffer = RenderSystem.getDevice().createBuffer(() -> "Moonstone Settings Uniform", 140, UBO_SIZE);
    private static final long START_TIME = System.nanoTime();

    public void update() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            float time = (System.nanoTime() - START_TIME) / 1_000_000_000.0F;
            ByteBuffer data = Std140Builder.onStack(stack, UBO_SIZE)
                    .putFloat(time)
                    .get();
            RenderSystem.getDevice().createCommandEncoder().writeToBuffer(this.buffer.slice(), data);
        }
        MoonstoneTimeVowsGlobals.setGpuBuffer(this.buffer);
    }

    @Override
    public void close() {
        this.buffer.close();
    }
}

