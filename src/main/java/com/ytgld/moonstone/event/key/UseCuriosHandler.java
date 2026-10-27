package com.ytgld.moonstone.event.key;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.blood.magic.BloodCandle;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import javax.annotation.Nonnull;

public class UseCuriosHandler {
    public static void register(final PayloadRegistrar registrar) {
        registrar.playToServer(UseChestCurios.TYPE, UseChestCurios.STREAM_CODEC,
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

    public static record UseChestCurios() implements CustomPacketPayload {

        public static final Type<UseChestCurios> TYPE =
                new Type<>(Identifier.fromNamespaceAndPath(Moonstone.MODID, "use_curio"));

        public static final StreamCodec<RegistryFriendlyByteBuf, UseChestCurios> STREAM_CODEC =
                StreamCodec.unit(new UseChestCurios());

        @Nonnull
        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
