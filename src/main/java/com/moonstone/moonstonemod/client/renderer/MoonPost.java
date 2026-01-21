package com.moonstone.moonstonemod.client.renderer;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//这个地方我参考了citadel模组，
//https://github.com/AlexModGuy/Citadel
public class MoonPost {

    private static final List<ResourceLocation> registry = new ArrayList<>();

    private static final Map<ResourceLocation, PostEffect> postEffects = new HashMap<>();

    public static void clear() {
        for(PostEffect postEffect : postEffects.values()){
            postEffect.close();
        }
        postEffects.clear();
    }

    public static void onInitializeOutline(Minecraft minecraft) {
        registry.add(MoonStoneMod.POST);
        clear();
        for (ResourceLocation resourceLocation : registry) {
            PostChain postChain ;
            RenderTarget renderTarget;
            try {
                postChain = new PostChain(minecraft.getTextureManager(), minecraft.getResourceManager(), minecraft.getMainRenderTarget(), resourceLocation);
                postChain.resize(minecraft.getMainRenderTarget().width,minecraft.getMainRenderTarget().height);
                renderTarget = postChain.getTempTarget("final");

            } catch (IOException | JsonSyntaxException ioexception) {

                MoonStoneMod.LOGGER.error(String.valueOf(ioexception));

                postChain = null;
                renderTarget = null;

            }
            postEffects.put(resourceLocation, new PostEffect(postChain, renderTarget, false));
        }
    }

    public static void resize(int x, int y) {
        for (PostEffect postEffect : postEffects.values()) {
            postEffect.resize(x, y);
        }
    }

    public static RenderTarget getRenderTargetFor(ResourceLocation resourceLocation) {
        PostEffect effect = postEffects.get(resourceLocation);
        return effect == null ? null : effect.getRenderTarget();
    }

    public static void renderEffectForNextTick(ResourceLocation resourceLocation) {
        if (!ConfigClient.Client.Shader.get()){
            return;
        }
        PostEffect effect = postEffects.get(resourceLocation);
        if (effect != null) {
            effect.setEnabled(true);
        }
    }

    public static void blitEffects(Minecraft minecraft) {
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        for (PostEffect postEffect : postEffects.values()) {
            if (postEffect.postChain != null && postEffect.isEnabled()) {
                postEffect.getRenderTarget().blitToScreen(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight(), false);
                postEffect.getRenderTarget().clear(Minecraft.ON_OSX);
                minecraft.getMainRenderTarget().bindWrite(false);
                postEffect.setEnabled(false);
            }
        }
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    public static void clearAndBindWrite(RenderTarget mainTarget) {
        for (PostEffect postEffect : postEffects.values()) {
            if (postEffect.isEnabled() && postEffect.postChain != null) {
                postEffect.getRenderTarget().clear(Minecraft.ON_OSX);
                mainTarget.bindWrite(false);
            }
        }
    }

    public static void processEffects(RenderTarget mainTarget) {
        for (PostEffect postEffect : postEffects.values()) {
            if (postEffect.isEnabled() && postEffect.postChain != null) {
                postEffect.postChain.process(Minecraft.getInstance().getDeltaFrameTime());
                mainTarget.bindWrite(false);
            }
        }
    }

    private static class PostEffect {
        private final PostChain postChain;
        private final RenderTarget renderTarget;
        private boolean enabled;

        public PostEffect(PostChain postChain, RenderTarget renderTarget, boolean enabled) {
            this.postChain = postChain;
            this.renderTarget = renderTarget;
            this.enabled = enabled;
        }

        public RenderTarget getRenderTarget() {
            return renderTarget;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void close() {
            if (postChain != null) {
                postChain.close();
            }
        }

        public void resize(int x, int y) {
            if (postChain != null) {
                postChain.resize(x, y);
            }
        }
    }
}
