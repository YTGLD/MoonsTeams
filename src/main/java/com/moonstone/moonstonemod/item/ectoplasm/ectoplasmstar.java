package com.moonstone.moonstonemod.item.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class ectoplasmstar extends ectoplasm {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(att());
            slotContext.entity().getAttributes().addTransientAttributeModifiers(att2(player));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().removeAttributeModifiers(att());
            slotContext.entity().getAttributes().removeAttributeModifiers(att2(player));
        }
    }

    public Multimap<Attribute, AttributeModifier> att(){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("00000000-0000-3005-998f-50309b7cf9e8");
        modifierMultimap.put(Attributes.LUCK, new AttributeModifier(uuid, MoonStoneMod.MODID + "ectoplasmstar", 20, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
    public Multimap<Attribute, AttributeModifier> att2(Player player){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("00000000-0000-3005-998f-50309b7cf9e8");
        float s = player.getLuck();
        s /= 100;
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ectoplasmstar", s/2, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ectoplasmstar", s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.ectoplasmstar.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.ectoplasmstar.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
    }
}


