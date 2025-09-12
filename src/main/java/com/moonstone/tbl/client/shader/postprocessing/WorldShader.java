package com.moonstone.tbl.client.shader.postprocessing;

import com.all.IPostChain;
import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.shaders.Uniform;
import com.moonstone.tbl.client.renderer.GLTextureObjectWrapper;
import com.moonstone.tbl.client.shader.LightSource;
import com.moonstone.tbl.common.MoonstoneTBL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorldShader extends PostChain implements AutoCloseable {;

	public static final ResourceLocation GAS_PARTICLE_TEXTURE = MoonstoneTBL.prefix("gas_particle");

	private final RenderTarget depthBuffer;

	private Matrix4f invertedModelviewProjectionMatrix;
    public static final int MAX_LIGHT_SOURCES_PER_PASS = 32;
	private final List<LightSource> lightSources = new ArrayList<>();
	public Vector3f cameraPos = new Vector3f();

	private static final int WORLD_INDEX = 0;


	@Nullable
	public Uniform invMVPUniform;
	@Nullable
	private final Uniform lightSourcePositionUniforms;
	@Nullable
	private final Uniform lightSourceColorUniforms;
	@Nullable
	private final Uniform lightSourceRadiusUniforms;
	@Nullable
	private final Uniform lightSourceAmountUniform;



    public WorldShader(TextureManager textureManager, ResourceManager resourceProvider, RenderTarget screenTarget) throws IOException, JsonSyntaxException {
		super(textureManager, resourceProvider, screenTarget, new ResourceLocation(MoonstoneTBL.ID, "shaders/post/world.json"));
		this.invMVPUniform = this.getUniform(WORLD_INDEX, "u_INVMVP");

		this.lightSourcePositionUniforms = this.getUniform(WORLD_INDEX, "u_lightSources_position");
		this.lightSourceColorUniforms = this.getUniform(WORLD_INDEX, "u_lightSources_color");
		this.lightSourceRadiusUniforms = this.getUniform(WORLD_INDEX, "u_lightSources_radius");
		this.lightSourceAmountUniform = this.getUniform(WORLD_INDEX, "u_lightSourcesAmount");

		this.depthBuffer = this.getTempTarget("s_diffuse_depth");

        Warp gasWarpEffect = new Warp(textureManager, resourceProvider, screenTarget);

		Minecraft.getInstance().getTextureManager().register(GAS_PARTICLE_TEXTURE, new GLTextureObjectWrapper(gasWarpEffect.gasTextureTarget.getColorTextureId()));
	}

	public void cleanUp() {
		clearLights();
	}


	private static final Comparator<LightSource> LIGHT_SOURCE_SORTER = (o1, o2) -> {
		double dx1 = o1.x - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x();
		double dy1 = o1.y - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y();
		double dz1 = o1.z - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z();
		double dx2 = o2.x - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x();
		double dy2 = o2.y - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y();
		double dz2 = o2.z - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z();
		double d1 = Math.sqrt(dx1 * dx1 + dy1 * dy1 + dz1 * dz1);
		double d2 = Math.sqrt(dx2 * dx2 + dy2 * dy2 + dz2 * dz2);
		if (d1 > d2) {
			return 1;
		} else if (d1 < d2) {
			return -1;
		}
		return 0;
	};

	public void uploadUniforms() {
        if (this.invMVPUniform != null) {
            this.invMVPUniform.set(invertedModelviewProjectionMatrix);
        }


        float[] positionBuff = new float[96];
		float[] colorBuff = new float[96];

		//Sort lights by distance
		this.lightSources.sort(LIGHT_SOURCE_SORTER);

		if (!lightSources.isEmpty()) {
			for (int i = 0; i < MAX_LIGHT_SOURCES_PER_PASS && i < lightSources.size(); i++) {
				positionBuff[(i*3)] = ((float) this.lightSources.get(i).x - this.cameraPos.x);
				positionBuff[(i*3)+1] = ((float) this.lightSources.get(i).y - this.cameraPos.y);
				positionBuff[(i*3)+2] = ((float) this.lightSources.get(i).z - this.cameraPos.z);
				colorBuff[(i*3)] = (this.lightSources.get(i).r);
				colorBuff[(i*3)+1] = (this.lightSources.get(i).g);
				colorBuff[(i*3)+2] = (this.lightSources.get(i).b);

                if (lightSourceRadiusUniforms != null) {
                    lightSourceRadiusUniforms.set(i, this.lightSources.get(i).radius);
                }
            }

            if (lightSourcePositionUniforms != null) {
                lightSourcePositionUniforms.set(positionBuff);
            }
            if (lightSourceColorUniforms != null) {
                lightSourceColorUniforms.set(colorBuff);
            }
        }

        if (lightSourceAmountUniform != null) {
            lightSourceAmountUniform.set(Math.min(this.lightSources.size(), 32));
        }
    }

	public RenderTarget getDepthBuffer() {
		return this.depthBuffer;
	}
	public void updateMatrices(final RenderLevelStageEvent event) {
		this.cameraPos = event.getCamera().getPosition().toVector3f();
        Matrix4f modelviewMatrix = event.getPoseStack().last().pose().transpose(new Matrix4f());
        Matrix4f projectionMatrix = event.getProjectionMatrix().transpose(new Matrix4f());
		this.invertedModelviewProjectionMatrix = new Matrix4f();

		Matrix4f MVP = modelviewMatrix.mul(projectionMatrix);
		MVP.invert(this.invertedModelviewProjectionMatrix);

		this.invertedModelviewProjectionMatrix = invertedModelviewProjectionMatrix.assume(0);
	}


	public void addLight(LightSource light) {
		this.lightSources.add(light);
	}

	public void clearLights() {
		this.lightSources.clear();
	}

	public Uniform getUniform(int index, String name) {
		if (this instanceof IPostChain iPostChain){
			return iPostChain.moonstone1_21_1$passes().get(index).getEffect().getUniform(name);
		}
		return null;
	}
}
