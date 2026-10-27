package com.ytgld.moonstone.item.si.nightmare.reversal;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.List;

/**
 * 腐香芦
 * <p>
 * 你的残躯将被轮回的因果无视
 * <p>
 * 因此得以抹去“颠倒之物”的负面诅咒
 * <p>
 * 腐烂的躯体使你游泳速度减半
 */
public class RottenGourd extends NightmareSmall {
    public RottenGourd(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.moonstone.rotten_gourd.string.1").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.translatable("item.moonstone.rotten_gourd.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.moonstone.rotten_gourd.string.3").withStyle(Style.EMPTY.withColor(0xffff0000)));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap = HashMultimap.create();
        attributeModifierMultimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(identifier(),
                -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return attributeModifierMultimap;
    }
}
