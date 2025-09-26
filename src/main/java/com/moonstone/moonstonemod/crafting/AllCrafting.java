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

    public static final RegistryObject<RecipeSerializer<RecipeGodDNA>> DNA =
            REGISTRY.register("dna",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodDNA(category,resourceLocation)));







    public static final RegistryObject<RecipeSerializer<RecipeGodAmbush>> RecipeGodDNA =
            REGISTRY.register("god_ambush",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodAmbush(category,resourceLocation)));
    public static final RegistryObject<RecipeSerializer<RecipeGodAtpoverdose>> RecipeGodAtpoverdose =
            REGISTRY.register("god_atpoverdose",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodAtpoverdose(category,resourceLocation)));
    public static final RegistryObject<RecipeSerializer<RecipeGodPutrefactive>> RecipeGodPutrefactive =
            REGISTRY.register("god_putrefactive",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodPutrefactive(category,resourceLocation)));
    public static final RegistryObject<RecipeSerializer<RecipeGodFermentation>> RecipeGodFermentation =
            REGISTRY.register("god_fermentation",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodFermentation(category,resourceLocation)));
    public static final RegistryObject<RecipeSerializer<RecipeGodAutolytic>> RecipeGodAutolytic =
            REGISTRY.register("god_autolytic",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodAutolytic(category,resourceLocation)));
    public static final RegistryObject<RecipeSerializer<RecipeGodRegenerative>> RecipeGodRegenerative =
            REGISTRY.register("god_regenerative",
                    ()-> new SimpleCraftingRecipeSerializer<>((resourceLocation ,category)->
                            new RecipeGodRegenerative(category,resourceLocation)));
}
