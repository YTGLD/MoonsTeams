package com.ytgld.moonstone.item.ms.necora;

import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.world.item.Item;

import java.util.HashSet;
import java.util.Set;

public class TheNecoraDNABush extends TheNecora {
    public TheNecoraDNABush(Properties properties) {
        super(properties);
    }

    @Override
    public HashSet<Item> canUSe() {

        return new HashSet<>( Set.of(
                InitItems.WarmApproachable.asItem(),
                InitItems.OceanAffinity.asItem(),
                InitItems.EarthAffinity.asItem()
        ));
    }
    @Override
    public int maxSize() {
        return 1;
    }
}
