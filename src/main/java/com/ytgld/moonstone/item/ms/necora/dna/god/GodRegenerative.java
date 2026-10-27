package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.GodDNA;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class GodRegenerative extends GodDNA {
    public GodRegenerative(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(this.getDefaultInstance())) {
                player.heal(1);
                player.getCooldowns().addCooldown(this.getDefaultInstance(), 15);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.god_regenerative.tool.string").withStyle(ChatFormatting.RED));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {

        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();


        modifierMultimap.put(com.ytgld.moonstone.other.AttReg.heal, new AttributeModifier(identifier(), 0.17F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
}
