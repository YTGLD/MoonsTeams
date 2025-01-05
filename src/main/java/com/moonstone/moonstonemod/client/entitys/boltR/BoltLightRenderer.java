package com.moonstone.moonstonemod.client.entitys.boltR;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.other.bolt;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;

public class BoltLightRenderer extends EntityRenderer<bolt> {
    public BoltLightRenderer(EntityRendererProvider.Context p_174286_) {
        super(p_174286_);
    }

    public void render(bolt p_115266_, float p_115267_, float p_115268_, PoseStack p_115269_, MultiBufferSource p_115270_, int p_115271_) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        RandomSource randomsource = RandomSource.create(p_115266_.seed);

        for(int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float)(randomsource.nextInt(11) - 5);
            f1 += (float)(randomsource.nextInt(11) - 5);
        }

        VertexConsumer vertexconsumer = p_115270_.getBuffer(RenderType.lightning());

        Matrix4f matrix4f = p_115269_.last().pose();

        for(int j = 0; j < 4; ++j) {
            RandomSource randomsource1 = RandomSource.create(p_115266_.seed);

            for(int k = 0; k < 3; ++k) {
                int l = 7;
                int i1 = 0;
                if (k > 0) {
                    l = 7 - k;
                }

                if (k > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for(int j1 = l; j1 >= i1; --j1) {
                    float f4 = f2;
                    float f5 = f3;
                    if (k == 0) {
                        f2 += (float)(randomsource1.nextInt(11) - 5);
                        f3 += (float)(randomsource1.nextInt(11) - 5);
                    } else {
                        f2 += (float)(randomsource1.nextInt(31) - 15);
                        f3 += (float)(randomsource1.nextInt(31) - 15);
                    }
                    float f10 = 0.1F + (float)j * 0.2F;
                    if (k == 0) {
                        f10 *= (float)j1 * 0.1F + 1.0F;
                    }

                    float f11 = 0.1F + (float)j * 0.2F;
                    if (k == 0) {
                        f11 *= ((float)j1 - 1.0F) * 0.1F + 1.0F;
                    }
                    float a  =100;
                    a-=p_115266_.timeB;
                    if (a>10){
                        a-= (float) (p_115266_.timeB*4);
                    }
                    if (a<0){
                        a=0;
                    }
                    a/=100;

                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, (float) (139 - p_115266_.timeB) /255, 0, (float) (139 + p_115266_.timeB) /255, f10, f11, false, false, true, false,a);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, (float) (139 - p_115266_.timeB) /255, 0, (float) (139 + p_115266_.timeB) /255, f10, f11, true, false, true, true,a);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, (float) (139 - p_115266_.timeB) /255, 0, (float) (139 + p_115266_.timeB) /255, f10, f11, true, true, false, true,a);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, (float) (139 - p_115266_.timeB) /255, 0, (float) (139 + p_115266_.timeB) /255, f10, f11, false, true, false, false,a);
                }
            }
        }

    }

    private static void quad(Matrix4f p_253966_, VertexConsumer p_115274_, float p_115275_, float p_115276_, int p_115277_, float p_115278_, float p_115279_, float p_115280_, float p_115281_, float p_115282_, float p_115283_, float p_115284_, boolean p_115285_, boolean p_115286_, boolean p_115287_, boolean p_115288_,float alp) {
        p_115274_.vertex(p_253966_, p_115275_ + (p_115285_ ? p_115284_ : -p_115284_), (float)(p_115277_ * 32), p_115276_ + (p_115286_ ? p_115284_ : -p_115284_)).color(p_115280_, p_115281_, p_115282_, alp).uv2(240).endVertex();
        p_115274_.vertex(p_253966_, p_115278_ + (p_115285_ ? p_115283_ : -p_115283_), (float)((p_115277_ + 1) * 32), p_115279_ + (p_115286_ ? p_115283_ : -p_115283_)).color(p_115280_, p_115281_, p_115282_, alp).uv2(240).endVertex();
        p_115274_.vertex(p_253966_, p_115278_ + (p_115287_ ? p_115283_ : -p_115283_), (float)((p_115277_ + 1) * 32), p_115279_ + (p_115288_ ? p_115283_ : -p_115283_)).color(p_115280_, p_115281_, p_115282_, alp).uv2(240).endVertex();
        p_115274_.vertex(p_253966_, p_115275_ + (p_115287_ ? p_115284_ : -p_115284_), (float)(p_115277_ * 32), p_115276_ + (p_115288_ ? p_115284_ : -p_115284_)).color(p_115280_, p_115281_, p_115282_, alp).uv2(240).endVertex();
    }

    public ResourceLocation getTextureLocation(bolt p_115264_) {
        return new ResourceLocation(MoonStoneMod.MODID,"textures/entity/bolt.png");
    }
}
