package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.book;
import com.moonstone.moonstonemod.item.amout.ectoplasmstone;
import com.moonstone.moonstonemod.item.amout.twistedstone;
import com.moonstone.moonstonemod.item.buyme.wind_and_rain;
import com.moonstone.moonstonemod.item.common.*;
import com.moonstone.moonstonemod.item.ectoplasm.*;
import com.moonstone.moonstonemod.item.maulice.*;
import com.moonstone.moonstonemod.item.maxitem.fortunecrystal;
import com.moonstone.moonstonemod.item.maxitem.maxamout;
import com.moonstone.moonstonemod.item.maxitem.mayhemcrystal;
import com.moonstone.moonstonemod.item.nanodoom.*;
import com.moonstone.moonstonemod.item.nightmare.*;
import com.moonstone.moonstonemod.item.plague.ALL.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.item.plague.BloodVirus.*;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.botton;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.catalyzer;
import com.moonstone.moonstonemod.item.plague.TheNecora.autolytic;
import com.moonstone.moonstonemod.item.plague.bloodvirus;
import com.moonstone.moonstonemod.item.plague.medicine.med.*;
import com.moonstone.moonstonemod.item.plague.necora;
import com.moonstone.moonstonemod.item.soul.soulbattery;
import com.moonstone.moonstonemod.item.soul.soulcube;
import com.moonstone.moonstonemod.item.uncommon.*;
import com.moonstone.moonstonemod.moonstoneitem.extend.apple;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MoonStoneMod.MODID);
    public static final RegistryObject<Item> ectoplasmapple  =REGISTRY.register("ectoplasmapple", ectoplasmapple::new);
    public static final RegistryObject<Item> ectoplasmball  =REGISTRY.register("ectoplasmball", ectoplasmball::new);
    public static final RegistryObject<Item> ectoplasmbattery  =REGISTRY.register("ectoplasmbattery", ectoplasmbattery::new);
    public static final RegistryObject<Item> ectoplasmcloub  =REGISTRY.register("ectoplasmcloub", ectoplasmcloub::new);
    public static final RegistryObject<Item> ectoplasmcube  =REGISTRY.register("ectoplasmcube", ectoplasmcube::new);
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




    public static final RegistryObject<Item> ambush =REGISTRY.register("ambush", com.moonstone.moonstonemod.item.plague.TheNecora.ambush::new);
    public static final RegistryObject<Item> atpoverdose =REGISTRY.register("atpoverdose", com.moonstone.moonstonemod.item.plague.TheNecora.atpoverdose::new);
    public static final RegistryObject<Item> autolytic =REGISTRY.register("autolytic",autolytic::new);
    public static final RegistryObject<Item> fermentation =REGISTRY.register("fermentation", com.moonstone.moonstonemod.item.plague.TheNecora.fermentation::new);
    public static final RegistryObject<Item> putrefactive =REGISTRY.register("putrefactive", com.moonstone.moonstonemod.item.plague.TheNecora.putrefactive::new);
    public static final RegistryObject<Item> regenerative =REGISTRY.register("regenerative", com.moonstone.moonstonemod.item.plague.TheNecora.regenerative::new);


    public static final RegistryObject<Item> bloodvirus =REGISTRY.register("bloodvirus",bloodvirus::new);
    public static final RegistryObject<Item> necora =REGISTRY.register("necora",necora::new);
    public static final RegistryObject<Item> maxamout =REGISTRY.register("maxamout",maxamout::new);

    public static final RegistryObject<Item> mayhemcrystal =REGISTRY.register("mayhemcrystal",mayhemcrystal::new);
    public static final RegistryObject<Item> fortunecrystal =REGISTRY.register("fortunecrystal",fortunecrystal::new);
    public static final RegistryObject<Item> plague =REGISTRY.register("plague", plague::new);
    public static final RegistryObject<Item> doomeye =REGISTRY.register("doomeye", doomeye::new);

    public static final RegistryObject<Item> doomswoud =REGISTRY.register("doomswoud", doomswoud::new);
    public static final RegistryObject<Item> book =REGISTRY.register("soulbook", book::new);
    public static final RegistryObject<Item> wind =REGISTRY.register("wind", wind::new);
    public static final RegistryObject<Item> wind_and_rain =REGISTRY.register("wind_and_rain", wind_and_rain::new);
    public static final RegistryObject<Item> ectoplasmstar =REGISTRY.register("ectoplasmstar", ectoplasmstar::new);
    public static final RegistryObject<Item> ectoplasmsoul =REGISTRY.register("ectoplasmsoul", ectoplasmsoul::new);
    public static final RegistryObject<Item> ectoplasmtree =REGISTRY.register("ectoplasmtree", ectoplasmtree::new);
    public static final RegistryObject<Item> brain =REGISTRY.register("brain", brain::new);
    public static final RegistryObject<Item> beacon =REGISTRY.register("beacon", beacon::new);
    public static final RegistryObject<Item> mhead =REGISTRY.register("mhead", mhead::new);
    public static final RegistryObject<Item> cell =REGISTRY.register("cell", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell::new);
    public static final RegistryObject<Item> adrenaline =REGISTRY.register("adrenaline", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.adrenaline::new);
    public static final RegistryObject<Item> cell_mummy =REGISTRY.register("cell_mummy", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_mummy::new);
    public static final RegistryObject<Item> cell_boom =REGISTRY.register("cell_boom", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_boom::new);
    public static final RegistryObject<Item> cell_calcification =REGISTRY.register("cell_calcification", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_calcification::new);
    public static final RegistryObject<Item> cell_blood =REGISTRY.register("cell_blood", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_blood::new);
    public static final RegistryObject<Item> motor =REGISTRY.register("motor", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.motor::new);
    public static final RegistryObject<Item> watergen =REGISTRY.register("watergen", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.watergen::new);
    public static final RegistryObject<Item> air =REGISTRY.register("air", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.me.air::new);
    public static final RegistryObject<Item> giant =REGISTRY.register("giant", com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.giant::new);
    public static final RegistryObject<Item> the_heart =REGISTRY.register("the_heart", com.moonstone.moonstonemod.item.maxitem.the_heart::new);
    public static final RegistryObject<Item> nightmare_orb =REGISTRY.register("nightmare_orb", com.moonstone.moonstonemod.item.nightmare.nightmare_orb::new);
    public static final RegistryObject<Item> nightmare_heart =REGISTRY.register("nightmare_heart", com.moonstone.moonstonemod.item.nightmare.nightmare_heart::new);
    public static final RegistryObject<Item> the_heart_image =REGISTRY.register("the_heart_image", ()->{
        return new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));

    });


    public static final RegistryObject<Item> gorillacake =REGISTRY.register("gorillacake", com.moonstone.moonstonemod.item.gorillacake::new);

}
