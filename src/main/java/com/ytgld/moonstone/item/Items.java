package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.BloodItem;
import com.ytgld.moonstone.item.ms.CommonItem;
import com.ytgld.moonstone.item.ms.blood.*;
import com.ytgld.moonstone.item.ms.blood.magic.*;
import com.ytgld.moonstone.item.ms.ectoplasm.*;
import com.ytgld.moonstone.item.ms.ectoplasm.soul.SoulBattery;
import com.ytgld.moonstone.item.ms.ectoplasm.soul.SoulCube;
import com.ytgld.moonstone.item.ms.maulice.*;
import com.ytgld.moonstone.item.ms.maxitem.*;
import com.ytgld.moonstone.item.ms.maxitem.uncommon.*;
import com.ytgld.moonstone.item.ms.maxitem.uncommon.common.*;
import com.ytgld.moonstone.item.ms.nanodoom.*;
import com.ytgld.moonstone.item.ms.necora.Necora;
import com.ytgld.moonstone.item.ms.necora.dna.*;
import com.ytgld.moonstone.item.ms.necora.dna.god.*;
import com.ytgld.moonstone.item.ms.necora.dnabush.Adrenaline;
import com.ytgld.moonstone.item.ms.necora.dnabush.GianNnightmare;
import com.ytgld.moonstone.item.ms.necora.dnabush.giant_dna.BoneCell;
import com.ytgld.moonstone.item.ms.necora.dnabush.giant_dna.DisgustingCells;
import com.ytgld.moonstone.item.ms.necora.dnabush.giant_dna.MotherCell;
import com.ytgld.moonstone.item.ms.necora.dnabush.giant_dna.ParasiticCell;
import com.ytgld.moonstone.item.ms.necora.dnabush.me.Air;
import com.ytgld.moonstone.item.ms.necora.dnabush.me.Motor;
import com.ytgld.moonstone.item.ms.necora.dnabush.me.Watergen;
import com.ytgld.moonstone.item.ms.necora.dnabush.small.*;
import com.ytgld.moonstone.item.ms.necora.medicine.med.*;
import com.ytgld.moonstone.item.ms.necora.medicine.MedicineBox;
import com.ytgld.moonstone.item.si.fall.DivineFallRing;
import com.ytgld.moonstone.item.si.nightmare.base.*;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeEye;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeHeart;
import com.ytgld.moonstone.item.si.nightmare.eye.BlackEyeRed;
import com.ytgld.moonstone.item.si.nightmare.eye.TrickyPuppets;
import com.ytgld.moonstone.item.si.nightmare.fool.Apple;
import com.ytgld.moonstone.item.si.nightmare.fool.FoolBetray;
import com.ytgld.moonstone.item.si.nightmare.fool.FoolBone;
import com.ytgld.moonstone.item.si.nightmare.fool.FoolSoul;
import com.ytgld.moonstone.item.si.nightmare.insight.*;
import com.ytgld.moonstone.item.si.nightmare.other.*;
import com.ytgld.moonstone.item.si.nightmare.redemption.HypocriticalSelfEsteem;
import com.ytgld.moonstone.item.si.nightmare.redemption.RedemptionDeception;
import com.ytgld.moonstone.item.si.nightmare.redemption.RedemptionDegenerate;
import com.ytgld.moonstone.item.si.nightmare.redemption.RedemptionDownAndOut;
import com.ytgld.moonstone.item.si.nightmare.reversal.Candle;
import com.ytgld.moonstone.item.si.nightmare.reversal.ReversalCard;
import com.ytgld.moonstone.item.si.nightmare.reversal.ReversalMysterious;
import com.ytgld.moonstone.item.si.nightmare.reversal.ReversalOrb;
import com.ytgld.moonstone.item.si.nightmare.start.StartEgg;
import com.ytgld.moonstone.item.si.nightmare.start.StartPod;
import com.ytgld.moonstone.item.si.nightmare.start.StartPower;
import com.ytgld.moonstone.item.si.nightmare.start.Wolf;
import com.ytgld.moonstone.item.si.nightmare.stone.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moonstone.MODID);
    public static final DeferredItem<@NotNull Item> NightmareBaseItem_ = register("nightmare_base", (Identifier) -> new NightmareBaseItem(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_black_eye = register("nightmare_base_black_eye", (Identifier) -> new NightmareBaseBlackEye(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_fool = register("nightmare_base_fool", (Identifier) -> new NightmareBaseFool(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_insight = register("nightmare_base_insight", (Identifier) -> new NightmareBaseInsight(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_redemption = register("nightmare_base_redemption", (Identifier) -> new NightmareBaseRedemption(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_reversal = register("nightmare_base_reversal", (Identifier) -> new NightmareBaseItemReversal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_start = register("nightmare_base_start", (Identifier) -> new NightmareBaseStart(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_stone = register("nightmare_base_stone", (Identifier) -> new NightmareBaseStone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> end_bone = register("end_bone", (Identifier) -> new EndBone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_stone_brain = register("nightmare_base_stone_brain", (Identifier) -> new StoneBrain(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_stone_meet = register("nightmare_base_stone_meet", (Identifier) -> new StoneMeat(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_stone_virus = register("nightmare_base_stone_virus", (Identifier) -> new StoneVirus(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> NightmareVirus_ = register("nightmare_virus", (Identifier) -> new NightmareVirus(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> nightmare_base_black_eye_eye = register("nightmare_base_black_eye_eye", (Identifier) -> new BlackEyeEye(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_black_eye_heart = register("nightmare_base_black_eye_heart", (Identifier) -> new BlackEyeHeart(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_black_eye_red = register("nightmare_base_black_eye_red", (Identifier) -> new BlackEyeRed(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> tricky_puppets = register("tricky_puppets", (Identifier) -> new TrickyPuppets(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));


    public static final DeferredItem<@NotNull Item> nightmare_base_redemption_deception = register("nightmare_base_redemption_deception", (Identifier) -> new RedemptionDeception(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> hypocritical_self_esteem = register("hypocritical_self_esteem", (Identifier) -> new HypocriticalSelfEsteem(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_redemption_down_and_out = register("nightmare_base_redemption_down_and_out", (Identifier) -> new RedemptionDownAndOut(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_redemption_degenerate = register("nightmare_base_redemption_degenerate", (Identifier) -> new RedemptionDegenerate(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_insight_insane = register("nightmare_base_insight_insane", (Identifier) -> new InsightInsane(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ring = register("ring", (Identifier) -> new Ring(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_insight_drug = register("nightmare_base_insight_drug", (Identifier) -> new InsightDrug(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_insight_collapse = register("nightmare_base_insight_collapse", (Identifier) -> new InsightCollapse(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> hidden_blade = register("hidden_blade", (Identifier) -> new HiddenBlade(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> candle = register("candle", (Identifier) -> new Candle(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_reversal_card = register("nightmare_base_reversal_card", (Identifier) -> new ReversalCard(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_reversal_mysterious = register("nightmare_base_reversal_mysterious", (Identifier) -> new ReversalMysterious(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_reversal_orb = register("nightmare_base_reversal_orb", (Identifier) -> new ReversalOrb(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> apple = register("apple", (Identifier) -> new Apple(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_fool_betray = register("nightmare_base_fool_betray", (Identifier) -> new FoolBetray(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_fool_bone = register("nightmare_base_fool_bone", (Identifier) -> new FoolBone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_fool_soul = register("nightmare_base_fool_soul", (Identifier) -> new FoolSoul(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> nightmare_base_start_egg = register("nightmare_base_start_egg", (Identifier) -> new StartEgg(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_start_pod = register("nightmare_base_start_pod", (Identifier) -> new StartPod(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> nightmare_base_start_power = register("nightmare_base_start_power", (Identifier) -> new StartPower(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> wolf = register("wolf", (Identifier) -> new Wolf(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> the_divine_fall_ring = register("the_divine_fall_ring", (Identifier) -> new DivineFallRing(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));


    //-------------------------------------common---------------------------------------------------------------------------------------------------------//

    public static final DeferredItem<@NotNull Item> soulbattery = register("soulbattery", (Identifier) -> new SoulBattery(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> soulcube = register("soulcube", (Identifier) -> new SoulCube(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> beacon = register("beacon", (Identifier) -> new Beacon(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmapple = register("ectoplasmapple", (Identifier) -> new EctoplasmApple(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmball = register("ectoplasmball", (Identifier) -> new EctoplasmBall(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmbattery = register("ectoplasmbattery", (Identifier) -> new EctoplasmBattery(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmcloub = register("ectoplasmcloub", (Identifier) -> new EctoplasmCloub(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmcube = register("ectoplasmcube", (Identifier) -> new EctoplasmCube(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmhorseshoe = register("ectoplasmhorseshoe", (Identifier) -> new EctoplasmHorseshoe(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmprism = register("ectoplasmprism", (Identifier) -> new EctoplasmPrism(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmshild = register("ectoplasmshild", (Identifier) -> new EctoplasmShild(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmsoul = register("ectoplasmsoul", (Identifier) -> new EctoplasmSoul(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmstar = register("ectoplasmstar", (Identifier) -> new EctoplasmStar(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmtree = register("ectoplasmtree", (Identifier) -> new EctoplasmtTee(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));


    public static final DeferredItem<@NotNull Item> brain = register("brain", (Identifier) -> new Brain(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mblock = register("mblock", (Identifier) -> new MBlock(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mbox = register("mbox", (Identifier) -> new MBox(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mbattery = register("mbattery", (Identifier) -> new MBattery(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mbottle = register("mbottle", (Identifier) -> new MBottle(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> meye = register("meye", (Identifier) -> new MEye(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));


    public static final DeferredItem<@NotNull Item> mhead = register("mhead", (Identifier) -> new MHead(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mkidney = register("mkidney", (Identifier) -> new MKidney(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> morb = register("morb", (Identifier) -> new MOrb(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mring = register("mring", (Identifier) -> new MRing(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> mshell = register("mshell", (Identifier) -> new MShell(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));


    public static final DeferredItem<@NotNull Item> blood_candle = register("blood_candle", (Identifier) -> new BloodCandle(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> blood_magic_box = register("blood_magic_box", (Identifier) -> new BloodMagicBox(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> the_blood_book = register("the_blood_book", (Identifier) -> new TheBloodBook(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> undead_blood_charm = register("undead_blood_charm", (Identifier) -> new UndeadBloodCharm(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> blood_amout = register("blood_amout", (Identifier) -> new BloodAmout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> blood_jelly = register("blood_jelly", (Identifier) -> new BloodJelly(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> blood_snake = register("blood_snake", (Identifier) -> new BloodSnake(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> killer = register("killer", (Identifier) -> new Killer(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> max_eye = register("max_eye", (Identifier) -> new MaxEye(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> the_prison_of_sin = register("the_prison_of_sin", (Identifier) -> new PrisonOfSin(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static final DeferredItem<@NotNull Item> blood = register("blood", (Identifier) -> new BloodItem(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier))));


    public static final DeferredItem<@NotNull Item> owner_blood_eye = register("owner_blood_eye", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> owner_blood_attack_eye = register("owner_blood_attack_eye", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> owner_blood_speed_eye = register("owner_blood_speed_eye", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> owner_blood_boom_eye = register("owner_blood_boom_eye", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> owner_blood_effect_eye = register("owner_blood_effect_eye", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> owner_blood_vex = register("owner_blood_vex", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> owner_blood_earth = register("owner_blood_earth", (identifier) -> new BloodItem(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> necora = register("necora", (identifier) -> new Necora(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> ambush = register("ambush", (identifier) -> new Ambush(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> atpoverdose = register("atpoverdose", (identifier) -> new Atpoverdose(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> autolytic = register("autolytic", (identifier) -> new Autolytic(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> fermentation = register("fermentation", (identifier) -> new Fermentation(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> putrefactive = register("putrefactive", (identifier) -> new Putrefactive(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> regenerative = register("regenerative", (identifier) -> new Regenerative(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> GodAmbush = register("god_ambush", (identifier) -> new GodAmbush(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> GodAtpoverdose = register("god_atpoverdose", (identifier) -> new GodAtpoverdose(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> GodAutolytic = register("god_autolytic", (identifier) -> new GodAutolytic(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> GodFermentation = register("god_fermentation", (identifier) -> new GodFermentation(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> GodPutrefactive = register("god_putrefactive", (identifier) -> new GodPutrefactive(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> GodRegenerative = register("god_regenerative", (identifier) -> new GodRegenerative(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> air = register("air", (identifier) -> new Air(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> motor = register("motor", (identifier) -> new Motor(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> watergen = register("watergen", (identifier) -> new Watergen(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> adrenaline = register("adrenaline", (identifier) -> new Adrenaline(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> cell = register("cell", (identifier) -> new Cell(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> cell_blood = register("cell_blood", (identifier) -> new CellBlood(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> cell_boom = register("cell_boom", (identifier) -> new CellBoom(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> cell_calcification = register("cell_calcification", (identifier) -> new cell_calcification(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> cell_mummy = register("cell_mummy", (identifier) -> new cell_mummy(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> giant = register("giant", (identifier) -> new Biant(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> giant_nightmare = register("giant_nightmare", (identifier) -> new GianNnightmare(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> bone_cell = register("bone_cell", (identifier) -> new BoneCell(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> disgusting_cells = register("disgusting_cells", (identifier) -> new DisgustingCells(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> mother_cell = register("mother_cell", (identifier) -> new MotherCell(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> parasitic_cell = register("parasitic_cell", (identifier) -> new ParasiticCell(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> badgeofthedead = register("badgeofthedead", (identifier) -> new BadgeOfTheDead(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> battery = register("battery", (identifier) -> new Battery(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> biggreedcrystal = register("biggreedcrystal", (identifier) -> new BigGreedcCystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> bigwarcrystal = register("bigwarcrystal", (identifier) -> new BigWarcrystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> blackeorb = register("blackeorb", (identifier) -> new Blackeorb(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> blueamout = register("blueamout", (identifier) -> new BlueAmout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> greedamout = register("greedamout", (identifier) -> new GreedAmout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> greedcrystal = register("greedcrystal", (identifier) -> new GreedCrystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> redamout = register("redamout", (identifier) -> new RedAmout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> warcrystal = register("warcrystal", (identifier) -> new WarCrystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> whiteorb = register("whiteorb", (identifier) -> new WhiteOrb(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> evilcandle = register("evilcandle", (identifier) -> new EvilCandle(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> luck_ring = register("luck_ring", (identifier) -> new LuckRing(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> luck_stone = register("luck_stone", (identifier) -> new LuckSone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> magnet = register("magnet", (identifier) -> new Magnet(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> obsidianring = register("obsidianring", (identifier) -> new ObsidianRing(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> evil_mob = register("evil_mob", (identifier) -> new EvilMob(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> fortunecrystal = register("fortunecrystal", (identifier) -> new Fortunecrystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> god_lead = register("god_lead", (identifier) -> new GodLead(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> malice_die = register("malice_die", (identifier) -> new MaliceDie(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> maxamout = register("maxamout", (identifier) -> new Maxamout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> mayhemcrystal = register("mayhemcrystal", (identifier) -> new Mayhemcrystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> rage_crystal = register("rage_crystal", (identifier) -> new RageCrystal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> rage_crystal_big = register("rage_crystal_big", (identifier) -> new RageCrystalBig(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> rage_crystal_max = register("rage_crystal_max", (identifier) -> new RageCystalMax(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> the_heart = register("the_heart", (identifier) -> new TheHeart(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> twelve_sword = register("twelve_sword", (identifier) -> new TwelveSword(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> as_amout = register("as_amout", (identifier) -> new AsAmout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> doomeye = register("doomeye", (identifier) -> new SevenSword(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> doomswoud = register("doomswoud", (identifier) -> new RineSword(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> magiceye = register("magiceye", (identifier) -> new MagicEye(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> magicstone = register("magicstone", (identifier) -> new MagicStone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> million = register("million", (identifier) -> new Million(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> nano_box = register("nano_box", (identifier) -> new NanoBox(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> nanocube = register("nanocube", (identifier) -> new NanoCube(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> nanorobot = register("nanorobot", (identifier) -> new NanoRbot(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> sword_amout = register("sword_amout", (identifier) -> new SwordAmout(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> thedoomstone = register("thedoomstone", (identifier) -> new Thedoomstone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> thefruit = register("thefruit", (identifier) -> new TheFruit(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> wind = register("wind", (identifier) -> new Wind(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));


    public static final DeferredItem<@NotNull Item> god_sword_ = register("god_sword", (identifier) -> new CommonItem(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> at_sword_ = register("at_sword", (identifier) -> new CommonItem(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> sword = register("sword", (identifier) -> new CommonItem(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> calcification = register("calcification", (identifier) -> new Calcification(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> masticatory = register("masticatory", (identifier) -> new Masticatory(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> polyphagia = register("polyphagia", (identifier) -> new Polyphagia(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> quadriceps = register("quadriceps", (identifier) -> new Quadriceps(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> reanimation = register("reanimation", (identifier) -> new Reanimation(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> medicinebox = register("medicinebox", (identifier) -> new MedicineBox(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));


    public static final DeferredItem<@NotNull Item> blood_god = register("blood_god", (identifier) -> new BloodGod(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> bone_or_god = register("bone_or_god", (identifier) -> new BoneOrGod(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> defend_against_runestone = register("defend_against_runestone", (identifier) -> new DefendAgainstRunestone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> lead = register("lead", (identifier) -> new lead(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> revive_runestone = register("revive_runestone", (identifier) -> new ReviveRunestone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> strengthen_runestone = register("strengthen_runestone", (identifier) -> new StrengthenRunestone(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> immortal = register("immortal", (identifier) -> new Immortal(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> dead_drder = register("dead_drder", (identifier) -> new DeadOrder(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));
    public static final DeferredItem<@NotNull Item> flag_of_protest = register("flag_of_protest", (identifier) -> new FlagOfProtest(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static final DeferredItem<@NotNull Item> consciousness = register("consciousness", (identifier) -> new Consciousness(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, identifier))));

    public static class TabChestItem {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Moonstone.MODID);
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> dna = CREATIVE_MODE_TABS.register(Moonstone.MODID + "_dna", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.tabmoonstone.necora"))
                .icon(() -> necora.asItem().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(necora);
                    output.accept(ambush);
                    output.accept(atpoverdose);
                    output.accept(autolytic);
                    output.accept(fermentation);
                    output.accept(putrefactive);
                    output.accept(regenerative);
                    output.accept(GodAmbush);
                    output.accept(GodAtpoverdose);
                    output.accept(GodAutolytic);
                    output.accept(GodFermentation);
                    output.accept(GodPutrefactive);
                    output.accept(GodRegenerative);

                    output.accept(air);
                    output.accept(motor);
                    output.accept(watergen);
                    output.accept(adrenaline);
                    output.accept(cell);
                    output.accept(cell_blood);
                    output.accept(cell_boom);
                    output.accept(cell_calcification);
                    output.accept(cell_mummy);
                    output.accept(giant);

                    output.accept(bone_cell);
                    output.accept(disgusting_cells);
                    output.accept(mother_cell);
                    output.accept(parasitic_cell);

                    output.accept(calcification);
                    output.accept(masticatory);
                    output.accept(polyphagia);
                    output.accept(quadriceps);
                    output.accept(reanimation);
                    output.accept(medicinebox);



                }).build());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> common = CREATIVE_MODE_TABS.register(Moonstone.MODID + "_common", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.tabmoonstone"))
                .icon(() -> ectoplasmball.asItem().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(soulbattery);
                    output.accept(soulcube);
                    output.accept(beacon);
                    output.accept(ectoplasmapple);
                    output.accept(ectoplasmball);
                    output.accept(ectoplasmbattery);
                    output.accept(ectoplasmcloub);
                    output.accept(ectoplasmcube);
                    output.accept(ectoplasmhorseshoe);
                    output.accept(ectoplasmprism);
                    output.accept(ectoplasmshild);
                    output.accept(ectoplasmsoul);
                    output.accept(ectoplasmstar);
                    output.accept(ectoplasmtree);


                    output.accept(brain);
                    output.accept(mbattery);
                    output.accept(mblock);
                    output.accept(mbottle);
                    output.accept(mbox);
                    output.accept(meye);
                    output.accept(mhead);
                    output.accept(mkidney);
                    output.accept(morb);
                    output.accept(mring);
                    output.accept(mshell);

                    output.accept(blood_candle);
                    output.accept(blood_magic_box);
                    output.accept(the_blood_book);
                    output.accept(undead_blood_charm);
                    output.accept(blood_amout);
                    output.accept(blood_jelly);
                    output.accept(blood_snake);
                    output.accept(killer);
                    output.accept(max_eye);
                    output.accept(the_prison_of_sin);
                    output.accept(blood);
                    output.accept(blood);

                    output.accept(owner_blood_eye);
                    output.accept(owner_blood_attack_eye);
                    output.accept(owner_blood_speed_eye);
                    output.accept(owner_blood_boom_eye);
                    output.accept(owner_blood_effect_eye);
                    output.accept(owner_blood_vex);
                    output.accept(owner_blood_earth);
                    output.accept(consciousness);

                    output.accept(badgeofthedead);
                    output.accept(battery);
                    output.accept(biggreedcrystal);
                    output.accept(bigwarcrystal);
                    output.accept(blackeorb);
                    output.accept(blueamout);
                    output.accept(greedamout);
                    output.accept(greedcrystal);
                    output.accept(redamout);
                    output.accept(warcrystal);
                    output.accept(whiteorb);
                    output.accept(evilcandle);
                    output.accept(luck_ring);
                    output.accept(luck_stone);
                    output.accept(magnet);
                    output.accept(obsidianring);
                    output.accept(evil_mob);
                    output.accept(fortunecrystal);
                    output.accept(god_lead);
                    output.accept(malice_die);
                    output.accept(maxamout);
                    output.accept(mayhemcrystal);
                    output.accept(rage_crystal);
                    output.accept(rage_crystal_big);
                    output.accept(rage_crystal_max);
                    output.accept(the_heart);
                    output.accept(twelve_sword);


                    output.accept(as_amout);
                    output.accept(doomeye);
                    output.accept(doomswoud);
                    output.accept(magiceye);
                    output.accept(magicstone);
                    output.accept(million);
                    output.accept(nano_box);
                    output.accept(nanocube);
                    output.accept(nanorobot);
                    output.accept(sword_amout);
                    output.accept(thedoomstone);
                    output.accept(thefruit);
                    output.accept(wind);

                }).build());


        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tab = CREATIVE_MODE_TABS.register(Moonstone.MODID + "_nightmare", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.moonstone"))
                .icon(() -> NightmareBaseItem_.asItem().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(NightmareBaseItem_);
                    output.accept(nightmare_base_black_eye);
                    output.accept(nightmare_base_fool);
                    output.accept(nightmare_base_insight);
                    output.accept(nightmare_base_redemption);
                    output.accept(nightmare_base_reversal);
                    output.accept(nightmare_base_start);
                    output.accept(nightmare_base_stone);

                    output.accept(end_bone);
                    output.accept(nightmare_base_stone_brain);
                    output.accept(nightmare_base_stone_meet);
                    output.accept(nightmare_base_stone_virus);
                    output.accept(NightmareVirus_);

                    output.accept(nightmare_base_black_eye_eye);
                    output.accept(nightmare_base_black_eye_heart);
                    output.accept(nightmare_base_black_eye_red);
                    output.accept(tricky_puppets);

                    output.accept(nightmare_base_redemption_deception);
                    output.accept(hypocritical_self_esteem);
                    output.accept(nightmare_base_redemption_down_and_out);
                    output.accept(nightmare_base_redemption_degenerate);
                    output.accept(nightmare_base_insight_insane);

                    output.accept(ring);
                    output.accept(nightmare_base_insight_drug);
                    output.accept(nightmare_base_insight_collapse);
                    output.accept(hidden_blade);


                    output.accept(candle);
                    output.accept(nightmare_base_reversal_card);
                    output.accept(nightmare_base_reversal_mysterious);
                    output.accept(nightmare_base_reversal_orb);

                    output.accept(apple);
                    output.accept(nightmare_base_fool_betray);
                    output.accept(nightmare_base_fool_bone);
                    output.accept(nightmare_base_fool_soul);

                    output.accept(nightmare_base_start_egg);
                    output.accept(nightmare_base_start_pod);
                    output.accept(nightmare_base_start_power);
                    output.accept(wolf);

                    output.accept(blood_god);
                    output.accept(bone_or_god);
                    output.accept(defend_against_runestone);
                    output.accept(lead);
                    output.accept(revive_runestone);
                    output.accept(strengthen_runestone);


                    output.accept(immortal);
                    output.accept(dead_drder);
                    output.accept(flag_of_protest);
                    output.accept(the_divine_fall_ring);

                }).build());

    }

    public static DeferredItem<@NotNull Item> register(String name, Function<Identifier, ? extends Item> func) {
        return ITEMS.register(name, func);
    }
}
