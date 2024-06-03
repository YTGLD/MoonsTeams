package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.cell_zombie;
import com.moonstone.moonstonemod.entity.flysword;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTs {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoonStoneMod.MODID);
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.flysword>> flysword = REGISTRY.register("flysword",
            ()-> EntityType.Builder.of(flysword::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("flysword"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.suddenrain>> suddenrain = REGISTRY.register("suddenrain",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.suddenrain::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("suddenrain"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.cell_zombie>> cell_zombie = REGISTRY.register("cell_zombie",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.cell_zombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("cell_zombie"));


    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.cell_giant>> cell_giant = REGISTRY.register("cell_giant",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.cell_giant::new, MobCategory.MONSTER).sized(0.9F, 2.9F).clientTrackingRange(16).build("cell_giant"));

    @SubscribeEvent
    public static void  EntityAttributeCreationEvent(EntityAttributeCreationEvent event){
        event.put(EntityTs.cell_zombie.get(), Zombie.createAttributes().build());
        event.put(EntityTs.cell_giant.get(), com.moonstone.moonstonemod.entity.cell_giant.createAttributes().build());

    }
}
