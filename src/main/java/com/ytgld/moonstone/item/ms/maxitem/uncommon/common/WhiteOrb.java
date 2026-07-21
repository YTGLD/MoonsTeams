package com.ytgld.moonstone.item.ms.maxitem.uncommon.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class WhiteOrb extends CommonItem {

    public WhiteOrb(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();

        double a = 0.1;
        if (com.ytgld.moonstone.Handler.hascurio(entity, com.ytgld.moonstone.item.Items.blackeorb.get())) {
            a *= 2;
        }

        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(identifier(), a, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
}
