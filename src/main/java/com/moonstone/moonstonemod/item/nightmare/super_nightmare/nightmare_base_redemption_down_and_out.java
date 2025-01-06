package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.moonstone.moonstonemod.moonstoneitem.nightmare;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class nightmare_base_redemption_down_and_out extends nightmare {
   @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption_down_and_out.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}


