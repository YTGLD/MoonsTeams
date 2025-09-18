package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmare_base_reversal_mysterious extends nightmare implements SuperNightmare {

    public  Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
         Multimap<Attribute, AttributeModifier> get = HashMultimap.create();
        double as = -0.1f;

        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            get.put(attribute.get(), new AttributeModifier(UUID.fromString("e8ecafcf-b8b6-425a-b3fa-31171a122b48"),"s", as, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return get;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            CompoundTag tag = stack.getTag();
            if (tag != null) {
                if (Handler.hascurio(slotContext.entity(), this)) {
                    player.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers());
                }
            } else {
                stack.getOrCreateTag();
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {

            player.getAttributes().removeAttributeModifiers(this.getAttributeModifiers());
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal_mysterious.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}

