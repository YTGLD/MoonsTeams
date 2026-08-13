package com.ytgld.moonstone.item.si.nightmare.other;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

/** 抵御符石
 * <P>
 * <P>
 * 受击时有概率获得抗性II
 * <P>
 * 攻击时有概率对目标造成虚弱II
 * <P>
 */
public class DefendAgainstRunestone extends NightmareSmall {


    public DefendAgainstRunestone(Properties properties) {
        super(properties);
    }

    public static void hurt (LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(Items.defend_against_runestone.get())) {
                if (SIHandler.hascurio(player, Items.defend_against_runestone.get())) {
                    if (Mth.nextInt(RandomSource.create(), 0, 100) <= 25) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                        player.getCooldowns().addCooldown(Items.defend_against_runestone.get(),30);
                    }
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getEntity() instanceof LivingEntity) {
                if (!player.getCooldowns().isOnCooldown(Items.defend_against_runestone.get())) {

                    if (SIHandler.hascurio(player, Items.defend_against_runestone.get())) {
                        if (Mth.nextInt(RandomSource.create(), 0, 100) <= 25) {
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                            player.getCooldowns().addCooldown(Items.defend_against_runestone.get(),30);
                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.defend_against_runestone.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.defend_against_runestone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
