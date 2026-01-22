package com.moonstone.moonstonemod.mixin.other;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.core.Holder;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin  extends Entity implements Attackable, net.minecraftforge.common.extensions.IForgeLivingEntity {
    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow public abstract boolean hasEffect(MobEffect p_21024_);

    @Shadow @Nullable public abstract MobEffectInstance getEffect(MobEffect p_21125_);


    @Shadow @Final private AttributeMap attributes;

    @Inject(at = @At("RETURN"), method = "getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D", cancellable = true)
    private void getAttributeValue(Attribute attribute, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.debilitating.get())) {
            if (attribute == Attributes.MAX_HEALTH){
                AttributeInstance at = attributes.getInstance(Attributes.MAX_HEALTH);
                if (at!=null) {
                    for (AttributeModifier attributeModifier : at.getModifiers()) {
                        if (attributeModifier!=null) {
                            cir.setReturnValue(attribute.getDefaultValue()+attributeModifier.getAmount()*1.5f);
                        }
                    }
                }
            }
        }

    }
    @Inject(at = @At("RETURN"), method = "canStandOnFluid", cancellable = true)
    public void canStandOnFluid(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (player.getPersistentData().getBoolean("canStandOnFluidTrue")){
                cir.setReturnValue(true);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "travel")
    public void moonstone$travel(Vec3 p_21280_, CallbackInfo ci) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            float speed = (float) player.getAttributeValue(AttReg.speed.get());
            speed-=1f;
            if (player.isSprinting()) {
                player.moveRelative(player.getSpeed()*speed,p_21280_);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "getJumpPower", cancellable = true)
    public void getJumpPower(CallbackInfoReturnable<Float> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.quadriceps.get())) {
                cir.setReturnValue(cir.getReturnValue() * 1.5f);
            }
        }
    }
}
