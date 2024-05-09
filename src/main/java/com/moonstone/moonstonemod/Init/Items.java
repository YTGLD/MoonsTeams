package com.moonstone.moonstonemod.Init;

import com.moonstone.moonstonemod.Item.Common.*;
import com.moonstone.moonstonemod.Item.Ectoplasm.*;
import com.moonstone.moonstonemod.Item.Maulice.*;
import com.moonstone.moonstonemod.Item.NaNoDoom.magiceye;
import com.moonstone.moonstonemod.Item.NaNoDoom.magicstone;
import com.moonstone.moonstonemod.Item.NaNoDoom.nanocube;
import com.moonstone.moonstonemod.Item.NaNoDoom.nanorobot;
import com.moonstone.moonstonemod.Item.NaNoDoom.thedoomstone;
import com.moonstone.moonstonemod.Item.NaNoDoom.thefruit;
import com.moonstone.moonstonemod.Item.Nightmare.*;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoonStoneMod.MODID);
    public static final RegistryObject<Item> ectoplasmapple  =REGISTRY.register("ectoplasmapple", ectoplasmapple::new);
    public static final RegistryObject<Item> ectoplasmball  =REGISTRY.register("ectoplasmball", ectoplasmball::new);
    public static final RegistryObject<Item> ectoplasmbattery  =REGISTRY.register("ectoplasmbattery", ectoplasmbattery::new);
    public static final RegistryObject<Item> ectoplasmcharm  =REGISTRY.register("ectoplasmcharm", ectoplasmcharm::new);
    public static final RegistryObject<Item> ectoplasmcharmstone  =REGISTRY.register("ectoplasmcharmstone", ectoplasmcharmstone::new);
    public static final RegistryObject<Item> ectoplasmcloub  =REGISTRY.register("ectoplasmcloub", ectoplasmcloub::new);
    public static final RegistryObject<Item> ectoplasmcube  =REGISTRY.register("ectoplasmcube", ectoplasmcube::new);
    public static final RegistryObject<Item> ectoplasmfood  =REGISTRY.register("ectoplasmfood", ectoplasmfood::new);
    public static final RegistryObject<Item> ectoplasmhorseshoe  =REGISTRY.register("ectoplasmhorseshoe", ectoplasmhorseshoe::new);
    public static final RegistryObject<Item> ectoplasmprism  =REGISTRY.register("ectoplasmprism", ectoplasmprism::new);
    public static final RegistryObject<Item> ectoplasmshild  =REGISTRY.register("ectoplasmshild", ectoplasmshild::new);

    public static final RegistryObject<Item> mbattery  =REGISTRY.register("mbattery", mbattery::new);
    public static final RegistryObject<Item> mblock  =REGISTRY.register("mblock", mblock::new);
    public static final RegistryObject<Item> mbottle  =REGISTRY.register("mbottle", mbottle::new);
    public static final RegistryObject<Item> mbox  =REGISTRY.register("mbox", mbox::new);
    public static final RegistryObject<Item> meye  =REGISTRY.register("meye", meye::new);
    public static final RegistryObject<Item> mkidney  =REGISTRY.register("mkidney", mkidney::new);
    public static final RegistryObject<Item> morb  =REGISTRY.register("morb", morb::new);

    public static final RegistryObject<Item> mring  =REGISTRY.register("mring", mring::new);
    public static final RegistryObject<Item> mshell  =REGISTRY.register("mshell", mshell::new);

    public static final RegistryObject<Item> nightmareanchor  =REGISTRY.register("nightmareanchor", nightmareanchor::new);
    public static final RegistryObject<Item> nightmarecharm  =REGISTRY.register("nightmarecharm", nightmarecharm::new);
    public static final RegistryObject<Item> nightmareeye  =REGISTRY.register("nightmareeye", nightmareeye::new);
    public static final RegistryObject<Item> nightmaremoai  =REGISTRY.register("nightmaremoai", nightmaremoai::new);
    public static final RegistryObject<Item> nightmarerotten  =REGISTRY.register("nightmarerotten", nightmarerotten::new);
    public static final RegistryObject<Item> nightmarestone  =REGISTRY.register("nightmarestone", nightmarestone::new);
    public static final RegistryObject<Item> nightmaretreasure  =REGISTRY.register("nightmaretreasure", nightmaretreasure::new);
    public static final RegistryObject<Item> nightmarewater  =REGISTRY.register("nightmarewater", nightmarewater::new);


    public static final RegistryObject<Item>  badgeofthedead =REGISTRY.register("badgeofthedead", badgeofthedead::new);
    public static final RegistryObject<Item>  battery =REGISTRY.register("battery", battery::new);
    public static final RegistryObject<Item>  biggreedcrystal =REGISTRY.register("biggreedcrystal", biggreedcrystal::new);
    public static final RegistryObject<Item>  bigwarcrystal =REGISTRY.register("bigwarcrystal", bigwarcrystal::new);
    public static final RegistryObject<Item>  blackeorb =REGISTRY.register("blackeorb", blackeorb::new);
    public static final RegistryObject<Item>  blueamout =REGISTRY.register("blueamout", blueamout::new);
    public static final RegistryObject<Item>  greedamout =REGISTRY.register("greedamout", greedamout::new);
    public static final RegistryObject<Item>  greedcrystal =REGISTRY.register("greedcrystal", greedcrystal::new);
    public static final RegistryObject<Item>  redamout =REGISTRY.register("redamout", redamout::new);
    public static final RegistryObject<Item>  warcrystal =REGISTRY.register("warcrystal", warcrystal::new);
    public static final RegistryObject<Item>  whiteorb =REGISTRY.register("whiteorb", whiteorb::new);


    public static final RegistryObject<Item>  magiceye =REGISTRY.register("magiceye", magiceye::new);
    public static final RegistryObject<Item>  magicstone =REGISTRY.register("magicstone", magicstone::new);
    public static final RegistryObject<Item>  nanocube =REGISTRY.register("nanocube", nanocube::new);
    public static final RegistryObject<Item>  nanorobot =REGISTRY.register("nanorobot", nanorobot::new);
    public static final RegistryObject<Item>  thedoomstone =REGISTRY.register("thedoomstone", thedoomstone::new);
    public static final RegistryObject<Item>  thefruit =REGISTRY.register("thefruit", thefruit::new);


}
