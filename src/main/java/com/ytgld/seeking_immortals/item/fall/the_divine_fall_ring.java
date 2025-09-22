package com.ytgld.seeking_immortals.item.fall;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.renderer.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class the_divine_fall_ring extends FallItem {
    public static final String uDead = "undead";
    public static void exp(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() instanceof Player) {
            if (Handler.hascurio(event.getAttackingPlayer(), Items.the_divine_fall_ring.get())) {
                event.setDroppedExperience(event.getDroppedExperience() * 2);
            }
        }
    }
    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return 2;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("8a0afb92-9a81-4f41-9f45-de0f12891df6"),"o", 10, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("8a0afb92-9a81-4f41-9f45-de0f12891df6"),"o", 10, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("8a0afb92-9a81-4f41-9f45-de0f12891df6"),"o", 10, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("8a0afb92-9a81-4f41-9f45-de0f12891df6"),"o", 0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return linkedHashMultimap;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            if (slotContext.entity().tickCount >= 20) {
            } else {
                slotContext.entity().addEffect(new MobEffectInstance(Effects.invulnerable.get(), 200, 0, false, false, false));
            }
        }
        if (stack.getTag() == null) {
            stack.getOrCreateTag();
        }
        if (slotContext.entity() instanceof Player player) {

            if (!player.level().isClientSide) {
                float lv = player.getHealth() / player.getMaxHealth();

                lv *= 100;
                int now = (int) (100 - (lv));
                if (stack.getTag() == null) {
                    stack.getOrCreateTag();
                }
                if (stack.getTag() != null) {
                    stack.getTag().putInt(uDead, now);
                }

                player.getAttributes().addTransientAttributeModifiers(ad(stack));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(ad(stack));
    }

    public Multimap<Attribute, AttributeModifier> ad(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.getTag() != null) {
            int lvl = stack.getTag().getInt(uDead);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;


            modifiers.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("652c804a-03ae-43e8-a9e4-1ab1e34e26a7"),"as",
                    heal, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("652c804a-03ae-43e8-a9e4-1ab1e34e26a7"),"as",
                    speed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("652c804a-03ae-43e8-a9e4-1ab1e34e26a7"),"as",
                    damage, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("652c804a-03ae-43e8-a9e4-1ab1e34e26a7"),"as",
                    attSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("652c804a-03ae-43e8-a9e4-1ab1e34e26a7"),"as",
                    armor, AttributeModifier.Operation.MULTIPLY_BASE));


        }
        return modifiers;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        String tring1 = "item.the_divine_fall_ring.tool.string.1";
        String tring2 = "item.the_divine_fall_ring.tool.string.2";
        String tring3 = "item.the_divine_fall_ring.tool.string.3";
        String tring4 = "item.the_divine_fall_ring.tool.string.4";
        String tring5 = "item.the_divine_fall_ring.tool.string.5";
        String tring6 = "item.the_divine_fall_ring.tool.string.6";
        String tring7 = "item.the_divine_fall_ring.tool.string.7";

        addTip(tooltipComponents, tring1);
        addTip(tooltipComponents, tring2);
        addTip(tooltipComponents, tring3);
        addTip(tooltipComponents, tring4);
        addTip(tooltipComponents, tring5);
        addTip(tooltipComponents, tring6);
        addTip(tooltipComponents, tring7);

        tooltipComponents.add(Component.translatable("item.immortal.tool.string.5").withStyle(ChatFormatting.RED));
    }
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF0000)));
        return co;
    }
    public void addTip(List<Component> tooltipComponents, String Z) {
        int red  = 255;
        int purple  = 255;
        int g = (int) (255 * Math.sin(NewEvent.time/200f));
        g /= 2;
        if (g < 0) {
            g = -g;
        }
        tooltipComponents.add(Component.translatable(Z).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255, red, g, purple)))));
    }
}