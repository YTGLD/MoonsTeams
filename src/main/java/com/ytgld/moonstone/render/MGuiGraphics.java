package com.ytgld.moonstone.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.function.Supplier;

public class MGuiGraphics {

    public static void blit(GuiGraphics guiGraphics, ResourceLocation p_282034_, float p_283671_, float p_282377_, float p_282058_, float p_281939_, float p_282285_, float p_283199_, float p_282186_, float p_282322_, float p_282481_, float p_281887_, float r, float g, float b, float a) {
        blit(guiGraphics, p_282034_, p_283671_, p_283671_ + p_282058_, p_282377_, p_282377_ + p_281939_, 0, p_282186_, p_282322_, p_282285_, p_283199_, p_282481_, p_281887_, r, g, b, a);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation p_283272_, float p_283605_, float p_281879_, float p_282809_, float p_282942_, float p_281922_, float p_282385_, float p_282596_, float p_281699_, float r, float g, float b, float a) {
        blit(guiGraphics, p_283272_, p_283605_, p_281879_, p_281922_, p_282385_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_, r, g, b, a);
    }

    private static void blit(GuiGraphics guiGraphics, ResourceLocation p_282639_, float p_282732_, float p_283541_, float p_281760_, float p_283298_, float p_283429_, float p_282193_, float p_281980_, float p_282660_, float p_281522_, float p_282315_, float p_281436_, float r, float g, float b, float a) {
        blit(guiGraphics, p_282639_, p_282732_, p_283541_, p_281760_, p_283298_, p_283429_, (p_282660_ + 0.0F) / (float) p_282315_, (p_282660_ + (float) p_282193_) / (float) p_282315_, (p_281522_ + 0.0F) / (float) p_281436_, (p_281522_ + (float) p_281980_) / (float) p_281436_, r, g, b, a);
    }

    private static void blit(GuiGraphics guiGraphics, ResourceLocation texture, float startX, float endX, float startY, float endY, float zLevel, float u0, float u1, float v0, float v1, float r, float g, float b, float a) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(CIStateShardsHasBlack::getHasBlock);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferbuilder.addVertex(matrix4f, (float) startX, (float) startY, (float) zLevel).setColor(r, g, b, a).setUv(u0, v0);
        bufferbuilder.addVertex(matrix4f, (float) startX, (float) endY, (float) zLevel).setColor(r, g, b, a).setUv(u0, v1);
        bufferbuilder.addVertex(matrix4f, (float) endX, (float) endY, (float) zLevel).setColor(r, g, b, a).setUv(u1, v1);
        bufferbuilder.addVertex(matrix4f, (float) endX, (float) startY, (float) zLevel).setColor(r, g, b, a).setUv(u1, v0);
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }
    public static class GUI{

        private final Supplier<ShaderInstance> shaderSupplier;
        private final boolean isLight;

        public GUI(Supplier<ShaderInstance> shaderSupplier, boolean isLight ){
            this.shaderSupplier = shaderSupplier;
            this.isLight = isLight;
        }
        public void blit(GuiGraphics guiGraphics, ResourceLocation p_282034_, float p_283671_, float p_282377_, float p_282058_, float p_281939_, float p_282285_, float p_283199_, float p_282186_, float p_282322_, float p_282481_, float p_281887_, float r, float g, float b, float a) {
            blit(guiGraphics, p_282034_, p_283671_, p_283671_ + p_282058_, p_282377_, p_282377_ + p_281939_, 0, p_282186_, p_282322_, p_282285_, p_283199_, p_282481_, p_281887_, r, g, b, a);
        }

        public void blit(GuiGraphics guiGraphics, ResourceLocation p_283272_, float p_283605_, float p_281879_, float p_282809_, float p_282942_, float p_281922_, float p_282385_, float p_282596_, float p_281699_, float r, float g, float b, float a) {
            blit(guiGraphics, p_283272_, p_283605_, p_281879_, p_281922_, p_282385_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_, r, g, b, a);
        }

        private void blit(GuiGraphics guiGraphics, ResourceLocation p_282639_, float p_282732_, float p_283541_, float p_281760_, float p_283298_, float p_283429_, float p_282193_, float p_281980_, float p_282660_, float p_281522_, float p_282315_, float p_281436_, float r, float g, float b, float a) {
            blit(guiGraphics, p_282639_, p_282732_, p_283541_, p_281760_, p_283298_, p_283429_, (p_282660_ + 0.0F) / (float) p_282315_, (p_282660_ + (float) p_282193_) / (float) p_282315_, (p_281522_ + 0.0F) / (float) p_281436_, (p_281522_ + (float) p_281980_) / (float) p_281436_, r, g, b, a);
        }

        private void blit(
                GuiGraphics guiGraphics,
                ResourceLocation texture,
                float startX,float endX,
                float startY,float endY,
                float zLevel,
                float u0,float u1,
                float v0,float v1,
                float r,float g,float b,float a
        ){

            try {

                RenderSystem.setShaderTexture(0, texture);
                RenderSystem.setShader(shaderSupplier);

                RenderSystem.enableBlend();

                if(isLight){
                    RenderSystem.blendFuncSeparate(
                            GlStateManager.SourceFactor.SRC_ALPHA,
                            GlStateManager.DestFactor.ONE,
                            GlStateManager.SourceFactor.ONE,
                            GlStateManager.DestFactor.ZERO
                    );
                }else{
                    RenderSystem.defaultBlendFunc();
                }


                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);


                Matrix4f matrix =
                        guiGraphics.pose().last().pose();


                BufferBuilder buffer =
                        Tesselator.getInstance()
                                .begin(
                                        VertexFormat.Mode.QUADS,
                                        DefaultVertexFormat.POSITION_TEX_COLOR
                                );


                buffer.addVertex(matrix,startX,startY,zLevel)
                        .setUv(u0,v0)
                        .setColor(r,g,b,a);

                buffer.addVertex(matrix,startX,endY,zLevel)
                        .setUv(u0,v1)
                        .setColor(r,g,b,a);

                buffer.addVertex(matrix,endX,endY,zLevel)
                        .setUv(u1,v1)
                        .setColor(r,g,b,a);

                buffer.addVertex(matrix,endX,startY,zLevel)
                        .setUv(u1,v0)
                        .setColor(r,g,b,a);


                BufferUploader.drawWithShader(buffer.buildOrThrow());


            }finally{

                RenderSystem.colorMask(true,true,true,true);

                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();

                RenderSystem.depthFunc(GL11.GL_LEQUAL);

                RenderSystem.disableBlend();

                RenderSystem.defaultBlendFunc();

                RenderSystem.setShaderColor(1,1,1,1);
            }
        }

        public void blit(GuiGraphics guiGraphics, ResourceLocation p_282034_, float p_283671_, float p_282377_, float p_282058_, float p_281939_, float p_282285_, float p_283199_, float p_282186_, float p_282322_, float p_282481_, float p_281887_, int c) {
            blit(guiGraphics, p_282034_, p_283671_, p_283671_ + p_282058_, p_282377_, p_282377_ + p_281939_, 0, p_282186_, p_282322_, p_282285_, p_283199_, p_282481_, p_281887_, c);
        }

        public void blit(GuiGraphics guiGraphics, ResourceLocation p_283272_, float p_283605_, float p_281879_, float p_282809_, float p_282942_, float p_281922_, float p_282385_, float p_282596_, float p_281699_, int c) {
            blit(guiGraphics, p_283272_, p_283605_, p_281879_, p_281922_, p_282385_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_, c);
        }

        private void blit(GuiGraphics guiGraphics, ResourceLocation p_282639_, float p_282732_, float p_283541_, float p_281760_, float p_283298_, float p_283429_, float p_282193_, float p_281980_, float p_282660_, float p_281522_, float p_282315_, float p_281436_, int c) {
            blit(guiGraphics, p_282639_, p_282732_, p_283541_, p_281760_, p_283298_, p_283429_, (p_282660_ + 0.0F) / (float) p_282315_, (p_282660_ + (float) p_282193_) / (float) p_282315_, (p_281522_ + 0.0F) / (float) p_281436_, (p_281522_ + (float) p_281980_) / (float) p_281436_, c);
        }


        private void blit(
                GuiGraphics guiGraphics,
                ResourceLocation texture,
                float startX,float endX,
                float startY,float endY,
                float zLevel,
                float u0,float u1,
                float v0,float v1,
                int color
        ){

            try {

                RenderSystem.setShaderTexture(0, texture);
                RenderSystem.setShader(shaderSupplier);

                RenderSystem.enableBlend();

                if(isLight){
                    RenderSystem.blendFuncSeparate(
                            GlStateManager.SourceFactor.SRC_ALPHA,
                            GlStateManager.DestFactor.ONE,
                            GlStateManager.SourceFactor.ONE,
                            GlStateManager.DestFactor.ZERO
                    );
                }else{
                    RenderSystem.defaultBlendFunc();
                }


                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);


                Matrix4f matrix =
                        guiGraphics.pose().last().pose();


                BufferBuilder buffer =
                        Tesselator.getInstance()
                                .begin(
                                        VertexFormat.Mode.QUADS,
                                        DefaultVertexFormat.POSITION_TEX_COLOR
                                );


                buffer.addVertex(matrix,startX,startY,zLevel)
                        .setUv(u0,v0)
                        .setColor(color);

                buffer.addVertex(matrix,startX,endY,zLevel)
                        .setUv(u0,v1)
                        .setColor(color);

                buffer.addVertex(matrix,endX,endY,zLevel)
                        .setUv(u1,v1)
                        .setColor(color);

                buffer.addVertex(matrix,endX,startY,zLevel)
                        .setUv(u1,v0)
                        .setColor(color);


                BufferUploader.drawWithShader(buffer.buildOrThrow());


            }finally{

                RenderSystem.colorMask(true,true,true,true);

                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();

                RenderSystem.depthFunc(GL11.GL_LEQUAL);

                RenderSystem.disableBlend();

                RenderSystem.defaultBlendFunc();

                RenderSystem.setShaderColor(1,1,1,1);
            }
        }
    }

}
