package com.ytgld.seeking_immortals.client;

import net.minecraft.client.gui.GuiGraphics;

public interface IAdvancementWidget {
    void seekingImmortals$draw(GuiGraphics guiGraphics, int x, int y);

    void seekingImmortals$drawHover(GuiGraphics guiGraphics, int x, int y, float fade, int width, int height);
}
