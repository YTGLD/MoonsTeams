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
        SpecialRecipeBuilder.special(AllCrafting.UniverseCrafting.get())
                .save(recipeOutput,"universe");
    }
}
