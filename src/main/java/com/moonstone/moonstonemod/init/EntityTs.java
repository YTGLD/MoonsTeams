package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.other.flysword;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTs {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoonStoneMod.MODID);
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.flysword>> flysword = REGISTRY.register("flysword",
            ()-> EntityType.Builder.of(flysword::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("flysword"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.suddenrain>> suddenrain = REGISTRY.register("suddenrain",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.suddenrain::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("suddenrain"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.necora.cell_zombie>> cell_zombie = REGISTRY.register("cell_zombie",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.necora.cell_zombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("cell_zombie"));


    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.necora.cell_giant>> cell_giant = REGISTRY.register("cell_giant",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.necora.cell_giant::new, MobCategory.MONSTER).sized(0.9F, 2.9F).clientTrackingRange(16).build("cell_giant"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.nightmare_entity>> nightmare_entity = REGISTRY.register("nightmare_entity",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.nightmare_entity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(16).build("nightmare_entity"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.test_e>> test_e = REGISTRY.register("test_e",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.test_e::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(16).build("test_e"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.bloodvruis.test_blood>> test_blood = REGISTRY.register("test_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.test_blood::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("test_blood"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.bloodvruis.blood_bat>> blood_bat = REGISTRY.register("blood_bat",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.blood_bat::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("blood_bat"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.red_entity>> red_entity = REGISTRY.register("red_entity",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.red_entity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(16).build("red_entity"));


    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.necora.nightmare_giant>> nightmare_giant = REGISTRY.register("nightmare_giant",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.necora.nightmare_giant::new, MobCategory.MONSTER).sized(0.9F, 2.9F).clientTrackingRange(16).build("nightmare_giant"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.sword>> sword = REGISTRY.register("sword",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.sword::new, MobCategory.MONSTER).sized(0.1f, 0.1f).clientTrackingRange(16).build("sword"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.bolt>> bolt = REGISTRY.register("bolt",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.bolt::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("bolt"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.bolt_light>> bolt_light = REGISTRY.register("bolt_light",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.bolt_light::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("bolt_light"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.bule_bolt>> bule_bolt = REGISTRY.register("bule_bolt",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.bule_bolt::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("bule_bolt"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.necora.cell_slime>> cell_slime = REGISTRY.register("cell_slime",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.necora.cell_slime::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(16).build("cell_slime"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.necora.small_zombie>> small_zombie = REGISTRY.register("small_zombie",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.necora.small_zombie::new, MobCategory.MONSTER).sized(0.25f, 1).clientTrackingRange(16).build("small_zombie"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.blood_zombie_fly>> blood_zombie_fly = REGISTRY.register("blood_zombie_fly",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.blood_zombie_fly::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("blood_zombie_fly"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.blood_zombie_boom>> blood_zombie_boom = REGISTRY.register("blood_zombie_boom",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.blood_zombie_boom::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("blood_zombie_boom"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.line>> line = REGISTRY.register("line",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.line::new, MobCategory.MONSTER).sized(1, 0.2f).clientTrackingRange(16).build("line"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.snake>> snake = REGISTRY.register("snake",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.snake::new, MobCategory.MONSTER).sized(1, 0.2f).clientTrackingRange(16).build("snake"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.blood>> blood = REGISTRY.register("blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.blood::new, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(16).build("blood"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.attack_blood>> attack_blood = REGISTRY.register("attack_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.attack_blood::new, MobCategory.MISC).sized(0.5f, 0.2f).clientTrackingRange(16).build("attack_blood"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.owner_blood>> owner_blood = REGISTRY.register("owner_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.owner_blood::new, MobCategory.MISC).sized(0.5f, 0.2f).clientTrackingRange(16).build("owner_blood"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.blood_orb_owner>> blood_orb_owner = REGISTRY.register("blood_orb_owner",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.blood_orb_owner::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("blood_orb_owner"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.blood_orb_attack>> blood_orb_attack = REGISTRY.register("blood_orb_attack",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.blood_orb_attack::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("blood_orb_attack"));

    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.other.blood_orb_small>> blood_orb_small = REGISTRY.register("blood_orb_small",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.other.blood_orb_small::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("blood_orb_small"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.sun>> sun = REGISTRY.register("sun",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.sun::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("sun"));
    public static final RegistryObject<EntityType<com.moonstone.moonstonemod.entity.as_sword>> as_sword = REGISTRY.register("as_sword",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.as_sword::new, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(16).build("as_sword"));

    @SubscribeEvent
    public static void  EntityAttributeCreationEvent(EntityAttributeCreationEvent event){
        event.put(EntityTs.cell_zombie.get(), Zombie.createAttributes().build());
        event.put(EntityTs.cell_giant.get(), com.moonstone.moonstonemod.entity.necora.cell_giant.createAttributes().build());
        event.put(EntityTs.nightmare_entity.get(), Bat.createAttributes().build());
        event.put(EntityTs.red_entity.get(), Zombie.createAttributes().build());
        event.put(EntityTs.nightmare_giant.get(), Warden.createAttributes().build());
        event.put(EntityTs.test_e.get(), Warden.createAttributes().build());
        event.put(EntityTs.test_blood.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_bat.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_zombie_boom.get(), Zombie.createAttributes().build());
        event.put(EntityTs.line.get(), Zombie.createAttributes().build());
        event.put(EntityTs.snake.get(), Bat.createAttributes().build());
        event.put(EntityTs.owner_blood.get(), IronGolem.createAttributes().build());
        event.put(EntityTs.blood_orb_owner.get(), IronGolem.createAttributes().build());
        event.put(EntityTs.small_zombie.get(), com.moonstone.moonstonemod.entity.necora.small_zombie.createAttributes().build());
        event.put(EntityTs.bolt.get(), Warden.createAttributes().build());
        event.put(EntityTs.sword.get(), Warden.createAttributes().build());
        event.put(EntityTs.bolt_light.get(), Warden.createAttributes().build());
        event.put(EntityTs.bule_bolt.get(), Warden.createAttributes().build());
        event.put(EntityTs.cell_slime.get(), com.moonstone.moonstonemod.entity.necora.cell_slime.createAttributes().build());

    }
}
