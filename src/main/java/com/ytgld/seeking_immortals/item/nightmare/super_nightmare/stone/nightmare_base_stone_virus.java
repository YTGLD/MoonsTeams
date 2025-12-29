package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmare_base_stone_virus extends nightmare implements SuperNightmare {

    public static void h(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone_virus.get())) {
                player.setHealth(player.getHealth() - player.getMaxHealth() * (Config.SERVER.Nightecora.get() / 100f));
            }
        }
    }

    public  Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
         Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        float v = Config.SERVER.nightmare_base_stone_virus.get();
        v/=100f;
        attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("fbec47ce-171e-4eeb-afb0-8ed4a7387030"),"a", v, AttributeModifier.Operation.MULTIPLY_BASE));
        attributeModifiers.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("fbec47ce-171e-4eeb-afb0-8ed4a7387030"),"a", v, AttributeModifier.Operation.MULTIPLY_BASE));
        attributeModifiers.put(AttReg.cit.get(), new AttributeModifier(UUID.fromString("fbec47ce-171e-4eeb-afb0-8ed4a7387030"),"a", v, AttributeModifier.Operation.MULTIPLY_BASE));

        return attributeModifiers;

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext,prevStack,stack);
        if (SIHandler.hascurio(slotContext.entity(), this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers());

    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float v = Config.SERVER.nightmare_base_stone_virus.get();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string",v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.1",v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.2",v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.3").withStyle(ChatFormatting.DARK_RED));
    }

}
