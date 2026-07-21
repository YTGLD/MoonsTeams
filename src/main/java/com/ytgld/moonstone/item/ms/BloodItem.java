package com.ytgld.moonstone.item.ms;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class BloodItem extends ItemBase {
    public BloodItem(Properties properties) {
        super(properties);
    }
    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        return component.copy().withStyle(Style.EMPTY.withColor(Light.ARGB.color(255,200,10,10)));
    }
    public static final String ITEMCategoryBloodItem = "BloodItem";

    public void renderBack(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height){
        Handler.renderBack(guiGraphics,x,y,width,height,
                Identifier.fromNamespaceAndPath(Moonstone.MODID,"tooltip/all/frame"),
                Identifier.fromNamespaceAndPath(Moonstone.MODID,"tooltip/all/background"),

                Light.ARGB.color(255,200,10,10),
                Light.ARGB.color(255,40,10,10)
        );
    }
}
