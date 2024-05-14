package com.moonstone.moonstonemod.item.uncommon;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class plague extends UnCommonItem {
    public static final String DoPlague="Plague";
    public static final String CursePlague="CursePlague";
    public static final String YanJIu="YanJIu";
    public static final String FanYanJIu="FanYanJIu";

    public static final String YanJIuBoolean="YanJIuBoolean";


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        stack.getOrCreateTag().putBoolean(DoPlague,true);
        if (slotContext.entity().tickCount%20 == 0) {
            if (stack.getOrCreateTag().getFloat(FanYanJIu) < 100) {
                stack.getOrCreateTag().putFloat(FanYanJIu, (float) (stack.getOrCreateTag().getFloat(FanYanJIu) + 0.01));
            }
        }

        if (stack.getOrCreateTag().getBoolean(YanJIuBoolean)){
            stack.getOrCreateTag().remove(CursePlague);
        }

        if (stack.getOrCreateTag().getFloat(YanJIu)>=100){
            stack.getOrCreateTag().putBoolean(YanJIuBoolean,true);
        }else {
            stack.getOrCreateTag().putBoolean(YanJIuBoolean,false);
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.APlague(stack));

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.APlague(stack));
        slotContext.entity().getAttributes().removeAttributeModifiers(this.APlague(newStack));

    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return Handler.hascurio(slotContext.entity(), Items.nightmareeye.get());
    }

    public Multimap<Attribute, AttributeModifier> APlague(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (stack.getOrCreateTag().getBoolean(YanJIuBoolean)) {
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.66, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.9, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.7, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.7, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", 0.7, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }else {
            float c =  stack.getOrCreateTag().getFloat(plague.CursePlague);
            c = -c;
            c /= 3;
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", c, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", c, AttributeModifier.Operation.MULTIPLY_TOTAL));

            float s = stack.getOrCreateTag().getFloat(FanYanJIu) / 100 / 3 / 100;
            s = -s;
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", s, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", s, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("380df991-f603-344c-a090-369bad2a924a"), MoonStoneMod.MODID + ":plague", s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return modifierMultimap;
    }

    public void appendHoverText(ItemStack stack, Level p_150750_, @NotNull List<Component> p_150751_, TooltipFlag p_150752_) {
        p_150751_.add(Component.translatable("item.plague.tool.string.un").withStyle(ChatFormatting.RED));
        if (!stack.getOrCreateTag().getBoolean(YanJIuBoolean)) {
            //研究进度
            p_150751_.add(Component.translatable("item.plague.tool.string").append("" + stack.getOrCreateTag().getFloat(FanYanJIu)).append("%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            //反研究进度.
            p_150751_.add(Component.translatable("item.plague.tool.string.1").append("" + stack.getOrCreateTag().getFloat(YanJIu)).append("%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            //病毒进度.
            p_150751_.add(Component.translatable("item.plague.tool.string.2").append("" + stack.getOrCreateTag().getFloat(CursePlague)).append("%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            p_150751_.add(Component.translatable(""));

            p_150751_.add(Component.translatable("item.plague.tool.string.3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            p_150751_.add(Component.translatable("item.plague.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            p_150751_.add(Component.translatable("item.plague.tool.string.5").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            p_150751_.add(Component.translatable(""));
            p_150751_.add(Component.translatable("item.plague.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }else {
            p_150751_.add(Component.translatable("item.plague.tool.string.8").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.9").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.10").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.11").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.12").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.13").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.14").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));

            p_150751_.add(Component.translatable("item.plague.tool.string.15").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.16").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.17").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            p_150751_.add(Component.translatable("item.plague.tool.string.18").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
    }
}
