package com.ytgld.moonstone;

import com.mojang.logging.LogUtils;
import com.ytgld.moonstone.config.Config;
import com.ytgld.moonstone.crafting.AllCrafting;
import com.ytgld.moonstone.crafting.MoonRecipeProvider;
import com.ytgld.moonstone.effect.Effects;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.event.*;
import com.ytgld.moonstone.event.itemevent.ZombieHandler;
import com.ytgld.moonstone.event.key.ClientEvent;
import com.ytgld.moonstone.event.key.UseCuriosHandler;
import com.ytgld.moonstone.event.loot.LootTableEvent;
import com.ytgld.moonstone.event.loot.Loots;
import com.ytgld.moonstone.item.Book;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.blood.magic.Consciousness;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.AttRegClient;
import com.ytgld.moonstone.other.DataReg;
import com.ytgld.moonstone.item.GatherItemModel;
import com.ytgld.moonstone.utils.network.WrathHeavenRenderPacket;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.slf4j.Logger;

import java.util.Set;

@Mod(Moonstone.MODID)
public class Moonstone {
    public static final String MODID = "moonstone";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Identifier POST_BLACK = Identifier.fromNamespaceAndPath(MODID,
            "black");
    public Moonstone(IEventBus modEventBus, ModContainer modContainer) {
        DataReg.REGISTRY.register(modEventBus);
        AttReg.REGISTRY.register(modEventBus);
        AttReg.ATTACHMENT_TYPES.register(modEventBus);
        InitItems.ITEMS.register(modEventBus);
        Effects.REGISTRY.register(modEventBus);
        Loots.LOOT.register(modEventBus);
        EntityTs.REGISTRY.register(modEventBus);
        AllCrafting.REGISTRY.register(modEventBus);
        AttRegClient.ATTACHMENT_TYPES.register(modEventBus);
        DNAItems.ITEMS.register(modEventBus);

        InitItems.TabChestItem.CREATIVE_MODE_TABS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.LOCAL, Config.fc);

        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::registerPayloadHandler);

        NeoForge.EVENT_BUS.register(new NewEvent());
        NeoForge.EVENT_BUS.register(new AdvancementEvt());
        NeoForge.EVENT_BUS.register(new TextEvt());
        NeoForge.EVENT_BUS.register(new LootTableEvent());
        NeoForge.EVENT_BUS.register(new ClientEvent());
        NeoForge.EVENT_BUS.register(new DNAModifyHandler());
        NeoForge.EVENT_BUS.register(new MainDNAEvent());

    }
    private void registerPayloadHandler(final RegisterPayloadHandlersEvent evt) {
        Consciousness.register(evt);
        UseCuriosHandler.register(evt.registrar("2.0"));
        ZombieHandler.register(evt.registrar("3.0"));

        WrathHeavenRenderPacket.registerPayloads(evt);
        Book.register(evt);

    }
    public void gatherData(GatherDataEvent.Client event) {
        event.createReloadableRegistryObjects(
                new RegistrySetBuilder()
                        .add(RecipeProvider.asBootstrap(MoonRecipeProvider::new)), Set.of(MODID));
        event.createProvider(GatherItemModel::new);
    }
}
