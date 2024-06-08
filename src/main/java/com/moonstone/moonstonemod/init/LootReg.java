package com.moonstone.moonstonemod.init;


import com.mojang.serialization.Codec;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.loot.DungeonLoot;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootReg {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MoonStoneMod.MODID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> TD = REGISTRY.register("dungeon",()->DungeonLoot.CODEC);
}
