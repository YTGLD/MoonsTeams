package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract ItemStack getItemInHand(InteractionHand p_21121_);

    @Shadow protected abstract void setLivingEntityFlag(int p_21156_, boolean p_21157_);
    @Inject(at = @At("RETURN"), method = "getMaxHealth", cancellable = true)
    public void getMaxHealth(CallbackInfoReturnable<Float> cir) {
        if ((LivingEntity) (Object) this instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_head.get())){
                cir.setReturnValue(10f);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "canBeSeenByAnyone", cancellable = true)
    public void mhead(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.mhead.get())) {
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "canStandOnFluid", cancellable = true)
    public void moonstone$canStandOnFluid(FluidState p_204042_, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof Player player) {
            if (Handler.hascurio(player, Items.ambush.get())){
                cir.setReturnValue(true);
            }
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
            if (player.isSprinting()) {
                if (Handler.hascurio(player, Items.flygene.get())) {
                    player.moveRelative((float) (player.getSpeed() * Config.flygene_speed.get()), p_21280_);
                    if (!player.onGround()) {
                        player.moveRelative((float) (player.getSpeed() * Config.flygene_speed.get()), p_21280_);
                    }
                }
                if (Handler.hascurio(player, Items.bloodvirus.get())) {
                    player.moveRelative((float) (player.getSpeed() * Config.bloodvirus_speed.get()), p_21280_);
                }
                if (Handler.hascurio(player, Items.motor.get())) {
                    player.moveRelative((float) (player.getSpeed() * Config.motor_speed.get()), p_21280_);
                }
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
