package com.moonstone.moonstonemod.item.TheNecora.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class GodFermentation  extends GodDNA {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID id, ItemStack stack) {
        Multimap<Attribute, AttributeModifier>modifierMultimap = HashMultimap.create();

        modifierMultimap.put(AttReg.cit.get(), new AttributeModifier(id, "aaa",0.25f, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(AttReg.all_attack.get(), new AttributeModifier(id, "aaa",0.1f, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.fermentation.tool.string").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.fermentation.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.fermentation.tool.string.2").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
    }
}


