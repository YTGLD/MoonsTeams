package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.entity.flysword;
import com.moonstone.moonstonemod.entity.suddenrain;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.item.buyme.wind_and_rain;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SwordEvent {
    @SubscribeEvent
    public void SwordEventLivingEntityUseItemEvent(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.getTag() !=null){
            if (stack.getTag().getBoolean(wind_and_rain.wind)){
                event.getToolTip().add(Component.translatable("item.moonstone.wind_and_rain").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
            }
        }
    }

    @SubscribeEvent
    public void SwordEventLivingEntityUseItemEvent(LivingEntityUseItemEvent.Tick event){
        if (event.getEntity() instanceof Player player){
            ItemStack stack = event.getItem();
            if (stack.getTag() !=null) {
                if (stack.getTag().getBoolean(wind_and_rain.wind)) {
                    float s = (float) Math.sin(player.tickCount);
                    if (s <= 0) {
                        s = 0.125f;
                    }

                    int b = Mth.nextInt(RandomSource.create(), 1, 50);
                    if (player.tickCount % 5 == 0) {
                        if (b == 1) {
                            player.getCooldowns().addCooldown(stack.getItem(), 160);
                        }


                        suddenrain item = new suddenrain(EntityTs.suddenrain.get(), player.level());
                        item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                        item.setDeltaMovement(0, s / 1.5f, 0);
                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            player.level().addFreshEntity(item);
                            stack.hurtAndBreak(3, player, (pl) -> pl.broadcastBreakEvent(event.getEntity().getUsedItemHand()));
                        }

                    } else if (player.tickCount % 5 == 1) {
                        if (b == 1) {
                            player.getCooldowns().addCooldown(stack.getItem(), 160);
                        }
                        flysword item = new flysword(EntityTs.flysword.get(), player.level());
                        item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                        item.setDeltaMovement(0, s / 1.5f, 0);
                        item.addTag(AllEvent.FlySword);
                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            player.level().addFreshEntity(item);
                            stack.hurtAndBreak(3, player, (pl) -> pl.broadcastBreakEvent(event.getEntity().getUsedItemHand()));
                        }
                    }

                }

            }
        }
    }
}