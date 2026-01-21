package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.LoveCharm;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.item.ectoplasm.ectoplasmapple;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MyFriends {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoonStoneMod.MODID);
    public static final RegistryObject<Item> LoveCharm  =REGISTRY.register("love_charm",
            ()-> new LoveCharm());

}
