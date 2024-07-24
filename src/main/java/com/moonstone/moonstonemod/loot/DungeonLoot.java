package com.moonstone.moonstonemod.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class DungeonLoot extends LootModifier {
    public static final Codec<DungeonLoot> CODEC= RecordCodecBuilder.create((inst) -> codecStart(inst)
            .apply(inst, DungeonLoot::new));
    protected DungeonLoot(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


        ResourceLocation s = context.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        int W = Mth.nextInt(RandomSource.create(), 1, 20);

        int N = Mth.nextInt(RandomSource.create(), 1, 60);
        int a = Mth.nextInt(RandomSource.create(), 1, 35);
        int ne = Mth.nextInt(RandomSource.create(), 1, 40);
        int T = Mth.nextInt(RandomSource.create(), 1, 10);

        int cell = Mth.nextInt(RandomSource.create(), 1, 50);
        int giant = Mth.nextInt(RandomSource.create(), 1, 10);
        int giant_p = Mth.nextInt(RandomSource.create(), 1, 10);

        int bat = Mth.nextInt(RandomSource.create(), 1, 10);

        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.bloodvirus.get())){
                        if (!Handler.hascurio(player,Items.bat_cell.get())) {
                            generatedLoot.add(new ItemStack(Items.bat_cell.get()));
                        }
                        if (Handler.hascurio(player,Items.bat_cell.get())){
                            if (bat==1){
                                generatedLoot.add(new ItemStack(Items.cell_blood_attack.get()));

                            }
                            if (bat==2){
                                generatedLoot.add(new ItemStack(Items.cell_desecrate.get()));

                            }
                            if (bat==3){
                                generatedLoot.add(new ItemStack(Items.cell_doctor.get()));

                            }
                            if (bat==4){
                                generatedLoot.add(new ItemStack(Items.cell_fear.get()));

                            }
                            if (bat==5){
                                generatedLoot.add(new ItemStack(Items.cell_harvest.get()));

                            }
                            if (bat==6){

                                generatedLoot.add(new ItemStack(Items.cell_immortal.get()));
                            }
                            if (bat==7){

                                generatedLoot.add(new ItemStack(Items.cell_not_do.get()));
                            }
                            if (bat==8){

                                generatedLoot.add(new ItemStack(Items.cell_rage.get()));
                            }
                            if (bat==9){

                                generatedLoot.add(new ItemStack(Items.cell_scientist.get()));
                            }
                        }
                    }
                    if (Handler.hascurio(player, Items.necora.get())){
                        if (!Handler.hascurio(player,Items.giant.get())) {
                            if (giant == 1) {
                                generatedLoot.add(new ItemStack(Items.giant.get()));
                            }
                        }

                        if (Handler.hascurio(player, Items.giant.get())) {
                            if (giant_p  == 2) {
                                generatedLoot.add(new ItemStack(Items.bone_cell.get()));
                            }
                            if (giant_p == 3) {
                                generatedLoot.add(new ItemStack(Items.parasitic_cell.get()));
                            }
                            if (giant_p == 4) {
                                generatedLoot.add(new ItemStack(Items.mother_cell.get()));
                            }
                            if (giant_p == 5) {
                                generatedLoot.add(new ItemStack(Items.disgusting_cells.get()));
                            }
                        }
                        if (Handler.hascurio(player, Items.giant_nightmare.get())) {
                            if (giant == 2) {
                                generatedLoot.add(new ItemStack(Items.giant_boom_cell.get()));
                            }
                            if (giant == 3) {
                                generatedLoot.add(new ItemStack(Items.not_blood_cell.get()));
                            }
                            if (giant == 4) {
                                generatedLoot.add(new ItemStack(Items.anaerobic_cell.get()));
                            }
                            if (giant == 5) {
                                generatedLoot.add(new ItemStack(Items.subspace_cell.get()));
                            }
                        }
                        if (!Handler.hascurio(player, Items.cell.get()) && !Handler.hascurio(player,Items.giant.get())){
                            generatedLoot.add(new ItemStack(Items.cell.get()));
                        }
                        if (Handler.hascurio(player, Items.cell.get()) && !Handler.hascurio(player,Items.giant.get())) {
                            if (cell == 2) {
                                generatedLoot.add(new ItemStack(Items.adrenaline.get()));
                            }
                            if (cell == 3) {
                                generatedLoot.add(new ItemStack(Items.cell_mummy.get()));
                            }
                            if (cell == 4) {
                                generatedLoot.add(new ItemStack(Items.cell_boom.get()));
                            }
                            if (cell == 5) {
                                generatedLoot.add(new ItemStack(Items.cell_calcification.get()));
                            }
                            if (cell == 6) {
                                generatedLoot.add(new ItemStack(Items.cell_blood.get()));
                            }
                        }

                        if (cell == 7) {
                            generatedLoot.add(new ItemStack(Items.motor.get()));
                        }
                        if (cell == 8) {
                            generatedLoot.add(new ItemStack(Items.air.get()));
                        }
                        if (cell == 9) {
                            generatedLoot.add(new ItemStack(Items.watergen.get()));
                        }
                    }

                    if (Handler.hascurio(player, Items.doomswoud.get()) && Handler.hascurio(player, Items.doomeye.get())) {
                        if (T == 1) {
                            generatedLoot.add(new ItemStack(Items.wind_and_rain.get()));
                        }
                    }


                    if (T == 3) {
                        if (!Handler.hascurio(player,Items.plague.get())) {
                            generatedLoot.add(new ItemStack(Items.plague.get()));
                        }
                    }
                }


            }
            if (idSting.contains("dungeon") || idSting.contains("mineshaft")) {
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.necora.get())) {
                        if (ne == 1) {
                            generatedLoot.add(new ItemStack(Items.ambush.get()));
                        }
                        if (ne == 2) {
                            generatedLoot.add(new ItemStack(Items.atpoverdose.get()));
                        }
                        if (ne == 3) {
                            generatedLoot.add(new ItemStack(Items.autolytic.get()));
                        }
                        if (ne == 4) {
                            generatedLoot.add(new ItemStack(Items.fermentation.get()));
                        }
                        if (ne == 5) {
                            generatedLoot.add(new ItemStack(Items.putrefactive.get()));
                        }
                        if (ne == 6) {
                            generatedLoot.add(new ItemStack(Items.regenerative.get()));
                        }
                        if (ne == 7){
                            generatedLoot.add(new ItemStack(Items.slime.get()));

                        }
                    }

                    if (Handler.hascurio(player, Items.nightmareeye.get())) {
                        if (N == 1) {
                            generatedLoot.add(new ItemStack(Items.nightmaremoai.get()));
                        }
                        if (N == 2) {
                            generatedLoot.add(new ItemStack(Items.nightmarewater.get()));
                        }
                        if (N == 3) {
                            generatedLoot.add(new ItemStack(Items.nightmarestone.get()));
                        }
                        if (N == 4) {
                            generatedLoot.add(new ItemStack(Items.nightmareanchor.get()));
                        }
                        if (N == 5) {
                            generatedLoot.add(new ItemStack(Items.nightmaretreasure.get()));
                        }
                        if (N == 6) {
                            generatedLoot.add(new ItemStack(Items.nightmarecharm.get()));
                        }
                        if (N == 7) {
                            generatedLoot.add(new ItemStack(Items.nightmarerotten.get()));
                        }
                        if (N == 8) {
                            generatedLoot.add(new ItemStack(Items.nightmare_orb.get()));
                        }
                        if (N == 9) {
                            generatedLoot.add(new ItemStack(Items.nightmare_heart.get()));
                        }
                    }
                }

            }

            if (idSting.contains("city")) {
                if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player) {
                    if (Handler.hascurio(player,Items.dna.get())){
                        if (a == 7) {
                            generatedLoot.add(new ItemStack(Items.germ.get()));
                        }
                    }

                    if (Handler.hascurio(player, Items.bloodvirus.get())) {
                        if (a == 1) {
                            generatedLoot.add(new ItemStack(Items.bloodgene.get()));
                        }
                        if (a == 2) {
                            generatedLoot.add(new ItemStack(Items.ragegene.get()));
                        }
                        if (a == 3) {
                            generatedLoot.add(new ItemStack(Items.flygene.get()));
                        }
                        if (a == 4) {
                            generatedLoot.add(new ItemStack(Items.heathgene.get()));
                        }
                        if (a == 5) {
                            generatedLoot.add(new ItemStack(Items.sleepgene.get()));
                        }
                        if (a == 6) {
                            generatedLoot.add(new ItemStack(Items.batgene.get()));
                        }
                    }
                }
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.doomswoud.get()) && Handler.hascurio(player, Items.doomeye.get())) {
                        if (W == 1) {
                            generatedLoot.add(new ItemStack(Items.wind_and_rain.get()));
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
