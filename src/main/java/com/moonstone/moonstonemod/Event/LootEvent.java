package com.moonstone.moonstonemod.Event;

import com.moonstone.moonstonemod.Init.Items;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootEvent {
    @SubscribeEvent
    public void LivingKnockBackEvent(LivingDropsEvent event) {
        int s = Mth.nextInt(RandomSource.create(), 1, 100);
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
        }

    }
}
