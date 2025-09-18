package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.SwordOfTwelve;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.tbl.client.shader.LightSource;
import com.moonstone.tbl.client.shader.ShaderHelper;
import com.moonstone.tbl.client.shader.postprocessing.WorldShader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SwordOfTwelveRenderer <T extends SwordOfTwelve> extends EntityRenderer<T> {
    public SwordOfTwelveRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }
    @Override
    public boolean shouldRender(T p_114491_, Frustum p_114492_, double p_114493_, double p_114494_, double p_114495_) {
        return true;
    }

    @Override
    public void render(T entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        setT(poseStack, entity, bufferSource);

        if (ConfigClient.Client.Shader.get()) {
            MoonPost.renderEffectForNextTick(MoonStoneMod.POST);
        }
        if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
            WorldShader shader = ShaderHelper.INSTANCE.getWorldShader();
            ShaderHelper.INSTANCE.require();
            if (shader != null) {
                shader.addLight(new LightSource(entity.getX(), entity.getY(), entity.getZ(), 4, 0.3f, 0.2f, 2));
            }
        }
        poseStack.pushPose();
        poseStack.scale(3,3,3);
        poseStack.translate(0,0.33,0);
        poseStack.mulPose(Axis.ZP.rotationDegrees(225));
        renderSphere1(poseStack,bufferSource,111,0.135f);

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        Item nightmareAxe = Items.sword.get();
        ItemStack axeStack = nightmareAxe.getDefaultInstance();
        BakedModel model = itemRenderer.getModel(axeStack, Minecraft.getInstance().level, null, 0);
        itemRenderer.render(axeStack, ItemDisplayContext.NONE, false, poseStack, bufferSource, Minecraft.getInstance().getEntityRenderDispatcher().getPackedLightCoords(entity, 0.0F), OverlayTexture.NO_OVERLAY, model);
        poseStack.popPose();
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
    }
    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s) {
        int stacks = 4; // 垂直方向的分割数
        int slices = 4; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.Bluer);
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);


                vertexConsumer.vertex(matrices.last().pose(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
            }
        }
    }
    private void setT(PoseStack matrices,
                      T entity,
                      MultiBufferSource vertexConsumers){
        matrices.pushPose();
        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            Handler.renderBlack(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderType.lightning(),0.15f);

        }
        matrices.popPose();

    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T p_114482_) {
        return new ResourceLocation(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}



