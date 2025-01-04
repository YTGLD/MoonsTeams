package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class nightmaretreasure extends nightmare {

   @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.2").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.3").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.6").withStyle(ChatFormatting.DARK_RED));

        }else {
            tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.5").withStyle(ChatFormatting.DARK_RED));
    }
}
