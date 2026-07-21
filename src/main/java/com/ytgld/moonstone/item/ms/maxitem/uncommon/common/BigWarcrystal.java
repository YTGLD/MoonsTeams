package com.ytgld.moonstone.item.ms.maxitem.uncommon.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class BigWarcrystal extends CommonItem implements TextEvt.Twelve{
    public BigWarcrystal(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(identifier(),  0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}
