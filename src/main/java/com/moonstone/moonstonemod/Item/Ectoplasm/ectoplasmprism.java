package com.moonstone.moonstonemod.Item.Ectoplasm;

import com.moonstone.moonstonemod.Item.IEctoplasm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ectoplasmprism extends Item implements IEctoplasm {
    public ectoplasmprism() {
        super(new Properties().stacksTo(64).rarity(Rarity.EPIC));
    }
}

