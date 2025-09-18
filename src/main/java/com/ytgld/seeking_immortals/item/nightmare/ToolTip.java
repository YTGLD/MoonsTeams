package com.ytgld.seeking_immortals.item.nightmare;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

public class ToolTip implements ClientTooltipComponent, TooltipComponent {
    private final AllTip allTip;
    private final ItemStack stack;
    public ToolTip(AllTip contents, ItemStack stack) {
        this.allTip = contents;
        this.stack = stack;
    }

    @Override
    public int getHeight() {
        if (Screen.hasShiftDown()){
            return  this.backgroundHeight() + 6;
        }
        return 0;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        if (Screen.hasShiftDown()){
            return this.backgroundWidth();
        }
        return 0;
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
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics guiGraphics) {
        if (Screen.hasShiftDown()) {
            int i = this.gridSizeX();
            int j = this.gridSizeY();
            int s = 0;
            int us = 0;
            for (int l = 0; l < j; ++l) {
                for (int i1 = 0; i1 < i; ++i1) {
                    int k1 = y + l * 32;
                    s++;
                    us+=10;
                    this.renderSlot(font, x, k1, guiGraphics,s,us);
                }
            }
        }
    }
    private void renderSlot(Font font, int x, int y, GuiGraphics guiGraphics, int ss, int yOFfSize) {
        drString(font, x, y, guiGraphics, ss, yOFfSize,255,1,"深渊的赐福：");
        for (int i = 0; i < 10; i++) {
            drString(font, x, y, guiGraphics, ss, yOFfSize,100 - i*10,1 + i/25f,"");
        }
    }
    private void drString(Font font, int x, int y, GuiGraphics guiGraphics, int i, int yOFfSize,int a ,float size,String hand ){
        i--;
        int frameCounter = (int) NewEvent.time;
        Map<Integer, String> stringMap = allTip.tooltip();
        PoseStack poseStack = guiGraphics.pose();
        guiGraphics.drawString(font, hand, x, y, ARGB.color(255, 255, 50, 50), false);
        poseStack.pushPose();
        if (stringMap != null) {
            Integer integer = stringMap.keySet().stream().toList().get(i);
            String string = stringMap.get(integer);

            for (int j = 0; j < string.length(); j++) {
                char character = string.charAt(j);
                float angle = (frameCounter % 360 + j * 10) * (float) Math.PI / 180;
                float scale = size + 0.1f * (float) Math.sin(angle);
                float offsetX = (float) Math.sin(angle) * 0.65f;
                float offsetY = (float) Math.cos(angle) * 0.65f;

                poseStack.pushPose();
                poseStack.translate(x + j * font.width(String.valueOf(character)), y + yOFfSize, 0);
                poseStack.translate(offsetX, offsetY, 0);
                poseStack.scale(scale, scale, 1);
                poseStack.translate(-x - j * font.width(String.valueOf(character)), -(y + yOFfSize), 0);
                int b = j*22;
                if (b > 255) {
                    b = 255;
                }

                drawString(guiGraphics, font, String.valueOf(character),
                        x + j * font.width(String.valueOf(character)) + offsetX,
                        y + yOFfSize + offsetY,
                        ARGB.color(a, 255, b/4,b ),
                        false);
                poseStack.popPose();
            }
        }
        poseStack.popPose();
    }
    public void drawString(GuiGraphics guiGraphics, Font p_283343_, @Nullable String p_281896_, float p_283569_, float p_283418_, int p_281560_, boolean p_282130_) {
        if (p_281896_ != null) {
            p_283343_.drawInBatch(p_281896_,
                    p_283569_, p_283418_, p_281560_,
                    p_282130_, guiGraphics.pose().last().pose(),
                    guiGraphics.bufferSource(), Font.DisplayMode.NORMAL,
                    0, 15728880,
                    p_283343_.isBidirectional());
        }
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
