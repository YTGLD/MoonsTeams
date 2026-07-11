package com.ytgld.moonstone;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Consumer;

public class ItemBase extends Item implements ICurioItem {
    public ItemBase(Properties properties) {
        super(properties);
    }

    public void text(ItemStack itemStack,Consumer<Component> builder,TooltipFlag tooltipFlag){

    }
    @Override
    public final void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        text(itemStack, builder, tooltipFlag);
    }
}
