package com.ytgld.moonstone.effect;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Effects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, Moonstone.MODID);
    public static final DeferredHolder<MobEffect, ?> life = REGISTRY.register("life", () -> new Life());
    public static final DeferredHolder<MobEffect, ?> life_apple = REGISTRY.register("life_apple", () -> new Life_apple());
    public static final DeferredHolder<MobEffect, ?> dead = REGISTRY.register("dead", () -> new Dead());

    public static final DeferredHolder<MobEffect, ?> hidden = REGISTRY.register("hidden", () -> new Hidden());
    public static final DeferredHolder<MobEffect, ?> blade = REGISTRY.register("blade", () -> new Blade());
}
