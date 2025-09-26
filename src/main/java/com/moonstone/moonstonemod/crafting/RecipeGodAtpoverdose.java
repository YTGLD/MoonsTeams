package com.moonstone.moonstonemod.crafting;

import com.moonstone.moonstonemod.event.EquippedEvt;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.Items;
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

public class RecipeGodAtpoverdose extends CustomRecipe {
    private final CraftingBookCategory category;

    public final Item in = Items.atpoverdose.get();
    public final Item out = Items.god_atpoverdose.get();

    public RecipeGodAtpoverdose(CraftingBookCategory category, ResourceLocation resourceLocation) {
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
        for (int i = 0; i < craftingInput.getContainerSize(); ++i) {
            ItemStack currentStack = craftingInput.getItem(i);

            if (currentStack.getTag() != null && currentStack.getTag().getBoolean(NewEvent.lootTable)) {
                if (currentStack.is(in)) {
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
    public ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {

        ItemStack stack = out.getDefaultInstance();
        CompoundTag tag  = new CompoundTag();
        tag.putBoolean(EquippedEvt.isGod,true);
        stack.setTag(tag);

        return stack;
    }

    @Override

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return AllCrafting.RecipeGodAtpoverdose.get();
    }
}

