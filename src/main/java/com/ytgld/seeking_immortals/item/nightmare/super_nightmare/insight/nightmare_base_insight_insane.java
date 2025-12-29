package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight;

import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class nightmare_base_insight_insane extends nightmare implements SuperNightmare {
    public static void LivingDeathEvents(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_insight_insane.get())) {
                player.heal(event.getEntity().getMaxHealth() / 10);
                player.getCooldowns().addCooldown(Items.nightmare_base_insight_insane.get(), 200);
            }
        }
    }

    public static void damage(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_insight_insane.get())) {
                if (player.getCooldowns().isOnCooldown(Items.nightmare_base_insight_insane.get())) {
                    event.setAmount(event.getAmount() * (1 + (Config.SERVER.nightmare_base_insight_insane.get() / 100f)));
                    player.getCooldowns().addCooldown(Items.nightmare_base_insight_insane.get(), 0);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        int v = Config.SERVER.nightmare_base_insight_insane.get();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string.2",v).withStyle(ChatFormatting.DARK_RED));
    }
}



