package com.ytgld.moonstone.enttiy.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import com.ytgld.moonstone.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SwordOfTwelveRender extends EntityRenderer<@NotNull SwordOfTwelve> {

    public SwordOfTwelveRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SwordOfTwelve swordOfTwelve) {
        return ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,"");
    }
    @Override
    public void render(@NotNull SwordOfTwelve p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        poseStack.scale(3,3,3);
        poseStack.translate(0, 0.45 - p_entity.tickCount / 150F, 0);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float)(3 % 8) * 360.0F / 8.0F));
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        Item nightmareAxe = Items.sword.get();
        ItemStack axeStack = nightmareAxe.getDefaultInstance();
        BakedModel model = itemRenderer.getModel(axeStack, Minecraft.getInstance().level, null, 0);
        itemRenderer.render(axeStack, ItemDisplayContext.NONE, false, poseStack, bufferSource, Minecraft.getInstance().getEntityRenderDispatcher().getPackedLightCoords(p_entity, 0.0F), OverlayTexture.NO_OVERLAY, model);

        poseStack.popPose();
    }
}
