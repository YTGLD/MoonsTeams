package com.ytgld.moonstone;

import com.ytgld.moonstone.config.ModLanguageProvider;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.nightmare.AllTip;
import com.ytgld.moonstone.item.nightmare.ToolTip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.function.Function;

@Mod(value = Moonstone.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Moonstone.MODID, value = Dist.CLIENT)
public class MoonstoneClient {
    public MoonstoneClient(ModContainer container) {

        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(ToolTip.class, Function.identity());
    }
    @SubscribeEvent // on the mod event bus
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModLanguageProvider::new);
    }
}
