package com.moonstone.moonstonemod.item.TheNecora.small;

import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class compression extends TheNecoraIC {
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player){
            return false;
        }
        return  true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.compression.tool.string").withStyle(ChatFormatting.RED));
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.moonstone.small.all").withStyle(ChatFormatting.GOLD));

    }
}

