package com.ytgld.moonstone.item.si.nightmare.reversal;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;

import java.util.List;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class Candle extends NightmareSmall {

    public Candle(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.IntValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.Candle")
                    .defineInRange("Candle", 50, 0, 1000);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("Candle",
                    "古蜡烛", "伤害")
            );
        }
    }

    public static void hurt(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.candle.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.candle.get().getDefaultInstance())) {

                    if (player.getHealth() >= player.getMaxHealth()) {

                        event.setNewDamage(event.getNewDamage() * 11);
                        int s = (int) (event.getNewDamage() / 10);

                        if (event.getNewDamage() > player.getHealth()) {
                            event.setNewDamage(0);
                            player.setHealth(1);
                        }
                        if (s > 5 * 20) {
                            s = 5 * 20;
                        }
                        player.invulnerableTime += s;
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RAVAGER_ROAR, SoundSource.NEUTRAL, 0.5f, 0.5f);

                        player.getCooldowns().addCooldown(Items.candle.get().getDefaultInstance(), 150);
                    }
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.candle.get())) {
                float v = ConfigItem.intValue.getAsInt();
                v /= 100;
                if (player.invulnerableTime > 0) {
                    event.setNewDamage(event.getNewDamage() * v);
                }
            }
        }
    }

    public static void heal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.candle.get())) {
                if (player.invulnerableTime > 0) {
                    event.setAmount(event.getAmount() * 2);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float v = ConfigItem.intValue.getAsInt();
        pTooltipComponents.add(Component.translatable("item.candle.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.5", v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.6").withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
