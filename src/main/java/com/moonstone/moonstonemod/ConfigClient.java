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
        Shader = BUILDER
                .comment("Do you want to enable the post rendering system")
                .define("Shader_", false);
        showDisplayNightmareTip = BUILDER
                .comment("在不佩戴噩梦基座的情况下显示噩梦物品的描述")
                .define("showDisplayNightmareTip", false);

        ItemGui = BUILDER
                .comment("显示物品的GUI叠加层（发光）")
                .define("ItemGui_", false);

        entityModel = BUILDER
                .comment("启用模组的自定义模型（对于佩戴在身上可以显示的类型）")
                .define("entityModel", false);


        BUILDER.pop();

        BUILDER.build();
    }
    public   ForgeConfigSpec.BooleanValue ItemGui ;

    public   ForgeConfigSpec.BooleanValue showDisplayNightmareTip ;

    public   ForgeConfigSpec.BooleanValue MaxAmout ;

    public   ForgeConfigSpec.BooleanValue Shader ;

    public   ForgeConfigSpec.BooleanValue entityModel ;

}
