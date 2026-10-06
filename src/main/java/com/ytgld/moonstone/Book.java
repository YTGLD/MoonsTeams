package com.ytgld.moonstone;

import com.ytgld.moonstone.render.book.MoonBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class Book extends Item {
    public Book(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new OpenBookPayload());
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    public record OpenBookPayload() implements CustomPacketPayload {
        public static final Type<OpenBookPayload> TYPE =
                new Type<>(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "open_book"));

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }

        public static final StreamCodec<RegistryFriendlyByteBuf, OpenBookPayload> STREAM_CODEC =
                StreamCodec.unit(new OpenBookPayload());
    }
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                OpenBookPayload.TYPE,
                OpenBookPayload.STREAM_CODEC,
                OpenBookHandler::handle
        );
    }
    public static class OpenBookHandler {
        public static void handle(OpenBookPayload payload, IPayloadContext context) {
            context.enqueueWork(() -> ClientOnly.openBook(context.player()));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class ClientOnly {
        public static void openBook(Player player) {
            Minecraft.getInstance().setScreen(new MoonBookScreen(player));
        }
    }
}
