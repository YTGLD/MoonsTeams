package com.ytgld.moonstone.event.itemevent;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.CellGiant;
import com.ytgld.moonstone.enttiy.CellZombie;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.ExtendZombieEntity;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.List;

import static com.ytgld.moonstone.event.AllEvent.*;

public class ZombieEventHandler {
    public static void useKey(Player player){
        if (Handler.hascurio(player, Items.cell.asItem())) {
            if (!player.getCooldowns().isOnCooldown(Items.cell.asItem())) {
                for (int i = 0; i < 8; i++) {
                    CellZombie cell_zombie = new CellZombie(EntityTs.cell_zombie.get(), player.level());
                    cell_zombie.setOwnerUUID(player.getUUID());
                    addSuperZombieTag(player,cell_zombie);
                    cell_zombie.setPos(new Vec3(player.getX() + Math.cos(i) * 3 ,player.getY() + 0.125f,player.getZ() + Math.sin(i) * 3));
                    player.level().addFreshEntity(cell_zombie);
                }
                player.level().playSound(null,player.blockPosition(),SoundEvents.SLIME_BLOCK_BREAK,SoundSource.BLOCKS,1,1);
                player.setData(AttReg.cooldownZombie,20);
                player.getCooldowns().addCooldown(Items.cell.asItem(),600);
            }else if (player.getData(AttReg.cooldownZombie.get()) <= 0){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 10;
                List<CellZombie> entities = player.level().getEntitiesOfClass(CellZombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (CellZombie zombie : entities) {
                    if (zombie.getOwner() instanceof Player player1 && player1.is(player)) {
                        player.level().levelEvent(2004, zombie.blockPosition(), Block.getId(Blocks.SLIME_BLOCK.defaultBlockState()));
                        if (zombie.isAlive()) {
                            player.level().playSound(null, player.blockPosition(), SoundEvents.ZOMBIE_DEATH, SoundSource.BLOCKS, 1, 1);
                        }
                        zombie.discard();
                    }
                }
            }
        }
        if (Handler.hascurio(player, Items.giant.asItem())) {
            if (!player.getCooldowns().isOnCooldown(Items.giant.asItem())) {
                for (int i = 0; i < 3; i++) {
                    CellGiant cellGiant = new CellGiant(EntityTs.cell_giant.get(), player.level());
                    cellGiant.setOwnerUUID(player.getUUID());
                    cellGiant.setPos(new Vec3(player.getX() + Math.cos(i) * 1.5f ,player.getY() + 0.125f,player.getZ() + Math.sin(i) * 1.5f));
                    cellGiant.setPose(Pose.EMERGING);
                    player.level().addFreshEntity(cellGiant);
                }
                player.level().playSound(null,player.blockPosition(),SoundEvents.SLIME_BLOCK_BREAK,SoundSource.BLOCKS,1,1);
                player.level().playSound(null,player.blockPosition(),SoundEvents.WARDEN_EMERGE,SoundSource.BLOCKS,1,1);
                player.getCooldowns().addCooldown(Items.giant.asItem(),600);
                player.setData(AttReg.cooldownZombie,20);
            }else if (player.getData(AttReg.cooldownZombie.get()) <= 0){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 10;
                List<CellGiant> entities = player.level().getEntitiesOfClass(CellGiant.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (CellGiant zombie : entities) {
                    if (zombie.getOwner() instanceof Player player1 && player1.is(player)) {
                        player.level().levelEvent(2004, zombie.blockPosition(), Block.getId(Blocks.SLIME_BLOCK.defaultBlockState()));
                        if (zombie.isAlive()) {
                            player.level().playSound(null, player.blockPosition(), SoundEvents.ZOMBIE_DEATH, SoundSource.BLOCKS, 1, 1);
                        }
                        zombie.discard();
                    }
                }
            }
        }
    }
    public static void downCooldown(Player player){
        if (player.getData(AttReg.cooldownZombie.get()) > 0){
            player.setData(AttReg.cooldownZombie,player.getData(AttReg.cooldownZombie.get()) - 1);
        }
    }
    public static void theCellZombieGiant(LivingDeathEvent event) {
        theCellZombie(event);
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player, Items.cell_boom.get())) {
                player.level().explode(null, player.getX(), player.getY(), player.getZ(), 5.5f, true, Level.ExplosionInteraction.MOB);
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.giant.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.giant.get())) {
                    if (player.level() instanceof ServerLevel) {
                        if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                            if (Handler.hascurio(player, Items.mother_cell.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                                    Handler.trySpawnMob(player, EntityTs.cell_giant.get(), event.getEntity().position());
                                }
                                for (int i = 0; i < 2; i++) {
                                    CellZombie cell_zombie = new CellZombie(EntityTs.cell_zombie.get(), player.level());
                                    cell_zombie.setOwnerUUID(player.getUUID());
                                    cell_zombie.setPos(player.position());
                                    player.level().addFreshEntity(cell_zombie);
                                }
                            }
                            Handler.trySpawnMob(player, EntityTs.cell_giant.get(), event.getEntity().position());
                            player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                            player.getCooldowns().addCooldown(Items.giant.get(), 600);
                        }
                    }
                }
            }
        }
    }

    public static void theCellZombie(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.cell.get())) {
                if (player.getCooldowns().isOnCooldown(Items.cell.get())) {
                    return;
                }
                if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                    CellZombie z = new CellZombie(EntityTs.cell_zombie.get(), player.level());
                    z.teleportTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                    z.setOwnerUUID(player.getUUID());
                    addSuperZombieTag(player,z);
                    player.level().addFreshEntity(z);
                    player.getCooldowns().addCooldown(Items.cell.get(), 100);
                }
            }
        }
    }
    private static void addSuperZombieTag(Player player, ExtendZombieEntity zombie){
        if (Handler.hascurio(player, Items.adrenaline.get())) {
            zombie.addTag(DamageCell);
        }
        if (Handler.hascurio(player, Items.cell_mummy.get())) {
            zombie.addTag(muMMY);
        }
        if (Handler.hascurio(player, Items.cell_boom.get())) {
            zombie.addTag(boom);
        }
        if (Handler.hascurio(player, Items.cell_calcification.get())) {
            zombie.addTag(calcification);
        }
        if (Handler.hascurio(player, Items.cell_blood.get())) {
            zombie.addTag(cb_blood);
        }
    }
}
