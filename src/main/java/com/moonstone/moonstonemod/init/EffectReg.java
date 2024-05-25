package com.moonstone.moonstonemod.init;

import com.mojang.blaze3d.shaders.Effect;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.effect.FearEffect;
import com.moonstone.moonstonemod.item.ectoplasm.ectoplasmapple;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectReg {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MoonStoneMod.MODID);
    public static final RegistryObject<MobEffect> fear  =REGISTRY.register("fear", FearEffect::new);

}
