package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class belt extends CommonItem implements Die {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        var s = super.getAttributeModifiers(slotContext, uuid, stack);
        CuriosApi.addSlotModifier(s, "crystal",uuid, 2, AttributeModifier.Operation.ADDITION);
        CuriosApi.addSlotModifier(s, "belt",uuid, 1, AttributeModifier.Operation.ADDITION);
        return s;
    }
}
