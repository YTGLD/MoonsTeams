package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MSound {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoonStoneMod.MODID);
    public static final RegistryObject<SoundEvent> black_hold  = REGISTRY.register("black_hold",()->{
        return SoundEvent.createVariableRangeEvent(new ResourceLocation(MoonStoneMod.MODID,"black_hold"));
    });
    public static final RegistryObject<SoundEvent> kidney_die  = REGISTRY.register("kidney_die",()->{
        return SoundEvent.createVariableRangeEvent(new ResourceLocation(MoonStoneMod.MODID,"kidney_die"));
    });
    public static final RegistryObject<SoundEvent> kidney  = REGISTRY.register("kidney",()->{
        return SoundEvent.createVariableRangeEvent(new ResourceLocation(MoonStoneMod.MODID,"kidney"));
    });

}
