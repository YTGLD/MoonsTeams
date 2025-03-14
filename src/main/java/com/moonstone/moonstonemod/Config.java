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

        the_pain_stone = BUILDER
                .comment("What is this value, divide the damage by (“2” is “/2”)")
                .defineInRange("Int", 2, 1, 1000);

        nine_sword = BUILDER
                .comment("The maximum amount of damage that can be dealt")
                .defineInRange("Nine Sword Books", 256D, 0, 5120);

        {
            {
                BUILDER.push("一般配置");
                canUnequipMoonstoneItem = BUILDER
                        .comment("可以取下月之石的“不可以取下”的物品")
                        .define("Can", false);
                giveBook = BUILDER
                        .comment("开局给书")
                        .define("give", true);

                itemQuality = BUILDER
                        .comment("物品再发现时可以获得品阶")
                        .define("Quality_", false);

                BUILDER.pop();
            }
            {
                BUILDER.push("噩梦");
                nightmareBaseMaxItem = BUILDER
                        .comment("“”噩梦基座“给玩家的罪孽数量")
                        .defineInRange("nig", 3, 0, 7);

                Nightecora = BUILDER
                        .comment("Nightecora病毒的额外生命值惩罚，单位百分比")
                        .defineInRange("Nightecora", 25, 0, 100);
                nightmare_base_redemption_deception = BUILDER
                        .comment("“欺骗”恢复的生命值，单位百分比")
                        .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);
                nightmare_base_redemption_deception_time = BUILDER
                        .comment("“欺骗”恢复的生命值，单位秒")
                        .defineInRange("nightmare_base_redemption_deception_time", 7, 0, 100);

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
                BUILDER.pop();
            }
            {
                BUILDER.push("狂暴or扭曲");
                rage_eye = BUILDER
                        .comment("狂暴之眼最多可以偷盗的属性（相对于玩家）")
                        .defineInRange("rage_eye", 0.25, 0, 1000);
                rage_eye_copy = BUILDER
                        .comment("狂暴之眼最多可以偷盗的属性（相对于怪物）")
                        .defineInRange("rage_eye_copy", 0.1, 0, 1000);

                pain_ring = BUILDER
                        .comment("邪祟之戒获得最大伤害加成和增加速度，单位百分比")
                        .defineInRange("pain_ring_", 100f, 0, 999999);
                BUILDER.pop();
            }


            {
                BUILDER.push("一般物品");

                ectoplasmstar = BUILDER
                        .comment("”灵质幸运星的最大幸运转换“")
                        .defineInRange("EctoplasmStar", 100, 0, 1024);

                nightmare_moai = BUILDER
                        .comment("深渊石球的附魔等级加成")
                        .defineInRange("EnchantmentBonus", 2, 0, 100);

                m_brain_many = BUILDER
                        .comment("莫里斯脑子的伤害次数")
                        .defineInRange("MBrain_many", 5, 1, 100);
                m_brain_critical = BUILDER
                        .comment("莫里斯脑子的伤害")
                        .defineInRange("MBrain_critical_multiplier", 2.25, 1, 999);
                battery_speed = BUILDER
                        .comment("电池的速度")
                        .defineInRange("battery", 0.1, 0, 999);
                quadriceps_speed = BUILDER
                        .comment("四头肌强化的速度")
                        .defineInRange("quadriceps", 0.25, 0, 999);
                flygene_speed = BUILDER
                        .comment("夜行蝠突变基因的速度")
                        .defineInRange("flygene", 0.125, 0, 999);
                bloodvirus_speed = BUILDER
                        .comment("暗影瘟疫的速度")
                        .defineInRange("bloodvirus", 0.175, 0, 999);

                motor_speed = BUILDER
                        .comment("运动控制强化的速度")
                        .defineInRange("motor", 0.15, 0, 999);
                nightmarerotten = BUILDER
                        .comment("万腐之心的属性")
                        .defineInRange("motor", 10, 0, 999);
                BUILDER.pop();
            }
            {
                BUILDER.push("战利品");
                bat = BUILDER
                        .comment("”暗影瘟疫“的战利品出现几率，值越大，概率越高")
                        .defineInRange("Plague_probability", 10, 1, 100);
                necora = BUILDER
                        .comment("”Necora病毒“的战利品出现几率，值越大，概率越高")
                        .defineInRange("Necora_probability", 10, 1, 100);

                night = BUILDER
                        .comment("”深渊之眼“的战利品出现几率，值越大，概率越高")
                        .defineInRange("Nightmare_probability", 10, 1, 100);

                common = BUILDER
                        .comment("注意！这个值影响了普通战利品的出现概率。它的值越大，概率越小！不要改反了")
                        .defineInRange("Common_probability", 1, 0.1, 100);
                nine_sword_book = BUILDER
                        .comment("给予九剑归一诀”")
                        .define("NineSwordBooks", true);

                BUILDER.pop();
            }
            {
                BUILDER.push("AllLootTable");
                allLoot = BUILDER
                        .comment("如果开启则将模组内的战利品表禁用")
                        .define("all_loot_table", false);
                BUILDER.pop();
            }
            BUILDER.build();
        }
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
    public   ForgeConfigSpec.IntValue nightmare_base_redemption_deception_time ;
    public   ForgeConfigSpec.IntValue nightmare_base_fool_bone ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug_2 ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_insane ;
    public   ForgeConfigSpec.DoubleValue pain_ring ;
    public   ForgeConfigSpec.IntValue nightmarerotten ;

    public   ForgeConfigSpec.BooleanValue allLoot;









    public   ForgeConfigSpec.BooleanValue itemQuality;

}
