package com.moonstone.moonstonemod.item.TheNecora.god;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class GodAmbush extends GodDNA {

    public static void LivingIncomingDamageEvent(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.god_ambush.get())) {
                event.setAmount(event.getAmount()*0.9f);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.ambush.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.god_ambush.tool.string").withStyle(ChatFormatting.RED));
        }else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
