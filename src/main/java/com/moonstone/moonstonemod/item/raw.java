package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class raw extends Item implements ICurioItem  {

    public raw() {
        super(new Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        pTooltipComponents.add(Component.translatable("item.raw.tool.string").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();

        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid,
                MoonStoneMod.MODID+":raw", 5, AttributeModifier.Operation.MULTIPLY_TOTAL));

        linkedHashMultimap.put(AttReg.heal.get(), new AttributeModifier(uuid,
                MoonStoneMod.MODID+":raw", 5, AttributeModifier.Operation.MULTIPLY_TOTAL));

        linkedHashMultimap.put(AttReg.all_attack.get(), new AttributeModifier(uuid,
                MoonStoneMod.MODID+":raw", 5, AttributeModifier.Operation.MULTIPLY_TOTAL));

        return linkedHashMultimap;
    }


}
