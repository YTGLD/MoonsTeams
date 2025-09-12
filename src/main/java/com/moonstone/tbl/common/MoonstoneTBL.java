package com.moonstone.tbl.common;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class MoonstoneTBL {
    public static final String ID = MoonStoneMod.MODID;
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation("moonstone", name.toLowerCase(Locale.ROOT));
    }
}
