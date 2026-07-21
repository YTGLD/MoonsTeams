package com.ytgld.moonstone.item.si.nightmare.start;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class StartPod extends NightmareSmall {
    public StartPod(Properties properties) {
        super(properties);
    }

    public static void damage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_start_pod.get())) {
                event.setNewDamage(event.getNewDamage() * 0.8f);
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_pod.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}



