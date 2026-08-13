package com.ytgld.moonstone.item.si.nightmare.fool;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.effect.Effects;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class Apple extends NightmareSmall {
    public Apple(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;
        public static ModConfigSpec.DoubleValue intValue3;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("com.ytgld.moonstone.config.Apple")
                    .defineInRange("Apple", 30f, 0, 1000);
            intValue2 = builder.translation("com.ytgld.moonstone.config.Apple2")
                    .defineInRange("Apple2", 2f, 0, 1000);
            intValue3 = builder.translation("com.ytgld.moonstone.config.Apple3")
                    .defineInRange("Apple3", 10f, 0, 1000);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("Apple",
                            "锁血果", "生命"),
                    new CIString("Apple2",
                            "锁血果2", "受伤"),
                    new CIString("Apple3",
                            "锁血果3", "伤害")
            );
        }
    }

    public static void damage(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.apple.get())) {
                event.setNewDamage(ConfigItem.intValue3.get().floatValue());
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.apple.get())) {
                event.setNewDamage(ConfigItem.intValue2.get().floatValue());
            }
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(Effects.life_apple, 100, 0, false, false));
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.apple.tool.string", ConfigItem.intValue.get().floatValue()).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.1", ConfigItem.intValue2.get().floatValue()).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.2", ConfigItem.intValue3.get().floatValue()).withStyle(ChatFormatting.DARK_RED));

    }

}
