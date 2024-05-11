package com.moonstone.moonstonemod.Item.Plague.ALL;

import com.moonstone.moonstonemod.Item.MoonStoneItem.Iplague;
import com.moonstone.moonstonemod.Item.MoonStoneItem.plague;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class dna extends plague implements ICurioItem , Iplague {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }
}