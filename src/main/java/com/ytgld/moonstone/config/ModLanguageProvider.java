package com.ytgld.moonstone.config;


import com.ytgld.moonstone.Moonstone;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Moonstone.MODID, "moonstone_test_lang");
    }

    @Override
    protected void addTranslations() {
        for (RegisterItemConfig registerItemConfig : ConfigPluginFinder.getModPlugins()) {
            for (RegisterItemConfig.CIString theLanguageProvider : registerItemConfig.theLanguageProvider()) {
                add("moonstone.configuration." + theLanguageProvider.path(), theLanguageProvider.doIt());
                add("moonstone.config." + theLanguageProvider.path(), theLanguageProvider.doIt() + theLanguageProvider.doName());
            }
        }
    }
}