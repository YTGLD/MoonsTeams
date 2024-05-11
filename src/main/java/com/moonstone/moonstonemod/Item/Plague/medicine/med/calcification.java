package com.moonstone.moonstonemod.Item.Plague.medicine.med;

import com.moonstone.moonstonemod.Item.Plague.medicine.extend.medIC;
import com.moonstone.moonstonemod.Item.Plague.medicine.extend.medicinebox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class calcification extends medIC {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("+ 11% resistance").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }

        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable(String.valueOf(medicinebox.do_hurt)).withStyle(ChatFormatting.RED));

    }


}


