package com.ytgld.moonstone.item.ms.necora.dnabush.small;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.CellZombie;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

import static com.ytgld.moonstone.event.AllEvent.*;

public class Cell extends TheNecora {
    public Cell(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.cell.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("dnabush"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("dnabush").build())

        ), true);
    }

    public static void evil(LivingDeathEvent event) {
        sumZ(event);
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player, Items.cell_boom.get())) {
                player.level().explode(null, player.getX(), player.getY(), player.getZ(), 5.5f, true, Level.ExplosionInteraction.MOB);
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.necora.get())) {
                if (Handler.hascurio(player, Items.giant.get())) {
                    if (!player.getCooldowns().isOnCooldown(Items.giant.get().getDefaultInstance())) {
                        if (player.level() instanceof ServerLevel p_222881_) {
                            if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                                if (Handler.hascurio(player, Items.mother_cell.get())) {
                                    if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                                        Handler.trySpawnMob(player, EntityTs.cell_giant.get(), event.getEntity().position());
                                    }
                                    for (int i = 0; i < 2; i++) {
                                        CellZombie cell_zombie = new CellZombie(EntityTs.cell_zombie.get(), player.level());
                                        cell_zombie.setOwner(player);
                                        cell_zombie.setPos(player.position());
                                        player.level().addFreshEntity(cell_zombie);
                                    }
                                }
                                Handler.trySpawnMob(player, EntityTs.cell_giant.get(), event.getEntity().position());
                                player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                                player.getCooldowns().addCooldown(Items.giant.get().getDefaultInstance(), 600);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void sumZ(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.cell.get())){
                if (player.getCooldowns().isOnCooldown(Items.cell.get().getDefaultInstance())){
                    return;
                }
                if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                    CellZombie z = new CellZombie(EntityTs.cell_zombie.get(), player.level());
                    z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                    z.setOwner(player);
                    if (Handler.hascurio(player, Items.adrenaline.get())) {
                        z.addTag(DamageCell);
                    }
                    if (Handler.hascurio(player, Items.cell_mummy.get())) {
                        z.addTag(muMMY);
                    }
                    if (Handler.hascurio(player, Items.cell_boom.get())) {
                        z.addTag(boom);
                    }
                    if (Handler.hascurio(player, Items.cell_calcification.get())) {
                        z.addTag(calcification);
                    }
                    if (Handler.hascurio(player, Items.cell_blood.get())) {
                        z.addTag(cb_blood);
                    }
                    player.level().addFreshEntity(z);
                    player.getCooldowns().addCooldown(Items.cell.get().getDefaultInstance(),100);
                }
            }
        }
    }
}
