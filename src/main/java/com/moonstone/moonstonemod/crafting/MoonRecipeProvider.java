package com.moonstone.moonstonemod.crafting;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MoonRecipeProvider extends VanillaRecipeProvider {

    public MoonRecipeProvider(PackOutput p_250820_) {
        super(p_250820_);
    }


    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput) {

        SpecialRecipeBuilder.special(AllCrafting.RecipeGodDNA.get()).save(recipeOutput, "god_ambush");
        SpecialRecipeBuilder.special(AllCrafting.RecipeGodAtpoverdose.get()).save(recipeOutput, "god_atpoverdose");
        SpecialRecipeBuilder.special(AllCrafting.RecipeGodPutrefactive.get()).save(recipeOutput, "god_putrefactive");
        SpecialRecipeBuilder.special(AllCrafting.RecipeGodFermentation.get()).save(recipeOutput, "god_fermentation");
        SpecialRecipeBuilder.special(AllCrafting.RecipeGodAutolytic.get()).save(recipeOutput, "god_autolytic");
        SpecialRecipeBuilder.special(AllCrafting.RecipeGodRegenerative.get()).save(recipeOutput, "god_regenerative");
        SpecialRecipeBuilder.special(AllCrafting.DoNightmareBaseRecipe.get()).save(recipeOutput, "do_nightmare_base");


        SpecialRecipeBuilder.special(AllCrafting.DNA.get()).save(recipeOutput, "dna");

    }
}
