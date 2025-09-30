package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.item.seven_star;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("RETURN"), method = "canBeAffected", cancellable = true)
    private void canBeAffected(MobEffectInstance effectInstance, CallbackInfoReturnable<Boolean> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        seven_star.canHasEffect(living,effectInstance,cir);
    }
    @Inject(at = @At("RETURN"), method = "die")
    public void moonstone$travel(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof OwnableEntity entity) {
            if (entity.getOwner() instanceof Player player) {
                seven_star.petDie(player);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "travel")
    public void moonstone$travel(Vec3 vec3, CallbackInfo ci) {
        LivingEntity living = (LivingEntity) (Object) this;
        seven_star.speedADD(vec3,living);
    }
    @Inject(at = @At("RETURN"), method = "getJumpPower", cancellable = true)
    public void moonstone$travel(CallbackInfoReturnable<Float> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        seven_star.speedADD(cir,living);

    }
}
