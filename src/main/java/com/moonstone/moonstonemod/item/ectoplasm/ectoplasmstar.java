package com.moonstone.moonstonemod.item.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.nightmare_base_stone_meet;
import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
            if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.getTag() != null) {
                    stack.getTag().putBoolean(nightmare_base_stone_meet.curse,true);
                }else {
                    stack.getOrCreateTag();
                }
            }
            slotContext.entity().getAttributes().addTransientAttributeModifiers(att(player));
            slotContext.entity().getAttributes().addTransientAttributeModifiers(att2(player));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {

            if (!Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.getTag() != null) {
                    stack.getTag().putBoolean(nightmare_base_stone_meet.curse,false);
                }else {
                    stack.getOrCreateTag();
                }
            }
            slotContext.entity().getAttributes().removeAttributeModifiers(att(player));
            slotContext.entity().getAttributes().removeAttributeModifiers(att2(player));
        }
    }

    public Multimap<Attribute, AttributeModifier> att(Player player){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        int max = Config.SERVER.ectoplasmstar.get();

        int s = 20;
        if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
            s*=2;
        }
        if (s>max){
            s=max;
        }

        modifierMultimap.put(Attributes.LUCK, new AttributeModifier(UUID.fromString("00000000-0000-3005-998f-50309b7cf9e8"),"as", s, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
    public Multimap<Attribute, AttributeModifier> att2(Player player){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s = player.getLuck();
        s /= 100;
        if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
            modifierMultimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("00000000-0000-3005-998f-50309b7cf9e8"), "sss", s*2.5f, AttributeModifier.Operation.MULTIPLY_BASE));
        }

        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("00000000-0000-3005-998f-50309b7cf9e8"),"as", s/2, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("00000000-0000-3005-998f-50309b7cf9e8"),"as", s, AttributeModifier.Operation.MULTIPLY_BASE));
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


