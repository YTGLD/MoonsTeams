package com.moonstone.moonstonemod.item.nightmare.super_nightmare.start;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
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
    UUID uuid = UUID.fromString("7b2fc485-d259-389c-bb22-fc20b2865ae3");
    public Multimap<Attribute, AttributeModifier> gets() {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        attributeModifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "a",10, AttributeModifier.Operation.ADDITION));
        attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "a", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
        attributeModifiers.put(AttReg.heal.get(), new AttributeModifier(uuid, "a", 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributeModifiers;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(),this) ){
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets());
    }
     @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}


