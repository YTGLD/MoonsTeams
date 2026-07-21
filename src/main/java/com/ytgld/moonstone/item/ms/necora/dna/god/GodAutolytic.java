package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.GodDNA;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GodAutolytic extends GodDNA {
    public GodAutolytic(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
            modifierMultimap.put(AttReg.heal, new AttributeModifier(identifier(), 0.33, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.autolytic.tool.string").withStyle(ChatFormatting.RED));
    }
}

