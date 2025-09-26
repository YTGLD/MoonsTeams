package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmare_base_insight_drug extends nightmare implements SuperNightmare {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (SIHandler.hascurio(slotContext.entity(), this))
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public  Multimap<Attribute, AttributeModifier> gets(SlotContext slotContext) {
         Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();
        List<Integer> integersHealth = new ArrayList<>();
        CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        integersHealth.add(1);
                    }
                }
            }
        });
        float health = Config.SERVER.nightmare_base_insight_drug.get();
        for (int ignored : integersHealth) {
            health -= Config.SERVER.nightmare_base_insight_drug_2.get();
        }
        if (health < 10) {
            health = 10;
        }
        health /= 100;


        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("9728fabf-d4e6-497b-b528-786e72d354c7"),"s", health, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("9728fabf-d4e6-497b-b528-786e72d354c7"),"s", health, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("9728fabf-d4e6-497b-b528-786e72d354c7"),"s", health, AttributeModifier.Operation.MULTIPLY_BASE));
        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_drug.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_drug.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}


