package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(at = @At("RETURN"), method = "isInvulnerableTo", cancellable = true)
    public void mhead(DamageSource p_20122_, CallbackInfoReturnable<Boolean> cir) {
       if (p_20122_.getEntity() instanceof Player player){
           if (Handler.hascurio(player, Items.mhead.get())){
               cir.setReturnValue(true);
           }
       }
    }
}
