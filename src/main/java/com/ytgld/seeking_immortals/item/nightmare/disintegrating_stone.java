package com.ytgld.seeking_immortals.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

/**
 * 此物品的属性会随着你安装模组的数量的增加而增加
 * <p>
//  来自任何模组的饰品都将无效化
 *
 */

public class disintegrating_stone extends nightmare {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        float size = ModList.get().size();
        size/=75f;
        if (size > 18) {
            size = 18;
        }
        attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"asdasd", size, AttributeModifier.Operation.MULTIPLY_BASE));
        attributeModifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid,"asdasd", size, AttributeModifier.Operation.MULTIPLY_BASE));

        return attributeModifiers;
    }


    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.disintegrating_stone.tool.string").withStyle(ChatFormatting.DARK_RED));

        pTooltipComponents.add(Component.literal(""));
        float size = ModList.get().size();
        size/=75f;
        if (size > 18) {
            size = 18;
        }

        pTooltipComponents.add(Component.translatable("effect.minecraft.health_boost").append(" : ").append(Component.literal(String.format("%.2f", size * 100)).append("%")).withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("effect.minecraft.strength").append(" : ").append(Component.literal(String.format("%.2f", size * 100))).append("%").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.disintegrating_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }

}
