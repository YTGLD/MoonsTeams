package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool;

import com.ytgld.seeking_immortals.Handler;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class apple extends nightmare implements SuperNightmare {
    public static void damage(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.apple.get())) {
                event.setAmount(10);
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.apple.get())) {
                event.setAmount(2);
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(Effects.life.get(),100,0,false,false));
    }

    @Override
    public void appendHoverText(ItemStack stack,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.apple.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

}
