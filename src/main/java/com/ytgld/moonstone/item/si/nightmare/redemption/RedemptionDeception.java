package com.ytgld.moonstone.item.si.nightmare.redemption;


import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class RedemptionDeception extends NightmareSmall {


    public RedemptionDeception(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("com.ytgld.moonstone.config.RedemptionDeception")
                    .defineInRange("RedemptionDeception", 1f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("RedemptionDeception",
                    "欺骗", "生命值"));
        }
    }

    public static void LivingHurtEvent(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_redemption_deception.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_redemption_deception.get())) {
                    if (event.getNewDamage() > player.getHealth()) {

                        player.heal(player.getMaxHealth() * (ConfigItem.intValue.get().floatValue() / 100f));


                        player.getCooldowns().addCooldown(Items.nightmare_base_redemption_deception.get(), 1200);
                        player.invulnerableTime += 100;
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        float range = 10;
                        List<LivingEntity> entities =
                                player.level().getEntitiesOfClass(LivingEntity.class,
                                        new AABB(playerPos.x - range,
                                                playerPos.y - range,
                                                playerPos.z - range,
                                                playerPos.x + range,
                                                playerPos.y + range,
                                                playerPos.z + range));

                        for (LivingEntity living : entities) {
                            if (living instanceof Mob targeting) {
                                if (targeting.getTarget() != null && targeting.getTarget().is(player)) {
                                    targeting.setTarget(null);
                                }
                            }
                        }
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
                        event.setNewDamage(0);
                    }
                }
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption_deception.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption_deception.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}
