package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight;

import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import java.util.List;

/**
 *
 *归隐刀锋之刃
 * <p>
 * <p>
 *  受击时发动猛烈反击
 * <p>
 *  受击后的3秒内攻击必定暴击
 * <p>
 * <p>
 *  造成非暴击伤害时攻速逐渐增加
 * <p>
 *  造成暴击伤害时伤害逐渐增加
 *
 */
public class hidden_blade extends nightmare implements SuperNightmare {

    public static void hurt_cit(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.hidden_blade.get())){
                if (event.getSource().getEntity() instanceof LivingEntity living) {
                    if (SIHandler.hascurio(living,Items.hidden_blade.get())){
                        return;
                    }
                }
                if (!player.getCooldowns().isOnCooldown(Items.hidden_blade.get())) {
                    if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                        livingEntity.hurt(livingEntity.damageSources().magic(), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 3));
                        player.getCooldowns().addCooldown(Items.hidden_blade.get(), 60);
                    }
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.hidden_blade.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.hidden_blade.get())) {
                    if (event.getEntity() instanceof LivingEntity) {
                        if (SIHandler.hascurio(event.getEntity(),Items.hidden_blade.get())){
                            return;
                        }
                    }
                    player.addEffect(new MobEffectInstance(Effects.hidden.get(), 120, 0));
                    MobEffectInstance instance = player.getEffect(Effects.hidden.get());
                    if (instance != null) {
                        if (instance.getAmplifier() < 9) {
                            player.addEffect(new MobEffectInstance(Effects.hidden.get(), 120, instance.getAmplifier() + 1));
                        } else {
                            player.addEffect(new MobEffectInstance(Effects.hidden.get(), 120, 9));
                        }
                    }
                }
            }
        }
    }
    public static void cit(CriticalHitEvent event){
        if (event.getEntity() instanceof Player){
            if (SIHandler.hascurio(event.getEntity(), Items.hidden_blade.get())) {
                if (event.getTarget() instanceof LivingEntity living) {
                    if (SIHandler.hascurio(living,Items.hidden_blade.get())){
                        return;
                    }
                }
                if (event.getEntity().getCooldowns().isOnCooldown(Items.hidden_blade.get())) {

                    event.setDamageModifier(event.getDamageModifier()*2f);

                    event.getEntity().addEffect(new MobEffectInstance(Effects.blade.get(), 120, 0));
                    MobEffectInstance instance = event.getEntity().getEffect(Effects.blade.get());
                    if (instance != null) {
                        if (instance.getAmplifier() < 9) {
                            event.getEntity().addEffect(new MobEffectInstance(Effects.blade.get(), 120, instance.getAmplifier() + 1));
                        } else {
                            event.getEntity().addEffect(new MobEffectInstance(Effects.blade.get(), 120, 9));
                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.hidden_blade.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.hidden_blade.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.hidden_blade.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.hidden_blade.tool.string.4").withStyle(ChatFormatting.DARK_RED));
    }
}
