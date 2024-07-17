package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.flysword;
import com.moonstone.moonstonemod.entity.suddenrain;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(at = @At("RETURN"), method = "isInvulnerableTo", cancellable = true)
    public void getMaxHealth(DamageSource p_36249_, CallbackInfoReturnable<Boolean> cir) {
        Player player =(Player) (Object) this;
        {
            if (Handler.hascurio(player, Items.doomeye.get())||Handler.hascurio(player, Items.doomswoud.get())||Handler.hascurio(player, Items.sword_amout.get())){
                if (p_36249_.getEntity()!=null) {
                    if (p_36249_.getEntity() instanceof suddenrain ||
                            p_36249_.getEntity().is(player) ||
                            p_36249_.getEntity() instanceof flysword) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}
