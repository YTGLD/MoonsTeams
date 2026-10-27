package com.ytgld.moonstone.enttiy.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import com.ytgld.moonstone.enttiy.state.SwordOfTwelveState;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.render.light.LightRenders;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
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
        HandlerClient.setShowRenderLight(true);
        SwordOfTwelve entity = state.entity;
        LightRenders.addLight(
                entity.position().x,entity.position().y,entity.position().z,
                32,
                0.2f,0.2f,2,
                1);

        poseStack.pushPose();
        poseStack.scale(4.5f,4.5f,4.5f);
        poseStack.translate(0, 0.5f, 0);
        poseStack.rotate(Axis.YP.rotation(entity.getId()));
        poseStack.rotate(Axis.XP.rotationDegrees(6.75f));
        poseStack.rotate(Axis.ZP.rotationDegrees((float)(3 % 8) * 360.0F / 8.0F + 6.75f));

        if (!state.item.isEmpty()) {
            state.item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        }
        poseStack.popPose();
    }

    @Override
    public void extractRenderState(SwordOfTwelve entity, SwordOfTwelveState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.entity = entity;
        reusedState.partialTick = partialTick;
        this.itemModelResolver.updateForNonLiving(reusedState.item, InitItems.sword.asItem().getDefaultInstance(), ItemDisplayContext.FIXED, entity);
    }

    @Override
    public boolean shouldRender(@NotNull SwordOfTwelve entity, Frustum culler, double camX, double camY, double camZ, float partialTicks) {
        return true;
    }

    @Override
    public SwordOfTwelveState createRenderState() {
        return new SwordOfTwelveState();
    }
}
