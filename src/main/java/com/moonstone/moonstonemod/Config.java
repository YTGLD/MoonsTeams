package com.moonstone.moonstonemod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final Config SERVER;
    public static final ForgeConfigSpec fc;
    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }
    public Config(ForgeConfigSpec.Builder BUILDER){
        plague_speed = BUILDER
                .comment("The growth rate of plague research sites")
                .defineInRange("GrowthSpeed", 0.1, 0, 100);
        plague_pain = BUILDER
                .comment("The corrosion speed of the plague")
                .defineInRange("CorrosionSpeed", 0.01, 0, 100);

        nightmare_moai = BUILDER
                .comment("Nightmare Moai's enchantment level bonus")
                .defineInRange("EnchantmentBonus", 2, 0, 100);


        m_brain_many = BUILDER
                .comment("How many critical hits will brain make")
                .defineInRange("MBrain_many", 5, 1, 100);
        m_brain_critical = BUILDER
                .comment("Brain's critical strike multiplier")
                .defineInRange("MBrain_critical_multiplier", 2.25, 1, 999);
        battery_speed = BUILDER
                .comment("The speed of the battery")
                .defineInRange("battery", 0.1, 0, 999);
        quadriceps_speed = BUILDER
                .comment("The speed of the quadriceps")
                .defineInRange("quadriceps", 0.25, 0, 999);
        flygene_speed = BUILDER
                .comment("The speed of the flygene")
                .defineInRange("flygene", 0.125, 0, 999);
        bloodvirus_speed = BUILDER
                .comment("The speed of the bloodvirus")
                .defineInRange("bloodvirus", 0.175, 0, 999);

        motor_speed = BUILDER
                .comment("The speed of the motor")
                .defineInRange("motor", 0.15, 0, 999);

        the_pain_stone = BUILDER
                .comment("What is this value, divide the damage by (“2” is “/2”)")
                .defineInRange("Int", 2, 1, 1000);

        nine_sword = BUILDER
                .comment("The maximum amount of damage that can be dealt")
                .defineInRange("Nine Sword Books", 256D, 0, 5120);

        giveBook = BUILDER
                .comment("Starting with a book or not")
                .define("give", true);

        ectoplasmstar = BUILDER
                .comment("What is the maximum number of lucky values that can be converted from the attributes of the Spirit Lucky Star")
                .defineInRange("EctoplasmStar", 100, 0, 1024);

        canUnequipMoonstoneItem = BUILDER
                .comment("Can unequip some moonstone item")
                .define("Can", false);
        itemQuality = BUILDER
                .comment("If enabled, the item's cultivation value system (Golden Immortal - Merge - Saint Monarch) is enabled.")
                .define("Quality", true);



        BUILDER.push("zh_cn");

        rage_eye = BUILDER
                .comment("狂暴之眼最多可以偷盗的属性（相对于玩家）")
                .defineInRange("rage_eye", 0.25, 0, 1000);
        rage_eye_copy = BUILDER
                .comment("狂暴之眼最多可以偷盗的属性（相对于怪物）")
                .defineInRange("rage_eye_copy", 0.1, 0, 1000);
        Nightecora = BUILDER
                .comment("Nightecora病毒的额外生命值惩罚，单位百分比")
                .defineInRange("Nightecora", 25, 0, 100);

        nightmare_base_redemption_deception = BUILDER
                .comment("“欺骗”恢复的生命值，单位百分比")
                .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);

        nightmare_base_fool_bone = BUILDER
                .comment("危险的头骨造成的额外伤害，“2”是两倍")
                .defineInRange("nightmare_base_fool_bone", 2, 0, 9999);

        nightmare_base_insight_drug = BUILDER
                .comment("疯狂灵药的最大属性加成，单位百分比")
                .defineInRange("nightmare_base_insight_drug", 100, 0, 99999);

        nightmare_base_insight_drug_2 = BUILDER
                .comment("疯狂灵药的单物品计算的属性衰败，单位百分比")
                .defineInRange("nightmare_base_insight_drug_2", 8, 0, 99999);

        nightmare_base_insight_insane = BUILDER
                .comment("癫狂之石的杀死生物后获得的伤害加成，单位百分比")
                .defineInRange("nightmare_base_insight_insane", 150, 0, 99999);
        pain_ring = BUILDER
                .comment("邪祟之戒获得最大伤害加成和增加速度，单位百分比")
                .defineInRange("pain_ring", 1, 0, 99999);
        nightmarerotten = BUILDER
                .comment("万腐之心的属性加成，单位百分比")
                .defineInRange("nightmarerotten", 10, 0, 1000000);
        BUILDER.pop();


        BUILDER.push("Loot");
        bat = BUILDER
                .comment("The probability of discovering Shadow Plague items from the chests")
                .defineInRange("Plague_probability", 10, 1, 100);
        necora = BUILDER
                .comment("The probability of Necora items from the chests")
                .defineInRange("Necora_probability", 10, 1, 100);

        night = BUILDER
                .comment("The probability of Nightmare items from the chests")
                .defineInRange("Nightmare_probability", 10, 1, 100);

        common = BUILDER
                .comment("The larger this value, the lower the probability of discovering the item")
                .defineInRange("Common_probability", 1, 0.1, 100);
        nine_sword_book = BUILDER
                .comment("After enabling, you can find “nine_sword_books”")
                .define("NineSwordBooks", true);
        BUILDER.pop();






        BUILDER.push("nightmare");
        nightmareBaseMaxItem = BUILDER
                .comment("The value is equip NightmareBase give your item size")
                .defineInRange("Common_probability", 3, 0, 7);
        BUILDER.pop();

        BUILDER.build();
    }
    public ForgeConfigSpec.DoubleValue rage_eye;
    public ForgeConfigSpec.DoubleValue rage_eye_copy;

    public  ForgeConfigSpec.DoubleValue plague_speed;
    public   ForgeConfigSpec.DoubleValue plague_pain;
    public   ForgeConfigSpec.IntValue nightmare_moai;
    public   ForgeConfigSpec.IntValue m_brain_many;
    public   ForgeConfigSpec.DoubleValue m_brain_critical;
    public   ForgeConfigSpec.DoubleValue battery_speed;
    public   ForgeConfigSpec.DoubleValue quadriceps_speed;
    public   ForgeConfigSpec.DoubleValue flygene_speed;
    public   ForgeConfigSpec.DoubleValue bloodvirus_speed;
    public   ForgeConfigSpec.DoubleValue motor_speed ;
    public   ForgeConfigSpec.IntValue the_pain_stone ;
    public   ForgeConfigSpec.BooleanValue giveBook ;
    public   ForgeConfigSpec.IntValue bat ;
    public   ForgeConfigSpec.IntValue necora ;
    public   ForgeConfigSpec.IntValue night ;
    public   ForgeConfigSpec.DoubleValue common ;
    public   ForgeConfigSpec.DoubleValue nine_sword ;

    public   ForgeConfigSpec.BooleanValue nine_sword_book ;


    public   ForgeConfigSpec.IntValue ectoplasmstar ;
    public   ForgeConfigSpec.BooleanValue canUnequipMoonstoneItem ;
    public   ForgeConfigSpec.IntValue nightmareBaseMaxItem ;








    public   ForgeConfigSpec.IntValue Nightecora ;
    public   ForgeConfigSpec.IntValue nightmare_base_redemption_deception ;
    public   ForgeConfigSpec.IntValue nightmare_base_fool_bone ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug_2 ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_insane ;
    public   ForgeConfigSpec.IntValue pain_ring ;
    public   ForgeConfigSpec.IntValue nightmarerotten ;










    public   ForgeConfigSpec.BooleanValue itemQuality;

}
