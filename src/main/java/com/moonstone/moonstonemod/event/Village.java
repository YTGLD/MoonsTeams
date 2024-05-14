package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Village {
    @SubscribeEvent
    public void ectoplasmball_VillagerTradesEvent(VillagerTradesEvent event){
        if (event.getType() == VillagerProfession.CLERIC){
            event.getTrades().get(1).add(new BasicItemListing( Items.ectoplasmball.get().getDefaultInstance(),new ItemStack(net.minecraft.world.item.Items.EMERALD),16, 4,1));
            event.getTrades().get(1).add(new BasicItemListing(1, Items.ectoplasmball.get().getDefaultInstance(),32, 4,1));
        }
        if (event.getType() == VillagerProfession.WEAPONSMITH){
            event.getTrades().get(1).add(new BasicItemListing(48, Items.mayhemcrystal.get().getDefaultInstance(),1, 4,1));

        }
        if (event.getType() == VillagerProfession.TOOLSMITH){
             event.getTrades().get(1).add(new BasicItemListing(48, Items.fortunecrystal.get().getDefaultInstance(),1, 4,1));

        }
    }
}
