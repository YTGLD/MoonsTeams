package com.ytgld.moonstone.render.gui_particles;

import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector2f;

import java.util.function.Supplier;

public record BlackKey(int x, int y,ImageColorAndRenderPipeline imageColorAndRenderPipeline) {
    public record ImageColorAndRenderPipeline(int size, ColorImage color,
                                              ResourceLocation identifier,
                                              ShadowImage shadowImage,
                                              Vector2f position,
                                              Vector2f velocity,
                                              Vector2f acceleration, boolean rot){}
    public record ColorImage(int a,int r,int g ,int b ){}
    public record ShadowImage(Supplier<ShaderInstance> shaderInstanceSupplier , boolean light){}
}