package com.ytgld.moonstone.utils;

import com.ytgld.moonstone.utils.network.WrathHeavenRenderPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class AddUtil {
    public static void addWrathHeaven(LivingEntity living, Vec3 pos, int color, int size, boolean a) {
        WrathHeavenRenderPacket packet = new WrathHeavenRenderPacket(
                pos.x, pos.y, pos.z,
                0,0,0,
                color, size,a
        );
        sendToNearbyPlayers(living, packet, 64.0);
    }
    private static void sendToNearbyPlayers(LivingEntity entity, CIPacketPayload packet,double range ){
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        serverLevel.getServer().getPlayerList().broadcast(null,
                        entity.getX(),entity.getY(), entity.getZ(),
                        range, serverLevel.dimension(), packet
                );
    }

}
