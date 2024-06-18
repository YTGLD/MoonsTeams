package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NewEvent {
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
    }
}
