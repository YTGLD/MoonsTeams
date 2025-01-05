package com.moonstone.moonstonemod.item.maxitem.uncommon;

import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class evilcandle extends UnCommonItem {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().clearFire();
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        tooltip.add(Component.translatable("item.evilcandle.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.evilcandle.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.evilcandle.tool.string.2").withStyle(ChatFormatting.GOLD));

    }
}
