package com.ytgld.moonstone.item.ms.ectoplasm;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class EctoplasmApple extends Ectoplasm {

    public EctoplasmApple(Properties properties) {
        super(properties);
    }
    public static void hurt(LivingDamageEvent.Post event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.ectoplasmapple.get())) {
                Entity source = event.getSource().getEntity();
                if (source instanceof LivingEntity) {
                    Vec3 playerPos = player.position();
                    int range = 8;
                    List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(
                            playerPos.x - range,
                            playerPos.y - range,
                            playerPos.z - range,
                            playerPos.x + range,
                            playerPos.y + range,
                            playerPos.z + range));
                    for (LivingEntity livingEntity : entities) {
                        if (!livingEntity.is(player)) {
                            float damage = livingEntity.getMaxHealth() / 50;
                            if (damage > player.getMaxHealth() * 10) {
                                damage = player.getMaxHealth() * 10;
                            }
                            livingEntity.hurt(livingEntity.damageSources().magic(),
                                    damage);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.ectoplasmapple.tool.string").withStyle(ChatFormatting.GOLD));
    }
}



