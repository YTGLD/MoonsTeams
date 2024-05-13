package com.moonstone.moonstonemod.item.common;

import com.moonstone.moonstonemod.item.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class badgeofthedead extends CommonItem {

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.badgeofthedead.tool.string").withStyle(ChatFormatting.GOLD));
    }
}



