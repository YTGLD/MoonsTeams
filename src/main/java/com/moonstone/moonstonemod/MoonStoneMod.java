package com.moonstone.moonstonemod;

import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.event.Anvil;
import com.moonstone.moonstonemod.event.LootEvent;
import com.moonstone.moonstonemod.event.Village;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.Particles;
import com.moonstone.moonstonemod.init.Tab;
import com.moonstone.moonstonemod.loot.abandoned_mineshaft;
import com.moonstone.moonstonemod.loot.ancient_city;
import com.moonstone.moonstonemod.loot.end_city;
import com.moonstone.moonstonemod.loot.simple_dungeon;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
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
        MinecraftForge.EVENT_BUS.register(new Anvil());
        MinecraftForge.EVENT_BUS.register(new Village());

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
            event.registerSpriteSet(Particles.gold.get(), com.moonstone.moonstonemod.Particle.red.Provider::new);
        }
        @SubscribeEvent
        public static void loot(RegisterEvent event) {
            event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
                helper.register(new ResourceLocation(MODID, "abandoned_mineshaft"), abandoned_mineshaft.CODEC);
                helper.register(new ResourceLocation(MODID, "ancient_city"), ancient_city.CODEC);
                helper.register(new ResourceLocation(MODID, "simple_dungeon"), simple_dungeon.CODEC);
                helper.register(new ResourceLocation(MODID, "end_city"), end_city.CODEC);
            });
        }
    }

}
