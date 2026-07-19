package com.ytgld.moonstone.item.si.nightmare.start;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.HandlerNames;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;

import java.util.List;

/**
 * 掌权者
 * <p>
 * 异变的大脑使你的意识格外清醒
 * <p>
 * 这使得“噩梦之起始”的护甲诅咒降低一半
 * <p>
 *  过度的清醒使你无法入眠
 */

public class SupremePower extends NightmareSmall {
    public SupremePower(Properties properties) {
        super(properties);
    }
    public static void sleep(CanPlayerSleepEvent event){
        Player player = (Player) event.getEntity();
        if (Handler.hascurio(player, Items.supreme_power.asItem())) {
            event.setProblem(Player.BedSleepingProblem.NOT_SAFE);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.moonstone.supreme_power.string.1").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.translatable("item.moonstone.supreme_power.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.translatable("item.moonstone.supreme_power.string.3").withStyle(Style.EMPTY.withColor(0xffff0000)));
    }
}
