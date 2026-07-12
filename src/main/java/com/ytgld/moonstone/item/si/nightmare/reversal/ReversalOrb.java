package com.ytgld.moonstone.item.si.nightmare.reversal;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ReversalOrb extends NightmareSmall {
    public ReversalOrb(Properties properties) {
        super(properties);
    }

    public static void LivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_reversal_orb.get())) {
                if (player.getHealth() > 10) {
                    if (event.getAmount() > player.getHealth()) {
                        player.setHealth(1);
                    } else {
                        player.setHealth(player.getHealth() - event.getAmount());
                    }
                }
                event.setAmount(0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal_orb.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal_orb.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(this.getDefaultInstance())) {
                player.setHealth(player.getMaxHealth());
                player.getCooldowns().addCooldown(this.getDefaultInstance(), 140);
            }
        }
    }
}
