package com.ytgld.moonstone.mixin;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(at = @At("RETURN"), method = "isInvulnerableToBase", cancellable = true)
    public void mhead(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_redemption_degenerate.get())) {
                if (source.is(DamageTypes.MAGIC) ||
                        source.is(DamageTypes.FALL) ||
                        source.is(DamageTypes.ON_FIRE) ||
                        source.is(DamageTypes.LAVA) ||
                        source.is(DamageTypes.IN_FIRE)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
