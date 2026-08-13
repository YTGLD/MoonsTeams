package com.ytgld.moonstone.item;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.render.CIStateShardsHasBlack;
import com.ytgld.moonstone.render.MGuiGraphics;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
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
    public int getHeight() {
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

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        Set<Item> getAll = ICanHasInItem.getAll(stack);
        int imageSize= 16;
        graphics.pose().pushPose();
        for (int j = 0; j < canHasInItem.maxSize(); j++) {
            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics, ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,
                    "textures/gui/necora_back.png"), x + j * 16, y, 0, 0, imageSize, imageSize, imageSize, imageSize,0xffffffff);
        }
        if (!getAll.isEmpty()) {
            for (int j = 0; j < getAll.size(); j++) {
                graphics.renderItem(getAll.stream().toList().get(j).getDefaultInstance(), x + j * 16, y);
            }
        }
        graphics.pose().popPose();

        Set<Item> allDNA = IDNASequence.getAllDNA(stack);
        graphics.pose().pushPose();
        for (int j = 0; j < sequence.maxDNAValue(); j++) {
            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,false).blit(graphics, ResourceLocation.fromNamespaceAndPath(Moonstone.MODID,
                    "textures/gui/dna_back.png"), x + j * 16, y + 24, 0, 0, imageSize, imageSize, imageSize, imageSize,0xffffffff);
        }
        if (!allDNA.isEmpty()) {
            for (int j = 0; j < allDNA.size(); j++) {
                graphics.renderItem(allDNA.stream().toList().get(j).getDefaultInstance(), x + j * 16, y + 24);
            }
        }
        graphics.pose().popPose();
    }
}

