package com.moonstone.moonstonemod.item.BloodVirus;

import com.moonstone.moonstonemod.moonstoneitem.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class catalyzer extends BloodViru {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.catalyzer.tool.string").withStyle(ChatFormatting.DARK_PURPLE));
    }
}
