package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.item.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmarerotten extends nightmare {

    public static final String nightmarerotten = "NightmareRotten";


    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        Multimap<Attribute, AttributeModifier> get = HashMultimap.create();
        double as = 0.33;

        for (Attribute attribute : ForgeRegistries.ATTRIBUTES){

            get.put(attribute, new AttributeModifier(UUID.fromString("1dd34f6b-f553-3906-92e2-e13f78ae2b51"), MoonStoneMod.MODID +":nightmarerotten", as, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return get;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            int s =  ForgeRegistries.ATTRIBUTES.getValues().size();
            stack.getOrCreateTag().putInt(nightmarerotten, s);
            player.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers());
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(this.getAttributeModifiers());
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmarerotten.tool.string").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.nightmarerotten.tool.string.1").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.nightmarerotten.tool.string.2").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("·now："+((float)(stack.getOrCreateTag().getInt(nightmarerotten)*1.1))+"%").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmarerotten.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmarerotten.tool.string.4").withStyle(ChatFormatting.DARK_RED));

    }
}
