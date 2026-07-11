package com.ytgld.moonstone.event;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseInsight;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseItemReversal;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseStart;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseStone;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.*;

public class NewEvent {

    public static float time= 0;
    @SubscribeEvent
    public void tick(ClientTickEvent.Pre event){
        time++;
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingDamageEvent.Pre event) {
        NightmareBaseStone.LivingHurtEvent(event);
        NightmareBaseStart.damage(event);
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingDeathEvent event) {
        NightmareBaseItemReversal.LivingDeathEvent(event);
    }
    @SubscribeEvent
    public void exp(LivingExperienceDropEvent event) {
        NightmareBaseInsight.exp(event);
    }
}
