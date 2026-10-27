package com.ytgld.moonstone.item.si.nightmare;

import net.minecraft.world.item.ItemStack;

import java.util.Map;

public interface AllTip {
    Map<Integer, String> tooltip();

    Map<Integer, String> element(ItemStack stack);

}
