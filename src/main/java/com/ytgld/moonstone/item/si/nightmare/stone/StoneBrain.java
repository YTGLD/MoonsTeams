package com.ytgld.moonstone.item.si.nightmare.stone;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class StoneBrain extends NightmareSmall {

    public StoneBrain(Properties properties) {
        super(properties);
    }

    public static void hurts(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone_brain.get())) {
                if (player.getHealth() >= player.getMaxHealth()) {
                    event.setNewDamage(0);
                } else {
                    event.setNewDamage(event.getNewDamage() * 1.15f);
                }
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone_brain.get())) {
                if (!(player.getHealth() >= player.getMaxHealth())) {
                    event.setNewDamage(event.getNewDamage() * 0.75f);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_brain.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_brain.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_brain.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}

