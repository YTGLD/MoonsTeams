package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmare_base_start_egg extends nightmare {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = super.getAttributeModifiers(slotContext, uuid, stack);
        attributeModifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "a",10, AttributeModifier.Operation.ADDITION));
        attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "a", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
        attributeModifiers.put(AttReg.heal.get(), new AttributeModifier(uuid, "a", 0.5, AttributeModifier.Operation.MULTIPLY_BASE));

        return attributeModifiers;
    }


     @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}


