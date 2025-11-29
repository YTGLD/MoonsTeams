package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class big_heart extends TheNecoraIC {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.big_heart.tool.string").withStyle(ChatFormatting.GOLD));
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().tickCount % 30 ==1) {
            slotContext.entity().addEffect(new MobEffectInstance(Effects.debilitating.get(), 2000, 0,false,false));
        }
    }
}
