package com.ytgld.moonstone.item.si.nightmare.eye;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Optional;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class BlackEyeEye extends NightmareSmall {

    public BlackEyeEye(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.BlackEyeEye")
                    .defineInRange("BlackEyeEye", 50f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("BlackEyeEye",
                    "惶恐肉瘤", "伤害"));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_eye.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_eye.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        float s = (float) ConfigItem.intValue.getAsDouble();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_eye.tool.string.2", s).withStyle(ChatFormatting.DARK_RED));
    }

    public static void attLook(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_black_eye_eye.get())) {
                Entity entity = getPlayerLookTarget(player.level(), player);
                if (entity instanceof LivingEntity living0) {
                    if (living0.is(event.getEntity())) {
                        float s = (float) ConfigItem.intValue.getAsDouble();
                        s /= 100f;
                        event.setNewDamage(event.getNewDamage() * (1 + s));
                    }
                }
            }
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        Entity entity = getPlayerLookTarget(slotContext.entity().level(), slotContext.entity());
        if (entity instanceof LivingEntity living0) {
            if (living0.level() instanceof ServerLevel) {
                if (!(living0 instanceof Player)) {
                    living0.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 3));
                    living0.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 2));
                    living0.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 10));
                    living0.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 3));
                    living0.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 30, 3));
                }
            }
        }
    }

    public static Entity getPlayerLookTarget(Level level, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = living.getEyePosition();
        Vec3 lookVec = living.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {

            if (possibleEntity.isPickable()) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }
}
