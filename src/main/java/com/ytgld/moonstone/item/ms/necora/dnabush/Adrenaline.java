package com.ytgld.moonstone.item.ms.necora.dnabush;

import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class Adrenaline extends TheNecora {
    public Adrenaline(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.adrenaline.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.adrenaline.tool.string.1").withStyle(ChatFormatting.DARK_RED));

    }
}
