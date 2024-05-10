package com.moonstone.moonstonemod.Item.Plague.BloodVirus.ex;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class botton extends BloodViru {



    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi.addSlotModifier(linkedHashMultimap, "medicine", uuid, 2, AttributeModifier.Operation.ADDITION);
        CuriosApi.addSlotModifier(linkedHashMultimap, "curio", uuid, 1, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.botton.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.botton.tool.string.1").withStyle(ChatFormatting.RED));
             tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.botton.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
    }
}
