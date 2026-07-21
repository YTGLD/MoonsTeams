package com.ytgld.moonstone.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class MoonRecipeProvider extends VanillaRecipeProvider {
    public MoonRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }
    @Override
    protected void buildRecipes() {
        SpecialRecipeBuilder.special(RecipeGodDNA::new).save(output, "dna");
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
            return new MoonRecipeProvider(lookupProvider, output);
        }

        @Override
        public String getName() {
            return "Moon Recipe Provider";
        }
    }
}
