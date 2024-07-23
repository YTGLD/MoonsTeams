package com.moonstone.moonstonemod.item.BloodVirus;

import com.moonstone.moonstonemod.moonstoneitem.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ragegene  extends BloodViru {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.ragegene.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.ragegene.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.ragegene.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.ragegene.tool.string.3").withStyle(ChatFormatting.RED));

        } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
