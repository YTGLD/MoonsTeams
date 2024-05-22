package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import com.moonstone.moonstonemod.moonstoneitem.INanoBattery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class beacon extends INanoBattery {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext,stack);
        if (slotContext.entity() instanceof Player  player) {

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack b = stackHandler.getStackInSlot(i);


                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        int range = 12;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                        Collection<MobEffectInstance> s = player.getActiveEffects();
                        for (MobEffectInstance mobEffectInstance : s) {
                            MobEffect effect = mobEffectInstance.getEffect();
                            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                                for (LivingEntity living : entities) {
                                    living.addEffect(new MobEffectInstance(effect, mobEffectInstance.getDuration(), mobEffectInstance.getAmplifier()));
                                    player.removeAllEffects();
                                    player.getCooldowns().addCooldown(stack.getItem(), t);
                                }
                            }
                        }
                    }
                }

            });
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.beacon.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.beacon.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.nanocube.tool.string.1").withStyle(ChatFormatting.GOLD));

    }

}