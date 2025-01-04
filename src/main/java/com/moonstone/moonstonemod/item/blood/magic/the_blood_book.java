package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class the_blood_book  extends Item implements ICurioItem, Blood {


    public the_blood_book() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }


}
