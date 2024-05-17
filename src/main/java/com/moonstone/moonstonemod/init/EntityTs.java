package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.flysword;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTs {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoonStoneMod.MODID);
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.flysword>> flysword = REGISTRY.register("flysword",
            ()-> EntityType.Builder.of(flysword::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("flysword"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.suddenrain>> suddenrain = REGISTRY.register("suddenrain",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.suddenrain::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("suddenrain"));



}
