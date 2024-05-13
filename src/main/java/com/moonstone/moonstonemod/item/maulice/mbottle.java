package com.moonstone.moonstonemod.item.maulice;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.item.moonstoneitem.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class mbottle extends MLS {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

        CuriosApi
                .addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        if (slotContext.entity().hasEffect(MobEffects.WEAKNESS)){
            return 2;
        }
        return 0;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        if (slotContext.entity().hasEffect(MobEffects.DIG_SLOWDOWN)){
            return 2;
        }
        return 0;

    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.mbottle.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mbottle.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mbottle.tool.string.2").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mbottle.tool.string.3").withStyle(ChatFormatting.DARK_GREEN));

    }

}

