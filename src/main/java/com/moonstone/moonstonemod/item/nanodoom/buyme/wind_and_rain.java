package com.moonstone.moonstonemod.item.nanodoom.buyme;

import com.moonstone.moonstonemod.moonstoneitem.Perhaps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class wind_and_rain extends Perhaps {
    public static final String wind = "WindAndRain";

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.wind_and_rain.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.wind_and_rain.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.wind_and_rain.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.wind_and_rain.tool.string.3").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.wind_and_rain.tool.string.4").withStyle(ChatFormatting.RED));
    }
}
