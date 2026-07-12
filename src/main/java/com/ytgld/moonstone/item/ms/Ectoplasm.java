package com.ytgld.moonstone.item.ms;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.Light;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;

public class Ectoplasm extends CommonItem {
    public Ectoplasm(Properties properties) {
        super(properties.food(new FoodProperties(4, 1, true)));
    }

    public static final String ITEMCategoryEctoplasm = "Ectoplasm";

    public void renderBack(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height){
        Handler.renderBack(guiGraphics,x,y,width,height,
                Identifier.fromNamespaceAndPath(Moonstone.MODID,"tooltip/all/frame"),
                Identifier.fromNamespaceAndPath(Moonstone.MODID,"tooltip/all/background"),

                Light.ARGB.color(255,100,150,255),
                Light.ARGB.color(255,19,24,25)
        );
    }
}
