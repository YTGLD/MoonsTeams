package com.moonstone.moonstonemod.item.maxitem.uncommon.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import com.moonstone.moonstonemod.moonstoneitem.IBattery;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class battery extends CommonItem implements IBattery {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("7d461d5d-f429-32b0-a043-0156fa837406"), MoonStoneMod.MODID+ ":battery", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("7d461d5d-f429-32b0-a043-0156fa837406"), MoonStoneMod.MODID+":battery", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("7d461d5d-f429-32b0-a043-0156fa837406"), MoonStoneMod.MODID+":battery", Config.SERVER.battery_speed.get(), AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }


}
