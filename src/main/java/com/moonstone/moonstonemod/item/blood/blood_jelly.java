package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class blood_jelly extends Item implements Blood {
    public blood_jelly() {
        super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
    }
}
