package com.ytgld.moonstone.item.ms.maxitem.uncommon.common;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class BadgeOfTheDead extends CommonItem {

    public BadgeOfTheDead(Properties properties) {
        super(properties);
    }

    public static void badgeofthedead(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.badgeofthedead.get())) {
                if (event.getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()) {
                        event.setNewDamage(event.getNewDamage() * 1.25f);
                    }
                }
            }
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.badgeofthedead.tool.string").withStyle(ChatFormatting.GOLD));
    }
}



