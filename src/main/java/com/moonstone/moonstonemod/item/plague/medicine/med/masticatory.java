package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class masticatory extends medIC {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.masticatory.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.masticatory.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }

        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable(""+ medicinebox.do_apple).withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.masticatory.tool.string.2").withStyle(ChatFormatting.RED));

    }

}

