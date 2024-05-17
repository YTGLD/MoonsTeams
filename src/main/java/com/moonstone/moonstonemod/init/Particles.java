package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Particles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES;

    public static final RegistryObject<SimpleParticleType> gold;
    public static final RegistryObject<SimpleParticleType> blue;
    public static final RegistryObject<SimpleParticleType> popr;

    static {
        PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MoonStoneMod.MODID);

        gold = PARTICLE_TYPES.register("red", ()->{
            return new SimpleParticleType(false);
        });
        blue = PARTICLE_TYPES.register("blue", ()->{
            return new SimpleParticleType(false);
        });
        popr = PARTICLE_TYPES.register("popr", ()->{
            return new SimpleParticleType(false);
        });
    }
}
