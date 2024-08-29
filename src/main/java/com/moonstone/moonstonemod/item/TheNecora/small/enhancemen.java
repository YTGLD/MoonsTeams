package com.moonstone.moonstonemod.item.TheNecora.small;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.necora.cell_slime;
import com.moonstone.moonstonemod.entity.necora.cell_zombie;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

import static com.moonstone.moonstonemod.event.AllEvent.*;

public class enhancemen extends TheNecoraIC {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "ncrdna", uuid, 1, AttributeModifier.Operation.ADDITION);

        CuriosApi
                .addSlotModifier(multimap, "dna", uuid, 1, AttributeModifier.Operation.ADDITION);
        return multimap;
    }
    public static void Death(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            if (Handler.hascurio(player, Items.giant.get())) {
                if (!Handler.hascurio(player, Items.giant_nightmare.get())) {
                    if (player.level() instanceof ServerLevel p_222881_) {
                        if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                            if (Handler.hascurio(player, Items.mother_cell.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                                    Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
                                }
                                for (int i = 0; i < 2; i++) {
                                    cell_zombie cell_zombie = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                                    cell_zombie.setOwnerUUID(player.getUUID());
                                    cell_zombie.setPos(player.position());
                                    player.level().addFreshEntity(cell_zombie);
                                }
                            }
                            Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
                            player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                            if (Handler.hascurio(player, Items.slime.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 30) <= 33) {

                                    cell_slime z = new cell_slime(EntityTs.cell_slime.get(), player.level());
                                    z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                                    z.setOwnerUUID(player.getUUID());
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

                                }
                            }
                        }
                    }
                } else {
                    if (player.level() instanceof ServerLevel p_222881_) {
                        if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {

                            Handler.trySpawnMob(player, EntityTs.nightmare_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);

                            if (!Handler.hascurio(player, Items.subspace_cell.get())) {
                                player.hurt(player.damageSources().dryOut(), player.getHealth() / 2);
                            }
                            player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                            if (Handler.hascurio(player, Items.slime.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 30) <= 33) {

                                    cell_slime z = new cell_slime(EntityTs.cell_slime.get(), player.level());
                                    z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                                    z.setOwnerUUID(player.getUUID());
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
                                }
                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.cell.get())) {
                if (Handler.hascurio(player, Items.slime.get())) {
                    if (Mth.nextInt(RandomSource.create(), 1, 100) <= 10) {

                        cell_slime z = new cell_slime(EntityTs.cell_slime.get(), player.level());
                        z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                        z.setOwnerUUID(player.getUUID());
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
                    }

                }
                cell_zombie z = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                z.setOwnerUUID(player.getUUID());
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
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.enhancemen.tool.string").withStyle(ChatFormatting.RED));
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.moonstone.small.all").withStyle(ChatFormatting.GOLD));

    }
}

