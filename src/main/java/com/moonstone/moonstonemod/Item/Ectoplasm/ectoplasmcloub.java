package com.moonstone.moonstonemod.Item.Ectoplasm;

import com.moonstone.moonstonemod.Item.MoonStoneItem.IEctoplasm;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ectoplasmcloub extends Item implements IEctoplasm {
    public ectoplasmcloub() {
        super(new Properties().stacksTo(64).rarity(Rarity.RARE).food(
                new FoodProperties.Builder().alwaysEat().nutrition(8).saturationMod(0.8f).build()));
    }
}
