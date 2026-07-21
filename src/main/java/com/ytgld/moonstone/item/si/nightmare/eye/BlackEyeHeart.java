package com.ytgld.moonstone.item.si.nightmare.eye;

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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;

import java.util.List;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class BlackEyeHeart extends NightmareSmall {
    public BlackEyeHeart(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.BlackEyeHeart")
                    .defineInRange("BlackEyeHeart", 25f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("BlackEyeHeart",
                    "死心", "伤害"));
        }
    }

    public static void heal(LivingHealEvent event) {
        Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
        float range = 8;
        List<Player> entities =
                event.getEntity().level().getEntitiesOfClass(Player.class,
                        new AABB(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
        for (Player player : entities) {
            if (!event.getEntity().is(player) && !(event.getEntity() instanceof Player)) {
                if (SIHandler.hascurio(player, Items.nightmare_base_black_eye_heart.get())) {
                    player.heal(event.getAmount());
                    event.setAmount(0);
                }
            }
        }
    }

    public static void hurt(LivingDamageEvent.Pre event) {
        Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
        float range = 8;
        List<Player> entities =
                event.getEntity().level().getEntitiesOfClass(Player.class,
                        new AABB(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
        for (Player player : entities) {
            if (!event.getEntity().is(player) && !(event.getEntity() instanceof Player)) {
                if (SIHandler.hascurio(player, Items.nightmare_base_black_eye_heart.get())) {
                    float v = ConfigItem.intValue.get().floatValue();
                    v /= 100f;
                    event.setNewDamage(event.getNewDamage() * v);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int v = (int) ConfigItem.intValue.get().floatValue();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_heart.tool.string", v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_heart.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_heart.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
