package com.ytgld.moonstone.render.light;


import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.buffers.GpuBuffer;
import com.mojang.renderpearl.api.pipeline.*;
import com.ytgld.moonstone.Moonstone;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public final class LightRenders {
    public static final int MAX_LIGHTS = 32;
    private static final int BUFFER_SIZE = 1024;
    public static final BindGroupLayout COLORED_LIGHTS;
    public static final RenderPipeline COLORED_LIGHT_POST;
    private static GpuBuffer coloredLightsBuffer;
    private static final List<ColoredLight> LIGHTS;

    private LightRenders() {
    }

    public static void registerPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(COLORED_LIGHT_POST);
    }

    private static void ensureBuffer() {
        if (coloredLightsBuffer == null) {
            coloredLightsBuffer = RenderSystem.getDevice().createBuffer(() -> "Moonstone Colored Lights", 136, 1024L);
        }
    }

    public static GpuBuffer getColoredLightsBuffer() {
        ensureBuffer();
        return coloredLightsBuffer;
    }

    public static void clearLights() {
        LIGHTS.clear();
    }

    public static void addLight(double x, double y, double z, float radius, float r, float g, float b, float intensity) {
        if (LIGHTS.size() < 32) {
            LIGHTS.add(new ColoredLight(x, y, z, radius, r, g, b, intensity));
        }
    }

    public static void uploadLights(double cameraX, double cameraY, double cameraZ, Matrix4fc viewRotationMatrix) {
        ensureBuffer();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer buffer = stack.malloc(1024);
            Std140Builder builder = Std140Builder.intoBuffer(buffer);

            for(int i = 0; i < 32; ++i) {
                if (i < LIGHTS.size()) {
                    ColoredLight light = (ColoredLight)LIGHTS.get(i);
                    float x = (float)(light.x - cameraX);
                    float y = (float)(light.y - cameraY);
                    float z = (float)(light.z - cameraZ);
                    Vector3f viewPosition = new Vector3f(x, y, z);
                    viewRotationMatrix.transformPosition(viewPosition);
                    builder.putVec4(viewPosition.x, viewPosition.y, viewPosition.z, light.radius);
                } else {
                    builder.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
                }
            }

            for(int i = 0; i < 32; ++i) {
                if (i < LIGHTS.size()) {
                    ColoredLight light = (ColoredLight)LIGHTS.get(i);
                    builder.putVec4(light.r, light.g, light.b, light.intensity);
                } else {
                    builder.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
                }
            }

            ByteBuffer data = builder.get();
            RenderSystem.getDevice().createCommandEncoder().writeToBuffer(coloredLightsBuffer.slice(0L, 1024L), data);
        }

    }

    static {
        COLORED_LIGHTS = BindGroupLayout.builder().withUniform("ColoredLights",
                UniformType.UNIFORM_BUFFER).withUniform("MainSampler",
                UniformType.COMBINED_IMAGE_SAMPLER).withUniform("DepthSampler",
                UniformType.COMBINED_IMAGE_SAMPLER).withUniform("Projection", 
                UniformType.UNIFORM_BUFFER).build();
        COLORED_LIGHT_POST = RenderPipeline.builder().withLocation(Identifier.fromNamespaceAndPath(Moonstone.MODID,
                "pipeline/colored_light_post")).withVertexShader(Identifier.fromNamespaceAndPath(Moonstone.MODID,
                "light/screenquad")).withFragmentShader(Identifier.fromNamespaceAndPath(Moonstone.MODID,
                "light/colored_light_post")).withBindGroupLayout(COLORED_LIGHTS)
                .withColorTargetState(ColorTargetState.DEFAULT)
                .withPrimitiveTopology(PrimitiveTopology.TRIANGLES).build();
        
        LIGHTS = new ArrayList<>();
    }

    public static record ColoredLight(double x, double y, double z, float radius, float r, float g, float b, float intensity) {
    }
}