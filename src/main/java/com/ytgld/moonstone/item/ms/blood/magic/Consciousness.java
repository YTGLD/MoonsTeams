package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.AttackBlood;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import javax.annotation.Nonnull;
import java.util.List;

public class Consciousness extends BloodItem {
    public Consciousness(Properties properties) {
        super(properties);
    }
    @Override
    public void appendHoverText(ItemStack p_41421_,  Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        pTooltipComponents.add(Component.translatable("item.consciousness.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.consciousness.tool.string.2").withStyle(ChatFormatting.RED));
    }
    public static void emp(PlayerInteractEvent.LeftClickEmpty event){
        if (event.getEntity() instanceof Player player && Handler.hascurio(player,Items.consciousness.asItem())) {
            PacketDistributor.sendToServer(new UseOppression());
        }
    }

    public static void cooldown(Player player){
        if (Handler.hascurio(player, Items.consciousness.asItem())) {
            if (!player.getCooldowns().isOnCooldown(Items.consciousness.asItem())) {
                Vec3 playerPos = player.position();
                int range = 5;
                Vec3 min = playerPos.subtract(range, range, range);
                Vec3 max = playerPos.add(range, range, range);
                AABB box = new AABB(min, max);
                List<OwnerBlood> players = player.level().getEntitiesOfClass(OwnerBlood.class, box);
                players.forEach(ownerBlood -> {
                    for (int i = 0; i < 8; i++) {
                        AttackBlood spirit = new AttackBlood(EntityTs.attack_blood_.get(), player.level());

                        OwnerBlood.addSuperAttackBlood(spirit, player);

                        spirit.setPos(ownerBlood.getX(), ownerBlood.getEyeY(), ownerBlood.getZ());
                        spirit.setOwner(player);
                        Vec3 lookVec = player.getLookAngle();
                        double spread = 0.1;
                        double offsetX = (Math.random() - 0.5) * spread;
                        double offsetY = (Math.random() - 0.5) * spread;
                        double offsetZ = (Math.random() - 0.5) * spread;

                        Vec3 velocity = lookVec.add(offsetX, offsetY, offsetZ).normalize().scale(2.5);
                        spirit.setDeltaMovement(velocity);
                        player.level().addFreshEntity(spirit);
                        player.getCooldowns().addCooldown(Items.consciousness.asItem(),30);
                    }
                });
            }
        }
    }
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                UseOppression.TYPE,
                UseOppression.STREAM_CODEC,
                UseOppressionHandler::handle
        );
    }
    public static class UseOppressionHandler {
        public static void handle(UseOppression payload, IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                Consciousness.cooldown(player);
            });
        }
    }
    public record UseOppression() implements CustomPacketPayload {
        public static final Type<UseOppression> TYPE =
                new Type<>(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "consciousness"));

        public static final StreamCodec<RegistryFriendlyByteBuf, UseOppression> STREAM_CODEC =
                StreamCodec.unit(new UseOppression());

        @Nonnull
        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
