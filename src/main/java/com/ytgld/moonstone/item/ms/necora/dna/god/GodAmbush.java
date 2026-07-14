package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.GodDNA;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class GodAmbush extends GodDNA {

    public GodAmbush(Properties properties) {
        super(properties);
    }

    public static void LivingIncomingDamageEvent(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.GodAmbush.get())) {
                event.setNewDamage(event.getNewDamage() * 0.9f);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pTooltipFlag.hasShiftDown()) {
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.ambush.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.god_ambush.tool.string").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
