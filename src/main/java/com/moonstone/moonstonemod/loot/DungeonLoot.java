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
        int W = Mth.nextInt(RandomSource.create(), 1, 30);
        int p = Mth.nextInt(RandomSource.create(), 1, 50);
        int N = Mth.nextInt(RandomSource.create(), 1, 45);
        int a = Mth.nextInt(RandomSource.create(), 1, 25);
        int ne = Mth.nextInt(RandomSource.create(), 1, 35);
        int T = Mth.nextInt(RandomSource.create(), 1, 25);

        int cell = Mth.nextInt(RandomSource.create(), 1, 33);

        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                if (entity instanceof Player player) {

                    if (Handler.hascurio(player, Items.necora.get())){
                        if (Handler.hascurio(player, Items.cell.get())) {
                            if (cell == 1) {
                                generatedLoot.add(new ItemStack(Items.cell.get()));
                            }
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
                        }else {
                            generatedLoot.add(new ItemStack(Items.cell.get()));
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
                }

                if (T == 3) {
                    generatedLoot.add(new ItemStack(Items.plague.get()));
                }
                if (T == 4) {
                    generatedLoot.add(new ItemStack(Items.twistedstone.get()));
                }
                if (T == 5) {
                    generatedLoot.add(new ItemStack(Items.ectoplasmstone.get()));
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
                    }
                }

                if (p == 1) {
                    generatedLoot.add(3, Items.ectoplasmball.get().getDefaultInstance());
                }
                if (p == 2) {
                    generatedLoot.add(2, Items.ectoplasmcloub.get().getDefaultInstance());
                }
                if (p == 3) {
                    generatedLoot.add(1, Items.ectoplasmcube.get().getDefaultInstance());
                }

                if (p == 6) {
                    generatedLoot.add(new ItemStack(Items.badgeofthedead.get()));
                }
                if (p == 7) {
                    generatedLoot.add(new ItemStack(Items.battery.get()));
                }
                if (p == 10) {
                    generatedLoot.add(new ItemStack(Items.blackeorb.get()));
                }
                if (p == 11) {
                    generatedLoot.add(new ItemStack(Items.blueamout.get()));
                }
                if (p == 12) {
                    generatedLoot.add(new ItemStack(Items.greedamout.get()));
                }
                if (p == 13) {
                    generatedLoot.add(new ItemStack(Items.greedcrystal.get()));
                }
                if (p == 14) {
                    generatedLoot.add(new ItemStack(Items.redamout.get()));
                }
                if (p == 15) {
                    generatedLoot.add(new ItemStack(Items.warcrystal.get()));
                }
                if (p == 16) {
                    generatedLoot.add(new ItemStack(Items.whiteorb.get()));
                }
                if (p == 17) {
                    generatedLoot.add(new ItemStack(Items.soulbattery.get()));
                }
                if (p == 18) {
                    generatedLoot.add(new ItemStack(Items.soulcube.get()));
                }
                if (p == 19) {
                    generatedLoot.add(new ItemStack(Items.diemug.get()));
                }
                if (p == 20) {
                    generatedLoot.add(new ItemStack(Items.evilcandle.get()));
                }
                if (p == 21) {
                    generatedLoot.add(new ItemStack(Items.evilmug.get()));
                }
                if (p == 22) {
                    generatedLoot.add(new ItemStack(Items.obsidianring.get()));
                }
                if (p == 23) {
                    generatedLoot.add(new ItemStack(Items.magicstone.get()));
                }
                if (p == 24) {
                    generatedLoot.add(new ItemStack(Items.magiceye.get()));
                }
            }
            if (idSting.contains("city")) {
                if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player) {
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
                if (p == 19) {
                    generatedLoot.add(new ItemStack(Items.nanocube.get()));
                }
                if (p == 20) {
                    generatedLoot.add(new ItemStack(Items.nanorobot.get()));
                }
                if (p == 21) {
                    generatedLoot.add(new ItemStack(Items.thedoomstone.get()));
                }
                if (p == 22) {
                    generatedLoot.add(new ItemStack(Items.thefruit.get()));
                }
                if (p == 23) {
                    generatedLoot.add(new ItemStack(Items.flyeye.get()));
                }
                if (p == 24) {
                    generatedLoot.add(new ItemStack(Items.doomeye.get()));
                }
                if (p == 25) {
                    generatedLoot.add(new ItemStack(Items.doomswoud.get()));
                }
                if (p == 26) {
                    generatedLoot.add(new ItemStack(Items.wind.get()));
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
