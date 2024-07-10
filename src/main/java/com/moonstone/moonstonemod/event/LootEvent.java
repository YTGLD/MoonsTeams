package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootEvent {
    @SubscribeEvent
    public void max_charm(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.redamout.get())
                    &&Handler.hascurio(player, Items.blueamout.get())
                    &&Handler.hascurio(player, Items.greedamout.get()))
            {
                if (event.getEntity() instanceof ElderGuardian elderGuardian){
                    event.getDrops().add(new ItemEntity(elderGuardian.level(), elderGuardian.getX(), elderGuardian.getY() ,elderGuardian.getZ(),
                            new ItemStack(Items.maxamout.get())));
                }
            }
        }
    }
    @SubscribeEvent
    public void NeCharm(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (!Handler.hascurio(player, Items.nightmareanchor.get())) {
                    if (event.getEntity() instanceof Warden elderGuardian) {
                        event.getDrops().add(new ItemEntity(
                                elderGuardian.level(),
                                elderGuardian.getX(),
                                elderGuardian.getY(),
                                elderGuardian.getZ(),
                                new ItemStack(Items.nightmareanchor.get())));
                    }
                }
                if (!Handler.hascurio(player, Items.nightmarecharm.get())) {
                    if (event.getEntity() instanceof ElderGuardian elderGuardian) {
                        event.getDrops().add(new ItemEntity(
                                        elderGuardian.level(),
                                        elderGuardian.getX(),
                                        elderGuardian.getY(),
                                        elderGuardian.getZ(),
                                        new ItemStack(Items.nightmarecharm.get())));
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void zom(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            int a = Mth.nextInt(RandomSource.create(), 1, 10);
            int ng = Mth.nextInt(RandomSource.create(), 1, 5);

            if (Handler.hascurio(player, Items.necora.get())) {
                if (Handler.hascurio(player, Items.nightmareeye.get())){
                    if (Handler.hascurio(player, Items.giant.get())){
                        if (!Handler.hascurio(player, Items.giant_nightmare.get())){
                            if (event.getEntity() instanceof Warden elderGuardian) {
                                if (ng == 1) {
                                    event.getDrops().add(new ItemEntity(elderGuardian.level(), elderGuardian.getX(), elderGuardian.getY(), elderGuardian.getZ(), new ItemStack(Items.giant_nightmare.get())));
                                }
                            }
                        }
                    }
                }


                if (!Handler.hascurio(player, Items.giant.get())) {
                    if (event.getEntity() instanceof Warden elderGuardian) {
                        if (a == 1) {
                            event.getDrops().add(new ItemEntity(elderGuardian.level(), elderGuardian.getX(), elderGuardian.getY(), elderGuardian.getZ(), new ItemStack(Items.giant.get())));
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void LivingKnockBackEvent(LivingDropsEvent event) {
        int a = Mth.nextInt(RandomSource.create(), 1, 20);
        if (event.getEntity() instanceof Zombie  ||
                event.getEntity() instanceof Spider  ||
                event.getEntity() instanceof Creeper  ||
                event.getEntity() instanceof WitherSkeleton  ||
                event.getEntity() instanceof Skeleton )
        {
            if (a == 1){
                event.getDrops().add(new ItemEntity(event.getEntity().level(),event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ(),
                        new ItemStack(Items.ectoplasmball.get())));
            }
            if (a== 2){
                event.getDrops().add(new ItemEntity(event.getEntity().level(),event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ(),
                        new ItemStack(Items.ectoplasmcloub.get())));
            }
        }

        if (event.getEntity() instanceof WitherBoss  ||
                event.getEntity() instanceof EnderDragon)
        {
            event.getDrops().add(new ItemEntity(event.getEntity().level(),event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ(),
                    new ItemStack(Items.ectoplasmcube.get())));
        }


        int s = Mth.nextInt(RandomSource.create(), 1, 150);
        if (event.getEntity() instanceof Zombie zombie){
            if (s == 1){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.mblock.get())));
            }
            if (s == 2){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.mbottle.get())));
            }
            if (s == 3){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.meye.get())));
            }
            if (s == 4){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.mkidney.get())));
            }
            if (s == 5){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.morb.get())));
            }
            if (s == 6){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.mring.get())));
            }
            if (s == 7){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.mshell.get())));
            }
            if (s == 8){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.mhead.get())));
            }
            if (s == 9){
                event.getDrops().add(new ItemEntity(zombie.level(),zombie.getX(),zombie.getY(),zombie.getZ(),
                        new ItemStack(Items.brain.get())));
            }
        }

    }
}
