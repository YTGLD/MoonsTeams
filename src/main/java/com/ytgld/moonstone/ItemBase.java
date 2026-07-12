package com.ytgld.moonstone;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class ItemBase extends Item implements ICurioItem {
    public ItemBase(Properties properties) {
        super(properties);
    }

    public void text(ItemStack itemStack,Consumer<Component> builder,TooltipFlag tooltipFlag){

    }

    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {

    }
    @Override
    public final void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        text(itemStack, builder, tooltipFlag);
        List<Component> components = new ArrayList<>();
        appendHoverText(itemStack,null,components,tooltipFlag);
        for (Component component : components){
            builder.accept(component);
        }
    }
}
