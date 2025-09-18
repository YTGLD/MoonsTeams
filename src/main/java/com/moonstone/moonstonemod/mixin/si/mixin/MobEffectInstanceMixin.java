package com.moonstone.moonstonemod.mixin.si.mixin;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mixin(MobEffectInstance.class)
public abstract class MobEffectInstanceMixin {
    @Shadow private int duration;


    @Shadow public abstract MobEffect getEffect();

    @Inject(at = @At("RETURN"), method = "tick")
    public void tick(LivingEntity entity, Runnable onExpirationRunnable, CallbackInfoReturnable<Boolean> cir){
        if (entity instanceof Player player) {
            if (Handler.hascurio(player, Items.ring.get())) {
                if (duration==1){
                    if (this.getEffect().isBeneficial()) {
                        duration--;
                        player.heal(4);
                    }else {
                        duration--;
                        player.heal(player.getMaxHealth()*0.1f);
                    }
                }
                List<Integer> integers = new ArrayList<>();
                int a = 0;
                Collection<MobEffectInstance> collection = player.getActiveEffects();
                if (!collection.isEmpty()) {
                    for (MobEffectInstance effectInstance : collection) {
                        if (effectInstance.getAmplifier() >= 2) {
                            integers.add(1);
                        }
                    }
                }
                for (int ignored : integers){
                    a++;
                }
                int f = 2 * a;
                if (f > 18) {
                    f = 19;
                }


                if (player.tickCount % 20 - f == 0){
                    duration-=20;
                }
            }
        }
    }
}
