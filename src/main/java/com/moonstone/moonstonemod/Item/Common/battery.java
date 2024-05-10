package com.moonstone.moonstonemod.Item.Common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Item.MoonStoneItem.CommonItem;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class battery extends CommonItem {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("7d461d5d-f429-32b0-a043-0156fa837406"), MoonStoneMod.MODID+"ec", 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("7d461d5d-f429-32b0-a043-0156fa837406"), MoonStoneMod.MODID+"ec", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("7d461d5d-f429-32b0-a043-0156fa837406"), MoonStoneMod.MODID+"ec", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }


}
