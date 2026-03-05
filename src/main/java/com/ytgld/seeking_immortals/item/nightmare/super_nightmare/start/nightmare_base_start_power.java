package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public class nightmare_base_start_power extends nightmare implements SuperNightmare {
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            if (SIHandler.hascurio(slotContext.entity(), this)) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
        }
    }

    public  Multimap<Attribute, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();
        List<Integer> integersHealth = new ArrayList<>();
        for (MobEffectInstance effect : living.getActiveEffects()) {
            if (effect != null
                    && effect.getEffect().isBeneficial()) {
                integersHealth.add(1);
            }
        }
        float att = 0;
        for (int ignored : integersHealth) {
            float ssa =(float) (double)Config.SERVER.nightmare_base_start_power.get();
            att += ssa;
        }
        att /= 100;
        Set<String> blacklist = new HashSet<>();
        for (String aaa : Config.SERVER.allAttributeModify.get()) {
            String[] parts = aaa.split(":");
            if (parts.length > 0) {
                blacklist.add(parts[0] + ":" + parts[1]);
            }
        }
        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            if (attribute != null) {
                String attributeId = Config.getRegisteredName(attribute);
                if (!blacklist.contains(attributeId)) {
                    linkedHashMultimap.put(attribute.get(), new AttributeModifier(UUID.fromString("6a4b481b-b838-41bb-bb54-f3567ba123c7"),"as", att, AttributeModifier.Operation.MULTIPLY_BASE));
                }
            }
        }

        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float ssa =(float) (double)Config.SERVER.nightmare_base_start_power.get();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_power.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_power.tool.string.1",ssa).withStyle(ChatFormatting.DARK_RED));
    }
}


