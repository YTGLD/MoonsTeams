package com.moonstone.moonstonemod.item.maxitem.uncommon.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class whiteorb extends CommonItem {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();

        double a = 0.1;
        if (Handler.hascurio(slotContext.entity(), Items.blackeorb.get())){
            a *= 2;
        }

        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID+":whiteorb", a, AttributeModifier.Operation.MULTIPLY_BASE));

        return modifierMultimap;
    }
}
