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

public class blueamout extends CommonItem {
    public blueamout(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    private void aaa(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, this)) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                }
            }
        }

    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.blueamout.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.blueamout.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
