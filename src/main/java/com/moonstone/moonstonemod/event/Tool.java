package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.init.Items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    @SubscribeEvent
    public void ItemTooltipEventASD(ItemTooltipEvent tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();
        /*
        if (stack.is(Items.gorilladna.get())){

            List<Component> list = new ArrayList<>();

            list.add(Component.translatable("item.gorilladna.tool.string").withStyle(ChatFormatting.GOLD));
            list.add(Component.translatable("item.gorilladna.tool.string.1").withStyle(ChatFormatting.GOLD));
            list.add(Component.literal(""));
            list.add(Component.translatable("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
            list.add(Component.translatable("item.gorilladna.tool.string.2").withStyle(ChatFormatting.GOLD));

            tooltipEvent.getToolTip().addAll(1,list);
        }

         */
    }
}
