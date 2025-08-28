package com.moonstone.moonstonemod.item.amout;

import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class probability_stone extends ectoplasm {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        tooltip.add(Component.translatable("item.probability_stone.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
