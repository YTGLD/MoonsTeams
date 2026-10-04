package com.ytgld.moonstone.render.gui_particles;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.resources.Identifier;
import org.joml.Vector2f;

public record BlackKey(int x, int y,ImageColorAndRenderPipeline imageColorAndRenderPipeline) {
    public record ImageColorAndRenderPipeline(int size, ColorImage color,
                                              Identifier identifier,
                                              RenderPipeline renderPipeline,
                                              Vector2f position,
                                              Vector2f velocity,
                                              Vector2f acceleration, boolean rot){}
    public record ColorImage(int a,int r,int g ,int b ){}
}