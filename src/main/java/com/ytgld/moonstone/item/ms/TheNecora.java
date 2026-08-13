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

public class TheNecora extends ItemBase {
    public TheNecora(Properties properties) {
        super(properties);
    }


    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(Light.ARGB.color(255, 200, 10, 10)));
    }

    public static final String ITEMCategoryTheNecora = "TheNecora";
    public static void renderBack(RenderTooltipEvent.Color event) {
        if (event.getItemStack().getItem() instanceof TheNecora item) {
            event.setBackground(Light.ARGB.color(255, 25, 0, 0));
            event.setBorderEnd(Light.ARGB.color(255, 255, 5, 5));
            event.setBorderStart(Light.ARGB.color(255, 255, 5, 5));
        }
    }
}

