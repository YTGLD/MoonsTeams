package com.moonstone.moonstonemod.item.ectoplasm;

import com.moonstone.moonstonemod.moonstoneitem.IEctoplasm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ectoplasmprism extends Item implements IEctoplasm {
    public ectoplasmprism() {
        super(new Properties().stacksTo(64).rarity(Rarity.EPIC));
    }
}

