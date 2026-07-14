package com.ytgld.moonstone.item.ms.nanodoom;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class MagicEye extends Doom implements TextEvt.Twelve {

    public MagicEye(Properties properties) {
        super(properties);
    }

    public static void damage(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.magiceye.get())) {
                Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
                int range = 4;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (!living.is(player)) {
                        if (!living.is(event.getEntity())) {
                            living.hurt(living.damageSources().magic(), event.getNewDamage() / 2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.magiceye.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.magiceye.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
