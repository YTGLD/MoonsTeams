package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.necora.GodDNA;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GodFermentation  extends GodDNA {

    public GodFermentation(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();

        modifierMultimap.put(AttReg.cit, new AttributeModifier(identifier(), 0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(identifier(), 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
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


