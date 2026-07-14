package com.ytgld.moonstone.event.key;

import com.ytgld.moonstone.item.ms.blood.magic.BloodCandle;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class UseCuriosHandler {
    public static void register(final PayloadRegistrar registrar) {
        registrar.playToServer(UseChestCurios.TYPE, UseChestCurios.CHEST_CURIOS_STREAM_CODEC,
                handlerUse::handleOpenCurios);

    }
    public static HandlerUse handlerUse = new HandlerUse();
    public static class HandlerUse {
        public void handleOpenCurios(final UseChestCurios data, final IPayloadContext ctx) {
            ctx.enqueueWork(() -> {
                Player player = ctx.player();
                BloodCandle.event(player);
            });
        }
    }
}
