package com.moonstone.moonstonemod.init.moonstoneitem.i;

import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public interface Blood extends Iplague, ICurioItem {
    @Override
    default boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(), stack.getItem())) {
            return false;
        }
        return true;
    }
}
