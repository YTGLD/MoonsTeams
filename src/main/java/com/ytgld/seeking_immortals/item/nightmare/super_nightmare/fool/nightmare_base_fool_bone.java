package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool;

import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class nightmare_base_fool_bone extends nightmare implements SuperNightmare {

    public static void attLook(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_fool_bone.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_fool_bone.get())) {
                    if (event.getEntity() instanceof Mob mob) {
                        if (mob.getTarget() != null && mob.getTarget().is(player)) {
                            if (Mth.nextInt(RandomSource.create(), 1, 100) <= 30) {
                                mob.invulnerableTime = 0;
                            }
                            event.setAmount(event.getAmount() * (Config.SERVER.nightmare_base_fool_bone.get().floatValue()));
                            player.getCooldowns().addCooldown(Items.nightmare_base_fool_bone.get(),20);
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
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_bone.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_bone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}

