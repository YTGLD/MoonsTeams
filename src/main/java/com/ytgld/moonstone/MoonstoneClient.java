package com.ytgld.moonstone;

import com.ytgld.moonstone.config.ModLanguageProvider;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.enttiy.render.*;
import com.ytgld.moonstone.event.key.Keys;
import com.ytgld.moonstone.item.ms.blood.magic.Consciousness;
import com.ytgld.moonstone.item.si.nightmare.ToolTip;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.function.Function;

@Mod(value = Moonstone.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Moonstone.MODID, value = Dist.CLIENT)
public class MoonstoneClient {
    public MoonstoneClient(ModContainer container) {

        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ToolTip.class, Function.identity());
    }

    @SubscribeEvent
    public static void emp(PlayerInteractEvent.LeftClickEmpty event){
        Consciousness.emp(event);
    }
    @SubscribeEvent
    public static void RegisterRenderPipelinesEvent(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityTs.owner_blood_.get(), OwnerBloodRender::new);
        event.registerEntityRenderer(EntityTs.attack_blood_.get(), AttackBloodsRender::new);
        event.registerEntityRenderer(EntityTs.cell_giant.get(), CellGiantRender::new);
        event.registerEntityRenderer(EntityTs.cell_zombie.get(), CellZombieRenderer::new);
        event.registerEntityRenderer(EntityTs.at_sword_entity.get(), AtSwordRender::new);
        event.registerEntityRenderer(EntityTs.sword.get(), SwordOfTwelveRender::new);

        event.registerEntityRenderer(EntityTs.flysword.get(), FlySwordRender::new);
        event.registerEntityRenderer(EntityTs.as_sword.get(), FlySwordRender::new);
    }
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(Keys.KEY_MAPPING_LAZY_R);
        event.register(Keys.ZombieC);
    }
    @SubscribeEvent // on the mod event bus
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModLanguageProvider::new);
    }
}
