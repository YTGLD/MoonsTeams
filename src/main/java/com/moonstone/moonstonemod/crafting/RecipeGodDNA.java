package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.event.EquippedEvt;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.item.TheNecora.god.CanUPLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeGodDNA  extends CustomRecipe {
    private final CraftingBookCategory category;


    public RecipeGodDNA(CraftingBookCategory category, ResourceLocation resourceLocation) {
        super(resourceLocation, category);
        this.category = category;
    }
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingContainer craftingInput, @NotNull Level level) {
        int count = 0;
        List<Item> arrayList = new ArrayList<>();

        for (int i = 0; i < craftingInput.getContainerSize(); ++i) {
            ItemStack currentStack = craftingInput.getItem(i);
            arrayList.add(currentStack.getItem());
            if (!(currentStack.getItem() instanceof CanUPLevel)) {
                if (currentStack.getTag() != null && currentStack.getTag().getBoolean(NewEvent.lootTable)) {
                    count++;
                    if (count > 2) {
                        return false;
                    }
                }
            }
        }
        if (arrayList.size()>2){
            return false;
        }
        return count == 2;
    }




    @Override
    public ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        int count = 0;
        for (int i = 0; i < craftingContainer.getContainerSize(); ++i) {
            ItemStack currentStack = craftingContainer.getItem(i);
            if (currentStack.getTag() != null && currentStack.getTag().getBoolean(NewEvent.lootTable)) {
                count++;
                if (count == 2) {
                    ItemStack stack = currentStack.copy();
                    CompoundTag compoundTag = new CompoundTag();
                    compoundTag.putBoolean(EquippedEvt.isGod, true);
                    stack.setTag(compoundTag);
                    return stack;
                }
            }
        }
        return ItemStack.EMPTY;
    }


    @Override

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.DNA.get();
    }
}


