package com.ytgld.moonstone.enttiy.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import com.ytgld.moonstone.enttiy.state.SwordOfTwelveState;
import com.ytgld.moonstone.item.Items;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class SwordOfTwelveRender extends EntityRenderer<@NotNull SwordOfTwelve, SwordOfTwelveState> {
    private final ItemModelResolver itemModelResolver;

    public SwordOfTwelveRender(EntityRendererProvider.Context context) {
        super(context);
        itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public void submit(SwordOfTwelveState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        SwordOfTwelve entity = state.entity;
        poseStack.pushPose();
        poseStack.scale(3,3,3);
        poseStack.translate(0, 0.45 - entity.tickCount / 150F, 0);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float)(3 % 8) * 360.0F / 8.0F));
        if (!state.item.isEmpty()) {
            state.item.submit(poseStack, submitNodeCollector, 255, OverlayTexture.NO_OVERLAY, state.outlineColor);
        }
        poseStack.popPose();
    }

    @Override
    public void extractRenderState(SwordOfTwelve entity, SwordOfTwelveState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.entity = entity;
        reusedState.partialTick = partialTick;
        this.itemModelResolver.updateForNonLiving(reusedState.item, Items.sword.asItem().getDefaultInstance(), ItemDisplayContext.FIXED, entity);
    }
    @Override
    public SwordOfTwelveState createRenderState() {
        return new SwordOfTwelveState();
    }
}
