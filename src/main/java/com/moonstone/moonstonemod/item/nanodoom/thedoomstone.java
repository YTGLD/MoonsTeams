package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class thedoomstone extends Doom {


    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }




    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.thedoomstone.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.thedoomstone.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.thedoomstone.tool.string.2").withStyle(ChatFormatting.GOLD));

    }
}
