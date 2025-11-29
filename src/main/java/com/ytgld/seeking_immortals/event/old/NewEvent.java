package com.ytgld.seeking_immortals.event.old;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.ConfigClient;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.fall.the_divine_fall_ring;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.base.*;
import com.ytgld.seeking_immortals.item.nightmare.falling_immortals;
import com.ytgld.seeking_immortals.item.nightmare.immortal;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.INightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.nightmare_base_black_eye_eye;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.nightmare_base_black_eye_heart;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.nightmare_base_black_eye_red;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.apple;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.nightmare_base_fool_bone;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight.hidden_blade;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight.nightmare_base_insight_insane;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.nightmare_base_redemption_deception;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.candle;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.nightmare_base_reversal_orb;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.nightmare_base_start_pod;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.wolf;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.end_bone;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.nightmare_base_stone_brain;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.nightmare_base_stone_virus;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class NewEvent {

    public static float time= 0;
    @SubscribeEvent
    public void tick(TickEvent.LevelTickEvent event){
        time++;
    }
    @SubscribeEvent
    public  void LivingExperienceDropEvent(LivingExperienceDropEvent event) {
        nightmare_base_insight.exp(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_base_reversal_orb.LivingHealEvent(event);
        nightmare_base_black_eye_heart.heal(event);
        candle.heal(event);
        nightmare_base.healGive(event);
        if (event.getEntity().getAttribute(AttReg.heal.get())!=null){
            float attack = (float) event.getEntity().getAttribute(AttReg.heal.get()).getValue();
            event.setAmount(event.getAmount()*(attack));
        }
    }

    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        falling_immortals.dieEqItem(event);
        nightmare_base_reversal.LivingDeathEvent(event);
        immortal.livDead(event);
        wolf.kill(event);
        nightmare_base_black_eye_red.kill(event);
        nightmare_base_insight_insane.LivingDeathEvents(event);
        nightmare_base.killGive(event);
        nightmare_base.die(event);
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        hidden_blade.hurt_cit(event);
        lead.hurtOfBlood(event);
        falling_immortals.damage(event);
        apple.damage(event);
        nightmare_base_stone_virus.h(event);
        strengthen_runestone.hurt(event);
        strengthen_runestone.hurt(event);
        strengthen_runestone.hurt(event);
        nightmare_base_black_eye_eye.attLook(event);
        nightmare_base_black_eye_heart.hurt(event);
        nightmare_base_stone.LivingHurtEvent(event);
        nightmare_base_stone_brain.hurts(event);
        nightmare_base_redemption_deception.LivingHurtEvent(event);
        nightmare_base_fool_bone.attLook(event);
        nightmare_base_insight_insane.damage(event);
        nightmare_base_start.damage(event);
        nightmare_base_start_pod.damage(event);
        end_bone.hurts(event);
        candle.hurt(event);
        immortal.hEvt(event);
        wolf.attack(event);
        revive_runestone.hurt(event);
        defend_against_runestone.hurt(event);
        bone_or_god.hurt(event);
        if (event.getEntity().hasEffect(Effects.dead.get()) && event.getEntity().getEffect(Effects.dead.get()) != null) {
            float lvl = event.getEntity().getEffect(Effects.dead.get()).getAmplifier();
            lvl *= 0.2f;
            event.setAmount(event.getAmount() * (1 + lvl));

        }
        if (event.getSource().getEntity() instanceof LivingEntity living) {
            if (living.getAttribute(AttReg.all_attack.get()) != null) {
                float attack = (float) living.getAttribute(AttReg.all_attack.get()).getValue();
                event.setAmount(event.getAmount() * (attack));
            }
        }


    }
    @SubscribeEvent
    public void exp(LivingExperienceDropEvent event) {
        the_divine_fall_ring.exp(event);
    }
    @SubscribeEvent
    public void LivingDamageEvent(LivingDamageEvent event){
        blood_god.hurtOfBlood(event);
        nightmare_base.damageGive(event);
    }
    @SubscribeEvent
    public void Start(LivingEntityUseItemEvent.Start event){
        blood_god.hurtOfBlood(event);
    }
    @SubscribeEvent
    public  void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!player.getTags().contains(SeekingImmortalsMod.MODID+"nightmare")) {

            if (!Config.SERVER.eqNightmareBase.get()) {
                if (Config.SERVER.giveNightmare.get()) {
                    player.addItem(Items.nightmare_base.get().getDefaultInstance());
                }
            }else {
                CuriosApi.getCuriosInventory(player).ifPresent(curiosItemHandler -> {
                    curiosItemHandler.addPermanentSlotModifier(
                            "nightmare",
                            UUID.fromString("66867e94-dc9e-4263-8d24-a854f7025a98"),
                            "nightmare_base_slot_add",Config.SERVER.nightmareBaseMaxItem.get()+1, AttributeModifier.Operation.ADDITION);
                });
                Random random = new Random();
                ArrayList<Item> items = new ArrayList<>(List.of(
                        Items.nightmare_base_stone.get(),
                        Items.nightmare_base_reversal.get(),
                        Items.nightmare_base_black_eye.get(),
                        Items.nightmare_base_redemption.get(),
                        Items.nightmare_base_fool.get(),
                        Items.nightmare_base_insight.get(),
                        Items.nightmare_base_start.get()
                ));
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                            Map<String, ICurioStacksHandler> curios = handler.getCurios();
                            int maxItemsToPlace = Config.SERVER.nightmareBaseMaxItem.get();
                            int itemsPlaced = 0;
                            boolean placedNightmareBase = false;
                            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                                ICurioStacksHandler stacksHandler = entry.getValue();
                                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                                for (int i = stacksHandler.getSlots() - 1; i >= 0; i--) {
                                    ItemStack stack = stackHandler.getStackInSlot(i);
                                    if (stack.isEmpty() && !placedNightmareBase) {
                                        ItemStack nig = Items.nightmare_base.get().getDefaultInstance();
                                        CompoundTag compoundTag = new CompoundTag();
                                        compoundTag.putBoolean("canDo", true);
                                        nig.setTag(compoundTag);
                                        stackHandler.setStackInSlot(i, nig);
                                        placedNightmareBase = true;
                                    } else if (stack.isEmpty() && !items.isEmpty() && itemsPlaced < maxItemsToPlace) {
                                        if (stacksHandler.getIdentifier().equals("nightmare")) {
                                            int index = random.nextInt(items.size());
                                            Item selectedItem = items.remove(index);
                                            stackHandler.setStackInSlot(i, selectedItem.getDefaultInstance());
                                            itemsPlaced++;
                                        }
                                    }
                                    if (itemsPlaced >= maxItemsToPlace) {
                                        break;
                                    }
                                }
                                if (itemsPlaced >= maxItemsToPlace) {
                                    break;
                                }
                            }
                        }
                );
            }
            player.addTag(SeekingImmortalsMod.MODID+"nightmare");
        }
    }
    @SubscribeEvent
    public void soulbattery(CriticalHitEvent event) {
        if (event.getEntity().getAttribute(AttReg.cit.get())!=null){
            float attack = (float) event.getEntity().getAttribute(AttReg.cit.get()).getValue();
            event.setDamageModifier(event.getDamageModifier()*(attack));
        }
        hidden_blade.cit(event);
    }
    @SubscribeEvent
    public void soulbattery(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().getAttribute(AttReg.break_speed.get())!=null){

            float dig = (float) event.getEntity().getAttribute(AttReg.break_speed.get()).getValue();

            event.setNewSpeed(event.getNewSpeed()*(dig));
        }
    }
    @SubscribeEvent
    public void hurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.hurt.get())!=null){
                float hurt = (float) living.getAttribute(AttReg.hurt.get()).getValue();
                event.setAmount(event.getAmount()*(hurt));
            }
        }

    }
    @SubscribeEvent
    public void hurt(ItemTooltipEvent event) {

        if (event.getItemStack().getItem() instanceof AllTip){
            event.getToolTip().add(1, Component.translatable(
                    "key.keyboard.left.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
        }
        if (ConfigClient.Client.showDisplayNightmareTip.get()) {
            if (!SIHandler.hascurio(event.getEntity(), Items.nightmare_base.get())) {
                if (!Config.SERVER.canUse.get()) {
                    if (event.getItemStack().getItem() instanceof SuperNightmare) {
                        List<Component> toolTip = event.getToolTip();
                        Random random = new Random();
                        for (int i = 0; i < toolTip.size(); i++) {
                            int randomLength = random.nextInt(25) + 1;
                            StringBuilder randomString = new StringBuilder();
                            for (int j = 0; j < randomLength; j++) {
                                randomString.append("§ka");
                            }
                            toolTip.set(i, Component.literal(randomString.toString()).withStyle(ChatFormatting.DARK_RED));
                        }
                    }
                }
            }
        }
        {
            ItemStack stack = event.getItemStack();
            Player player = event.getEntity();
            if (stack.getItem() instanceof SuperNightmare) {
                if (!Config.SERVER.canUse.get()) {
                    if (!SIHandler.hascurio(player, Items.nightmare_base.get())) {
                        event.getToolTip().add(1, Component.literal(""));
                        event.getToolTip().add(1, Component.translatable("moonstone.super_nightmare.name.1").withStyle(ChatFormatting.DARK_RED));
                        event.getToolTip().add(1, Component.translatable("moonstone.super_nightmare.name").withStyle(ChatFormatting.DARK_RED));
                    } else {
                        event.getToolTip().add(1, Component.literal(""));
                        event.getToolTip().add(1, Component.translatable("moonstone.super_nightmare.name.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
                        event.getToolTip().add(1, Component.translatable("moonstone.super_nightmare.name").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void Color(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();

        if (stack.getItem() instanceof INightmare) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderEnd(0xFF800080);

            tooltipEvent.setBackgroundStart(0x00000000);
            tooltipEvent.setBackgroundEnd(0x00000000);
        }
    }
}
