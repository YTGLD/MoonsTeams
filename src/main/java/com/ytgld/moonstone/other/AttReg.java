package com.ytgld.moonstone.other;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, Moonstone.MODID);
    public static final DeferredHolder<Attribute,?> heal = REGISTRY.register("heal",()->{
        return new RangedAttribute("attribute.name.chest_item.heal", 1, -1024, 1024).setSyncable(true);
    });
}
