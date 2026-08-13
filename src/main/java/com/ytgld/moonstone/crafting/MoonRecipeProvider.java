package com.ytgld.moonstone.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class MoonRecipeProvider extends VanillaRecipeProvider {
    public MoonRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        SpecialRecipeBuilder.special(RecipeGodDNA::new).save(recipeOutput, "dna");
    }
}
