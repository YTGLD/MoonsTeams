package com.moonstone.moonstonemod.Item.Amout;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Item.MoonStoneItem.CommonItem;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ectoplasmstone extends CommonItem {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().addTransientAttributeModifiers(aaa(player,stack));
            final int bbb = Mth.nextInt(RandomSource.create(), -5, 10);
            CompoundTag tag = stack.getOrCreateTag();
            CompoundTag compoundTag = new CompoundTag();
            stack.addTagElement("abc", compoundTag);
            if (stack.hasTag()) {
                if (stack.getOrCreateTag().getInt("double_stone_moonstone_soul") == 0) {
                    tag.putInt("double_stone_moonstone_soul", bbb);
                }
            }
        }

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(aaa(player, stack));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (!stack.isEmpty()) {
            if (stack.hasTag()) {
                tooltip.add(Component.translatable("· Health："+ stack.getOrCreateTag().getInt("double_stone_moonstone_soul")).withStyle(ChatFormatting.GOLD));
            }
        }
    }
    public Multimap<Attribute, AttributeModifier> aaa(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(player.getUUID(), MoonStoneMod.MODID + "soul", stack.getOrCreateTag().getInt("double_stone_moonstone_soul"), AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
}
