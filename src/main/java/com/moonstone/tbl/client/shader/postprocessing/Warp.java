package com.moonstone.tbl.client.shader.postprocessing;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.moonstone.tbl.common.MoonstoneTBL;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceProvider;

import java.io.IOException;

public class Warp extends PostChain implements AutoCloseable {
	public RenderTarget BaseTexture;
	public RenderTarget gasTextureTarget;
	public Warp(TextureManager textureManager, ResourceManager resourceProvider, RenderTarget screenTarget) throws IOException, JsonSyntaxException {
		super(textureManager, resourceProvider, screenTarget, new ResourceLocation(MoonstoneTBL.ID, "shaders/post/warp.json"));
		this.BaseTexture = this.getTempTarget("input");
		this.BaseTexture.setClearColor(1,1,1,1);
		this.BaseTexture.clear(false);
		this.gasTextureTarget = this.getTempTarget("output");
	}

}