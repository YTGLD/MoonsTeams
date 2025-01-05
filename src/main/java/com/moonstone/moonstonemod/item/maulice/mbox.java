package com.moonstone.moonstonemod.item.maulice;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.MLS;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;


public class mbox extends MLS {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID+":mbox", -0.2, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID+":mbox", 8, AttributeModifier.Operation.ADDITION));
        CuriosApi.addSlotModifier(linkedHashMultimap, "charm", uuid, 2, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }
}

