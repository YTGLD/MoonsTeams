package com.ytgld.moonstone.item.ms.maulice;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

public class MBottle extends MLS {

    public MBottle(Properties properties) {
        super(properties);
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("charm"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                1, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("charm").build())
        ), true);
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, @Nullable LootContext lootContext, ItemStack stack) {
        if (slotContext.entity().hasEffect(MobEffects.WEAKNESS)) {
            return 2;
        }
        return 0;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        if (slotContext.entity().hasEffect(MobEffects.MINING_FATIGUE)) {
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

