package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.nightmare_base_stone_meet;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class maxamout extends UnCommonItem implements TextEvt.Twelve {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.hasEffect(MobEffects.DIG_SLOWDOWN)){
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
            }
        }
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.getTag() != null) {
                    stack.getTag().putBoolean(nightmare_base_stone_meet.curse,true);
                }else {
                    stack.getOrCreateTag();
                }
            }
        }
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(swim(player, stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(swim(player, stack));
        }
        if (slotContext.entity() instanceof Player player) {
            if (!Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.getTag() != null) {
                    stack.getTag().putBoolean(nightmare_base_stone_meet.curse,false);
                }else {
                    stack.getOrCreateTag();
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {

            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.1").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string.2").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.3").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string.4").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.5").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string.6").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.7").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.8").withStyle(ChatFormatting.GOLD));
        }else {
            tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.GOLD,ChatFormatting.BOLD));


        }
    }
    public Multimap<Attribute, AttributeModifier> swim(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("82a617a0-56fe-4ead-82d4-9d968ca7c777"), MoonStoneMod.MODID + "souddsl", 0.75, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }
}


