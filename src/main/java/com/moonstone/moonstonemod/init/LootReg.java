package com.moonstone.moonstonemod.init;


import com.mojang.serialization.Codec;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.loot.*;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootReg {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MoonStoneMod.MODID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> AM = REGISTRY.register("abandoned_mineshaft",()->abandoned_mineshaft.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> AC = REGISTRY.register("ancient_city",()->ancient_city.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SD = REGISTRY.register("simple_dungeon",()->simple_dungeon.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> EC = REGISTRY.register("end_city",()->end_city.CODEC);
}
