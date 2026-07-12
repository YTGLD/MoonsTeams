package com.ytgld.moonstone.enttiy;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Moonstone.MODID)
public class EntityTs {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, Moonstone.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<AttackBlood>> attack_blood_ = REGISTRY.register("attack_blood", () ->
            EntityType.Builder.of(AttackBlood::new, MobCategory.MISC).sized(0.01f, 0.01f).clientTrackingRange(50).build(ResourceKey.create(Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Moonstone.MODID, "attack_blood"))));
    public static final DeferredHolder<EntityType<?>, EntityType<OwnerBlood>> owner_blood_ = REGISTRY.register("owner_blood", () ->
            EntityType.Builder.of(OwnerBlood::new, MobCategory.MISC).sized(0.01f, 0.01f).clientTrackingRange(50).build(ResourceKey.create(Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Moonstone.MODID, "owner_blood"))));
    public static final DeferredHolder<EntityType<?>, EntityType<CellZombie>> cell_zombie = REGISTRY.register("cell_zombie", () ->
            EntityType.Builder.of(CellZombie::new, MobCategory.MISC).sized(0.8f, 1.8f).clientTrackingRange(50).build(ResourceKey.create(Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Moonstone.MODID, "cell_zombie"))));
    public static final DeferredHolder<EntityType<?>, EntityType<CellGiant>> cell_giant = REGISTRY.register("cell_giant", () ->
            EntityType.Builder.of(CellGiant::new, MobCategory.MISC).sized(1.25f, 2.2f).clientTrackingRange(50).build(ResourceKey.create(Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Moonstone.MODID, "cell_giant"))));

    @SubscribeEvent
    public static void  EntityAttributeCreationEvent(EntityAttributeCreationEvent event){
        event.put(owner_blood_.get(), Zombie.createAttributes().build());
        event.put(cell_zombie.get(), Zombie.createAttributes().build());
        event.put(cell_giant.get(), CellGiant.createAttributes().build());
    }
}
