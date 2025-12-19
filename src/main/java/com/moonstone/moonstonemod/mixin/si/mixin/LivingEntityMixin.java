package com.moonstone.moonstonemod.mixin.si.mixin;

import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract double getAttributeBaseValue(Holder<Attribute> p_248605_);

    @Shadow public abstract double getAttributeBaseValue(Attribute p_21173_);

    @Inject(at = @At("RETURN"), method = "getMaxHealth", cancellable = true)
    private void SeekingImmortalscreateAttributes(CallbackInfoReturnable<Float> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life.get())){
            cir.setReturnValue(30f);
        }
        if (living.hasEffect(Effects.life_apple.get())){
            cir.setReturnValue(30f);
        }
    }

    @Inject(at = @At("RETURN"), method = "getArmorValue", cancellable = true)
    private void getArmorValue(CallbackInfoReturnable<Integer> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life.get())){
            cir.setReturnValue(10);
        }
    }
    @Inject(at = @At("RETURN"), method = "getAttributeValue(Lnet/minecraft/core/Holder;)D", cancellable = true)
    private void getAttributeValue(Holder<Attribute> attribute, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life.get())){
            cir.setReturnValue(this.getAttributeBaseValue(attribute));
        }
    }
    @Inject(at = @At("RETURN"), method = "canBeAffected", cancellable = true)
    private void canBeAffected(MobEffectInstance effectInstance, CallbackInfoReturnable<Boolean> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (SIHandler.hascurio(living, Items.nightmare_base_black_eye.get())|| SIHandler.hascurio(living, Items.the_divine_fall_ring.get())) {
            if (effectInstance.getEffect()==(MobEffects.BLINDNESS)||effectInstance.getEffect() ==(MobEffects.DARKNESS)) {
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D", cancellable = true)
    private void getAttributeValue2(Attribute p_21134_, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life.get())){
            cir.setReturnValue(this.getAttributeBaseValue(p_21134_));
        }
    }
}
