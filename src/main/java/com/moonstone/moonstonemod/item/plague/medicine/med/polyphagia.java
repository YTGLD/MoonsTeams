package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.moonstone.moonstonemod.item.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class polyphagia extends medIC {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("Null").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
    }

}

