package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Init.Items;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("RETURN"), method = "canStandOnFluid", cancellable = true)
    public void moonstone$canStandOnFluid(FluidState p_204042_, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.evilcandle.get())) {
                if (p_204042_.is(Fluids.LAVA)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "travel")
    public void moonstone$travel(Vec3 p_21280_, CallbackInfo ci) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.flygene.get())){
                player.moveRelative(player.getSpeed()*0.125f, p_21280_);
                if (!player.onGround()){
                    player.moveRelative(player.getSpeed()*0.225f, p_21280_);
                }
            }
            if (Handler.hascurio(player, Items.bloodvirus.get())) {
                player.moveRelative(player.getSpeed()*0.175f, p_21280_);
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
