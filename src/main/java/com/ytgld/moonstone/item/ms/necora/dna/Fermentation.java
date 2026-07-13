package com.ytgld.moonstone.item.ms.necora.dna;


import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.NecoraHandler;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

import static com.ytgld.moonstone.item.Items.GodFermentation;

public class Fermentation extends TheNecora implements CanUPLevel {
    public Fermentation(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.Fermentation")
                    .defineInRange("Fermentation", 0.3f, 0, Integer.MAX_VALUE);
            intValue2 = builder.translation("moonstone.config.Fermentation2")
                    .defineInRange("Fermentation2", 3f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("Fermentation",
                            "埋伏狩猎", "最小伤害"),
                    new CIString("Fermentation2",
                            "埋伏狩猎2", "致命一击伤害"));
        }
    }
    public static void fermentation(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (NecoraHandler.has(player, Items.fermentation.get()) || Handler.hascurio(player, GodFermentation.asItem())){
                if (player.getCooldowns().isOnCooldown(Items.fermentation.get().getDefaultInstance())){
                    event.setNewDamage(event.getNewDamage() * ConfigItem.intValue.get().floatValue());
                }else {
                    event.setNewDamage((float) (event.getNewDamage() * ConfigItem.intValue2.get()));
                    player.getCooldowns().addCooldown(Items.fermentation.get().getDefaultInstance(),100);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.fermentation.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.fermentation.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.fermentation.tool.string.2").withStyle(ChatFormatting.RED));
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.fermentation.tool.string.3").withStyle(ChatFormatting.RED));
        }
    }

    @Override
    public Item upLevelItem() {
        return GodFermentation.asItem();
    }
}



