package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootTableEvent {

    @SubscribeEvent
    public void ItemTooltipEventASD(LootTableLoadEvent event){

        LootTable table = event.getTable();

        if (event.getName().toString().contains("chests/")){

            if (event.getName().toString().contains("bastion")) {
                table.addPool(LootPool.lootPool().name("moon_bastion")

                        .add(LootItem.lootTableItem(Items.ectoplasmball.get()).setWeight(20))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.the_pain_stone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.pain_candle.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.pain_ring.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .build());
            }




            if (event.getName().toString().contains("ancien")){
                table.addPool(LootPool.lootPool().name("ancien_moon")
                        .add(LootItem.lootTableItem(Items.nano_box.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.ectoplasmball.get()).setWeight(48))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.magicstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.magiceye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.nanocube.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.nanorobot.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.thedoomstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.thefruit.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.doomeye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.doomswoud.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.wind.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .build());
            }

            if (event.getName().toString().contains("treasure")){
                table.addPool(LootPool.lootPool().name("treasures")
                        .add(LootItem.lootTableItem(Items.ectoplasmball.get()).setWeight(20))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.the_heart.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.twistedstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.ectoplasmstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .build());
            }

            if (event.getName().toString().contains("dungeon") ||event.getName().toString().contains("mineshaft")){
                table.addPool(LootPool.lootPool().name("dungeon_or_mineshaft")
                        .add(LootItem.lootTableItem(Items.ectoplasmball.get()).setWeight(60))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.badgeofthedead.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.battery.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.blackeorb.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.redamout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.greedamout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.blueamout.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.greedcrystal.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.warcrystal.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.whiteorb.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.diemug.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.evilcandle.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))


                        .add(LootItem.lootTableItem(Items.evilmug.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.obsidianring.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.magicstone.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.magiceye.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.ectoplasmhorseshoe.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.soulcube.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .add(LootItem.lootTableItem(Items.soulbattery.get()).setWeight(1))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))

                        .build());





            }
        }
    }

}
