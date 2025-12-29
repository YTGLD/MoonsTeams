package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption;

import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class nightmare_base_redemption_down_and_out extends nightmare implements SuperNightmare {
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float v = Config.SERVER.nightmare_base_redemption_down_and_out.get();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption_down_and_out.tool.string",v).withStyle(ChatFormatting.DARK_RED));
    }
}


