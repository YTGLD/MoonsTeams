package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllCrafting {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MoonStoneMod.MODID);
    public static final RegistryObject<RecipeSerializer<UniverseCrafting>> UniverseCrafting =
            REGISTRY.register("universe",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new UniverseCrafting(category, resourceLocation)));

}
