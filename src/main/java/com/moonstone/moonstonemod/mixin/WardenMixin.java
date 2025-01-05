package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.nanodoom.thefruit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public abstract class WardenMixin {
    @Shadow public abstract void setAttackTarget(LivingEntity p_219460_);


    @Inject(at = @At("RETURN"), method = "canTargetEntity", cancellable = true)
    public void moonstone$increaseAngerAt(Entity p_219386_, CallbackInfoReturnable<Boolean> cir) {
        Warden mob = (Warden) (Object) this;
        Entity living = p_219386_;
        if (living instanceof Player player) {
            if (!Handler.hascurio(player, Items.nightmareeye.get())) {
                if (player.getPersistentData().getBoolean(thefruit.thefruit)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }

}
