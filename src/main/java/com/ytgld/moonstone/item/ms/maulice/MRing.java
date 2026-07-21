package com.ytgld.moonstone.item.ms.maulice;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;

import java.util.List;

public class MRing extends MLS {

    public MRing(Properties properties) {
        super(properties);
    }
    public static void LivingExperienceDropEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.mring.get())) {
                event.setAmount(event.getAmount() * 1.4f);
            }
        }
    }
    public static void LivingExperienceDropEvent(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.mring.get())) {
                event.setStrength(event.getStrength() * 2);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.mring.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mring.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));

    }

}


