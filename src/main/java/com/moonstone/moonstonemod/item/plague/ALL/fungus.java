package com.moonstone.moonstonemod.item.plague.ALL;

import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class fungus extends medIC implements ICurioItem {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 10;
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

            for (LivingEntity entity : entities){
                if (!player.level().isClientSide&&player.tickCount% 80 == 0) {
                    fungus_pets(entity, player);
                }
            }


            if (player.level().isDay()) {
                if (player.level().canSeeSkyFromBelowWater(new BlockPos(player.getBlockX(), player.getBlockY() - 1, player.getBlockZ()))) {
                    player.setSecondsOnFire(2);
                }
            }
            if (player.hasEffect(MobEffects.POISON)
            ||player.hasEffect(MobEffects.WITHER)
            ||player.hasEffect(MobEffects.WEAKNESS)
            ||player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                player.removeEffect(MobEffects.POISON);
                player.removeEffect(MobEffects.WEAKNESS);
                player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                player.removeEffect(MobEffects.WITHER);
            }
            BlockState state = player.level().getBlockState(new BlockPos(player.getBlockX(), player.getBlockY() - 1, player.getBlockZ()));

            if (state.is(Blocks.GRASS_BLOCK)) {
                player.level().setBlock(new BlockPos(player.getBlockX(), player.getBlockY() - 1, player.getBlockZ()), Blocks.MYCELIUM.defaultBlockState(), 3);
            }
            if (state.is(Blocks.MYCELIUM)) {
                if (!player.level().isClientSide && player.tickCount % 200 == 0) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 220, 1));
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.fungus.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.fungus.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.fungus.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.fungus.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.fungus.tool.string.4").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.fungus.tool.string.5").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.fungus.tool.string.6").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.fungus.tool.string.7").withStyle(ChatFormatting.RED));
             } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("Heal: "+(AllEvent.aFloat*100) +"%").withStyle(ChatFormatting.DARK_RED));

    }
    public void fungus_pets(LivingEntity living,Player player){
        if (!living.is(player)) {
            if (living.isAlliedTo(player)) {
                living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
            }
        }
        if (living instanceof OwnableEntity ownableEntity){
            if (ownableEntity.getOwner()!= null&&ownableEntity.getOwner().is(player)){
                living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
            }
        }
    }
}

