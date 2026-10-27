package com.ytgld.moonstone.item.ms.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class EctoplasmBattery extends Ectoplasm {

    public EctoplasmBattery(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(identifier(), 10, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(identifier(), 4, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(identifier(), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(identifier(), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}
