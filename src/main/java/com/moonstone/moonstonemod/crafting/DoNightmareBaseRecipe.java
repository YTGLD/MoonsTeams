package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DoNightmareBaseRecipe extends CustomRecipe {
    private final CraftingBookCategory category;
    public DoNightmareBaseRecipe(CraftingBookCategory category, ResourceLocation resourceLocation) {
        super(resourceLocation, category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingContainer craftingInput, @NotNull Level level) {
        for (int i = 0; i < craftingInput.getContainerSize(); ++i) {
            ItemStack currentStack = craftingInput.getItem(i);
            if (currentStack.is(Items.nightmare_base.get())) {
                return true;
            }
        }
        return false;
    }





    @Override
    public @NotNull ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        if (Config.SERVER.disFallRing.get()){
            return ItemStack.EMPTY;
        }else {
            return Items.the_divine_fall_ring.get().getDefaultInstance();
        }
    }

    @Override

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.DoNightmareBaseRecipe.get();
    }
}

