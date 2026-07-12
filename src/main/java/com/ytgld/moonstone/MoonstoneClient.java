package com.ytgld.moonstone;

import com.ytgld.moonstone.config.ModLanguageProvider;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.enttiy.render.AttackBloodsRender;
import com.ytgld.moonstone.enttiy.render.OwnerBloodRender;
import com.ytgld.moonstone.item.si.nightmare.ToolTip;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.data.event.GatherDataEvent;

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
    public static void RegisterRenderPipelinesEvent(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityTs.owner_blood_.get(), OwnerBloodRender::new);
        event.registerEntityRenderer(EntityTs.attack_blood_.get(), AttackBloodsRender::new);
    }
    @SubscribeEvent // on the mod event bus
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModLanguageProvider::new);
    }
}
