package com.moonstone.moonstonemod.Item.Common;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class redamout extends CommonItem {
    public redamout(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    private void aaa(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                    event.getEntity().knockback(2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));

                }
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.redamout.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.redamout.tool.string.1").withStyle(ChatFormatting.GOLD));
    }
}
