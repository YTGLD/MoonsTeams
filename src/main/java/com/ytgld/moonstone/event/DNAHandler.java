package com.ytgld.moonstone.event;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.enttiy.ExtendZombieEntity;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ICanHasInItem;
import com.ytgld.moonstone.item.IDNASequence;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.List;

public class DNAHandler {
    public static void dieOfZombie(ExtendZombieEntity entity){
        if (entity.getOwner() instanceof Player player) {
            if (IDNASequence.hasHowDNASequence(player, DNAItems.muscle_manipulation.asItem())) {
                if (entity.deathTime == 2) {
                    float heal = entity.getMaxHealth() * 0.05f;
                    player.heal(heal);
                    player.addEffect(new MobEffectInstance(MobEffects.STRENGTH,200,1));
                }
            }
        }
    }
    public static void addModifyAttribute(ItemBase itemBase, ItemStack stack, LivingEntity livingEntity, Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap){
        itemBase.modifyAttribute(stack, DNAItems.biological_enzyme_synthesis.asItem(), Attributes.MAX_HEALTH, new AttributeModifier(itemBase.id(stack),
                0.05f,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE), attributeModifierMultimap);

        int resKno = 0;
        if (livingEntity.isSprinting()){
            resKno = 10;
        }
        itemBase.modifyAttribute(stack, DNAItems.lactic_acid_accumulation.asItem(), Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(itemBase.id(stack),
                resKno,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE), attributeModifierMultimap);

    }
    public static void addModifyText(List<Component> components, ItemStack stack){
        myDNASequenceComponent(stack,DNAItems.muscle_manipulation.asItem(),
                Component.translatable("moonstone.muscle_manipulation.modify"),components);
        myDNASequenceComponent(stack,DNAItems.lactic_acid_accumulation.asItem(),
                Component.translatable("moonstone.lactic_acid_accumulation.modify"),components);
        myDNASequenceComponent(stack,DNAItems.compound_dispersion.asItem(),
                Component.translatable("moonstone.compound_dispersion.modify"),components);
        myDNASequenceComponent(stack,DNAItems.necrotizing_cyst.asItem(),
                Component.translatable("moonstone.necrotizing_cyst.modify"),components);
    }

    private static void myDNASequenceComponent(ItemStack stack, Item item, Component component, List<Component> components) {
        if (stack.getItem() instanceof ItemBase && IDNASequence.getAllDNA(stack).contains(item)) {
            components.add(Component.literal("+").append(component));
        }

    }

    private static void myModifyComponent(ItemStack stack, Item item, Component component, List<Component> components) {
        if (stack.getItem() instanceof ItemBase && ICanHasInItem.getAll(stack).contains(item)) {
            components.add(Component.literal("+").append(component));
        }
    }
    @SubscribeEvent
    public void effect(MobEffectEvent.Applicable event){
        if (event.getEntity() instanceof Player player) {
            if (IDNASequence.hasHowDNASequence(player, DNAItems.necrotizing_cyst.asItem())){
                if (event.getEffectInstance().is(MobEffects.POISON)) {
                    event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
                }
            }
        }
    }
    @SubscribeEvent
    public void die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getEntity() instanceof LivingEntity living) {
                if (IDNASequence.hasHowDNASequence(player, DNAItems.necrotizing_cyst.asItem())) {
                    AreaEffectCloud cloud = new AreaEffectCloud(living.level(), living.getX(), living.getY(), living.getZ());
                    cloud.setRadius(2.5F);
                    cloud.setOwner(player);
                    cloud.setRadiusOnUse(-0.5F);
                    cloud.setWaitTime(10);
                    cloud.setDuration(300);
                    cloud.setPotionDurationScale(0.25F);
                    cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());
                    cloud.addEffect(new MobEffectInstance(MobEffects.POISON,200,2));
                    living.level().addFreshEntity(cloud);
                }
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (event.getSource().getEntity() instanceof LivingEntity living) {
                if (IDNASequence.hasHowDNASequence(player, DNAItems.compound_dispersion.asItem())) {
                    living.hurt(living.damageSources().playerAttack(player),living.getMaxHealth());
                }
            }
        }
    }
}
