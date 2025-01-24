package com.moonstone.moonstonemod;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigClient {
    public static final ConfigClient Client;
    public static final ForgeConfigSpec fc;
    static {
        final Pair<ConfigClient, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ConfigClient::new);
        Client = specPair.getLeft();
        fc = specPair.getRight();
    }
    public ConfigClient(ForgeConfigSpec.Builder BUILDER){

        BUILDER.push("client");

        MaxAmout = BUILDER
                .comment("Display Nexus' blood cells")
                .define("MaxAmout", true);
        BUILDER.pop();

        BUILDER.build();
    }

    public   ForgeConfigSpec.BooleanValue MaxAmout ;



}
