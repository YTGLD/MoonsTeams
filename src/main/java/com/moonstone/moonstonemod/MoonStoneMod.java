package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.client.particle.blue;
import com.moonstone.moonstonemod.client.particle.popr;
import com.moonstone.moonstonemod.client.particle.red;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.c.CellZombieG;
import com.moonstone.moonstonemod.entity.c.SwordRenderer;
import com.moonstone.moonstonemod.entity.c.ZombieRenderer;
import com.moonstone.moonstonemod.event.*;
import com.moonstone.moonstonemod.init.*;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.IOException;

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
        MinecraftForge.EVENT_BUS.register(new Tool());
        MinecraftForge.EVENT_BUS.register(new LootTableEvent());
        MinecraftForge.EVENT_BUS.register(new NewEvent());

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
            event.registerSpriteSet(Particles.gold.get(), red.Provider::new);
            event.registerSpriteSet(Particles.blue.get(), blue.Provider::new);
            event.registerSpriteSet(Particles.popr.get(), popr.Provider::new);
        }

        @SubscribeEvent
        public static void EntityRenderersEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EntityTs.flysword.get(), SwordRenderer::new);
            event.registerEntityRenderer(EntityTs.suddenrain.get(), SwordRenderer::new);
            event.registerEntityRenderer(EntityTs.cell_zombie.get(), ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.cell_giant.get(), CellZombieG::new);
            event.registerEntityRenderer(EntityTs.nightmare_entity.get(), ZombieRenderer::new);

        }
        @SubscribeEvent
        public static void EntityRenderersEvent(RegisterShadersEvent event) {
            try {


                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"rendertype_gateway"),
                        DefaultVertexFormat.POSITION_COLOR_TEX),MRender::setShaderInstance_gateway);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"rendertype_mls"),
                        DefaultVertexFormat.POSITION_COLOR_TEX),MRender::setShaderInstance_mls);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"rendertype_ging"),
                        DefaultVertexFormat.POSITION_COLOR_TEX),MRender::setShaderInstance_ging);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

}
