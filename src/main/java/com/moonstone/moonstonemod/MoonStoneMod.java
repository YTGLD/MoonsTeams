package com.moonstone.moonstonemod;

import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.entity.SwordRenderer;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.event.LootEvent;
import com.moonstone.moonstonemod.event.SwordEvent;
import com.moonstone.moonstonemod.event.Village;
import com.moonstone.moonstonemod.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MoonStoneMod.MODID)
public class MoonStoneMod {
    public static final String MODID = "moonstone";
    public static final Logger LOGGER = LogUtils.getLogger();
    public MoonStoneMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new AllEvent());
        MinecraftForge.EVENT_BUS.register(new LootEvent());
        MinecraftForge.EVENT_BUS.register(new Village());
        MinecraftForge.EVENT_BUS.register(new SwordEvent());

        LootReg.REGISTRY.register(modEventBus);

        EntityTs.REGISTRY.register(modEventBus);

        Particles.PARTICLE_TYPES.register(modEventBus);
        Items.REGISTRY.register(modEventBus);
        Tab.TABS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    @Mod.EventBusSubscriber(
            modid = MODID,
            value = { Dist.CLIENT },
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    public static class Client {
        @SubscribeEvent
        public static void registerFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(Particles.gold.get(), com.moonstone.moonstonemod.particle.red.Provider::new);
            event.registerSpriteSet(Particles.blue.get(), com.moonstone.moonstonemod.particle.blue.Provider::new);
            event.registerSpriteSet(Particles.popr.get(), com.moonstone.moonstonemod.particle.popr.Provider::new);
        }
        @SubscribeEvent
        public static void EntityRenderersEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EntityTs.flysword.get(), SwordRenderer::new);
            event.registerEntityRenderer(EntityTs.suddenrain.get(), SwordRenderer::new);

        }
    }

}
