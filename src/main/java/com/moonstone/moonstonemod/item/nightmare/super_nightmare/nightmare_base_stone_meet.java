package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class nightmare_base_stone_meet   extends nightmare implements SuperNightmare{
    public static final String curse = "IS_CURSE";

     @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_meet.tool.string.1").withStyle(ChatFormatting.RED));

        pTooltipComponents.add(Component.translatable("item.moonstone.ectoplasmstar").withStyle(ChatFormatting.DARK_RED));
        //幸运值加倍，且每点幸运加2.5%治疗
        pTooltipComponents.add(Component.translatable("item.moonstone.mayhemcrystal").withStyle(ChatFormatting.DARK_RED));
        //伤害增幅增加50%
        pTooltipComponents.add(Component.translatable("item.moonstone.maxamout").withStyle(ChatFormatting.DARK_RED));
        //所有效果BUFF等级加2
        //所有触发概加100%
        //反伤效果增加400%


    }
   
}
