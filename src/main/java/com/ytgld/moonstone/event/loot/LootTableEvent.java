package com.ytgld.moonstone.event.loot;

import com.ytgld.moonstone.item.InitItems;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public class LootTableEvent {

    @SubscribeEvent
    public void ItemTooltipEventASD(LootTableLoadEvent event){
        int bc = 2;
        LootTable table = event.getTable();
        if (event.getName().toString().contains("chests/")){

            if (event.getName().toString().contains("bastion")) {
                table.addPool(LootPool.lootPool().name("moon_bastion")

                        .add(LootItem.lootTableItem(InitItems.ectoplasmball.get()).setWeight((int) (bc*20)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//                        .add(LootItem.lootTableItem(Items.the_pain_stone.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//                        .add(LootItem.lootTableItem(Items.pain_candle.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//                        .add(LootItem.lootTableItem(Items.pain_ring.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .build());
            }




            if (event.getName().toString().contains("ancien")){
                table.addPool(LootPool.lootPool().name("ancien_moon")

                        .add(LootItem.lootTableItem(InitItems.ectoplasmball.get()).setWeight((int) (bc*48)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//                        .add(LootItem.lootTableItem(Items.soul_apple.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.luck_stone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.luck_ring.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.magicstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.magiceye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.nanocube.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.nanorobot.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.thedoomstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.thefruit.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.doomeye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.doomswoud.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.wind.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.million.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.as_amout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                        .add(LootItem.lootTableItem(InitItems.magnet.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.rage_crystal.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.greedcrystal.get()).setWeight(2))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.warcrystal.get()).setWeight(2))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .build());
            }

            if (event.getName().toString().contains("treasure")){
                table.addPool(LootPool.lootPool().name("treasures")
                        .add(LootItem.lootTableItem(InitItems.ectoplasmball.get()).setWeight((int) (bc*20)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.the_heart.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.max_eye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//                        .add(LootItem.lootTableItem(Items.twistedstone.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//
//                        .add(LootItem.lootTableItem(Items.ectoplasmstone.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.blood_amout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.evil_mob.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.malice_die.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.god_lead.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.rage_crystal.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.greedcrystal.get()).setWeight(2))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.warcrystal.get()).setWeight(2))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .build());
            }

            if (event.getName().toString().contains("dungeon") ||event.getName().toString().contains("mineshaft")){
                table.addPool(LootPool.lootPool().name("dungeon_or_mineshaft")
                        .add(LootItem.lootTableItem(InitItems.ectoplasmball.get()).setWeight((int) (bc*60)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.badgeofthedead.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.battery.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.blackeorb.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.redamout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.greedamout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.blueamout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.greedcrystal.get()).setWeight(2))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.warcrystal.get()).setWeight(2))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.whiteorb.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//                        .add(LootItem.lootTableItem(Items.diemug.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(InitItems.evilcandle.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
//
//
//                        .add(LootItem.lootTableItem(Items.evilmug.get()).setWeight(1))
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.obsidianring.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.magicstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.magiceye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.ectoplasmhorseshoe.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.soulcube.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.soulbattery.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.rage_crystal.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(InitItems.magnet.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .build());





            }
        }
    }

}
