package com.ytgld.moonstone.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    private static final Pair<Config, ModConfigSpec> BUILDER = new ModConfigSpec.Builder().configure(Config::new);
    public static Config config = BUILDER.getKey();
    public static ModConfigSpec fc = BUILDER.getRight();
    public Config(ModConfigSpec.Builder builder) {
        for (RegisterItemConfig registerItemConfig : ConfigPluginFinder.getModPlugins()) {
            registerItemConfig.config(builder);
        }
    }
}
