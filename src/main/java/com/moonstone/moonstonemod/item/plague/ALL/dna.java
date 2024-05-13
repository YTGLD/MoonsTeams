package com.moonstone.moonstonemod.item.plague.ALL;

import com.moonstone.moonstonemod.item.moonstoneitem.Iplague;
import com.moonstone.moonstonemod.item.moonstoneitem.plague;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class dna extends plague implements ICurioItem , Iplague {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }
}