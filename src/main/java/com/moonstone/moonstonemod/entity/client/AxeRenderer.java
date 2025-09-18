package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.axe;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AxeRenderer<T extends axe> extends EntityRenderer<T> {
    public AxeRenderer(EntityRendererProvider.Context p_173917_) {
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
                shader.addLight(new LightSource(entity.getX(), entity.getY(), entity.getZ(), 8, 1.5f, 0.2f, 1.5f));
            }
        }
        {
            Vec3 playerPos = entity.position();
            float range = 12;
            List<Player> entities =
                    entity.level().getEntitiesOfClass(Player.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));

            for (Player player : entities) {

                Vec3 entityPos = entity.position();
                Vec3 nearbyEntityPos = player.position();

                Vec3 end = nearbyEntityPos.subtract(entityPos);

                Handler.renderLine(poseStack, bufferSource, new Vec3(0, 2, 0), end, 1, MRender.out_nig, 0.0003f);
            }
        }
        {
            Vec3 playerPos = entity.position();
            float range =12;
            List<axe> entities =
                    entity.level().getEntitiesOfClass(axe.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));

            for (axe player : entities) {
                Vec3 entityPos = entity.position();
                Vec3 nearbyEntityPos = player.position();

                Vec3 end = nearbyEntityPos.subtract(entityPos);

                Handler.renderLine(poseStack, bufferSource, new Vec3(0, 2, 0), end, 1, MRender.out, 0.005f);
            }
        }
        poseStack.pushPose();
        poseStack.scale(3,3,3);
        poseStack.translate(0,0.5,0);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        Item nightmareAxe = Items.nightmare_axe.get();
        ItemStack axeStack = nightmareAxe.getDefaultInstance();
        BakedModel model = itemRenderer.getModel(axeStack, Minecraft.getInstance().level, null, 0);
        itemRenderer.render(axeStack, ItemDisplayContext.NONE, false, poseStack, bufferSource, Minecraft.getInstance().getEntityRenderDispatcher().getPackedLightCoords(entity, 0.0F), OverlayTexture.NO_OVERLAY, model);
        poseStack.popPose();
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
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

            Handler.renderBlack(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderType.lightning(),0.5f);

        }
        matrices.popPose();

    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T p_114482_) {
        return new ResourceLocation(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}


