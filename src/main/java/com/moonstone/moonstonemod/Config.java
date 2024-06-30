package com.moonstone.moonstonemod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

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

    public static final ForgeConfigSpec.BooleanValue Exclusion_and_affinity = BUILDER
            .comment("Whether to enable exclusion and affinity mechanisms")
            .define("Exclusion and affinity",false);

    static final ForgeConfigSpec SPEC = BUILDER.build();
}
