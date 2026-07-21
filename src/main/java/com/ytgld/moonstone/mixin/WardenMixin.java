package com.ytgld.moonstone.mixin;

import com.ytgld.moonstone.item.ms.nanodoom.TheFruit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public class WardenMixin {
    @Inject(at = @At("RETURN"), method = "canTargetEntity", cancellable = true)
    public void canTargetEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity livingEntity) {
            TheFruit.setNotTarget(livingEntity,cir);
        }
    }
}
