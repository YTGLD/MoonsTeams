package com.ytgld.moonstone;

import com.ytgld.moonstone.config.Config;
import com.ytgld.moonstone.effect.Effects;
import com.ytgld.moonstone.event.AdvancementEvt;
import com.ytgld.moonstone.event.NewEvent;
import com.ytgld.moonstone.event.loot.Loots;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Moonstone.MODID)
public class Moonstone {
    public static final String MODID = "moonstone";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public Moonstone(IEventBus modEventBus, ModContainer modContainer) {
        DataReg.REGISTRY.register(modEventBus);
        AttReg.REGISTRY.register(modEventBus);
        InitItems.ITEMS.register(modEventBus);
        Effects.REGISTRY.register(modEventBus);
        Loots.LOOT.register(modEventBus);

        InitItems.TabChestItem.CREATIVE_MODE_TABS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.fc);

        NeoForge.EVENT_BUS.register(new NewEvent());
        NeoForge.EVENT_BUS.register(new AdvancementEvt());
    }
}
