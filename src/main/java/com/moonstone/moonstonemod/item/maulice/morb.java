package com.moonstone.moonstonemod.item.maulice;

import com.moonstone.moonstonemod.item.moonstoneitem.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class morb extends MLS {

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.morb.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.morb.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));

    }

}

