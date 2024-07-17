package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.client.particle.blue;
import com.moonstone.moonstonemod.client.particle.popr;
import com.moonstone.moonstonemod.client.particle.red;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.boltR.BoltLightRenderer;
import com.moonstone.moonstonemod.entity.boltR.BoltRenderer;
import com.moonstone.moonstonemod.entity.boltR.bule_boltR;
import com.moonstone.moonstonemod.entity.nightmare.CellZombieN;
import com.moonstone.moonstonemod.entity.swords.SwordRenderer;
import com.moonstone.moonstonemod.entity.zomb.CellZombieG;
import com.moonstone.moonstonemod.entity.zomb.ZombieRenderer;
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
        MSound.REGISTRY.register(modEventBus);


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
            event.registerEntityRenderer(EntityTs.red_entity.get(), com.moonstone.moonstonemod.entity.zomb.red.ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.nightmare_giant.get(), CellZombieN::new);
            event.registerEntityRenderer(EntityTs.test_e.get(), com.moonstone.moonstonemod.entity.zomb.red.ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.sword.get(), com.moonstone.moonstonemod.entity.zomb.red.ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.bolt.get(), BoltRenderer::new);
            event.registerEntityRenderer(EntityTs.bolt_light.get(), BoltLightRenderer::new);
            event.registerEntityRenderer(EntityTs.bule_bolt.get(), bule_boltR::new);

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

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"trail"),
                        DefaultVertexFormat.POSITION_COLOR_TEX),MRender::setShaderInstance_trail);
                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"meteor_trail"),
                        DefaultVertexFormat.POSITION_COLOR_TEX),MRender::set_meteorTrailShader);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

}
