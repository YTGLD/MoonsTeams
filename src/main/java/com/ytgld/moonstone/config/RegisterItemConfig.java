package com.ytgld.moonstone.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public interface RegisterItemConfig {
    void config(ModConfigSpec.Builder builder);

    List<CIString> theLanguageProvider();

    default String theCategory() {
        return "";
    }
    static ModConfigSpec.DoubleValue defineInRange(ModConfigSpec.Builder builder,
                                                   String translationKey,
                                                   String path,
                                                   double defaultValue,
                                                   double min,
                                                   double max) {
        if (defaultValue < min) {
            defaultValue = min;
        }
        if (defaultValue > max) {
            defaultValue = max;
        }
        return builder.translation(translationKey)
                .defineInRange(path, defaultValue, min, max);
    }
    record CIString(String path, String doIt, String doName) {

    }
}