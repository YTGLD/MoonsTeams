package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
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

public class ToolTipDNAItem implements ClientTooltipComponent, TooltipComponent {
    private final ICanHasInItem canHasInItem;
    private final IDNASequence sequence;
    private final ItemStack stack;
    public ToolTipDNAItem(ICanHasInItem canHasInItem, IDNASequence sequence, ItemStack stack) {
        this.canHasInItem = canHasInItem;
        this.sequence = sequence;
        this.stack = stack;
    }

    @Override
    public int getHeight(Font font) {
        int a = 0;
        if (canHasInItem.maxSize() > 0) {
            a += 24;
        }
        if (sequence.maxDNAValue() > 0) {
            a += 24;
        }
        return a;
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

        Set<Item> allDNA = IDNASequence.getAllDNA(stack);
        graphics.pose().pushMatrix();
        for (int j = 0; j < sequence.maxDNAValue(); j++) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                    "textures/gui/dna_back.png"), x + j * 16, y + 24, 0, 0, imageSize, imageSize, imageSize, imageSize);
        }
        if (!allDNA.isEmpty()) {
            for (int j = 0; j < allDNA.size(); j++) {
                graphics.item(allDNA.stream().toList().get(j).getDefaultInstance(), x + j * 16, y + 24);
            }
        }
        graphics.pose().popMatrix();
    }
}

