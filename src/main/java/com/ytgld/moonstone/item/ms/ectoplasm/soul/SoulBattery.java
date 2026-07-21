package com.ytgld.moonstone.item.ms.ectoplasm.soul;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.CommonItem;
import com.ytgld.moonstone.item.ms.IBattery;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SoulBattery extends CommonItem implements IBattery {

    public SoulBattery(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 0.02, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(AttReg.cit, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;

    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.soulbattery.tool.string").withStyle(ChatFormatting.GOLD));

    }
}

