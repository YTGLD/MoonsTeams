package com.ytgld.moonstone.item.ms;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

public class MLS extends ItemBase {
    public MLS(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(Light.ARGB.color(255, 10, 255, 10)));
    }
    public static void renderBack(RenderTooltipEvent.Color event) {
        if (event.getItemStack().getItem() instanceof MLS item) {
            event.setBackground(Light.ARGB.color(255, 5, 20, 5));
            event.setBorderEnd(Light.ARGB.color(255, 50, 200, 50));
            event.setBorderStart(Light.ARGB.color(255, 50, 200, 50));
        }
    }
    public static final String ITEMCategoryMLS = "ITEMCategoryMLS";
}
