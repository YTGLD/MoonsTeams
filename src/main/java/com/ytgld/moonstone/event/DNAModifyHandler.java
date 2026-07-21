package com.ytgld.moonstone.event;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.item.ICanHasInItem;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.List;

public class DNAModifyHandler {
    public static void doStrongerDNAModifiers(ICanHasInItem canHasInItem, ItemStack stack, LivingEntity livingEntity,
                                              Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap) {
        canHasInItem.modifyAttribute(stack, Items.regenerative.asItem(), AttReg.heal,new AttributeModifier(
                canHasInItem.id(stack),0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.autolytic.asItem(), NeoForgeMod.SWIM_SPEED,new AttributeModifier(
                canHasInItem.id(stack),0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.atpoverdose.asItem(), Attributes.MAX_HEALTH,new AttributeModifier(
                canHasInItem.id(stack),4, AttributeModifier.Operation.ADD_VALUE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.fermentation.asItem(), AttReg.cit,new AttributeModifier(
                canHasInItem.id(stack),0.13, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.putrefactive.asItem(), Attributes.ARMOR,new AttributeModifier(
                canHasInItem.id(stack),0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.ambush.asItem(), Attributes.LUCK,new AttributeModifier(
                canHasInItem.id(stack),2, AttributeModifier.Operation.ADD_VALUE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.quadriceps.asItem(), Attributes.MOVEMENT_SPEED,new AttributeModifier(
                canHasInItem.id(stack),0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.calcification.asItem(), Attributes.ARMOR,new AttributeModifier(
                canHasInItem.id(stack),4, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.WarmApproachable.asItem(), Attributes.MOVEMENT_SPEED,new AttributeModifier(
                canHasInItem.id(stack),0.03, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.EarthAffinity.asItem(), Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(
                canHasInItem.id(stack),0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        canHasInItem.modifyAttribute(stack,Items.OceanAffinity.asItem(), NeoForgeMod.SWIM_SPEED,new AttributeModifier(
                canHasInItem.id(stack),0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

    }

    public static void modifyComponent(List<Component> components,ItemStack stack ){
        if (stack.getItem() instanceof ItemBase itemBase) {
            if (itemBase.maxSize() > 0) {
                components.add(Component.translatable("item.modifiers.any").withStyle(ChatFormatting.GOLD));
            }
        }


        addComponent(stack,Items.reanimation.asItem(),Component.translatable("moonstone.reanimation.modify"),components);
        addComponent(stack,Items.polyphagia.asItem(),Component.translatable("moonstone.polyphagia.modify"),components);
        addComponent(stack,Items.masticatory.asItem(),Component.translatable("moonstone.masticatory.modify"),components);
    }


    private static void addComponent(ItemStack stack, Item item, Component component,List<Component> components){
        if (stack.getItem() instanceof ItemBase) {
            if (ICanHasInItem.getAll(stack).contains(item)) {
                components.add(Component.literal("+").append(component));
            }
        }
    }
    @SubscribeEvent
    public void eatStart(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            if (event.getItem().getUseAnimation() == ItemUseAnimation.EAT) {
                if (Handler.hasModifyFormItem(player, Items.polyphagia.get())) {
                    player.heal(6);
                }
            }
        }
    }
    @SubscribeEvent
    public void eatEnt(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player){
            if (event.getItem().getUseAnimation() == ItemUseAnimation.EAT) {
                if (Handler.hasModifyFormItem(player, Items.masticatory.get())) {
                    event.setDuration((int) (event.getDuration() * 0.75f));
                }
            }
        }
    }
    @SubscribeEvent
    public void reanimation(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hasModifyFormItem(player,Items.reanimation.get())){
                if (!player.getCooldowns().isOnCooldown(Items.reanimation.get().getDefaultInstance())) {
                    if (event.getNewDamage() > player.getHealth()) {
                        event.setNewDamage(0);
                        player.heal(player.getMaxHealth() / 4);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 0.8F, 0.8F);
                        player.getCooldowns().addCooldown(Items.reanimation.get().getDefaultInstance(), 3000);
                    }
                }
            }
        }
    }
}
