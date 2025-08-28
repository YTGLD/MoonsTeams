package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.universe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class UniverseCrafting  extends CustomRecipe {
    private final CraftingBookCategory category;


    public UniverseCrafting(CraftingBookCategory category, ResourceLocation resourceLocation) {
        super(resourceLocation, category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }
    public void addTag(ItemStack itemStack) {
        if (itemStack.getTag() == null) {
            itemStack.getOrCreateTag();
        }
    }

    @Override
    public boolean matches(CraftingContainer craftingContainer, Level level) {
        boolean hasFirstItem = false;
        boolean hasSecondItem = false;

        int must = 0;

        ItemStack me = ItemStack.EMPTY;
        ItemStack other = ItemStack.EMPTY;


        for (int i = 0; i < craftingContainer.getContainerSize(); ++i) {
            ItemStack itemStack = craftingContainer.getItem(i).copy();
            if (!itemStack.isEmpty()) {
                if (itemStack.is(Items.universe.get())) {
                    if (itemStack.getTag() == null) {
                        itemStack.getOrCreateTag();
                    }

                    if (itemStack.getTag() != null
                            && itemStack.getTag().getInt(universe.doAsUniverse) < universe.universeSize) {
                        me =itemStack;
                        hasFirstItem = true;
                        must++;
                    }
                }
            }
        }
        for (int i = 0; i < craftingContainer.getContainerSize(); ++i) {
            ItemStack itemStack = craftingContainer.getItem(i).copy();
            if (!itemStack.is(Items.universe.get())) {
                if (BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getNamespace().equals(MoonStoneMod.MODID)) {
                    if (itemStack.getItem() instanceof ICurioItem) {
                        other = itemStack;
                        hasSecondItem = true;
                        must++;
                    }
                }
            }
        }

        if (other == me){
            return false;
        }
        return hasFirstItem && hasSecondItem&&must==2;
    }
    @Override
    public ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        ItemStack universeItem = ItemStack.EMPTY;

        for (int i = 0; i < craftingContainer.getContainerSize(); ++i) {
            ItemStack itemStack = craftingContainer.getItem(i).copy();
            if (itemStack.is(Items.universe.get())) {
                addTag(itemStack);
                if (itemStack.getTag().getInt(universe.doAsUniverse) < universe.universeSize) {
                    universeItem = itemStack;
                    break;
                }
            }
        }

        if (universeItem.isEmpty())
            return ItemStack.EMPTY;

        for (int j = 0; j < craftingContainer.getContainerSize(); ++j) {
            ItemStack other = craftingContainer.getItem(j).copy();
            if (!other.isEmpty() && other.getItem() instanceof ICurioItem && BuiltInRegistries.ITEM.getKey(other.getItem()).getNamespace().equals(MoonStoneMod.MODID)) {
                String name = other.getItem().getDescriptionId();
                universeItem.getTag().putString(name, name);
                universeItem.getTag().putInt(universe.doAsUniverse, universeItem.getTag().getInt(universe.doAsUniverse) + 1);
            }
        }

        return universeItem;
    }

    @Override

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.UniverseCrafting.get();
    }
}

