package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.giant_boom_cell;
import com.moonstone.moonstonemod.item.pain.pain_candle;
import com.moonstone.moonstonemod.item.pain.pain_ring;
import com.moonstone.moonstonemod.item.pain.the_pain_stone;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NewEvent {
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        pain_candle.Heal(event);
        nightmare_head.LivingHealEvent(event);
        pain_ring.Heal(event);


    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        pain_candle.Hurt(event);
        giant_boom_cell.Boom(event);
        bat_cell.Bat(event);
        pain_ring.Hurt(event);
        the_pain_stone.PainStoneAttack(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        the_pain_stone.PainStone(event);
    }
}
