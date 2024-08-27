package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.necora.small_zombie;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.giant_boom_cell;
import com.moonstone.moonstonemod.item.amout.twistedamout;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import com.moonstone.moonstonemod.item.pain.pain_candle;
import com.moonstone.moonstonemod.item.pain.pain_ring;
import com.moonstone.moonstonemod.item.pain.the_pain_stone;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import oshi.driver.mac.net.NetStat;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NewEvent {
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        pain_candle.Heal(event);
        nightmare_head.LivingHealEvent(event);
        pain_ring.Heal(event);


    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        pain_candle.Hurt(event);
        giant_boom_cell.Boom(event);
        bat_cell.Bat(event);
        pain_ring.Hurt(event);
        the_pain_stone.PainStoneAttack(event);
        twistedamout.hurt(event);


        if (Handler.hascurio(event.getEntity(),Items.acid.get())){
            event.setAmount(event.getAmount()*50);
        }
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        the_pain_stone.PainStone(event);
        twistedamout.die(event);
    }
    @SubscribeEvent
    public void PlayerInteractEvent(PlayerInteractEvent.EntityInteract event) {
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.acid.get());
    }


    public void PlayerInteractZombie(Player player, Entity target, Item doItem) {
        if (target instanceof small_zombie smallZombie){
            if (player.getMainHandItem().is(doItem)&&!player.isShiftKeyDown()){

                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            stackHandler.setStackInSlot(i, new ItemStack(doItem));
                            player.getMainHandItem().shrink(1);
                        }
                    }
                });

            } else if (player.getMainHandItem().isEmpty()&&player.isShiftKeyDown()) {
                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            stackHandler.getStackInSlot(i).shrink(1);
                        }
                    }
                    player.addItem(new ItemStack(doItem));

                });

            }
        }
    }
}
