package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MoonStoneMod.MODID);
    public static final RegistryObject<Attribute> heal  =REGISTRY.register("heal", ()->{
        return new RangedAttribute("attribute.name.moonstone.heal", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });
    public static final RegistryObject<Attribute> cit  =REGISTRY.register("cit", ()->{
        return new RangedAttribute("attribute.name.moonstone.cit", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });
    public static final RegistryObject<Attribute> all_attack  =REGISTRY.register("all_attack", ()->{
        return new RangedAttribute("attribute.name.moonstone.all_attack", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });
    public static final RegistryObject<Attribute> break_speed  =REGISTRY.register("break_speed", ()->{
        return new RangedAttribute("attribute.name.moonstone.break_speed", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });

    public static final RegistryObject<Attribute> hurt  =REGISTRY.register("hurt", ()->{
        return new RangedAttribute("attribute.name.moonstone.hurt", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });
    public static final RegistryObject<Attribute> speed  =REGISTRY.register("speed", ()->{
        return new RangedAttribute("attribute.name.moonstone.speed", 1.0d, 0.0D, 1024.0D).setSyncable(true);
    });

    public static final RegistryObject<Attribute> zombie_attack_damage = REGISTRY.register("zombie_attack_damage",()->{
        return new RangedAttribute("attribute.name.moonstone.zombie_attack_damage", 1, -1024, 1024).setSyncable(true);
    });


    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeModificationEvent event){
        event.add(EntityType.PLAYER , AttReg.heal.get(),1);
        event.add(EntityType.PLAYER , AttReg.all_attack.get(),1);
        event.add(EntityType.PLAYER , AttReg.cit.get(),1);
        event.add(EntityType.PLAYER , AttReg.hurt.get(),1);
        event.add(EntityType.PLAYER , AttReg.break_speed.get(),1);
        event.add(EntityType.PLAYER , AttReg.zombie_attack_damage.get(),1);
        event.add(EntityType.PLAYER , AttReg.speed.get(),1);

    }
}
