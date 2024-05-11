package com.moonstone.moonstonemod.Item.Plague.BloodVirus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Item.Plague.BloodVirus.ex.BloodViru;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class bloodgene extends BloodViru {


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers( slotContext.entity(),stack));
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.getAttributeModifiers( slotContext.entity(),stack));

    }

    public String blood = "bloodgene";

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(LivingEntity player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        float as= stack.getOrCreateTag().getFloat(blood) / 100;

        UUID uuid = UUID.fromString("0d077092-9045-3af8-b41e-a691f388e76a");
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                uuid, MoonStoneMod.MODID+"ec", (-0.5)+as, AttributeModifier.Operation.MULTIPLY_TOTAL)

        );
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                uuid, MoonStoneMod.MODID+"ec", (-0.5)+as, AttributeModifier.Operation.MULTIPLY_TOTAL)

        );

        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("基础攻击,攻速下降50%").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("但是每次受到伤害都会获得短暂的提升").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("最大可以提升至原先的100%").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("增加10%受到伤害").withStyle(ChatFormatting.RED));

        } else {
            tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));


        }
    }
}
