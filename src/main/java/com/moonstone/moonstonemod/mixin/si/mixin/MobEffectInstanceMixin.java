package com.moonstone.moonstonemod.mixin.si.mixin;

import com.ytgld.seeking_immortals.SIHandler;
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

    @Shadow
    public abstract boolean isInfiniteDuration();

}
