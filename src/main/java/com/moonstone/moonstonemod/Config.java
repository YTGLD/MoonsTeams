package com.moonstone.moonstonemod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

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


        plague_effect = BUILDER
                .comment("The corrosive effect of plague(All effects will be multiplied by this value)")
                .defineInRange("CorrosionEffect", 1d, 0.01, 100);
        plague_effect = BUILDER
                .comment("The corrosive effect of plague(All effects will be multiplied by this value)")
                .defineInRange("CorrosionEffect", 1d, 0.01, 100);
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
        ZombieNightmareGiant = BUILDER
                .comment("What creatures should be killed and dropped")
                .define("Mob","minecraft:warden");

        the_pain_stone = BUILDER
                .comment("What is this value, divide the damage by (“2” is “/2”)")
                .defineInRange("Int", 2, 1, 1000);

        FlyingSword = BUILDER
                .comment("Will the Flying Sword and Suddenrain Sword attack any creatures")
                .define("Entity",List.of("minecraft:pig","minecraft:villager"));


        BUILDER.build();
    }
    public  ForgeConfigSpec.DoubleValue plague_speed;
    public   ForgeConfigSpec.DoubleValue plague_pain;

    public   ForgeConfigSpec.DoubleValue plague_effect;

    public   ForgeConfigSpec.IntValue nightmare_moai;
    public   ForgeConfigSpec.IntValue m_brain_many

            ;

    public   ForgeConfigSpec.DoubleValue m_brain_critical;

    public   ForgeConfigSpec.DoubleValue battery_speed;

    public   ForgeConfigSpec.DoubleValue quadriceps_speed;

    public   ForgeConfigSpec.DoubleValue flygene_speed;


    public   ForgeConfigSpec.DoubleValue bloodvirus_speed;
    public   ForgeConfigSpec.DoubleValue motor_speed ;
    public   ForgeConfigSpec.ConfigValue<String> ZombieNightmareGiant;

    public   ForgeConfigSpec.IntValue the_pain_stone ;

    public   ForgeConfigSpec.ConfigValue<List<String>> FlyingSword ;


}
