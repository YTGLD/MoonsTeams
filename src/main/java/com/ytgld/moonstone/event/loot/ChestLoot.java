package com.ytgld.moonstone.event.loot;


import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.event.AdvancementEvt;
import com.ytgld.moonstone.event.EquippedEvt;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.DataReg;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChestLoot extends LootModifier {
    public static final Supplier<MapCodec<ChestLoot>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst,
                    ChestLoot::new)));

    protected ChestLoot(LootItemCondition[] conditions, int priority) {
        super(conditions, priority);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        @Nullable Entity entity = lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);
        Identifier s = lootContext.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        if (idSting.contains("chests/")) {
            if (entity != null) {
                AdvancementEvt.addLoot(objectArrayList, entity, 20);
                AdvancementEvt.nightmare_base_reversal_mysteriousLOOT(objectArrayList, entity);
                AdvancementEvt.nightmare_base_start_pod(objectArrayList, entity);
                AdvancementEvt.tricky_puppets(objectArrayList, entity);

                addLoot(objectArrayList, lootContext.getRandom(), Items.blood_candle.get(), entity, List.of(
                        Items.owner_blood_eye.get(),
                        Items.owner_blood_attack_eye.get(),
                        Items.owner_blood_speed_eye.get(),
                        Items.owner_blood_effect_eye.get(),
                        Items.owner_blood_boom_eye.get(),
                        Items.owner_blood_vex.get(),
                        Items.owner_blood_earth.get()
                ), 12,lootContext);

                addLoot(objectArrayList, lootContext.getRandom(), Items.NightmareBaseItem_.get(), entity, List.of(
                        Items.defend_against_runestone.get(),
                        Items.revive_runestone.get(),
                        Items.strengthen_runestone.get()
                ), 10,lootContext);


                addLoot(objectArrayList, lootContext.getRandom(), Items.medicinebox.get(), entity, List.of(
                        Items.WarmApproachable.get(),
                        Items.OceanAffinity.get(),
                        Items.EarthAffinity.get(),
                        Items.calcareous.get(),
                        Items.frontal_lobe.get(),
                        Items.high_energy.get(),
                        Items.surge.get()
                ), 18,lootContext);


                addLoot(objectArrayList, lootContext.getRandom(), Items.necora.get(), entity, List.of(
                        Items.ambush.get(),
                        Items.atpoverdose.get(),
                        Items.autolytic.get(),
                        Items.fermentation.get(),
                        Items.putrefactive.get(),
                        Items.regenerative.get(),
                        Items.adrenaline.get(),
                        Items.cell_mummy.get(),
                        Items.cell_boom.get(),
                        Items.cell_calcification.get(),
                        Items.cell_blood.get(),
                        Items.bone_cell.get(),
                        Items.parasitic_cell.get(),
                        Items.mother_cell.get(),
                        Items.disgusting_cells.get(),
                        Items.peptide_surge.get(),
                        Items.acidic_reflux.get(),
                        Items.hypertrophy.get(),
                        Items.naja_mortis.get()


                ), 20,lootContext);


            }
        }
        return objectArrayList;
    }
    private void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                         RandomSource random ,
                         Item mustHas ,
                         Entity entity ,
                         List<Item> itemList,
                         int gLvl,LootContext lootContext){
        ServerLevel serverLevel= lootContext.getLevel();
        if (entity instanceof Player player ){
            if (Handler.hascurio(player,mustHas)){
                int i = random.nextInt(itemList.size());
                if (gLvl >= 100){
                    gLvl = 100;
                }
                ItemStack stack = new ItemStack(itemList.get(i));
                CompoundTag compoundTag = new CompoundTag();
                if (serverLevel.getDifficulty()==(Difficulty.PEACEFUL)) {
                    compoundTag.putBoolean(Difficulty.PEACEFUL.getSerializedName(), true);
                }
                if (serverLevel.getDifficulty()==(Difficulty.EASY)) {
                    compoundTag.putBoolean(Difficulty.EASY.getSerializedName(), true);
                }
                if (serverLevel.getDifficulty()==(Difficulty.NORMAL)) {
                    compoundTag.putBoolean(Difficulty.NORMAL.getSerializedName(), true);
                }
                if (serverLevel.getDifficulty()==(Difficulty.HARD)) {
                    int lv = Mth.nextInt(RandomSource.create(),1,2);

                    if (lv == 1) {
                        compoundTag.putBoolean(Difficulty.HARD.getSerializedName(), true);
                    } else if (lv == 2){
                        compoundTag.putBoolean(EquippedEvt.lootTable, true);
                    }
                }
                stack.set(DataReg.tag,compoundTag);
                if (Mth.nextInt(random, 1, 100) <= gLvl) {
                    generatedLoot.add(stack);
                }
            }
        }
    }
    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }


}
