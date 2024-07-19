package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.item.nanodoom.light_amout;
import com.moonstone.moonstonemod.item.nanodoom.sword_amout;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.giants.giant_boom_cell;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NewEvent {
    public static final String ectoplasmAndDOThat = "ectoplasmAndDOThat";
    public static final String meetAndDOThat = "meetAndDOThat";
    public static final String nanoAndDOThat = "nanoAndDOThat";
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        nightmare_head.LivingHealEvent(event);
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        giant_boom_cell.Boom(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);

    }
}
