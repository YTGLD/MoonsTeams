package com.ytgld.moonstone.enttiy.render;

import com.ytgld.moonstone.HandlerClient;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.CellGiant;
import com.ytgld.moonstone.enttiy.state.CellGiantStart;
import com.ytgld.moonstone.enttiy.zombie.CellGiantModel;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class CellGiantRender  extends MobRenderer<CellGiant, CellGiantStart, CellGiantModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/cell_giant.png");
    private static final Identifier HEART_TEXTURE = Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/hearth.png");
    private static final Identifier PULSATING_SPOTS_TEXTURE_1 = Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/cell_giant_spots_1.png");
    private static final Identifier PULSATING_SPOTS_TEXTURE_2 = Identifier.fromNamespaceAndPath(Moonstone.MODID,"textures/entity/cell_giant_spots_2.png");

    public CellGiantRender(EntityRendererProvider.Context context) {
        super(context, new CellGiantModel(context.bakeLayer(ModelLayers.WARDEN)), 0.9F);


        CellGiantModel bioluminescentModel = new CellGiantModel(context.bakeLayer(ModelLayers.WARDEN_BIOLUMINESCENT));
        CellGiantModel pulsatingSpotsModel = new CellGiantModel(context.bakeLayer(ModelLayers.WARDEN_PULSATING_SPOTS));
        CellGiantModel tendrilsModel = new CellGiantModel(context.bakeLayer(ModelLayers.WARDEN_TENDRILS));
        CellGiantModel heartModel = new CellGiantModel(context.bakeLayer(ModelLayers.WARDEN_HEART));

        this.addLayer(
                new LivingEntityEmissiveLayer<>(
                        this,
                        renderState -> PULSATING_SPOTS_TEXTURE_1,
                        (warden, ageInTicks) -> Math.max(0.0F, Mth.cos(ageInTicks * 0.045F) * 0.25F),
                        pulsatingSpotsModel,
                        RenderTypes::entityTranslucentEmissive,
                        false
                )
        );
        this.addLayer(
                new LivingEntityEmissiveLayer<>(
                        this,
                        renderState -> PULSATING_SPOTS_TEXTURE_2,
                        (warden, ageInTicks) -> Math.max(0.0F, Mth.cos(ageInTicks * 0.045F + (float) Math.PI) * 0.25F),
                        pulsatingSpotsModel,
                        RenderTypes::entityTranslucentEmissive,
                        false
                )
        );
        this.addLayer(
                new LivingEntityEmissiveLayer<>(
                        this, renderState -> TEXTURE, (warden, ageInTicks) -> warden.tendrilAnimation, tendrilsModel, RenderTypes::entityTranslucentEmissive, false
                )
        );
        this.addLayer(
                new LivingEntityEmissiveLayer<>(
                        this, renderState -> HEART_TEXTURE, (warden, ageInTicks) -> warden.heartAnimation, heartModel,RenderTypes::entityTranslucentEmissive, false
                )
        );
    }

    public Identifier getTextureLocation(CellGiantStart state) {
        return TEXTURE;
    }

    public CellGiantStart createRenderState() {
        return new CellGiantStart();
    }

    public void extractRenderState(CellGiant entity, CellGiantStart state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.tendrilAnimation = entity.getTendrilAnimation(partialTicks);
        state.heartAnimation = entity.getHeartAnimation(partialTicks);
        state.roarAnimationState.copyFrom(entity.roarAnimationState);
        state.sniffAnimationState.copyFrom(entity.sniffAnimationState);
        state.emergeAnimationState.copyFrom(entity.emergeAnimationState);
        state.diggingAnimationState.copyFrom(entity.diggingAnimationState);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.sonicBoomAnimationState.copyFrom(entity.sonicBoomAnimationState);
    }
}
