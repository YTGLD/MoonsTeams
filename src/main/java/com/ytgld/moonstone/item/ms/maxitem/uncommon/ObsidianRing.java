package com.ytgld.moonstone.item.ms.maxitem.uncommon;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class ObsidianRing extends UnCommonItem implements TextEvt.Twelve{
    public ObsidianRing(Properties properties) {
        super(properties);
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {

        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(identifier(), 10, AttributeModifier.Operation.ADD_VALUE));

        return modifierMultimap;
    }

}


