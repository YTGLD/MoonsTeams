package com.ytgld.moonstone.other;

import com.mojang.serialization.Codec;
import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Moonstone.MODID)
public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, Moonstone.MODID);

    public static final DeferredHolder<Attribute, ?> heal = REGISTRY.register("heal", () -> {
        return new RangedAttribute("attribute.name.moonstone.heal", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute, ?> cit = REGISTRY.register("cit", () -> {
        return new RangedAttribute("attribute.name.moonstone.cit", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute, ?> speed = REGISTRY.register("speed", () -> {
        return new RangedAttribute("attribute.name.moonstone.speed", 1, -1024, 1024).setSyncable(true);
    });

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Moonstone.MODID);

    public static final Supplier<AttachmentType<Integer>> cooldownZombie = ATTACHMENT_TYPES.register(
            "cooldown_zombie", () -> AttachmentType.builder(() -> 0).sync(new IntSyncHandler())
                    .serialize(Codec.INT.fieldOf("cooldown_zombie")).build()
    );
    public static final Supplier<AttachmentType<Float>> nightmareShieldTypeSupplier = ATTACHMENT_TYPES.register(
            "nightmare_shield", () -> AttachmentType.builder(() -> 0f).sync(new SyncHandler())
                    .serialize(Codec.FLOAT.fieldOf("nightmare_shield")).build()
    );
    public static final DeferredHolder<Attribute,?> nightmare_shield = REGISTRY.register("nightmare_shield",()->{
        return new RangedAttribute("attribute.name.moonstone.nightmare_shield", 0, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> nightmare_stronger = REGISTRY.register("nightmare_stronger",()->{
        return new RangedAttribute("attribute.name.moonstone.nightmare_stronger", 1, -1024, 1024).setSyncable(true);
    });
    public static final Supplier<AttachmentType<Integer>> nightmareShieldCooldownDataAttachmentType = ATTACHMENT_TYPES.register(
            "nightmare_shield_cooldown_data", () -> AttachmentType.builder(() -> 0).sync(new IntSyncHandler()).serialize(Codec.INT.
                    fieldOf("nightmare_shield_cooldown_data")).build()
    );

    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, AttReg.heal, 1);
        event.add(EntityType.PLAYER, AttReg.cit, 1);
        event.add(EntityType.PLAYER, AttReg.speed, 1);
        event.add(EntityType.PLAYER, AttReg.nightmare_shield, 0);
        event.add(EntityType.PLAYER, AttReg.nightmare_stronger, 1);

    }
}
