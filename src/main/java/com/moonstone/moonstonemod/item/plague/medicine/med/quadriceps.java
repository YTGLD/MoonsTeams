package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import com.moonstone.moonstonemod.moonstoneitem.extend.medicinebox;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class quadriceps extends medIC {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(aaa(player, stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(aaa(player, stack));
        }
    }

    public Multimap<Attribute, AttributeModifier> aaa(Player player , ItemStack stack){
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("c13feccd-e582-4ae1-aee7-35e8acf7e9e1"), MoonStoneMod.MODID+":quadriceps", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.quadriceps.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.quadriceps.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.quadriceps.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable(""+ medicinebox.do_jump).withStyle(ChatFormatting.RED));
    }

}
