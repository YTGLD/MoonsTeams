package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Effects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SeekingImmortalsMod.MODID);
    public static final RegistryObject<MobEffect> dead  =REGISTRY.register("dead",()->new dead());
    public static final RegistryObject<MobEffect> invulnerable  =REGISTRY.register("invulnerable",()->new invulnerable());
    public static final RegistryObject<MobEffect> blood  =REGISTRY.register("blood",()->new blood());
    public static final RegistryObject<MobEffect> life  =REGISTRY.register("life",()->new life());
    public static final RegistryObject<MobEffect> life_apple  =REGISTRY.register("life_apple",()->new life_apple());

    public static final RegistryObject<MobEffect> hidden  =REGISTRY.register("hidden",()->new hidden());
    public static final RegistryObject<MobEffect> blade  =REGISTRY.register("blade",()->new blade());
    public static final RegistryObject<MobEffect> meet  =REGISTRY.register("meet",()->new meet());
    public static final RegistryObject<MobEffect> debilitating  =REGISTRY.register("debilitating",()->new debilitating());
}
