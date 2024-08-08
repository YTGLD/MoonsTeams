package com.moonstone.moonstonemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.DoubleValue plague_speed = BUILDER
            .comment("The growth rate of plague research sites")
            .defineInRange("GrowthSpeed", 0.1, 0, 100);
    public static final ForgeConfigSpec.DoubleValue plague_pain = BUILDER
            .comment("The corrosion speed of the plague")
            .defineInRange("CorrosionSpeed", 0.01, 0, 100);

    public static final ForgeConfigSpec.DoubleValue plague_effect = BUILDER
            .comment("The corrosive effect of plague(All effects will be multiplied by this value)")
            .defineInRange("CorrosionEffect", 1d, 0.01, 100);

    public static final ForgeConfigSpec.IntValue nightmare_moai = BUILDER
            .comment("Nightmare Moai's enchantment level bonus")
            .defineInRange("EnchantmentBonus", 2, 0, 100);
    public static final ForgeConfigSpec.IntValue m_brain_many = BUILDER
            .comment("How many critical hits will brain make")
            .defineInRange("MBrain_many", 5, 1, 100);

    public static final ForgeConfigSpec.DoubleValue m_brain_critical = BUILDER
            .comment("Brain's critical strike multiplier")
            .defineInRange("MBrain_critical_multiplier", 2.25, 1, 999);

    public static final ForgeConfigSpec.DoubleValue battery_speed = BUILDER
            .comment("The speed of the battery")
            .defineInRange("battery", 0.1, 0, 999);

    public static final ForgeConfigSpec.DoubleValue quadriceps_speed = BUILDER
            .comment("The speed of the quadriceps")
            .defineInRange("quadriceps", 0.25, 0, 999);

    public static final ForgeConfigSpec.DoubleValue flygene_speed = BUILDER
            .comment("The speed of the flygene")
            .defineInRange("flygene", 0.125, 0, 999);
    public static final ForgeConfigSpec.DoubleValue bloodvirus_speed = BUILDER
            .comment("The speed of the bloodvirus")
            .defineInRange("bloodvirus", 0.175, 0, 999);
    public static final ForgeConfigSpec.DoubleValue motor_speed = BUILDER
            .comment("The speed of the motor")
            .defineInRange("motor", 0.15, 0, 999);
    public static final ForgeConfigSpec.ConfigValue<String> ZombieNightmareGiant = BUILDER
            .comment("What creatures should be killed and dropped")
            .define("Mob","minecraft:warden");
    public static final ForgeConfigSpec.IntValue the_pain_stone = BUILDER
            .comment("What is this value, divide the damage by (“2” is “/2”)")
            .defineInRange("Int", 2, 1, 1000);


    static final ForgeConfigSpec SPEC = BUILDER.build();
}
