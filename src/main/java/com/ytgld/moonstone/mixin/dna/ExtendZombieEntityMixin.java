package com.ytgld.moonstone.mixin.dna;

import com.ytgld.moonstone.enttiy.ExtendZombieEntity;
import com.ytgld.moonstone.event.DNAHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExtendZombieEntity.class)
public class ExtendZombieEntityMixin {

    @Inject(
            at = {@At("RETURN")},
            method = {"tick"}
    )
    public void tick(CallbackInfo ci) {
        ExtendZombieEntity entity = (ExtendZombieEntity)(Object)this;
        DNAHandler.dieOfZombie(entity);
    }

}
