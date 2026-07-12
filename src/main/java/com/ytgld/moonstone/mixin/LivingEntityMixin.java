package com.ytgld.moonstone.mixin;

import com.ytgld.moonstone.effect.Effects;
import com.ytgld.moonstone.item.si.nightmare.fool.Apple;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract double getAttributeBaseValue(Holder<Attribute> p_248605_);

    @Inject(at = @At("RETURN"), method = "getMaxHealth", cancellable = true)
    private void SeekingImmortalscreateAttributes(CallbackInfoReturnable<Float> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life)) {
            cir.setReturnValue(30F);
        }
        if (living.hasEffect(Effects.life_apple)) {
            cir.setReturnValue((float) Apple.ConfigItem.intValue.getAsDouble());
        }
    }

    @Inject(at = @At("RETURN"), method = "getArmorValue", cancellable = true)
    private void getArmorValue(CallbackInfoReturnable<Integer> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life)) {
            cir.setReturnValue(10);
        }
    }

    @Inject(at = @At("RETURN"), method = "getAttributeValue(Lnet/minecraft/core/Holder;)D", cancellable = true)
    private void getAttributeValue(Holder<Attribute> attribute, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life)) {
            cir.setReturnValue(this.getAttributeBaseValue(attribute));
        }
    }

    @Inject(at = @At("RETURN"), method = "getAttributeValue", cancellable = true)
    private void getAttributeValue2(Holder<Attribute> attribute, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living.hasEffect(Effects.life)) {
            cir.setReturnValue(this.getAttributeBaseValue(attribute));
        }
    }
}
