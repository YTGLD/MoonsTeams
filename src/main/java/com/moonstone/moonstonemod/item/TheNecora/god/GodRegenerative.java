package com.moonstone.moonstonemod.item.TheNecora.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class GodRegenerative extends GodDNA {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(this)) {
                player.heal(1);
                player.getCooldowns().addCooldown(this, 15);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.god_regenerative.tool.string").withStyle(ChatFormatting.RED));
    }
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier>modifierMultimap = HashMultimap.create();


        modifierMultimap.put(AttReg.heal.get(), new AttributeModifier(uuid,"asd", 0.17f, AttributeModifier.Operation.MULTIPLY_BASE));

        return modifierMultimap;
    }

}
