package com.moonstone.moonstonemod.item.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.IBattery;
import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class ectoplasmbattery extends ectoplasm implements IBattery {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {

        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + ":ectoplasmbattery", 10, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + ":ectoplasmbattery", 4, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + ":ectoplasmbattery", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + ":ectoplasmbattery", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }
}
