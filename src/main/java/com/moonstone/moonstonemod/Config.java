package com.moonstone.moonstonemod;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final Config SERVER;
    public static final ForgeConfigSpec fc;
    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }

    public static String getRegisteredName(Holder<Attribute> attribute) {
        return attribute.unwrapKey().map((p_316542_) -> {
            return p_316542_.location().toString();
        }).orElse("[unregistered]");
    }
    public   ForgeConfigSpec.BooleanValue eqNightmareBase ;
    public   ForgeConfigSpec.BooleanValue disFallRing ;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> disItemOfNightmare ;


    public   ForgeConfigSpec.IntValue blood_god_kill ;
    public   ForgeConfigSpec.IntValue blood_god_heal ;
    public   ForgeConfigSpec.IntValue blood_god_damage ;
    public   ForgeConfigSpec.IntValue Nightecora ;
    public   ForgeConfigSpec.IntValue nightmare_base_redemption_deception ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug ;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_drug_2 ;
    public   ForgeConfigSpec.IntValue nightmare_base_redemption_deception_time ;
    public   ForgeConfigSpec.IntValue nightmareBaseMaxItem ;
    public final ForgeConfigSpec.BooleanValue nightmare_base_black_eye ;
    public final ForgeConfigSpec.DoubleValue nightmare_base_stone ;
    public final ForgeConfigSpec.DoubleValue nightmare_base_fool ;
    public final ForgeConfigSpec.IntValue nightmare_base_insight ;
    public final ForgeConfigSpec.IntValue nightmare_base_redemption ;
    public final ForgeConfigSpec.IntValue nightmare_base_reversal ;
    public final ForgeConfigSpec.IntValue nightmare_base_start ;
    public final ForgeConfigSpec.IntValue give_nightmare_base_insight_drug ;
    public  final ForgeConfigSpec.BooleanValue the_prison_of_sin;
    public  final ForgeConfigSpec.DoubleValue the_prison_of_sin2;
    public  final ForgeConfigSpec.DoubleValue the_prison_of_sin3;
    public  final ForgeConfigSpec.DoubleValue the_prison_of_sin4;

    public ForgeConfigSpec.ConfigValue<List<? extends String>>  allAttributeModify;
    public ForgeConfigSpec.ConfigValue<List<? extends String>>  attackTarget;

    public Config(ForgeConfigSpec.Builder BUILDER){
        plague_speed = BUILDER
                .comment("远古病毒研究点的增长速度")
                .defineInRange("GrowthSpeed", 0.1, 0, 100);
        plague_pain = BUILDER
                .comment("远古病毒的腐蚀速度")
                .defineInRange("CorrosionSpeed", 0.01, 0, 100);

        the_pain_stone = BUILDER
                .comment("这个伤害数值是多少")
                .defineInRange("Int", 2, 1, 1000);

        nine_sword = BUILDER
                .comment("最大伤害")
                .defineInRange("Nine Sword Books", 256D, 0, 5120);
        allAttributeModify = BUILDER
                .comment("增加全属性的物品其属性黑名单")
                .defineList("allAttributeModify",
                        List.of("forge:entity_gravity"),
                        s->s instanceof String);
        attackTarget = BUILDER
                .comment("飞剑，血灵一类生物的攻击目标黑名单")
                .defineList("attackTarget",
                        List.of("minecraft:player","minecraft:pig"),
                        s->s instanceof String);

        {
            {
                BUILDER.push("一般配置");
                killFlySword = BUILDER
                        .comment("关闭飞剑")
                        .define("killFlySword", false);
                canUnequipMoonstoneItem = BUILDER
                        .comment("可以取下月之石的“不可以取下”的物品")
                        .define("Can", false);
                giveBook = BUILDER
                        .comment("开局给书")
                        .define("give", true);

                itemQuality = BUILDER
                        .comment("物品再发现时可以获得品阶")
                        .define("Quality_", false);

                canUse = BUILDER
                        .comment("玩家可以在不佩戴相关前置物品的情况下使用噩梦的相关物品")
                        .define("canUseNightmare", false);

                canUseAbyss = BUILDER
                        .comment("玩家可以在不佩戴相关前置物品的情况下使用深渊的相关物品")
                        .define("canUseAbyss", false);

                BUILDER.pop();
            }
            {
                BUILDER.push("噩梦");

                giveNightmare = BUILDER
                        .comment("给予玩家噩梦座")
                        .define("giveNightmare", true);
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

                the_prison_of_sin = BUILDER
                        .comment("显示罪孽囚笼的描述（会在不佩戴的情况下显示具体的信息）")
                        .define("the_prison_of_sin", false);
                the_prison_of_sin2 = BUILDER
                        .comment("罪孽囚笼的属性增长倍数")
                        .defineInRange("the_prison_of_sin2", 4.5f,0,9999f);
                the_prison_of_sin3 = BUILDER
                        .comment("罪孽囚笼的属性增长最大值，单位是“%”，（因为默认罪孽囚笼给予了10%，所以应该比默认值高10%）")
                        .defineInRange("the_prison_of_sin3", 100,0,10000000f);
                the_prison_of_sin4 = BUILDER
                        .comment("罪孽囚笼的生命值和护甲的属性衰败")
                        .defineInRange("the_prison_of_sin4", 0.8,0,10000000f);


                openDamageForAttacker = BUILDER
                        .comment("一些饰品的反伤效果，默认开启")
                        .define("openDamageForAttacker", true);


                blockParticle = BUILDER
                        .comment("一些（诺克萨斯纹章/决裁徽章/符石）的粒子效果")
                        .define("blockParticle", true);


                ytgld_curse = BUILDER
                        .comment("终极湮灭病毒的增值速度，单位：刻，值越大速度越慢")
                        .defineInRange("ytgld_curse", 15, 1, 999999);
                ytgld_research = BUILDER
                        .comment("终极湮灭病毒的研究速度，10点是0.1%")
                        .defineInRange("ytgld_research", 10, 1, 999999);
                off_or_on_ytgld = BUILDER
                        .comment("启用终极湮灭病毒")
                        .define("off_or_on_ytgld", true);




                canFlySword = BUILDER
                        .comment("”七剑修罗·万法“和”七剑修罗·剑阵之章“同时佩戴后仍然可以发射飞剑")
                        .define("canFlySword", true);

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
                        .defineInRange("motor_speed", 0.15, 0, 999);
                nightmarerotten = BUILDER
                        .comment("万腐之心的属性")
                        .defineInRange("nightmarerotten", 10, 0, 999);

                batgene = BUILDER
                        .comment("夜行蝙蝠基因：蝙蝠给予玩家的最大伤害加成，默认2（200%）")
                        .defineInRange("batgene", 2, 0, 99999f);

                undead_blood_charm = BUILDER
                        .comment("决裁者徽章的伤害，“1”就是100%")
                        .defineInRange("undead_blood_charm", 1, 0, 99999f);


                fermentation = BUILDER
                        .comment("埋伏性狩猎激活时的伤害倍数")
                        .defineInRange("fermentation", 4, 0, 100000f);
                fermentation2 = BUILDER
                        .comment("埋伏性狩猎冷却时的伤害倍数")
                        .defineInRange("fermentation2", 0.3, 0, 100000f);
                fermentation3 = BUILDER
                        .comment("埋伏性狩猎的冷却时间")
                        .defineInRange("fermentation3", 200, 0, 100000);


                universe = BUILDER
                        .comment("万象模板的加伤害时长")
                        .defineInRange("universe", 200, 0, 100000);
                universe2 = BUILDER
                        .comment("万象模板的加伤害加成，单位%")
                        .defineInRange("universe2", 10, 0, 100000);
                raw = BUILDER
                        .comment("原始人的愤怒其伤害，治疗和最大生命")
                        .defineInRange("raw", 5, 0, 100000f);

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

            {
                BUILDER.push("nineSwordList");
                nineSwordList = BUILDER
                        .comment("九剑归一诀的白名单（识别剑类武器）")
                        .defineListAllowEmpty("nineSwordList", List.of("minecraft:dirt"),  Config::validateItemName);
                BUILDER.pop();
            }
            {
                BUILDER.push("噩梦");
                {
                    BUILDER.push("其他");
                    eqNightmareBase = BUILDER
                            .comment("开局自动佩戴噩梦基座和所有附属罪孽")
                            .define("eqNightmareBase", false);

                    disFallRing = BUILDER
                            .comment("禁止使用噩梦基座合成天启之戒")
                            .define("disFallRing", false);

                    disItemOfNightmare = BUILDER
                            .comment("禁止获取的救赎物品")
                            .defineList("disItemOfNightmare",
                                    List.of(),

                                    s->s instanceof String);


                    BUILDER.pop();

                }
                {
                    BUILDER.push("获取");
                    give_nightmare_base_insight_drug = BUILDER
                            .comment("疯狂灵药获取时要求的药水数量")
                            .defineInRange("give_nightmare_base_insight_drug", 9,1,100);
                    blood_god_kill = BUILDER.comment("神血祖符需要的击杀数")
                            .defineInRange("blood_god_kill", 500,1,Integer.MAX_VALUE);
                    blood_god_heal = BUILDER.comment("神血祖符需要的治疗量")
                            .defineInRange("blood_god_heal", 5000,1,Integer.MAX_VALUE);
                    blood_god_damage = BUILDER.comment("神血祖符需要的造成伤害")
                            .defineInRange("blood_god_damage", 10000,1,Integer.MAX_VALUE);
                    BUILDER.pop();

                }
                {
                    BUILDER.push("罪孽");
                    {
                        BUILDER.push("邪念之窥眸");
                        nightmare_base_black_eye = BUILDER
                                .comment("近视效果开关")
                                .define("nightmare_base_black_eye", true);
                        BUILDER.pop();

                        BUILDER.push("死兆方尖碑");
                        nightmare_base_stone = BUILDER
                                .comment("满血的受伤伤害")
                                .defineInRange("nightmare_base_stone", 5f, 1, 999);
                        BUILDER.pop();

                        BUILDER.push("愚者之危");
                        nightmare_base_fool = BUILDER
                                .comment("最大处罚值，0.5就是50%")
                                .defineInRange("nightmare_base_fool", 0.5f, 0, 1);
                        BUILDER.pop();

                        BUILDER.push("噩梦洞悉者");
                        nightmare_base_insight = BUILDER
                                .comment("附魔的减少值")
                                .defineInRange("nightmare_base_insight", 2, 0, 1000);
                        BUILDER.pop();

                        BUILDER.push("“救赎”");
                        nightmare_base_redemption = BUILDER
                                .comment("属性衰败比例")
                                .defineInRange("nightmare_base_redemption", 15, 0, 100);
                        nightmare_base_redemption_power = BUILDER
                                .comment("正面属性加成")
                                .defineInRange("nightmare_base_redemption_power", 10f, 0, 100);
                        BUILDER.pop();

                        BUILDER.push("颠倒之物");
                        nightmare_base_reversal = BUILDER
                                .comment("每次死亡降低的最低值")
                                .defineInRange("nightmare_base_reversal", 4, 0, 100);
                        BUILDER.pop();

                        BUILDER.push("噩梦之起始");
                        nightmare_base_start = BUILDER
                                .comment("护甲值的处罚")
                                .defineInRange("nightmare_base_start", 100, 0, 100);

                        BUILDER.pop();
                    }
                    BUILDER.pop();
                }
                {
                    BUILDER.push("救赎");
                    {
                        nightmareBaseMaxItem = BUILDER
                                .comment("“”噩梦基座“给玩家的罪孽数量")
                                .defineInRange("nig_", 5, 0, 7);

                        Nightecora = BUILDER
                                .comment("Nightecora病毒的额外生命值惩罚，单位百分比")
                                .defineInRange("Nightecora_", 10, 0, 100);
                        nightmare_base_redemption_deception = BUILDER
                                .comment("“欺骗”恢复的生命值，单位百分比")
                                .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);
                        nightmare_base_redemption_deception_time = BUILDER
                                .comment("“欺骗”无敌时间，单位秒")
                                .defineInRange("nightmare_base_redemption_deception_time", 7, 0, 100);

                        nightmare_base_insight_drug = BUILDER
                                .comment("疯狂灵药的最大属性加成，单位百分比")
                                .defineInRange("nightmare_base_insight_drug", 80, 0, 99999);

                        nightmare_base_insight_drug_2 = BUILDER
                                .comment("疯狂灵药的单物品计算的属性衰败，单位百分比")
                                .defineInRange("nightmare_base_insight_drug_2", 8, 0, 99999);



                        nightmare_base_insight_insane = BUILDER
                                .comment("癫狂之石的杀死生物后获得的伤害加成，单位百分比")
                                .defineInRange("nightmare_base_insight_insane", 30, 0, 99999);
                        nightmare_base_fool_bone = BUILDER
                                .comment("危险的头骨造成的额外伤害")
                                .defineInRange("nightmare_base_fool_bone", 25f, 0, 9999);
                        nightmare_base_black_eye_eye = BUILDER
                                .comment("惶恐肉瘤伤害加成")
                                .defineInRange("nightmare_base_black_eye_eye", 30, 0, 99999);
                        nightmare_base_black_eye_heart = BUILDER
                                .comment("死心伤害加成")
                                .defineInRange("nightmare_base_black_eye_heart", 125, 0, 99999);
                        nightmare_base_black_eye_red = BUILDER
                                .comment("罪恶红唇全属性加成")
                                .defineInRange("nightmare_base_black_eye_red", 50, 0, 99999);

                        nightmare_base_stone_virus = BUILDER
                                .comment("Nightecore病毒 'moonstone:nightmare_base_stone_virus' 伤害加成 暴击加成")
                                .defineInRange("nightmare_base_stone_virus", 33, 0, 99999);

                        nightmare_base_reversal_card = BUILDER
                                .comment("不稳卡片 'moonstone:nightmare_base_reversal_card' 全属性加成")
                                .defineInRange("nightmare_base_reversal_card", 46, 0, 99999);
                        candle = BUILDER
                                .comment("邪异古烛 'moonstone:candle' 无敌期间的伤害加成")
                                .defineInRange("candle", 120, 0, 99999);

                        nightmare_base_redemption_down_and_out = BUILDER
                                .comment("落魄 'moonstone:nightmare_base_redemption_down_and_out' 属性衰败减少")
                                .defineInRange("nightmare_base_redemption_down_and_out", 35, 0, 99999);

                        nightmare_base_fool_soul   = BUILDER
                                .comment("幽怨之魂 'moonstone:nightmare_base_redemption_down_and_out' 生命值加成")
                                .defineInRange("nightmare_base_fool_soul", 1, 0, 99999);
                        nightmare_base_fool_soul2   = BUILDER
                                .comment("幽怨之魂 'moonstone:nightmare_base_redemption_down_and_out' 治疗")
                                .defineInRange("nightmare_base_fool_soul2", 2, 0, 99999);
                        nightmare_base_start_egg = BUILDER
                                .comment("起源卵蛋 'moonstone:nightmare_base_start_egg' 伤害加成")
                                .defineInRange("nightmare_base_start_egg", 20, 0, 99999);
                        nightmare_base_start_egg1 = BUILDER
                                .comment("起源卵蛋 'moonstone:nightmare_base_start_egg1' 治疗")
                                .defineInRange("nightmare_base_start_egg1", 50, 0, 99999);
                        nightmare_base_start_egg2 = BUILDER
                                .comment("起源卵蛋 'moonstone:nightmare_base_start_egg' 幸运")
                                .defineInRange("nightmare_base_start_egg2", 10, 0, 99999);

                        nightmare_base_start_power = BUILDER
                                .comment("愕然权利 'moonstone:nightmare_base_start_power' 全属性加成")
                                .defineInRange("nightmare_base_start_power", 2f, 0, 99999);



                        nightmare_base_stone_meet1 = BUILDER
                                .comment("绝望共鸣 混乱水晶的伤害加成")
                                .defineInRange("nightmare_base_stone_meet1_", 150f, 0, 99999);
                        nightmare_base_stone_meet2 = BUILDER
                                .comment("绝望共鸣幸运的治疗")
                                .defineInRange("nightmare_base_stone_meet2_", 2.5, 0, 99999);
                        nightmare_base_stone_meet3 = BUILDER
                                .comment("绝望共鸣药水效果加成等级")
                                .defineInRange("nightmare_base_stone_meet3_", 2f, 0, 99999);
                        end_bone = BUILDER
                                .comment("末世脊骨的反伤")
                                .defineInRange("end_bone", 0.7f, 0, 99999);


                        apple_health = BUILDER
                                .comment("倾斜异果生命")
                                .defineInRange("apple_health", 30, 1, 99999);
                        apple_damage = BUILDER
                                .comment("倾斜异果的伤害")
                                .defineInRange("apple_health", 10, 1, 99999);
                        apple_hurt = BUILDER
                                .comment("倾斜异果受伤")
                                .defineInRange("apple_health", 2,1, 99999);
                        apple_give = BUILDER
                                .comment("倾斜异果的给予倍数")
                                .defineInRange("apple_health", 30, 1, 99999);

                    }
                    BUILDER.pop();
                }
                BUILDER.pop();

            }
            BUILDER.build();
        }
    }
    public   ForgeConfigSpec.IntValue nightmare_base_black_eye_eye;
    public   ForgeConfigSpec.IntValue nightmare_base_insight_insane ;
    public   ForgeConfigSpec.DoubleValue nightmare_base_fool_bone ;
    public   ForgeConfigSpec.IntValue nightmare_base_black_eye_heart;
    public   ForgeConfigSpec.IntValue nightmare_base_black_eye_red;
    public   ForgeConfigSpec.IntValue nightmare_base_stone_virus;
    public   ForgeConfigSpec.DoubleValue nightmare_base_stone_meet1;
    public   ForgeConfigSpec.DoubleValue nightmare_base_stone_meet2;
    public   ForgeConfigSpec.DoubleValue nightmare_base_stone_meet3;
    public   ForgeConfigSpec.IntValue nightmare_base_reversal_card;
    public   ForgeConfigSpec.IntValue nightmare_base_redemption_down_and_out;
    public   ForgeConfigSpec.IntValue nightmare_base_fool_soul;
    public   ForgeConfigSpec.IntValue nightmare_base_fool_soul2;
    public   ForgeConfigSpec.IntValue nightmare_base_start_egg;
    public   ForgeConfigSpec.IntValue nightmare_base_start_egg1;
    public   ForgeConfigSpec.IntValue nightmare_base_start_egg2;
    public   ForgeConfigSpec.DoubleValue nightmare_base_start_power;
    public   ForgeConfigSpec.IntValue candle;
    public   ForgeConfigSpec.DoubleValue nightmare_base_redemption_power;
    public   ForgeConfigSpec.DoubleValue end_bone;
    public   ForgeConfigSpec.IntValue apple_health;
    public   ForgeConfigSpec.IntValue apple_hurt;
    public   ForgeConfigSpec.IntValue apple_damage;
    public   ForgeConfigSpec.IntValue apple_give;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(new ResourceLocation(itemName));
    }














    public   ForgeConfigSpec.DoubleValue raw;

    public   ForgeConfigSpec.IntValue universe;
    public   ForgeConfigSpec.IntValue universe2;



    public   ForgeConfigSpec.DoubleValue fermentation;
    public   ForgeConfigSpec.DoubleValue fermentation2;
    public   ForgeConfigSpec.IntValue fermentation3;


    public ForgeConfigSpec.ConfigValue<List<? extends String>> nineSwordList ;
    public   ForgeConfigSpec.BooleanValue giveNightmare ;
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

    public   ForgeConfigSpec.IntValue ytgld_research ;
    public   ForgeConfigSpec.IntValue ytgld_curse ;
    public   ForgeConfigSpec.BooleanValue off_or_on_ytgld ;




    public   ForgeConfigSpec.DoubleValue batgene ;

    public   ForgeConfigSpec.DoubleValue pain_ring ;
    public   ForgeConfigSpec.IntValue nightmarerotten ;

    public   ForgeConfigSpec.BooleanValue allLoot;
    public   ForgeConfigSpec.BooleanValue canFlySword;
    public   ForgeConfigSpec.BooleanValue blockParticle ;
    public   ForgeConfigSpec.BooleanValue canUse ;

    public   ForgeConfigSpec.BooleanValue killFlySword ;

    public   ForgeConfigSpec.BooleanValue itemQuality;
    public   ForgeConfigSpec.BooleanValue giveYtgld;
    public   ForgeConfigSpec.BooleanValue openDamageForAttacker;
    public   ForgeConfigSpec.BooleanValue canUseAbyss;



    public   ForgeConfigSpec.DoubleValue undead_blood_charm ;

}
