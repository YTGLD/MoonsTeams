package com.ytgld.moonstone.enttiy.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ytgld.moonstone.enttiy.AtSword;
import com.ytgld.moonstone.enttiy.AttackBlood;
import com.ytgld.moonstone.enttiy.FlySword;
import com.ytgld.moonstone.enttiy.state.AtSwordState;
import com.ytgld.moonstone.enttiy.state.AttackBloodsState;
import com.ytgld.moonstone.item.Items;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class AtSwordRender extends EntityRenderer<@NotNull AtSword, AtSwordState> {
    private final ItemModelResolver itemModelResolver;

    public AtSwordRender(EntityRendererProvider.Context context) {
        super(context);
        itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public boolean shouldRender(AtSword entity, Frustum culler, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void submit(AtSwordState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        AtSword entity = state.entity;
        poseStack.pushPose();
        poseStack.scale(3,3,3);
        poseStack.translate(0, 0.45 - entity.tickCount / 150F, 0);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float)(3 % 8) * 360.0F / 8.0F));
        if (!state.item.isEmpty()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            state.item.submit(poseStack, submitNodeCollector, 255, OverlayTexture.NO_OVERLAY, state.outlineColor);
        }
        poseStack.popPose();
    }

    @Override
    public void extractRenderState(AtSword entity, AtSwordState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.entity = entity;
        reusedState.partialTick = partialTick;
        this.itemModelResolver.updateForNonLiving(reusedState.item, Items.god_sword_.asItem().getDefaultInstance(), ItemDisplayContext.FIXED, entity);
    }
    @Override
    public AtSwordState createRenderState() {
        return new AtSwordState();
    }
}
