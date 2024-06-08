package com.moonstone.moonstonemod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoonStoneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 32, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.IntValue MAGIC_NUMBER2 = BUILDER
            .comment("A magic number 2")
            .defineInRange("magicNumber2", 32, 0, Integer.MAX_VALUE);
    static final ForgeConfigSpec SPEC = BUILDER.build();
}
