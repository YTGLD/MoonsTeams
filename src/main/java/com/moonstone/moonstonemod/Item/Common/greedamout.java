package com.moonstone.moonstonemod.Item.Common;

import com.moonstone.moonstonemod.Item.MoonStoneItem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class greedamout extends CommonItem {

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.greedamout.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.greedamout.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}

