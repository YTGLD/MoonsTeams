package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.event.NewEvent;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ToolTipImageFormStack implements ClientTooltipComponent, TooltipComponent {
    private final ICanHasInItem canHasInItem;
    private final ItemStack stack;
    public ToolTipImageFormStack(ICanHasInItem canHasInItem, ItemStack stack) {
        this.canHasInItem = canHasInItem;
        this.stack = stack;
    }

    @Override
    public int getHeight(Font font) {
        return 24;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return this.backgroundWidth();
    }

    private int backgroundWidth() {
        return canHasInItem.maxSize() * 16;
    }

    public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        Set<Item> getAll = ICanHasInItem.getAll(stack);
        int imageSize= 16;
        graphics.pose().pushMatrix();
        for (int j = 0; j < canHasInItem.maxSize(); j++) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                    "textures/gui/necora_back.png"), x + j * 16, y, 0, 0, imageSize, imageSize, imageSize, imageSize);
        }
        if (!getAll.isEmpty()) {
            for (int j = 0; j < getAll.size(); j++) {
                graphics.item(getAll.stream().toList().get(j).getDefaultInstance(), x + j * 16, y);
            }
        }
        graphics.pose().popMatrix();
    }
}

