package com.moonstone.moonstonemod.Item.Nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.MoonStoneItem.nightmare;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmareeye extends nightmare {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(un_un_pla(player));
        }
    }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    public Multimap<Attribute, AttributeModifier> un_un_pla(Player player) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("83f9fb4e-5c3f-3b02-a19a-70f2fa258dfa");

        float s = 0.25f;
        if (Handler.hascurio(player, com.moonstone.moonstonemod.Init.Items.nightmarecharm.get())){
            s -= 0.15f;
        }
        if (Handler.hascurio(player, com.moonstone.moonstonemod.Init.Items.nightmareanchor.get())){
            s -= 0.05f;
        }
        if (Handler.hascurio(player, com.moonstone.moonstonemod.Init.Items.nightmarerotten.get())){
            float a = ForgeRegistries.ATTRIBUTES.getValues().size();
            a /= 100;
            a *= 1.1F;
            s += a;
        }


        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", -s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmareeye.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmareeye.tool.string.1").withStyle(ChatFormatting.DARK_RED));


    }
}
