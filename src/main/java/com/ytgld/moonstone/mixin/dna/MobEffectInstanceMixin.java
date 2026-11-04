package com.ytgld.moonstone.mixin.dna;

import com.ytgld.moonstone.other.IMobEffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin implements IMobEffectInstance {
    @Shadow
    private int duration;

    @Override
    public void moonstoneGeneSequence$setTime(int time) {
        this.duration = time;
    }
}
