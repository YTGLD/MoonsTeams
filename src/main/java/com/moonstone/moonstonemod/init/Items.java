package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.book;
import com.moonstone.moonstonemod.entity.necora.small_zombie;
import com.moonstone.moonstonemod.item.BloodVirus.batskill;
import com.moonstone.moonstonemod.item.BloodVirus.botton;
import com.moonstone.moonstonemod.item.BloodVirus.catalyzer;
import com.moonstone.moonstonemod.item.BloodVirus.flygene;
import com.moonstone.moonstonemod.item.TheNecora.autolytic;
import com.moonstone.moonstonemod.item.TheNecora.small.*;
import com.moonstone.moonstonemod.item.amout.ectoplasmstone;
import com.moonstone.moonstonemod.item.amout.twistedamout;
import com.moonstone.moonstonemod.item.amout.twistedstone;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.the_blood_book;
import com.moonstone.moonstonemod.item.bloodvirus;
import com.moonstone.moonstonemod.item.ectoplasm.*;
import com.moonstone.moonstonemod.item.ectoplasm.soul.soulbattery;
import com.moonstone.moonstonemod.item.ectoplasm.soul.soulcube;
import com.moonstone.moonstonemod.item.maulice.*;
import com.moonstone.moonstonemod.item.maxitem.fortunecrystal;
import com.moonstone.moonstonemod.item.maxitem.maxamout;
import com.moonstone.moonstonemod.item.maxitem.mayhemcrystal;
import com.moonstone.moonstonemod.item.maxitem.uncommon.common.*;
import com.moonstone.moonstonemod.item.nanodoom.buyme.wind_and_rain;
import com.moonstone.moonstonemod.item.nanodoom.*;
import com.moonstone.moonstonemod.item.necora;
import com.moonstone.moonstonemod.item.nightmare.*;
import com.moonstone.moonstonemod.item.plague.medicine.med.*;
import com.moonstone.moonstonemod.item.plague.mobitem.*;
import com.moonstone.moonstonemod.moonstoneitem.extend.apple;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

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
    public static final RegistryObject<Item>  blueamout =REGISTRY.register("blueamout", com.moonstone.moonstonemod.item.maxitem.uncommon.common.blueamout::new);
    public static final RegistryObject<Item>  greedamout =REGISTRY.register("greedamout", com.moonstone.moonstonemod.item.maxitem.uncommon.common.greedamout::new);
    public static final RegistryObject<Item>  greedcrystal =REGISTRY.register("greedcrystal", com.moonstone.moonstonemod.item.maxitem.uncommon.common.greedcrystal::new);
    public static final RegistryObject<Item>  redamout =REGISTRY.register("redamout", com.moonstone.moonstonemod.item.maxitem.uncommon.common.redamout::new);
    public static final RegistryObject<Item>  warcrystal =REGISTRY.register("warcrystal", com.moonstone.moonstonemod.item.maxitem.uncommon.common.warcrystal::new);
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

    public static final RegistryObject<Item>  diemug =REGISTRY.register("diemug", com.moonstone.moonstonemod.item.maxitem.uncommon.diemug::new);
    public static final RegistryObject<Item>  evilcandle =REGISTRY.register("evilcandle", com.moonstone.moonstonemod.item.maxitem.uncommon.evilcandle::new);
    public static final RegistryObject<Item>  evilmug =REGISTRY.register("evilmug", com.moonstone.moonstonemod.item.maxitem.uncommon.evilmug::new);
    public static final RegistryObject<Item>  obsidianring =REGISTRY.register("obsidianring", com.moonstone.moonstonemod.item.maxitem.uncommon.obsidianring::new);


    public static final RegistryObject<Item>  dna =REGISTRY.register("dna", () -> new dna());
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




    public static final RegistryObject<Item> batgene =REGISTRY.register("batgene", com.moonstone.moonstonemod.item.BloodVirus.batgene::new);
    public static final RegistryObject<Item> bloodgene =REGISTRY.register("bloodgene", com.moonstone.moonstonemod.item.BloodVirus.bloodgene::new);
    public static final RegistryObject<Item> flygene =REGISTRY.register("flygene",flygene::new);
    public static final RegistryObject<Item> heathgene =REGISTRY.register("heathgene", com.moonstone.moonstonemod.item.BloodVirus.heathgene::new);
    public static final RegistryObject<Item> ragegene =REGISTRY.register("ragegene", com.moonstone.moonstonemod.item.BloodVirus.ragegene::new);
    public static final RegistryObject<Item> sleepgene =REGISTRY.register("sleepgene", com.moonstone.moonstonemod.item.BloodVirus.sleepgene::new);
    public static final RegistryObject<Item> medicinebox =REGISTRY.register("medicinebox",medicinebox::new);
    public static final RegistryObject<Item> apple =REGISTRY.register("apple",apple::new);




    public static final RegistryObject<Item> ambush =REGISTRY.register("ambush", com.moonstone.moonstonemod.item.TheNecora.ambush::new);
    public static final RegistryObject<Item> atpoverdose =REGISTRY.register("atpoverdose", com.moonstone.moonstonemod.item.TheNecora.atpoverdose::new);
    public static final RegistryObject<Item> autolytic =REGISTRY.register("autolytic",autolytic::new);
    public static final RegistryObject<Item> fermentation =REGISTRY.register("fermentation", com.moonstone.moonstonemod.item.TheNecora.fermentation::new);
    public static final RegistryObject<Item> putrefactive =REGISTRY.register("putrefactive", com.moonstone.moonstonemod.item.TheNecora.putrefactive::new);
    public static final RegistryObject<Item> regenerative =REGISTRY.register("regenerative", com.moonstone.moonstonemod.item.TheNecora.regenerative::new);


    public static final RegistryObject<Item> bloodvirus =REGISTRY.register("bloodvirus",bloodvirus::new);
    public static final RegistryObject<Item> necora =REGISTRY.register("necora",necora::new);
    public static final RegistryObject<Item> maxamout =REGISTRY.register("maxamout",maxamout::new);

    public static final RegistryObject<Item> mayhemcrystal =REGISTRY.register("mayhemcrystal",mayhemcrystal::new);
    public static final RegistryObject<Item> fortunecrystal =REGISTRY.register("fortunecrystal",fortunecrystal::new);
    public static final RegistryObject<Item> plague =REGISTRY.register("plague", com.moonstone.moonstonemod.item.maxitem.uncommon.plague::new);
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
    public static final RegistryObject<Item> cell =REGISTRY.register("cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.cell::new);
    public static final RegistryObject<Item> adrenaline =REGISTRY.register("adrenaline", com.moonstone.moonstonemod.item.TheNecora.bnabush.adrenaline::new);
    public static final RegistryObject<Item> cell_mummy =REGISTRY.register("cell_mummy", com.moonstone.moonstonemod.item.TheNecora.bnabush.cell_mummy::new);
    public static final RegistryObject<Item> cell_boom =REGISTRY.register("cell_boom", com.moonstone.moonstonemod.item.TheNecora.bnabush.cell_boom::new);
    public static final RegistryObject<Item> cell_calcification =REGISTRY.register("cell_calcification", com.moonstone.moonstonemod.item.TheNecora.bnabush.cell_calcification::new);
    public static final RegistryObject<Item> cell_blood =REGISTRY.register("cell_blood", com.moonstone.moonstonemod.item.TheNecora.bnabush.cell_blood::new);
    public static final RegistryObject<Item> motor =REGISTRY.register("motor", com.moonstone.moonstonemod.item.TheNecora.bnabush.me.motor::new);
    public static final RegistryObject<Item> watergen =REGISTRY.register("watergen", com.moonstone.moonstonemod.item.TheNecora.bnabush.me.watergen::new);
    public static final RegistryObject<Item> air =REGISTRY.register("air", com.moonstone.moonstonemod.item.TheNecora.bnabush.me.air::new);
    public static final RegistryObject<Item> giant =REGISTRY.register("giant", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant::new);
    public static final RegistryObject<Item> the_heart =REGISTRY.register("the_heart", com.moonstone.moonstonemod.item.maxitem.the_heart::new);
    public static final RegistryObject<Item> nightmare_orb =REGISTRY.register("nightmare_orb", com.moonstone.moonstonemod.item.nightmare.nightmare_orb::new);
    public static final RegistryObject<Item> nightmare_heart =REGISTRY.register("nightmare_heart", com.moonstone.moonstonemod.item.nightmare.nightmare_heart::new);
    public static final RegistryObject<Item> nightmare_head =REGISTRY.register("nightmare_head", com.moonstone.moonstonemod.item.nightmare.nightmare_head::new);
    public static final RegistryObject<Item> giant_nightmare =REGISTRY.register("giant_nightmare", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare::new);
    public static final RegistryObject<Item> nightmare_cube =REGISTRY.register("nightmare_cube", com.moonstone.moonstonemod.item.nightmare.nightmare_cube::new);
    public static final RegistryObject<Item> million_sword =REGISTRY.register("million_sword", com.moonstone.moonstonemod.item.nanodoom.sword.million_sword::new);

    public static final RegistryObject<Item> speed_seed =REGISTRY.register("speed_seed", com.moonstone.moonstonemod.moonstoneitem.speed_seed::new);
//    public static final RegistryObject<Item> sword_amout =REGISTRY.register("sword_amout", com.moonstone.moonstonemod.item.nanodoom.sword_amout::new);
//
//    public static final RegistryObject<Item> light_amout =REGISTRY.register("light_amout", com.moonstone.moonstonemod.item.nanodoom.light_amout::new);

    public static final RegistryObject<Item> nano_box =REGISTRY.register("nano_box", com.moonstone.moonstonemod.item.nanodoom.nano_box::new);
    public static final RegistryObject<Item> model_box_nano =REGISTRY.register("model_box_nano", com.moonstone.moonstonemod.item.nanodoom.nano_box.model_box_nano::new);








    public static final RegistryObject<Item> not_blood_cell =REGISTRY.register("not_blood_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.not_blood_cell::new);
    public static final RegistryObject<Item> anaerobic_cell =REGISTRY.register("anaerobic_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.anaerobic_cell::new);
    public static final RegistryObject<Item> giant_boom_cell =REGISTRY.register("giant_boom_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.giant_boom_cell::new);
    public static final RegistryObject<Item> subspace_cell =REGISTRY.register("subspace_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.subspace_cell::new);
    public static final RegistryObject<Item> bone_cell =REGISTRY.register("bone_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_dna.bone_cell::new);
    public static final RegistryObject<Item> parasitic_cell =REGISTRY.register("parasitic_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_dna.parasitic_cell::new);
    public static final RegistryObject<Item> mother_cell =REGISTRY.register("mother_cell", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_dna.mother_cell::new);
    public static final RegistryObject<Item> disgusting_cells =REGISTRY.register("disgusting_cells", com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_dna.disgusting_cells::new);
    public static final RegistryObject<Item> slime =REGISTRY.register("slime", com.moonstone.moonstonemod.item.TheNecora.bnabush.slimes.slime::new);



    public static final RegistryObject<Item> bat_cell =REGISTRY.register("bat_cell", com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell::new);
    public static final RegistryObject<Item> cell_doctor =REGISTRY.register("cell_doctor", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_doctor::new);
    public static final RegistryObject<Item> cell_desecrate =REGISTRY.register("cell_desecrate", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_desecrate::new);
    public static final RegistryObject<Item> cell_harvest =REGISTRY.register("cell_harvest", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_harvest::new);

    public static final RegistryObject<Item> cell_scientist =REGISTRY.register("cell_scientist", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_scientist::new);
    public static final RegistryObject<Item> cell_immortal =REGISTRY.register("cell_immortal", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_immortal::new);
    public static final RegistryObject<Item> cell_rage =REGISTRY.register("cell_rage", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_rage::new);
    public static final RegistryObject<Item> cell_blood_attack =REGISTRY.register("cell_blood_attack", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_blood_attack::new);
    public static final RegistryObject<Item> cell_fear =REGISTRY.register("cell_fear", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_fear::new);
    public static final RegistryObject<Item> cell_not_do =REGISTRY.register("cell_not_do", com.moonstone.moonstonemod.item.BloodVirus.dna.cell_not_do::new);
    public static final RegistryObject<Item> the_pain_stone =REGISTRY.register("the_pain_stone", com.moonstone.moonstonemod.item.pain.the_pain_stone::new);
    public static final RegistryObject<Item> pain_candle =REGISTRY.register("pain_candle", com.moonstone.moonstonemod.item.pain.pain_candle::new);
    public static final RegistryObject<Item> pain_ring =REGISTRY.register("pain_ring", com.moonstone.moonstonemod.item.pain.pain_ring::new);

    public static final RegistryObject<Item> twistedamout =REGISTRY.register("twistedamout", twistedamout::new);
    public static final RegistryObject<Item> acid =REGISTRY.register("acid", acid::new);
    public static final RegistryObject<Item> compression =REGISTRY.register("compression", compression::new);
    public static final RegistryObject<Item> atrophy =REGISTRY.register("atrophy", atrophy::new);
    public static final RegistryObject<Item> enhancemen =REGISTRY.register("enhancemen", enhancemen::new);

    public static final RegistryObject<Item> zombie_box =REGISTRY.register("zombie_box", zombie_box::new);
    public static final RegistryObject<Item> zombie_box_nobo =REGISTRY.register("zombie_box_nobo", ()->{
        return new Item(new Item.Properties().rarity(Rarity.UNCOMMON)){

            @Override
            public InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand p_41434_) {
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 2;
                List<small_zombie> entities = player.level().getEntitiesOfClass(small_zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (small_zombie smallZombie : entities){
                    player.getItemInHand(p_41434_).shrink(1);
                    player.setItemInHand(p_41434_,new ItemStack(Items.zombie_box.get()));
                    smallZombie.kill();
                }
                return super.use(p_41432_, player, p_41434_);
            }
        };
    });

    public static final RegistryObject<Item> max_eye =REGISTRY.register("max_eye", () -> new max_eye());
    public static final RegistryObject<Item> max_blood_cube =REGISTRY.register("max_blood_cube", () -> new max_blood_cube());
    public static final RegistryObject<Item> blood_amout =REGISTRY.register("blood_amout", () -> new blood_amout());

    public static final RegistryObject<Item> blood_snake =REGISTRY.register("blood_snake", () -> new blood_snake());
    public static final RegistryObject<Item> the_prison_of_sin =REGISTRY.register("the_prison_of_sin", () -> new the_prison_of_sin());
    public static final RegistryObject<Item> blood_magic_box =REGISTRY.register("blood_magic_box", () -> new blood_magic_box());
    public static final RegistryObject<Item> the_blood_book =REGISTRY.register("the_blood_book", () -> new the_blood_book());
    public static final RegistryObject<Item> blood =REGISTRY.register("blood", ()-> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> blood_candle =REGISTRY.register("blood_candle", com.moonstone.moonstonemod.item.blood.magic.blood_candle::new);
    public static final RegistryObject<Item> probability_stone =REGISTRY.register("probability_stone", com.moonstone.moonstonemod.item.amout.probability_stone::new);

    public static final RegistryObject<Item> blood_sun =REGISTRY.register("blood_sun", com.moonstone.moonstonemod.item.blood.magic.blood_sun::new);
    public static final RegistryObject<Item> blood_jelly =REGISTRY.register("blood_jelly", com.moonstone.moonstonemod.item.blood.blood_jelly::new);


    public static final RegistryObject<Item> million =REGISTRY.register("million", com.moonstone.moonstonemod.item.nanodoom.million::new);
    public static final RegistryObject<Item> as_amout =REGISTRY.register("as_amout", com.moonstone.moonstonemod.item.nanodoom.as_amout::new);

    public static final RegistryObject<Item> nine_sword_book =REGISTRY.register("nine_sword_book", com.moonstone.moonstonemod.item.maxitem.book.nine_sword_book::new);
    public static final RegistryObject<Item> nine_sword_books =REGISTRY.register("nine_sword_books", com.moonstone.moonstonemod.item.maxitem.book.nine_sword_books::new);













    public static final RegistryObject<Item> gorillacake =REGISTRY.register("gorillacake", com.moonstone.moonstonemod.moonstoneitem.gorillacake::new);

}
