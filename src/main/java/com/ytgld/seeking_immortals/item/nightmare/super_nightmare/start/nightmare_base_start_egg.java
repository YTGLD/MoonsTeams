package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmare_base_start_egg extends nightmare implements SuperNightmare {
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        attributeModifiers.put(Attributes.LUCK, new AttributeModifier(UUID.fromString("4b1bee1f-d645-4bc7-aa9b-cc41e6e9dd8f"),"as", 10, AttributeModifier.Operation.ADDITION));
        attributeModifiers.put(AttReg.all_attack.get(), new AttributeModifier(UUID.fromString("4b1bee1f-d645-4bc7-aa9b-cc41e6e9dd8f"),"as", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
        attributeModifiers.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("4b1bee1f-d645-4bc7-aa9b-cc41e6e9dd8f"),"as", 0.5, AttributeModifier.Operation.MULTIPLY_BASE));

        return attributeModifiers;

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext,prevStack,stack);
        if (Handler.hascurio(slotContext.entity(), this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers());

    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}


