package com.moonstone.moonstonemod.Item.NaNoDoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class thedoomstone extends Doom {


    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }




    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.thedoomstone.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.thedoomstone.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.thedoomstone.tool.string.2").withStyle(ChatFormatting.GOLD));

    }
}
