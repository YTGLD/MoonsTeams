package com.ytgld.moonstone.event;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseInsight;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseItemReversal;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseStart;
import com.ytgld.moonstone.item.nightmare.base.NightmareBaseStone;
import com.ytgld.moonstone.item.nightmare.eye.BlackEyeEye;
import com.ytgld.moonstone.item.nightmare.eye.BlackEyeHeart;
import com.ytgld.moonstone.item.nightmare.eye.BlackEyeRed;
import com.ytgld.moonstone.item.nightmare.fool.Apple;
import com.ytgld.moonstone.item.nightmare.fool.FoolBone;
import com.ytgld.moonstone.item.nightmare.insight.HiddenBlade;
import com.ytgld.moonstone.item.nightmare.insight.InsightInsane;
import com.ytgld.moonstone.item.nightmare.redemption.RedemptionDeception;
import com.ytgld.moonstone.item.nightmare.reversal.Candle;
import com.ytgld.moonstone.item.nightmare.reversal.ReversalOrb;
import com.ytgld.moonstone.item.nightmare.start.StartPod;
import com.ytgld.moonstone.item.nightmare.start.Wolf;
import com.ytgld.moonstone.item.nightmare.stone.EndBone;
import com.ytgld.moonstone.item.nightmare.stone.StoneBrain;
import com.ytgld.moonstone.item.nightmare.stone.StoneVirus;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

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
        EndBone.hurts(event);
        StoneBrain.hurts(event);
        StoneVirus.h(event);
        BlackEyeHeart.hurt(event);
        BlackEyeEye.attLook(event);
        HiddenBlade.hurt_cit(event);
        InsightInsane.damage(event);
        Candle.hurt(event);
        FoolBone.attLook(event);
        Wolf.attack(event);
        StartPod.damage(event);

        Apple.damage(event);
        RedemptionDeception.LivingHurtEvent(event);
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingDeathEvent event) {
        NightmareBaseItemReversal.LivingDeathEvent(event);
        BlackEyeRed.kill(event);
        InsightInsane.LivingDeathEvents(event);
        Wolf.kill(event);
    }
    @SubscribeEvent
    public void exp(LivingExperienceDropEvent event) {
        NightmareBaseInsight.exp(event);
    }
    @SubscribeEvent
    public void exp(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            float a = (float) player.getAttributeValue(AttReg.heal);
            if (a != 1) {
                event.setAmount(event.getAmount() * a);
            }
        }
        ReversalOrb.LivingHealEvent(event);
        BlackEyeHeart.heal(event);
    }
    @SubscribeEvent
    public void exp(CriticalHitEvent event) {
        if (event.getEntity() instanceof Player player) {
            float a = (float) player.getAttributeValue(AttReg.cit);
            if (a != 1 && event.isCriticalHit()) {
                event.setDamageMultiplier(event.getDamageMultiplier() * a);
            }
        }
        HiddenBlade.cit(event);
    }
}
