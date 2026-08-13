package com.ytgld.moonstone.item.ms.necora.medicine.med;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class Reanimation extends TheNecora {

    public Reanimation(Properties properties) {
        super(properties);
    }

    public static   void reanimation(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.reanimation.get())){
                if (!player.getCooldowns().isOnCooldown(Items.reanimation.get())) {
                    if (event.getNewDamage() > player.getHealth()) {
                        player.heal(player.getMaxHealth() / 2);
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 0.8F, 0.8F);

                        player.getCooldowns().addCooldown(Items.reanimation.get(), 3000);
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.reanimation.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.reanimation.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.reanimation.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.reanimation.tool.string.3").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }

        tooltip.add(Component.translatable("item.reanimation.tool.string.4").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        tooltip.add(Component.literal(""));
    }

}

