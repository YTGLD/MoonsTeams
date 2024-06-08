package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.moonstoneitem.INanoBattery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class beacon extends INanoBattery {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext,stack);
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.beacon.tool.string.1").withStyle(ChatFormatting.GOLD));

    }

}