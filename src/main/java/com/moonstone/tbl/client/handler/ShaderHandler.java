package com.moonstone.tbl.client.handler;

import com.all.ILevelRender;
import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.systems.RenderSystem;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.tbl.client.shader.ShaderHelper;
import com.moonstone.tbl.client.shader.postprocessing.DiffBlitDepth;
import com.moonstone.tbl.common.MoonstoneTBL;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.event.RenderLevelStageEvent;

import javax.annotation.Nullable;
import java.io.IOException;

import static net.minecraftforge.client.event.RenderLevelStageEvent.Stage.AFTER_WEATHER;


/**
 *	Handles shader events such as registration, render stages and resizing.<br>
 *	Also removes the translucent element batch from our worldShader depth buffer during fast & fancy rendering modes.
 */
public class ShaderHandler {

    public static final ResourceLocation DIFFBLITDEPTH_LOCAL = new ResourceLocation(MoonstoneTBL.ID, "shaders/post/diffblitdepth.json");

	@Nullable
	public static DiffBlitDepth diffBlitDepth;
    public static void loadWorldShader(ResourceManager resourceProvider) {
        try {
            diffBlitDepth = new DiffBlitDepth(Minecraft.getInstance().getTextureManager(), resourceProvider, Minecraft.getInstance().getMainRenderTarget());
		} catch (IOException ioexception) {
            MoonStoneMod.LOGGER.warn("Failed to load shader: {}", DIFFBLITDEPTH_LOCAL, ioexception);
        } catch (JsonSyntaxException jsonsyntaxexception) {
			MoonStoneMod.LOGGER.warn("Failed to parse shader: {}", DIFFBLITDEPTH_LOCAL, jsonsyntaxexception);
        }
    }

	/**
	 * - Copies depth buffer data before renderItemInHand clears main depth buffer (only on Fantastic graphics).<br>
	 * - Collects viewport matrices for WorldShader.
	 */
	public static void onRenderWeather(final RenderLevelStageEvent event) {

		// Fetch depth and matrix data before debug elements render
		if (event.getStage() == AFTER_WEATHER) {
			if (Minecraft.getInstance().levelRenderer instanceof ILevelRender levelRender) {
				// Fabulous graphics depth collector
				if (levelRender.moonstone1_21_1$transparencyChain() != null) {
                    if (ShaderHelper.INSTANCE.getWorldShader() != null) {
                        ShaderHelper.INSTANCE.getWorldShader().getDepthBuffer().copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
                    }
                    Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
				}

                if (ShaderHelper.INSTANCE.getWorldShader() != null) {
                    ShaderHelper.INSTANCE.getWorldShader().updateMatrices(event);
                }
            }
		}
	}

    /**
     * Renders the main shader to the screen.
     */
    public static void renderWorldShader(float partialTick) {
		if (ShaderHelper.INSTANCE.canUseShaders()) {
            if (ShaderHelper.INSTANCE.getWorldShader() != null) {
                ShaderHelper.INSTANCE.getWorldShader().uploadUniforms();
				ShaderHelper.INSTANCE.getWorldShader().process(partialTick);
				ShaderHelper.INSTANCE.getWorldShader().cleanUp();
			}
		}
	}

	/**
	 * Composite new depth buffer for worldShader
	 */
	public static void onPreRenderDebug() {
		// Fast & Fancy only
		if (Minecraft.getInstance().levelRenderer instanceof ILevelRender levelRender) {
			if (!ShaderHelper.INSTANCE.canUseShaders() || levelRender.moonstone1_21_1$transparencyChain() != null)
				return;
		}
		// Composite changes after translucent batch on top of base buffer
        if (ShaderHandler.diffBlitDepth != null) {
            ShaderHandler.diffBlitDepth.AfterTarget.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
        }
        RenderSystem.enableDepthTest();
		if (ShaderHandler.diffBlitDepth != null) {
			ShaderHandler.diffBlitDepth.process(Minecraft.getInstance().getDeltaFrameTime());
		}
		// Set worldShader depth to diffBlitDepth output
        if (ShaderHelper.INSTANCE.getWorldShader() != null) {
			if (ShaderHandler.diffBlitDepth != null) {
				ShaderHelper.INSTANCE.getWorldShader().getDepthBuffer().copyDepthFrom(ShaderHandler.diffBlitDepth.Output);
			}
		}
        // Clean up
		Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
	}

	/**
	 * Sets base buffer for cutting out translucent render batch
	 */
	public static void onPreTranslucentBatch() {
		// Set base buffer and cleanup
        if (ShaderHandler.diffBlitDepth != null) {
            ShaderHandler.diffBlitDepth.Base.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
        }
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
	}

	/**
	 * Sets before buffer for cutting out translucent render batch
	 */
	public static void onPostTranslucentBatch() {
        if (ShaderHandler.diffBlitDepth != null) {
            ShaderHandler.diffBlitDepth.BeforeTarget.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
        }
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
	}
	public static void resize(int width, int height) {
		// Depth differential blit targets
		if (ShaderHandler.diffBlitDepth != null) {
			ShaderHandler.diffBlitDepth.resize(width, height);
		}

		// World shader targets
		if (ShaderHelper.INSTANCE.getWorldShader() != null) {
			ShaderHelper.INSTANCE.getWorldShader().resize(width, height);
		}
	}
}
