package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.ectoplasm.*;
import com.ytgld.moonstone.item.ms.ectoplasm.soul.SoulBattery;
import com.ytgld.moonstone.item.ms.ectoplasm.soul.SoulCube;
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
    public static final DeferredItem<@NotNull Item> ectoplasmball = register("ectoplasmball", (Identifier) -> new EctoplasmBall(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmbattery = register("ectoplasmbattery", (Identifier) -> new EctoplasmBattery(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmcloub = register("ectoplasmcloub", (Identifier) -> new EctoplasmCloub(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmcube = register("ectoplasmcube", (Identifier) -> new EctoplasmCube(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmhorseshoe = register("ectoplasmhorseshoe", (Identifier) -> new EctoplasmHorseshoe(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmprism = register("ectoplasmprism", (Identifier) -> new EctoplasmPrism(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmshild = register("ectoplasmshild", (Identifier) -> new EctoplasmShild(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmsoul = register("ectoplasmsoul", (Identifier) -> new EctoplasmSoul(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmstar = register("ectoplasmstar", (Identifier) -> new EctoplasmStar(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));
    public static final DeferredItem<@NotNull Item> ectoplasmtree = register("ectoplasmtree", (Identifier) -> new EctoplasmtTee(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier))));

    public static class TabChestItem {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Moonstone.MODID);
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

                    output.accept(the_divine_fall_ring);

                }).build());

    }

    public static DeferredItem<@NotNull Item> register(String name, Function<Identifier, ? extends Item> func) {
        return ITEMS.register(name, func);
    }
}
