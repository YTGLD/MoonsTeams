package com.ytgld.seeking_immortals.item.nightmare;

import net.minecraft.world.item.ItemStack;

import java.util.Map;

public interface AllTip {
    Map<Integer, String>  tooltip();
    Map<Integer, String> element(ItemStack stack);

}
