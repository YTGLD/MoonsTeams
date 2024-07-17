package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.bule_bolt;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import com.moonstone.moonstonemod.moonstoneitem.INanoBattery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nano_box extends INanoBattery {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext,stack);
        this.setT(20,slotContext.entity() ,stack);
        if (slotContext.entity() instanceof Player player){
            if (player.isShiftKeyDown()) {
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 8;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                for (LivingEntity living : entities) {
                    if (!living.is(player)&& !(living instanceof bule_bolt)) {
                        if (!player.getCooldowns().isOnCooldown(this)) {
                            bule_bolt bule_bolt = new bule_bolt(EntityTs.bule_bolt.get(),living.level());
                            bule_bolt.teleportTo(living.getX(),living.getY(),living.getZ());
                            living.hurt(living.damageSources().playerAttack(player),living.getMaxHealth() / 20);
                            living.level().addFreshEntity(bule_bolt);

                            player.getCooldowns().addCooldown(this, getT());
                        }
                    }
                }
            }
        }
    }
    public static class model_box_nano extends CommonItem {

    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.nano_box.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.nano_box.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
