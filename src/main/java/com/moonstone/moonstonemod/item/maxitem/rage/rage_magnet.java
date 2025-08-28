package com.moonstone.moonstonemod.item.maxitem.rage;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.maxitem.uncommon.magnet;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.List;

public class rage_magnet extends magnet implements RAGE{
    public static void pick(PlayerEvent.ItemPickupEvent event){
        if (Handler.hascurio(event.getEntity(), Items.rage_magnet.get())) {
            event.getEntity().heal(1);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.rage_magnet.tool.string").withStyle(ChatFormatting.GOLD));
    }
}
