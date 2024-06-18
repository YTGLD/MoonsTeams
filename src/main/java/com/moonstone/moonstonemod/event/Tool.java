package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    @SubscribeEvent
    public void ItemTooltipEventASD(ItemTooltipEvent event){
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        if (Handler.hascurio(player,Items.gorillacake.get())){
            /*
            if (stack.is(Items.ectoplasmball.get())) {
                List<Component> list = new ArrayList<>();
                list.add(Component.translatable("tool.string.ectoplasmball").withStyle(ChatFormatting.GOLD));
                list.add(Component.translatable("tool.string.ectoplasmball.1").withStyle(ChatFormatting.GOLD));
                list.add(Component.translatable("tool.string.ectoplasmball.2").withStyle(ChatFormatting.GOLD));
                list.add(Component.literal(""));
                list.add(Component.translatable("tool.string.ectoplasmball.3").withStyle(ChatFormatting.GRAY));
                event.getToolTip().addAll(list);
            }

             */
        }
    }
}
