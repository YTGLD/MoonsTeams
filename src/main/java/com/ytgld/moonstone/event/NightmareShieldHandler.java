package com.ytgld.moonstone.event;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class NightmareShieldHandler {

    public static void nightmareShield(LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (!player.level().isClientSide()) {
                double max = player.getAttributeValue(AttReg.nightmare_shield);
                float data = player.getData(AttReg.nightmareShieldTypeSupplier);
                AttributeInstance nightmareStronger = player.getAttribute(AttReg.nightmare_stronger);
                if (nightmareStronger != null) {
                    float valueStronger = (float) nightmareStronger.getValue();
                    if (valueStronger <= 0) {
                        return;
                    }
                    if (data > 0) {
                        float damage = event.getNewDamage();
                        float newData = data - ((damage / valueStronger) / 5f);
                        if (newData > 0) {
                            event.setNewDamage(0);
                            player.setData(AttReg.nightmareShieldTypeSupplier, newData);
                        } else {
                            player.setData(AttReg.nightmareShieldTypeSupplier, 0f);
                        }
                    } else {
                        player.setData(AttReg.nightmareShieldTypeSupplier, 0f);
                        player.setData(AttReg.nightmareShieldCooldownDataAttachmentType, 10);
                    }
                }
            }
        }
    }
    public static void tickEntityTickEvent(EntityTickEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (player.getData(AttReg.nightmareShieldCooldownDataAttachmentType) <= 0) {
                AttributeInstance nightmareShield = player.getAttribute(AttReg.nightmare_shield);
                if (nightmareShield != null) {
                    float time = 100;
                    float data = player.getData(AttReg.nightmareShieldTypeSupplier);
                    if (player.tickCount % (int) time == 1) {
                        addShadowBlackShieldData(player,1);
                    }
                    if (data < 0) {
                        player.setData(AttReg.nightmareShieldTypeSupplier.get(),0f);
                    }
                }
            }else if (player.tickCount % 20 == 1 && player.getData(AttReg.nightmareShieldCooldownDataAttachmentType) > 0){
                player.setData(AttReg.nightmareShieldCooldownDataAttachmentType.get(),player.getData(AttReg.nightmareShieldCooldownDataAttachmentType) - 1);

            }
        }
    }

    public static void addShadowBlackShieldData(LivingEntity living , float number){
        if (living instanceof Player player) {
            AttributeInstance shadowAttributeInstance = player.getAttribute(AttReg.nightmare_shield);
            if (shadowAttributeInstance != null ) {
                int cooldown = player.getData(AttReg.nightmareShieldCooldownDataAttachmentType);
                if (cooldown > 0) {
                    number = 0;
                }
                float shadowValue = (float) shadowAttributeInstance.getValue();
                float data = living.getData(AttReg.nightmareShieldTypeSupplier);
                float newValue = data + number;
                if (data > shadowValue) {
                    return;
                }
                if (newValue > shadowValue) {
                    newValue = shadowValue;
                }

                player.setData(AttReg.nightmareShieldTypeSupplier,newValue);
            }
        }
    }
}
