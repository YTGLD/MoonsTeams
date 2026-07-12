package com.ytgld.moonstone.enttiy.zombie;

import com.ytgld.moonstone.enttiy.ExtendEntityLiving;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.component.SwingAnimation;

public abstract class MoonAbstractZombieRenderer<T extends ExtendEntityLiving, S extends ZombieRenderState, M extends ZombieModel<S>> extends HumanoidMobRenderer<T, S, M> {
    private static final Identifier ZOMBIE_LOCATION = Identifier.withDefaultNamespace("textures/entity/zombie/zombie.png");
    private static final Identifier BABY_ZOMBIE_LOCATION = Identifier.withDefaultNamespace("textures/entity/zombie/zombie_baby.png");

    protected MoonAbstractZombieRenderer(EntityRendererProvider.Context context, M model, M babyModel, ArmorModelSet<M> armorSet, ArmorModelSet<M> babyArmorSet) {
        super(context, model, babyModel, 0.5F);
        this.addLayer(new HumanoidArmorLayer(this, armorSet, babyArmorSet, context.getEquipmentRenderer()));
    }

    public Identifier getTextureLocation(S state) {
        return state.isBaby ? BABY_ZOMBIE_LOCATION : ZOMBIE_LOCATION;
    }

    public void extractRenderState(T entity, S state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isAggressive = entity.isAggressive();
        state.isConverting = entity.isInWater();
    }

    protected boolean isShaking(S state) {
        return super.isShaking(state) || state.isConverting;
    }

    protected HumanoidModel.ArmPose getArmPose(T mob, HumanoidArm arm) {
        SwingAnimation otherAnim = (SwingAnimation)mob.getItemHeldByArm(arm.getOpposite()).get(DataComponents.SWING_ANIMATION);
        return otherAnim != null && otherAnim.type() == SwingAnimationType.STAB ? HumanoidModel.ArmPose.SPEAR : super.getArmPose(mob, arm);
    }
}

