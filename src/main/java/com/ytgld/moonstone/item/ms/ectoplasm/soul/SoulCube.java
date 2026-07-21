package com.ytgld.moonstone.item.ms.ectoplasm.soul;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class SoulCube extends CommonItem {


    public SoulCube(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(identifier(), 8, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }
}
