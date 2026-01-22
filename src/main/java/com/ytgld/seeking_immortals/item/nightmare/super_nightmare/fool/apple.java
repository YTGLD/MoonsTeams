package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool;

import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class apple extends nightmare implements SuperNightmare {
    public static void damage(LivingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.apple.get())) {
                event.setAmount(Config.SERVER.apple_damage.get());
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.apple.get())) {
                event.setAmount(Config.SERVER.apple_hurt.get());
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(Effects.life_apple.get(),100,0,false,false));
    }

    @Override
    public void appendHoverText(ItemStack stack,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.apple.tool.string",Config.SERVER.apple_health.get()).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.1",Config.SERVER.apple_hurt.get()).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.2",Config.SERVER.apple_damage.get()).withStyle(ChatFormatting.DARK_RED));

    }

}
