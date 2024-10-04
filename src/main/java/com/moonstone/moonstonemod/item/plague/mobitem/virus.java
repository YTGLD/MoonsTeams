package com.moonstone.moonstonemod.item.plague.mobitem;

import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class virus extends TheNecoraIC implements ICurioItem {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.virus.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.virus.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.virus.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));

    }
}