package com.ytgld.moonstone.utils.network;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.utils.CIPacketPayload;
import com.ytgld.moonstone.utils.RenderObjectManager;
import com.ytgld.moonstone.utils.dout.WrathHeavenRenderObject;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.joml.Vector3f;

public record WrathHeavenRenderPacket(double x, double y, double z,
                                     float rotationX, float rotationY, float rotationZ,
                                     int color, int size,boolean hasDown)
        implements CIPacketPayload {

    public static final Type<WrathHeavenRenderPacket> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Moonstone.MODID,
                    "wrath_heaven_render"
            ));

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            WrathHeavenRenderPacket
            > STREAM_CODEC = StreamCodec.composite(

            ByteBufCodecs.DOUBLE,
            WrathHeavenRenderPacket::x,

            ByteBufCodecs.DOUBLE,
            WrathHeavenRenderPacket::y,

            ByteBufCodecs.DOUBLE,
            WrathHeavenRenderPacket::z,

            ByteBufCodecs.FLOAT,
            WrathHeavenRenderPacket::rotationX,

            ByteBufCodecs.FLOAT,
            WrathHeavenRenderPacket::rotationY,

            ByteBufCodecs.FLOAT,
            WrathHeavenRenderPacket::rotationZ,

            ByteBufCodecs.INT,
            WrathHeavenRenderPacket::color,

            ByteBufCodecs.INT,
            WrathHeavenRenderPacket::size,

            ByteBufCodecs.BOOL,
            WrathHeavenRenderPacket::hasDown,

            WrathHeavenRenderPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public Vec3 position() {
        return new Vec3(x, y, z);
    }
    public static void handle(
            WrathHeavenRenderPacket packet,
            IPayloadContext context
    ) {
        context.enqueueWork(() -> {
            WrathHeavenRenderObject sword = new WrathHeavenRenderObject(
                    packet.position(),
                    packet.color,
                    packet.size(),
                    packet.hasDown);

            sword.vector3f = new Vector3f(0, 0, 0);
            sword.color = packet.color();
            RenderObjectManager.add(sword);
        });
    }

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                WrathHeavenRenderPacket.TYPE,
                WrathHeavenRenderPacket.STREAM_CODEC,
                WrathHeavenRenderPacket::handle
        );
    }
}