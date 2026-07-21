package com.ytgld.moonstone.other;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Moonstone.MODID)
public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, Moonstone.MODID);

    public static final DeferredHolder<Attribute, ?> heal = REGISTRY.register("heal", () -> {
        return new RangedAttribute("attribute.name.chest_item.heal", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute, ?> cit = REGISTRY.register("cit", () -> {
        return new RangedAttribute("attribute.name.chest_item.cit", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute, ?> speed = REGISTRY.register("speed", () -> {
        return new RangedAttribute("attribute.name.chest_item.speed", 1, -1024, 1024).setSyncable(true);
    });
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeModificationEvent event) {
        event.add(EntityTypes.PLAYER, AttReg.heal, 1);
        event.add(EntityTypes.PLAYER, AttReg.cit, 1);
        event.add(EntityTypes.PLAYER, AttReg.speed, 1);

    }
}
