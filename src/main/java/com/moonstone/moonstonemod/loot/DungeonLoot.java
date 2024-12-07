package com.moonstone.moonstonemod.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class DungeonLoot extends LootModifier {
    public static final Codec<DungeonLoot> CODEC= RecordCodecBuilder.create((inst) -> codecStart(inst)
            .apply(inst, DungeonLoot::new));
    protected DungeonLoot(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    private void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                              Random random ,
                              Item mustHas ,
                              Entity entity ,
                              List<Item> itemList,
                              int gLvl){
        if (entity instanceof Player player ){
            if (Handler.hascurio(player,mustHas)){
                int i = random.nextInt(itemList.size());
                if (gLvl >= 100){
                    gLvl = 100;
                }
                if (Mth.nextInt(net.minecraft.util.RandomSource.create(), 1, 100) <= gLvl) {
                    generatedLoot.add(new ItemStack(itemList.get(i)));
                }
            }
        }
    }
    private void addLootHasB(ObjectArrayList<ItemStack> generatedLoot,
                         Random random ,
                             boolean a,
                         List<Item> itemList,
                         int gLvl) {
        if (a) {
            int i = random.nextInt(itemList.size());
            if (gLvl >= 100) {
                gLvl = 100;
            }
            if (Mth.nextInt(net.minecraft.util.RandomSource.create(), 1, 100) <= gLvl) {
                generatedLoot.add(new ItemStack(itemList.get(i)));
            }
        }
    }
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation s = context.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        Random random = new Random();

        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                addLoot(generatedLoot, random, Items.bat_cell.get(), entity, List.of(
                        Items.cell_blood_attack.get(),
                        Items.cell_desecrate.get(),
                        Items.cell_doctor.get(),
                        Items.cell_fear.get(),
                        Items.cell_harvest.get(),
                        Items.cell_immortal.get(),
                        Items.cell_not_do.get(),
                        Items.cell_rage.get(),
                        Items.cell_scientist.get()
                ), Config.SERVER.bat.get());
            }
            if (idSting.contains("dungeon") || idSting.contains("mineshaft") || idSting.contains("city")||idSting.contains("treasure")) {
                addLoot(generatedLoot, random, Items.nightmareeye.get(), entity, List.of(
                        Items.nightmare_heart.get(),
                        Items.nightmare_orb.get(),
                        Items.nightmareanchor.get(),
                        Items.nightmarecharm.get(),
                        Items.nightmareeye.get(),
                        Items.nightmaremoai.get(),
                        Items.nightmarerotten.get(),
                        Items.nightmarestone.get(),
                        Items.nightmaretreasure.get(),
                        Items.nightmarewater.get()
                ),  Config.SERVER.night.get());

                addLoot(generatedLoot, random, Items.bloodvirus.get(), entity, List.of(
                        Items.batgene.get(),
                        Items.batskill.get(),
                        Items.bloodgene.get(),
                        Items.botton.get(),
                        Items.catalyzer.get(),
                        Items.flygene.get(),
                        Items.heathgene.get(),
                        Items.ragegene.get(),
                        Items.sleepgene.get()
                ), Config.SERVER.bat.get());

                addLoot(generatedLoot, random, Items.necora.get(), entity, List.of(
                        Items.ambush.get(),
                        Items.atpoverdose.get(),
                        Items.autolytic.get(),
                        Items.fermentation.get(),
                        Items.putrefactive.get(),
                        Items.regenerative.get(),
                        Items.air.get(),
                        Items.motor.get(),
                        Items.watergen.get()

                ), Config.SERVER.necora.get());


                if (entity instanceof Player player) {
                    boolean wind = Handler.hascurio(player, Items.doomeye.get())
                            && Handler.hascurio(player, Items.doomswoud.get());
                    addLootHasB(generatedLoot, random, wind, List.of(
                            Items.wind_and_rain.get()
                    ), 5);
                }
            }
        }
        if (idSting.contains("chests/")) {
            if (entity instanceof Player player) {
                if (idSting.contains("treasure")) {
                    boolean ab = !Handler.hascurio(player, Items.cell.get())
                            && !Handler.hascurio(player, Items.giant.get())
                            && Handler.hascurio(player, Items.necora.get());

                    addLootHasB(generatedLoot, random, ab, List.of(
                            Items.cell.get()
                    ), 100);


                    boolean cellBat = !Handler.hascurio(player,Items.bat_cell.get())
                            && Handler.hascurio(player, Items.bloodvirus.get());

                    addLootHasB(generatedLoot, random, cellBat, List.of(
                            Items.bat_cell.get()
                    ), 100);

                    if (Handler.hascurio(player, Items.necora.get())) {
                        boolean cellGiant = Handler.hascurio(player, Items.giant.get());

                        addLootHasB(generatedLoot, random, cellGiant, List.of(
                                Items.bone_cell.get(),
                                Items.parasitic_cell.get(),
                                Items.mother_cell.get(),
                                Items.disgusting_cells.get()
                        ), Config.SERVER.necora.get());

                        boolean cellGiantNig = Handler.hascurio(player, Items.giant_nightmare.get());

                        addLootHasB(generatedLoot, random, cellGiantNig, List.of(
                                Items.giant_boom_cell.get(),
                                Items.not_blood_cell.get(),
                                Items.anaerobic_cell.get(),
                                Items.subspace_cell.get()
                        ), Config.SERVER.necora.get());

                        boolean cell = Handler.hascurio(player, Items.cell.get())
                                && !Handler.hascurio(player,Items.giant.get());

                        addLootHasB(generatedLoot, random, cell, List.of(
                                Items.adrenaline.get(),
                                Items.cell_mummy.get(),
                                Items.cell_boom.get(),
                                Items.cell_calcification.get(),
                                Items.cell_blood.get()
                        ), Config.SERVER.necora.get());
                    }
                }
            }
        }
        if (entity instanceof Player player){
            if (idSting.contains("chests/")){
                if (Handler.hascurio(player, Items.medicinebox.get())){
                    if (!player.getTags().contains("medicinebox")){
                        generatedLoot.add(new ItemStack(Items.zombie_box.get()));
                        player.addTag("medicinebox");
                    }
                }
            }
        }

        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.bloodvirus.get())){
                        if (!Handler.hascurio(player,Items.bat_cell.get())) {
                            generatedLoot.add(new ItemStack(Items.bat_cell.get()));
                        }
                    }
                    if (Handler.hascurio(player, Items.necora.get())){
                        if (!Handler.hascurio(player,Items.giant.get())) {
                            if (Mth.nextInt(RandomSource.create(),1,10) == 1) {
                                generatedLoot.add(new ItemStack(Items.giant.get()));
                            }
                        }
                    }
                }
            }
        }
        return generatedLoot;
    }
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
