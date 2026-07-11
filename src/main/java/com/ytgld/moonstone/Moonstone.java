package com.ytgld.moonstone;

import com.ytgld.moonstone.config.Config;
import com.ytgld.moonstone.event.NewEvent;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
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

        InitItems.TabChestItem.CREATIVE_MODE_TABS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.fc);
        NeoForge.EVENT_BUS.register(new NewEvent());

    }
}
