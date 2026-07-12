package com.ytgld.moonstone.event;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.DataReg;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class AdvancementEvt {
    public static void giveItem(Player player, ItemStack item) {
        ItemEntity entity = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), item);

        entity.setNoPickUpDelay();
        entity.setGlowingTag(true);
        AttributeModifier attributeModifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, entity.getItem().getItem().getDescriptionId()), 1, AttributeModifier.Operation.ADD_VALUE);
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("nightmare").ifPresent(stacks -> {
            stacks.addPermanentModifier(attributeModifier);
        }));

        player.level().addFreshEntity(entity);
    }

    public static void giveItemEntity(Player player, ItemEntity entity) {

        Set<String> blacklist = new HashSet<>();
        AttributeModifier attributeModifier = new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, entity.getItem().getItem().getDescriptionId()), 1, AttributeModifier.Operation.ADD_VALUE);
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("nightmare").ifPresent(stacks -> {
            stacks.addPermanentModifier(attributeModifier);
        }));

        entity.setNoPickUpDelay();
        entity.setGlowingTag(true);
        player.level().addFreshEntity(entity);
    }

    public static final String nightmare_base_black_eye_heart = "nightmare_base_black_eye_heart";
    public static final String nightmare_base_black_eye_eye = "nightmare_base_black_eye_eye";
    public static final String nightmare_base_black_eye_red = "nightmare_base_black_eye_red";
    public static final String tricky_puppets = "tricky_puppets";


    public static final String nightmare_base_stone_brain = "nightmare_base_stone_brain";
    public static final String nightmare_base_stone_meet = "nightmare_base_stone_meet";
    public static final String nightmare_base_stone_virus = "nightmare_base_stone_virus";
    public static final String end_bone = "end_bone";


    public static final String nightmare_base_reversal_card = "nightmare_base_reversal_card";
    public static final String nightmare_base_reversal_mysterious = "nightmare_base_reversal_mysterious";
    public static final String nightmare_base_reversal_orb = "nightmare_base_reversal_orb";


    public static final String nightmare_base_redemption_deception = "nightmare_base_redemption_deception";
    public static final String nightmare_base_redemption_degenerate = "nightmare_base_redemption_degenerate";
    public static final String nightmare_base_redemption_down_and_out = "nightmare_base_redemption_down_and_out";
    public static final String hypocritical_self_esteem = "hypocritical_self_esteem";


    public static final String nightmare_base_fool_betray = "nightmare_base_fool_betray";
    public static final String nightmare_base_fool_bone = "nightmare_base_fool_bone";
    public static final String nightmare_base_fool_soul = "nightmare_base_fool_soul";
    public static final String apple = "apple";


    public static final String nightmare_base_insight_drug = "nightmare_base_insight_drug";
    public static final String nightmare_base_insight_insane = "nightmare_base_insight_insane";
    public static final String nightmare_base_insight_collapse = "nightmare_base_insight_collapse";
    public static final String ring = "ring";


    public static final String nightmare_base_start_power = "nightmare_base_start_power";
    public static final String nightmare_base_start_egg = "nightmare_base_start_egg";
    public static final String nightmare_base_start_pod = "nightmare_base_start_pod";
    public static final String wolf = "wolf";

    @SubscribeEvent
    public void wolf(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Wolf wolf_entity) {
            if (wolf_entity.getOwner() instanceof Player player) {
                if (SIHandler.hascurio(player, Items.nightmare_base_start.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.nightmare_base_start.get())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (event.getEntity() instanceof Warden warden) {
                                            if (!stack.get(DataReg.tag).getBooleanOr(wolf, false)) {
                                                giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                        new ItemStack(Items.wolf.get())));
                                                stack.get(DataReg.tag).putBoolean(wolf, true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public void hypocritical_self_esteem(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_redemption.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_redemption.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon enderDragon) {
                                        if (player.getMainHandItem().isEmpty() && player.hasEffect(MobEffects.WITHER)) {
                                            if (!stack.get(DataReg.tag).getBooleanOr(hypocritical_self_esteem, false)) {

                                                giveItemEntity(player, new ItemEntity(enderDragon.level(), enderDragon.getX(), enderDragon.getY(), enderDragon.getZ(),
                                                        new ItemStack(Items.hypocritical_self_esteem.get())));

                                                stack.get(DataReg.tag).putBoolean(hypocritical_self_esteem, true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void ring(LivingUseTotemEvent event) {
        if (event.getEntity() instanceof Player player) {


            List<Integer> integers = new ArrayList<>();
            int a = 0;
            Collection<MobEffectInstance> collection = player.getActiveEffects();
            if (!collection.isEmpty()) {
                for (MobEffectInstance effectInstance : collection) {
                    if (!effectInstance.getEffect().value().isBeneficial()) {
                        integers.add(1);
                    }
                }
            }
            for (int ignored : integers) {
                a++;
            }
            if (a >= 10) {
                if (SIHandler.hascurio(player, Items.nightmare_base_insight.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.nightmare_base_insight.get())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(ring, false)) {
                                            giveItem(player, new ItemStack(Items.ring.get()));
                                            stack.get(DataReg.tag).putBoolean(ring, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public void apple(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_fool.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_fool.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof LivingEntity) {
                                        LivingEntity warden = event.getEntity();
                                        if (warden.getMaxHealth() >= player.getMaxHealth() * 30) {
                                            if (!stack.get(DataReg.tag).getBooleanOr(apple, false)) {

                                                giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                        new ItemStack(Items.apple.get())));

                                                stack.get(DataReg.tag).putBoolean(apple, true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_start_egg(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_start.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_start.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Sniffer warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_start_egg, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_start_egg.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_start_egg, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void end_bone(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_stone.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Warden warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(end_bone, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.end_bone.get())));

                                            stack.get(DataReg.tag).putBoolean(end_bone, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_start_power(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_start.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_start.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Warden warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_start_power, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_start_power.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_start_power, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_insight(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_insight.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_insight.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_insight_collapse, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_insight_collapse.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_insight_collapse, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_insight_insane(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_insight.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_insight.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_insight_insane, false)) {
                                        giveItem(player, new ItemStack(Items.nightmare_base_insight_insane.get()));
                                        stack.get(DataReg.tag).putBoolean(nightmare_base_insight_insane, true);
                                    }

                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_fool(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_fool.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_fool.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_fool_betray, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_fool_betray.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_fool_betray, true);
                                        }
                                    }
                                }
                            }
                            if (stack.is(Items.nightmare_base_fool.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Warden warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_fool_bone, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_fool_bone.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_fool_bone, true);
                                        }
                                    }
                                }
                            }
                            if (stack.is(Items.nightmare_base_fool.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof WitherBoss warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_fool_soul, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_fool_soul.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_fool_soul, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }


    @SubscribeEvent
    public void nightmare_base_redemption_degenerate(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_redemption.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_redemption.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Villager raider) {
                                        if (stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_degenerate, 0) < 100) {
                                            stack.get(DataReg.tag).putInt(nightmare_base_redemption_degenerate, stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_degenerate, 0) + 1);
                                        } else if (stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_degenerate, 0) == 100) {
                                            giveItem(player, new ItemStack(Items.nightmare_base_redemption_degenerate.get()));
                                            stack.get(DataReg.tag).putInt(nightmare_base_redemption_degenerate, stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_degenerate, 0) + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_redemption_deception(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_redemption.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_redemption.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (player.hasEffect(MobEffects.BAD_OMEN)) {
                                        if (event.getEntity() instanceof Raider raider) {
                                            if (stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_deception, 0) < 100) {
                                                stack.get(DataReg.tag).putInt(nightmare_base_redemption_deception, stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_deception, 0) + 1);
                                            } else if (stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_deception, 0) == 100) {
                                                giveItem(player, new ItemStack(Items.nightmare_base_redemption_deception.get()));
                                                stack.get(DataReg.tag).putInt(nightmare_base_redemption_deception, stack.get(DataReg.tag).getIntOr(nightmare_base_redemption_deception, 0) + 1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_reversal_card(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_reversal.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_reversal.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_reversal_card, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_reversal_card.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_reversal_card, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_stone_meet(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_stone.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_stone_meet, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_stone_meet.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_stone_meet, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void nightmare_base_stone_virus(LivingUseTotemEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone.get())) {
                if (event.getSource().getEntity() instanceof WitherBoss) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.nightmare_base_stone.get())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_stone_virus, false)) {
                                            giveItem(player, new ItemStack(Items.nightmare_base_stone_virus.get()));
                                            stack.get(DataReg.tag).putBoolean(nightmare_base_stone_virus, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }


    @SubscribeEvent
    public void nightmare_base_stone_brain(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_stone.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Zombie warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_stone_brain, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.NightmareVirus_.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_stone_brain, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }


    @SubscribeEvent
    public void LivingUseTotemEvent(LivingUseTotemEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                if (player.hasEffect(MobEffects.POISON)
                        && player.hasEffect(MobEffects.WITHER)
                        && player.hasEffect(MobEffects.SLOWNESS)) {
                    if (player.getRemainingFireTicks() > 0) {
                        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                            Map<String, ICurioStacksHandler> curios = handler.getCurios();
                            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                                ICurioStacksHandler stacksHandler = entry.getValue();
                                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                    ItemStack stack = stackHandler.getStackInSlot(i);
                                    if (stack.is(Items.nightmare_base_black_eye.get())) {
                                        if (stack.get(DataReg.tag) != null) {
                                            if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_black_eye_heart, false)) {

                                                giveItem(player, new ItemStack(Items.nightmare_base_black_eye_heart.get()));

                                                stack.get(DataReg.tag).putBoolean(nightmare_base_black_eye_heart, true);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void drop(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_black_eye.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Warden warden) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_black_eye_eye, false)) {

                                            giveItemEntity(player, new ItemEntity(warden.level(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_black_eye_eye.get())));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_black_eye_eye, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                               Entity entity,
                               int lv) {
        if (entity instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_black_eye.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_black_eye_red, false)) {
                                        if (Mth.nextInt(RandomSource.create(), 0, 100) <= lv) {
                                            generatedLoot.add(new ItemStack(Items.nightmare_base_black_eye_red.get()));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_black_eye_red, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }


    public static void nightmare_base_reversal_mysteriousLOOT(ObjectArrayList<ItemStack> generatedLoot,
                                                              Entity entity) {
        if (entity instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_reversal_orb.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_reversal_orb.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_reversal_mysterious, false)) {
                                        generatedLoot.add(new ItemStack(Items.nightmare_base_reversal_mysterious.get()));
                                        stack.get(DataReg.tag).putBoolean(nightmare_base_reversal_mysterious, true);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void nightmare_base_start_pod(ObjectArrayList<ItemStack> generatedLoot,
                                                Entity entity) {
        if (entity instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_start.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_start.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 100) <= 25) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(nightmare_base_start_pod, false)) {
                                            generatedLoot.add(new ItemStack(Items.nightmare_base_start_pod.get()));
                                            stack.get(DataReg.tag).putBoolean(nightmare_base_start_pod, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void tricky_puppets(ObjectArrayList<ItemStack> generatedLoot,
                                      Entity entity) {
        if (entity instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_black_eye.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 100) <= 75) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(tricky_puppets, false)) {
                                            generatedLoot.add(new ItemStack(Items.tricky_puppets.get()));
                                            stack.get(DataReg.tag).putBoolean(tricky_puppets, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
