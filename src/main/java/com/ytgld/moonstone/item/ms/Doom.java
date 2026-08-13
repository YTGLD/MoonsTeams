package com.ytgld.moonstone.item.ms;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class Doom extends CommonItem {

    public Doom(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(Light.ARGB.color(255, 100, 150, 255)));
    }

    public static final String ITEMCategoryDoom = "Doom";

    public void renderBack(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        Handler.renderBack(guiGraphics, x, y, width, height,
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "tooltip/all/frame"),
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "tooltip/all/background"),

                Light.ARGB.color(255, 50, 50, 255),
                Light.ARGB.color(255, 5, 5, 25)
        );
    }
}
