package com.ytgld.moonstone.crafting;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCrafting {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Moonstone.MODID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RecipeGodDNA>> RecipeGodDNA =
            REGISTRY.register("dna",
                    ()-> new RecipeSerializer<RecipeGodDNA>(com.ytgld.moonstone.crafting.RecipeGodDNA.MAP_CODEC, com.ytgld.moonstone.crafting.RecipeGodDNA.STREAM_CODEC));

}
