package com.ytgld.moonstone.item.ms.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class Mayhemcrystal extends UnCommonItem {
    public Mayhemcrystal(Properties properties) {
        super(properties);
    }


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s  = 0.3f;
        if (Handler.hascurio(livingEntity, Items.nightmare_base_stone_meet.get())) {
            s*= 2;
        }
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(identifier(), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}

