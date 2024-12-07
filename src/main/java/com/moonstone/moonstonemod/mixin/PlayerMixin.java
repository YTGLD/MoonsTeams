package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.init.AttReg;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Player.class)
public class PlayerMixin {
    @Inject(at = @At("RETURN"), method = "createAttributes", cancellable = true)
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir){
        cir.setReturnValue(cir.getReturnValue()
                .add(AttReg.heal.get(),1)

        );
    }
}
