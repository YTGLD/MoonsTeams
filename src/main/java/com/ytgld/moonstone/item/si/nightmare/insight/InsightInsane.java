package com.ytgld.moonstone.item.si.nightmare.insight;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.List;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class InsightInsane extends NightmareSmall {
    public InsightInsane(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.InsightInsane")
                    .defineInRange("InsightInsane", 50f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("InsightInsane",
                    "战神石", "伤害"));
        }
    }

    public static void LivingDeathEvents(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_insight_insane.get())) {
                player.heal(event.getEntity().getMaxHealth() / 10);
                player.getCooldowns().addCooldown(Items.nightmare_base_insight_insane.get(), 200);
            }
        }
    }

    public static void damage(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_insight_insane.get())) {
                if (player.getCooldowns().isOnCooldown(Items.nightmare_base_insight_insane.get())) {
                    event.setNewDamage(event.getNewDamage() * (1 + (ConfigItem.intValue.get().floatValue() / 100f)));
                    player.getCooldowns().addCooldown(Items.nightmare_base_insight_insane.get(), 0);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        int v = (int) ConfigItem.intValue.get().floatValue();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string.2", v).withStyle(ChatFormatting.DARK_RED));
    }
}



