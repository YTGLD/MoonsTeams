package com.moonstone.moonstonemod.Item.Plague.ALL;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Item.MoonStoneItem.plague;
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
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class germ extends plague implements ICurioItem {

    private final String lvl= "germString";
    private final String lvlSize= "germStringLvlSize";

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.germ.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.germ.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("now："+stack.getOrCreateTag().getInt(lvlSize)).withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("").withStyle(ChatFormatting.RED));
        if (stack.getOrCreateTag().getInt(lvl)==0){
            tooltip.add(Component.translatable("+1 伤害").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+15% 攻速").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+4 护甲").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+8 生命").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+20% 游泳速度").withStyle(ChatFormatting.RED));
        }
        if (stack.getOrCreateTag().getInt(lvl)==1){
            tooltip.add(Component.translatable("+1.5 伤害").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+20% 攻速").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+5 护甲").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+10 生命").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+25% 游泳速度").withStyle(ChatFormatting.RED));
        }
        if (stack.getOrCreateTag().getInt(lvl)==2){
            tooltip.add(Component.translatable("+1.75 伤害").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+25% 攻速").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+6 护甲").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+12 生命").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+30% 游泳速度").withStyle(ChatFormatting.RED));
        }
        if (stack.getOrCreateTag().getInt(lvl)==3){
            tooltip.add(Component.translatable("+2 伤害").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+30% 攻速").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+7 护甲").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+14 生命").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("+35% 游泳速度").withStyle(ChatFormatting.RED));
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(player, stack));
            stack.getOrCreateTag().putInt(lvl, stack.getOrCreateTag().getInt(lvl));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(this.getAttributeModifiers(player, stack));
        }
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("efe2ab2d-072a-337a-9d65-10da4f48c969");
        float ss = stack.getOrCreateTag().getInt(lvl);
        if (ss == 0) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 1, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 4, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 8, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }else if (ss == 1){
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 1.5, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 5, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 10, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
        } else  if (ss == 2){
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 1.75, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 6, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 12, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.3, AttributeModifier.Operation.MULTIPLY_TOTAL));

        }else  if (ss == 3){
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 2, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.3, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 7, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec",14 , AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.35, AttributeModifier.Operation.MULTIPLY_TOTAL));

        }


        return modifierMultimap;
    }


}
