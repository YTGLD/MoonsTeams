package com.moonstone.moonstonemod.Init;

import com.moonstone.moonstonemod.Item.Common.*;
import com.moonstone.moonstonemod.Item.Ectoplasm.*;
import com.moonstone.moonstonemod.Item.Amout.*;
import com.moonstone.moonstonemod.Item.Soul.*;
import com.moonstone.moonstonemod.Item.UnCommon.*;
import com.moonstone.moonstonemod.Item.Plague.ALL.*;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.ex.*;
import com.moonstone.moonstonemod.Item.Plague.medicine.med.*;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.Skill.*;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.*;
import com.moonstone.moonstonemod.Item.Plague.medicine.extend.*;
import com.moonstone.moonstonemod.Item.Plague.medicine.TheNecora.*;
import com.moonstone.moonstonemod.Item.Plague.*;

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
    public static final RegistryObject<Item>  ectoplasmstone =REGISTRY.register("ectoplasmstone", ectoplasmstone::new);
    public static final RegistryObject<Item>  twistedstone =REGISTRY.register("twistedstone", twistedstone::new);

    public static final RegistryObject<Item>  soulbattery =REGISTRY.register("soulbattery", soulbattery::new);
    public static final RegistryObject<Item>  soulcube =REGISTRY.register("soulcube", soulcube::new);

    public static final RegistryObject<Item>  diemug =REGISTRY.register("diemug", diemug::new);
    public static final RegistryObject<Item>  evilcandle =REGISTRY.register("evilcandle",evilcandle::new);
    public static final RegistryObject<Item>  evilmug =REGISTRY.register("evilmug", evilmug::new);
    public static final RegistryObject<Item>  obsidianring =REGISTRY.register("obsidianring", obsidianring::new);


    public static final RegistryObject<Item>  dna =REGISTRY.register("dna",dna::new);
    public static final RegistryObject<Item>  fungus =REGISTRY.register("fungus",fungus::new);
    public static final RegistryObject<Item>  germ =REGISTRY.register("germ",germ::new);
    public static final RegistryObject<Item>  parasite =REGISTRY.register("parasite",parasite::new);
    public static final RegistryObject<Item>  virus =REGISTRY.register("virus",virus::new);
    public static final RegistryObject<Item>  botton =REGISTRY.register("botton",botton::new);
    public static final RegistryObject<Item>  catalyzer =REGISTRY.register("catalyzer",catalyzer::new);


    public static final RegistryObject<Item>  calcification =REGISTRY.register("calcification",calcification::new);
    public static final RegistryObject<Item>  masticatory =REGISTRY.register("masticatory",masticatory::new);
    public static final RegistryObject<Item>  polyphagia =REGISTRY.register("polyphagia",polyphagia::new);
    public static final RegistryObject<Item>  quadriceps =REGISTRY.register("quadriceps",quadriceps::new);
    public static final RegistryObject<Item>  reanimation =REGISTRY.register("reanimation",reanimation::new);
    public static final RegistryObject<Item>  batskill =REGISTRY.register("batskill",batskill::new);




    public static final RegistryObject<Item> batgene =REGISTRY.register("batgene",batgene::new);
    public static final RegistryObject<Item> bloodgene =REGISTRY.register("bloodgene",bloodgene::new);
    public static final RegistryObject<Item> flygene =REGISTRY.register("flygene",flygene::new);
    public static final RegistryObject<Item> heathgene =REGISTRY.register("heathgene",heathgene::new);
    public static final RegistryObject<Item> ragegene =REGISTRY.register("ragegene",ragegene::new);
    public static final RegistryObject<Item> sleepgene =REGISTRY.register("sleepgene",sleepgene::new);
    public static final RegistryObject<Item> medicinebox =REGISTRY.register("medicinebox",medicinebox::new);
    public static final RegistryObject<Item> apple =REGISTRY.register("apple",apple::new);




    public static final RegistryObject<Item> ambush =REGISTRY.register("ambush",ambush::new);
    public static final RegistryObject<Item> atpoverdose =REGISTRY.register("atpoverdose",atpoverdose::new);
    public static final RegistryObject<Item> autolytic =REGISTRY.register("autolytic",autolytic::new);
    public static final RegistryObject<Item> fermentation =REGISTRY.register("fermentation",fermentation::new);
    public static final RegistryObject<Item> putrefactive =REGISTRY.register("putrefactive",putrefactive::new);
    public static final RegistryObject<Item> regenerative =REGISTRY.register("regenerative",regenerative::new);


    public static final RegistryObject<Item> bloodvirus =REGISTRY.register("bloodvirus",bloodvirus::new);
    public static final RegistryObject<Item> necora =REGISTRY.register("necora",necora::new);


}
