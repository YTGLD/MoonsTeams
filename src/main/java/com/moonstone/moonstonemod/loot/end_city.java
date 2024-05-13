package com.moonstone.moonstonemod.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class end_city extends LootModifier {

    public static final Codec<end_city> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, end_city::new));
    }

    protected end_city(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int a = Mth.nextInt(RandomSource.create(), 1, 20);
        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player){
            if (Handler.hascurio(player, Items.bloodvirus.get())){
                if (a == 1){
                    generatedLoot.add(new ItemStack(Items.bloodgene.get()));
                }

                if (a == 2){
                    generatedLoot.add(new ItemStack(Items.ragegene.get()));
                }
                if (a == 3){
                    generatedLoot.add(new ItemStack(Items.flygene.get()));
                }
                if (a == 4){
                    generatedLoot.add(new ItemStack(Items.heathgene.get()));
                }
                if (a == 5){
                    generatedLoot.add(new ItemStack(Items.sleepgene.get()));
                }
                if (a == 6){
                    generatedLoot.add(new ItemStack(Items.batgene.get()));
                }
            }
        }


        return generatedLoot;
    }

    public Codec<end_city> codec() {
        return CODEC;
    }
}





