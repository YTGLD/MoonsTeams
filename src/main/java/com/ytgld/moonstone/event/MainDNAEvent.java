package com.ytgld.moonstone.event;

import com.ytgld.moonstone.item.ms.necora.sequence.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.*;

public class MainDNAEvent {
    @SubscribeEvent
    public void event(LivingDeathEvent event){
        MethaneSynthesis.die(event);
    }
    @SubscribeEvent
    public void event(LivingIncomingDamageEvent event){
        MethaneSynthesis.hurt(event);
        EpidermalDigestion.attack(event);
        FatAccumulation.proArrow(event);
    }
    @SubscribeEvent
    public void event(LivingDamageEvent.Post event){
        SpikeProtrusion.damageHurt(event);
    }
    @SubscribeEvent
    public void event(LivingHealEvent event){
        ActivationEnzyme.heal(event);
        BloodReconstruction.heal(event);
    }
    @SubscribeEvent
    public void event(MobEffectEvent.Added event){
        BloodReconstruction.effect(event);
    }
    @SubscribeEvent
    public void event(LivingEntityUseItemEvent.Finish event){
        MucosalInjury.eat(event);
    }
    @SubscribeEvent
    public void event(LivingDamageEvent.Pre event){
        SpikeProtrusion.damageHurt(event);
        BoneFibers.res(event);
    }
}
