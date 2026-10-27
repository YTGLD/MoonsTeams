package com.ytgld.moonstone.crafting;

import com.mojang.serialization.MapCodec;
import com.ytgld.moonstone.event.EquippedEvt;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RecipeGodDNA extends CustomRecipe {
    private final CraftingBookCategory category;
    public static final RecipeSerializer<RecipeGodDNA> SERIALIZER;
    public static final RecipeGodDNA INSTANCE = new RecipeGodDNA();
    public static final MapCodec<RecipeGodDNA> MAP_CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeGodDNA> STREAM_CODEC;
    public RecipeGodDNA() {
        super();
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
            if (currentStack.get(DataReg.tag) != null && currentStack.get(DataReg.tag).getBooleanOr(EquippedEvt.lootTable,false)) {
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
    public ItemStack assemble(CraftingInput craftingInput) {
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
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AllCrafting.RecipeGodDNA.get();
    }
    static {
        MAP_CODEC = MapCodec.unit(INSTANCE);
        STREAM_CODEC = StreamCodec.unit(INSTANCE);
        SERIALIZER = new RecipeSerializer(MAP_CODEC, STREAM_CODEC);
    }
}
