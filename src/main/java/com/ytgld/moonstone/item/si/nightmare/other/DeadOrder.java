package com.ytgld.moonstone.item.si.nightmare.other;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;


/**
 * 死亡之令
 * <p>
 * 佩戴噩梦饰品会给予大量的梦魇护盾
 * <p>
 * 梦魇护盾可为你吸收全部伤害
 * */

public class DeadOrder extends NightmareSmall {
    public DeadOrder(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(AttReg.nightmare_shield, new AttributeModifier(identifier(), 30, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.dead_drder.tool.string.1").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.translatable("item.dead_drder.tool.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));

    }
}
