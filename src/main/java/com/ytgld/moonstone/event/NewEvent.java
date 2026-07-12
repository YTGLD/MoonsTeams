package com.ytgld.moonstone.event;

import com.ytgld.moonstone.item.ms.blood.MaxEye;
import com.ytgld.moonstone.item.ms.blood.PrisonOfSin;
import com.ytgld.moonstone.item.ms.blood.magic.BloodMagicBox;
import com.ytgld.moonstone.item.ms.blood.magic.UndeadBloodCharm;
import com.ytgld.moonstone.item.ms.ectoplasm.EctoplasmApple;
import com.ytgld.moonstone.item.ms.ectoplasm.EctoplasmHorseshoe;
import com.ytgld.moonstone.item.ms.ectoplasm.EctoplasmShild;
import com.ytgld.moonstone.item.ms.maulice.*;
import com.ytgld.moonstone.item.si.fall.DivineFallRing;
import com.ytgld.moonstone.item.si.nightmare.base.*;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeEye;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeHeart;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeRed;
import com.ytgld.moonstone.item.si.nightmare.fool.Apple;
import com.ytgld.moonstone.item.si.nightmare.fool.FoolBone;
import com.ytgld.moonstone.item.si.nightmare.insight.HiddenBlade;
import com.ytgld.moonstone.item.si.nightmare.insight.InsightInsane;
import com.ytgld.moonstone.item.si.nightmare.redemption.RedemptionDeception;
import com.ytgld.moonstone.item.si.nightmare.reversal.Candle;
import com.ytgld.moonstone.item.si.nightmare.reversal.ReversalOrb;
import com.ytgld.moonstone.item.si.nightmare.start.StartPod;
import com.ytgld.moonstone.item.si.nightmare.start.Wolf;
import com.ytgld.moonstone.item.si.nightmare.stone.EndBone;
import com.ytgld.moonstone.item.si.nightmare.stone.StoneBrain;
import com.ytgld.moonstone.item.si.nightmare.stone.StoneVirus;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

public class NewEvent {

    public static float time = 0;

    @SubscribeEvent
    public void tick(ClientTickEvent.Pre event) {
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
        EctoplasmHorseshoe.hurt(event);
        EctoplasmShild.hurt(event);
        Brain.brainLHurt(event);
        MKidney.brainLHurt(event);
        MShell.LivingHurtEvent(event);
        UndeadBloodCharm.LivingIncomingDamageEvent(event);
        MaxEye.A(event);


        Apple.damage(event);
        RedemptionDeception.LivingHurtEvent(event);
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingDamageEvent.Post event) {
        EctoplasmApple.hurt(event);

    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingDeathEvent event) {
        NightmareBaseItemReversal.LivingDeathEvent(event);
        BlackEyeRed.kill(event);
        InsightInsane.LivingDeathEvents(event);
        Wolf.kill(event);
        BloodMagicBox.Did(event);
        MaxEye.Die(event);
        PrisonOfSin.LivingDeathEvent(event);
    }

    @SubscribeEvent
    public void exp(LivingExperienceDropEvent event) {
        NightmareBaseInsight.exp(event);
        DivineFallRing.exp(event);
        MOrb.LivingExperienceDropEvent(event);

    }

    @SubscribeEvent
    public void effect(MobEffectEvent.Applicable event) {
        NightmareBaseBlackEye.exp(event);
        DivineFallRing.exp(event);
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
        MRing.LivingExperienceDropEvent(event);
        UndeadBloodCharm.LivingHealEvent(event);
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
    @SubscribeEvent
    public void LivingExperienceDropEvent(LivingKnockBackEvent event) {
        MRing.LivingExperienceDropEvent(event);
    }
}
