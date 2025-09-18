package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;

public class DamageTps {
    public static final ResourceKey<DamageType> abyss_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MoonStoneMod.MODID, "abyss"));

    public static DamageSource abyssDamage(LivingEntity causer) {
        if (!causer.level().isClientSide) {
            return new DamageSource(
                    causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(abyss_DAMAGE),
                    causer);
        }else {
            return causer.damageSources().dryOut();
        }
    }

    public static final ResourceKey<DamageType> swordDamage_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MoonStoneMod.MODID, "sword_damage"));
    public static DamageSource swordDamage(LivingEntity causer) {
        if (!causer.level().isClientSide) {
            return new DamageSource(
                    causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(swordDamage_DAMAGE),
                    causer);
        }else {
            return causer.damageSources().dryOut();
        }
    }
}
