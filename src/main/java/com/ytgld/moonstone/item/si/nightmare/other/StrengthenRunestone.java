package com.ytgld.moonstone.item.si.nightmare.other;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class StrengthenRunestone extends NightmareSmall {


    public StrengthenRunestone(Properties properties) {
        super(properties);
    }

    public static void hurt (LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(Items.strengthen_runestone.get().getDefaultInstance())) {
                if (event.getSource().getEntity() instanceof LivingEntity living) {
                    if (SIHandler.hascurio(player, Items.strengthen_runestone.get())) {
                        if (Mth.nextInt(RandomSource.create(), 0, 100) <= 25) {
                            living.hurt(living.damageSources().playerAttack(player), event.getNewDamage() * 0.2f);
                            player.getCooldowns().addCooldown(Items.strengthen_runestone.get().getDefaultInstance(),30);
                        }
                    }
                }
            }
            
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(Items.strengthen_runestone.get().getDefaultInstance())) {
                if (event.getEntity() instanceof LivingEntity) {
                    if (SIHandler.hascurio(player, Items.strengthen_runestone.get())) {
                        if (Mth.nextInt(RandomSource.create(), 0, 100) <= 25) {
                            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 100, 1));
                            player.getCooldowns().addCooldown(Items.strengthen_runestone.get().getDefaultInstance(),30);
                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.strengthen_runestone.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.strengthen_runestone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
