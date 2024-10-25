package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.necora.small_zombie;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna.giant_boom_cell;
import com.moonstone.moonstonemod.item.TheNecora.small.enhancemen;
import com.moonstone.moonstonemod.item.amout.twistedamout;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.blood_sun;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import com.moonstone.moonstonemod.item.pain.pain_candle;
import com.moonstone.moonstonemod.item.pain.pain_ring;
import com.moonstone.moonstonemod.item.pain.the_pain_stone;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class NewEvent {
    @SubscribeEvent
    public  void RightClickItem(PlayerInteractEvent.RightClickItem event){
        max_blood_cube.RightClickItem(event);
    }
    @SubscribeEvent
    public  void necora(LivingEntityUseItemEvent.Finish event){
        medicinebox.necora(event);
    }
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
        max_eye.A(event);
        blood_amout.Hurt(event);
        if (Handler.hascurio(event.getEntity(),Items.acid.get())){
            event.setAmount(event.getAmount()*50);
        }
        if (Handler.hascurio(event.getEntity(),Items.compression.get())){
            event.setAmount(event.getAmount()*2);
        }
        if (event.getSource().getEntity() instanceof small_zombie smallZombie) {
            if (Handler.hascurio(smallZombie, Items.compression.get())){
                smallZombie.heal(20);
            }
        }
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        the_pain_stone.PainStone(event);
        twistedamout.die(event);
        enhancemen.Death(event);
        the_prison_of_sin.LivingDeathEvent(event);
        max_eye.Die(event);
        blood_snake.Die(event);
        blood_magic_box.Did(event);
        blood_sun.Did(event);
    }
    @SubscribeEvent
    public void PlayerInteractEvent(PlayerInteractEvent.EntityInteract event) {
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.acid.get(),"zombie");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.compression.get(),"zombie");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.atrophy.get(),"zombie");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.enhancemen.get(),"zombie");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.motor.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.watergen.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.air.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.bat_cell.get(),"ncrdna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.adrenaline.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_blood.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_boom.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_calcification.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_mummy.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant_nightmare.get(),"dna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.bone_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.disgusting_cells.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.mother_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.parasitic_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.anaerobic_cell.get(),"dna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant_boom_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.not_blood_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.subspace_cell.get(),"dna");


    }


    public void PlayerInteractZombie(Player player, Entity target, Item doItem,String slot) {
        if (target instanceof small_zombie smallZombie){
            if (player.getMainHandItem().is(doItem)&&!player.isShiftKeyDown()){

                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            if (!Handler.hascurio(smallZombie,doItem)
                                    && stacksHandler.getIdentifier().contains(slot)) {
                                ItemStack present = stackHandler.getStackInSlot(i);
                                if (present.isEmpty()) {
                                    stackHandler.setStackInSlot(i, new ItemStack(doItem));
                                    player.getMainHandItem().shrink(1);
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.NEUTRAL, 1F, 1F);
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_NETHERITE, SoundSource.NEUTRAL, 1F, 1F);
                                }
                            }
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
                            if (!stackHandler.getStackInSlot(i).isEmpty()
                                    && stackHandler.getStackInSlot(i).is(doItem)
                                    && stacksHandler.getIdentifier().contains(slot))
                            {

                                player.addItem(new ItemStack(doItem));
                                stackHandler.getStackInSlot(i).shrink(1);

                            }
                        }
                    }
                });
            }
        }
    }
}
