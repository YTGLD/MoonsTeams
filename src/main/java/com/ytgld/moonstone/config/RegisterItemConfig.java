package com.ytgld.moonstone.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public interface RegisterItemConfig {
    void config(ModConfigSpec.Builder builder);

    List<CIString> theLanguageProvider();

    default String theCategory() {
        return "";
    }

    record CIString(String path, String doIt, String doName) {

    }
}