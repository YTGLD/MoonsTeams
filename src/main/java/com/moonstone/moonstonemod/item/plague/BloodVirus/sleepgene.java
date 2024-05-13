package com.moonstone.moonstonemod.item.plague.BloodVirus;

import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class sleepgene extends BloodViru {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.sleepgene.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.sleepgene.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
