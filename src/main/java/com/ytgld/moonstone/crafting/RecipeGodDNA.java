package com.ytgld.moonstone.crafting;

import com.ytgld.moonstone.event.EquippedEvt;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RecipeGodDNA extends CustomRecipe {
    private final CraftingBookCategory category;


    public RecipeGodDNA(CraftingBookCategory category) {
        super(category);
        this.category = CraftingBookCategory.MISC;
    }

    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }


    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        int count = 0;
        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack currentStack = craftingInput.getItem(i);
            if (currentStack.get(DataReg.tag) != null && currentStack.get(DataReg.tag).getBoolean(EquippedEvt.lootTable)) {
                if (currentStack.getItem() instanceof CanUPLevel) {
                    count++;
                    if (count > 2) {
                        return false;
                    }
                }
            }
        }
        return count == 2;
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack currentStack = craftingInput.getItem(i);
            if (currentStack.getItem() instanceof CanUPLevel canUPLevel) {
                ItemStack stack = canUPLevel.upLevelItem().getDefaultInstance();
                CompoundTag tag = new CompoundTag();
                tag.putBoolean(EquippedEvt.isGod, true);
                stack.set(DataReg.tag, tag);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return i >= 2 && i1 >= 2;
    }
    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AllCrafting.RecipeGodDNA.get();
    }
}
