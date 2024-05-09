package com.moonstone.moonstonemod.Item.Nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Init.Items;
import com.moonstone.moonstonemod.Item.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class nightmaretreasure extends nightmare {

   @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.2").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.3").withStyle(ChatFormatting.DARK_RED));

        }else {
            tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmaretreasure.tool.string.5").withStyle(ChatFormatting.DARK_RED));
    }
}
