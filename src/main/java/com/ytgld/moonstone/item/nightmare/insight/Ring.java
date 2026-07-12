package com.ytgld.moonstone.item.nightmare.insight;

import com.ytgld.moonstone.item.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

/**
 *永恒的力量,抗性.生命恢复,急迫,生命提升
 * <p>
 * <p>
 *  每获得一种3级及以上的药水状态
 * <p>
 *  减少10%药水持续时间
 * <p>
 * <p>
 *  任何药水效果结束，你将恢复4点生命值
 * <p>
 *  若药水状态为负面，则恢复10%最大生命值

 */

public class Ring extends NightmareSmall {
    public Ring(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.STRENGTH,1000000000,2,false,false));
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.REGENERATION,1000000000,2,false,false));
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.RESISTANCE,1000000000,2,false,false));
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.HASTE,1000000000,2,false,false));
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST,1000000000,2,false,false));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().removeEffect(MobEffects.STRENGTH);
        slotContext.entity().removeEffect(MobEffects.REGENERATION);
        slotContext.entity().removeEffect(MobEffects.RESISTANCE);
        slotContext.entity().removeEffect(MobEffects.HASTE);
        slotContext.entity().removeEffect(MobEffects.HEALTH_BOOST);
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.ring.tool.string").withStyle(ChatFormatting.DARK_RED));
    }

}
