package com.moonstone.moonstonemod.mixin;

import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public abstract class MobEffectInstanceMixin {

    @Shadow
    @Final
    private MobEffect effect;

    @Shadow
    public abstract boolean isInfiniteDuration();

    @Inject(at = @At("RETURN"), method = "getDuration", cancellable = true)
    private void canBeAffected(CallbackInfoReturnable<Integer> cir){
        if (this.effect == Effects.invulnerable.get()) {
            if (cir.getReturnValue() > 200 || this.isInfiniteDuration()) {
                cir.setReturnValue(200);
            }
        }

    }

}
