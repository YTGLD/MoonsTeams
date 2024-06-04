package com.moonstone.moonstonemod.item.plague.BloodVirus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.item.plague.BloodVirus.ex.BloodViru;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class heathgene extends BloodViru {
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.heathgene.tool.string").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                uuid, MoonStoneMod.MODID+this.getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL)

        );

        return modifierMultimap;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers( slotContext.entity(),stack));
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.getAttributeModifiers( slotContext.entity(),stack));

    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(LivingEntity player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();

        UUID uuid = UUID.fromString("26d3a8a7-c365-3f1b-98bd-1e86d16aa724");
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                uuid, MoonStoneMod.MODID+this.getDescriptionId(), 10, AttributeModifier.Operation.ADDITION)

        );

        return modifierMultimap;
    }
}
