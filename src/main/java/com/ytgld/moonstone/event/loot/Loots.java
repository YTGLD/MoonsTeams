package com.ytgld.moonstone.event.loot;

import com.mojang.serialization.MapCodec;
import com.ytgld.moonstone.Moonstone;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class Loots {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Moonstone.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>,?> LOOT_chest = LOOT.register("loot",(Identifier)->{
        return ChestLoot.CODEC.get();
    });
}
