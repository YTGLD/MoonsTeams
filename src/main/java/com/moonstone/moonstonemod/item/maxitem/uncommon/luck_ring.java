package com.moonstone.moonstonemod.item.maxitem.uncommon;

import com.all.IGreedyItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class luck_ring extends CommonItem implements Die, IGreedyItem {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (slotContext.entity() instanceof Player living) {
            int s = living.experienceLevel / 5;
            if (s > 100) {
                s = 100;
            }
            multimap.put(Attributes.LUCK, new AttributeModifier(uuid, "a",
                                s,
                                AttributeModifier.Operation.ADDITION));

        }
        return multimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.luck_ring.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.luck_ring.tool.string.2").withStyle(ChatFormatting.GOLD));
    }

}


