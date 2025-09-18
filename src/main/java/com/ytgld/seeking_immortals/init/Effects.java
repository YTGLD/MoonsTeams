package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.effect.blood;
import com.ytgld.seeking_immortals.effect.dead;
import com.ytgld.seeking_immortals.effect.invulnerable;
import com.ytgld.seeking_immortals.effect.life;
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

}
