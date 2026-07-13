package com.ytgld.moonstone.item.ms.maulice;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

import java.util.List;

public class MHead extends MLS {
    public MHead(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.mhead.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));
    }

    public static void target(LivingChangeTargetEvent event){
        if (event.getNewAboutToBeSetTarget() instanceof Player player) {
            if (Handler.hascurio(player, Items.mhead.asItem())) {
                if (player.getLastHurtMob()!=null) {
                    if (player.getLastHurtMob().is(event.getEntity())) {
                        return;
                    }
                }
                event.setCanceled(true);
            }
        }
    }
}
