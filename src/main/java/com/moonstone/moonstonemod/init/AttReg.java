package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MoonStoneMod.MODID);
    public static final RegistryObject<Attribute> heal  =REGISTRY.register("heal", ()->{
        return new RangedAttribute("attribute.name.moonstone.heal", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });


}
