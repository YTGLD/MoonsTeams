package com.moonstone.moonstonemod.item.TheNecora.god;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class GodAtpoverdose extends GodDNA {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio", uuid , 2, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }

}
