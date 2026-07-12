package com.ytgld.moonstone.item.si.nightmare.fool;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;


import java.util.List;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class FoolBone extends NightmareSmall {

    public FoolBone(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("chest_item.config.FoolBone")
                    .defineInRange("FoolBone", 30f, 0, 1000);

        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("FoolBone",
                    "危险的头骨", "伤害")
            );
        }
    }

    public static void attLook(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_fool_bone.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_fool_bone.get().getDefaultInstance())) {
                    if (event.getEntity() instanceof Mob mob) {
                        if (mob.getTarget() != null && mob.getTarget().is(player)) {
                            if (Mth.nextInt(RandomSource.create(), 1, 100) <= 30) {
                                mob.invulnerableTime = 0;
                            }
                            event.setNewDamage(event.getNewDamage() * (1 + (ConfigItem.intValue.get().floatValue() / 100f)));
                            player.getCooldowns().addCooldown(Items.nightmare_base_fool_bone.get().getDefaultInstance(), 20);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_bone.tool.string").withStyle(ChatFormatting.DARK_RED));
        float v = ConfigItem.intValue.get().floatValue();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_bone.tool.string.1", v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_bone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}

