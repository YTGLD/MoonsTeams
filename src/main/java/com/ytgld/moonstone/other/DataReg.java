package com.ytgld.moonstone.other;

import com.mojang.serialization.Codec;
import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataReg {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Moonstone.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> tag =
            REGISTRY.register("tag", () -> DataComponentType.<CompoundTag>builder().persistent(CompoundTag.CODEC).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Set<String>>> theSetString =
            REGISTRY.register("set", () -> DataComponentType.<Set<String>>builder().
                    persistent(Codec.STRING.listOf()
                            .xmap(HashSet::new, ArrayList::new)).build());
}


