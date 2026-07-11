package com.ytgld.moonstone.item.nightmare;

import com.ytgld.moonstone.event.NewEvent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;

import java.util.Map;

public class ToolTip implements ClientTooltipComponent, TooltipComponent {
    private final AllTip allTip;
    private final ItemStack stack;
    public ToolTip(AllTip contents, ItemStack stack) {
        this.allTip = contents;
        this.stack = stack;
    }

    @Override
    public int getHeight(Font font) {
        return  this.backgroundHeight() + 6;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return this.backgroundWidth();
    }

    private int backgroundWidth() {
        return this.gridSizeX() * 64;
    }

    private int backgroundHeight() {
        Map<Integer, String> map = allTip.element(stack);
        if (map!=null) {
            return map.size() * 16;
        }else {
            return 32;

        }
    }

    public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        int i = this.gridSizeX();
        int j = this.gridSizeY();
        int s = 0;
        int us = 0;
        for (int l = 0; l < j; ++l) {
            for (int i1 = 0; i1 < i; ++i1) {
                int k1 = y + l * 32;
                s++;
                us+=10;
                this.renderSlot(font, x, k1, graphics,s,us);
            }
        }
    }
    private void renderSlot(Font font, int x, int y, GuiGraphicsExtractor guiGraphics, int ss, int yOFfSize) {
        drString(font, x, y, guiGraphics, ss, yOFfSize,255,1,"深渊的赐福：");
        for (int i = 0; i < 10; i++) {
            drString(font, x, y, guiGraphics, ss, yOFfSize,100 - i*10,1 + i/25f,"");
        }
    }
    private void drString(Font font, int x, int y, GuiGraphicsExtractor guiGraphics, int i, int yOFfSize,int a ,float size,String hand ){
        i--;
        int frameCounter = (int) NewEvent.time;
        Map<Integer, String> stringMap = allTip.tooltip();
        Matrix3x2fStack poseStack = guiGraphics.pose();
        guiGraphics.text(font, hand, x, y, ARGB.color(255, 255, 50, 50), false);
        poseStack.pushMatrix();
        if (stringMap != null) {
            Integer integer = stringMap.keySet().stream().toList().get(i);
            String string = stringMap.get(integer);

            for (int j = 0; j < string.length(); j++) {
                char character = string.charAt(j);
                float angle = (frameCounter % 360 + j * 10) * (float) Math.PI / 180;
                float scale = size + 0.1f * (float) Math.sin(angle);
                float offsetX = (float) Math.sin(angle) * 0.65f;
                float offsetY = (float) Math.cos(angle) * 0.65f;

                poseStack.pushMatrix();
                poseStack.translate(x + j * font.width(String.valueOf(character)), y + yOFfSize);
                poseStack.translate(offsetX, offsetY);
                poseStack.scale(scale, scale);
                poseStack.translate(-x - j * font.width(String.valueOf(character)), -(y + yOFfSize));
                int b = j*22;
                if (b > 255) {
                    b = 255;
                }

                guiGraphics.text(font, String.valueOf(character),
                        (int) (x + j * font.width(String.valueOf(character)) + offsetX),
                        (int) (y + yOFfSize + offsetY),
                        ARGB.color(a, 255, b/4,b ),
                        false);
                poseStack.popMatrix();
            }
        }
        poseStack.popMatrix();
    }

    private int gridSizeX() {
        Map<Integer, String> map = allTip.element(stack);
        if (map!=null) {
            return map.size();
        }else return 0;
    }
    public static class ARGB {
        public static int color(int alpha, int red, int green, int blue) {
            return (alpha << 24) | (red << 16) | (green << 8) | blue;
        }
    }
    private int gridSizeY() {
        return 1;
    }
}
