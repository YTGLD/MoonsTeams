package com.moonstone.moonstonemod.item.soul;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class soulcube extends CommonItem {


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID+this.getDescriptionId(), 8, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
}
