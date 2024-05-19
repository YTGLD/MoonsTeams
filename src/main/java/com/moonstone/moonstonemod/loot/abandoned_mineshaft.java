package com.moonstone.moonstonemod.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
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
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class abandoned_mineshaft extends LootModifier {

    public static final Codec<abandoned_mineshaft> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, abandoned_mineshaft::new));
    }

    public abandoned_mineshaft(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }


    @Nonnull
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int p = Mth.nextInt(RandomSource.create(), 1, 50);
        int S = Mth.nextInt(RandomSource.create(), 1, 30);
        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player){
            if (Handler.hascurio(player ,Items.nightmareeye.get())){
                if (S == 1){generatedLoot.add(new ItemStack(Items.nightmaremoai.get()));}
                if (S == 2){generatedLoot.add(new ItemStack(Items.nightmarewater.get()));}
                if (S == 3){generatedLoot.add(new ItemStack(Items.nightmarestone.get()));}
                if (S == 4){generatedLoot.add(new ItemStack(Items.nightmareanchor.get()));}
                if (S == 5){generatedLoot.add(new ItemStack(Items.nightmaretreasure.get()));}
                if (S == 6){generatedLoot.add(new ItemStack(Items.nightmarecharm.get()));}
                if (S == 7){generatedLoot.add(new ItemStack(Items.nightmarerotten.get()));}
            }
        }
        if (p == 1){
            generatedLoot.add(new ItemStack(Items.ectoplasmbattery.get()));
        }
        if (p == 2){
            generatedLoot.add(new ItemStack(Items.ectoplasmapple.get()));
        }
        if (p == 3){
            generatedLoot.add(new ItemStack(Items.ectoplasmhorseshoe.get()));
        }
        if (p == 4){
            generatedLoot.add(new ItemStack(Items.ectoplasmshild.get()));
        }
        if (p == 5){
            generatedLoot.add(new ItemStack(Items.ectoplasmprism.get()));
        }


        if (p == 6){ generatedLoot.add(new ItemStack(Items.badgeofthedead.get()));}
        if (p == 7){ generatedLoot.add(new ItemStack(Items.battery.get()));}
        if (p == 8){ generatedLoot.add(new ItemStack(Items.biggreedcrystal.get()));}
        if (p == 9){ generatedLoot.add(new ItemStack(Items.bigwarcrystal.get()));}
        if (p == 10){generatedLoot.add(new ItemStack(Items.blackeorb.get()));}
        if (p == 11){generatedLoot.add(new ItemStack(Items.blueamout.get()));}
        if (p == 12){generatedLoot.add(new ItemStack(Items.greedamout.get()));}
        if (p == 13){generatedLoot.add(new ItemStack(Items.greedcrystal.get()));}
        if (p == 14){generatedLoot.add(new ItemStack(Items.redamout.get()));}
        if (p == 15){generatedLoot.add(new ItemStack(Items.warcrystal.get()));}
        if (p == 16){generatedLoot.add(new ItemStack(Items.whiteorb.get()));}


        if (p == 17){generatedLoot.add(new ItemStack(Items.soulbattery.get()));}
        if (p == 18){generatedLoot.add(new ItemStack(Items.soulcube.get()));}
        if (p == 19){generatedLoot.add(new ItemStack(Items.diemug.get()));}
        if (p == 20){generatedLoot.add(new ItemStack(Items.evilcandle.get()));}
        if (p == 21){generatedLoot.add(new ItemStack(Items.evilmug.get()));}
        if (p == 22){generatedLoot.add(new ItemStack(Items.obsidianring.get()));}

        return generatedLoot;
    }


    public Codec<abandoned_mineshaft> codec() {
        return CODEC;
    }
}



