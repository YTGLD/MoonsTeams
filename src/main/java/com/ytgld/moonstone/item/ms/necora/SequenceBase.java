package com.ytgld.moonstone.item.ms.necora;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SequenceBase extends DNASequence {
    public SequenceBase(Properties properties) {
        super(properties);
    }
    @Override
    public  Component getName(ItemStack itemStack) {
        var name = super.getName(itemStack);
        MutableComponent copy= name.copy();
        copy.setStyle(Style.EMPTY.withColor(0xffff0000));
        return Component.translatable("moonstone.sequence_base").withStyle(Style.EMPTY.withColor(-3308225))
                .append(Component.literal("<").withStyle(ChatFormatting.DARK_RED))
                .append(copy)
                .append(Component.literal(">").withStyle(ChatFormatting.DARK_RED));
    }
    public void addText(List<Component> list, MutableComponent component, boolean positive){
        ChatFormatting chatFormatting = ChatFormatting.RED;
        String at = "moonstone.base.todo";
        if (!positive) {
            chatFormatting = ChatFormatting.DARK_RED;
            at = "moonstone.base.todo.not";
        }
        list.add(Component.translatable(at).withStyle(chatFormatting).append(component.withStyle(chatFormatting)));
    }
}
