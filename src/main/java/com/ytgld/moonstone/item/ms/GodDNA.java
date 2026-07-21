package com.ytgld.moonstone.item.ms;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Consumer;

public abstract class GodDNA extends TheNecora {
    public GodDNA(Properties properties) {
        super(properties);
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("moonstone.jei.god_dna").withStyle(ChatFormatting.GOLD));
    }
}
