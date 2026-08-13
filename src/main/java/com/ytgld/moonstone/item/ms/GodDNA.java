package com.ytgld.moonstone.item.ms;

import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.CIStateShardsHasBlack;
import com.ytgld.moonstone.render.MGuiGraphics;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class GodDNA extends TheNecora {
    public GodDNA(Properties properties) {
        super(properties);
    }
    public static void renderItem(GuiGraphics guiGraphicsExtractor, ItemStack stack, int x, int y) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        ResourceLocation texId = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(),
                "textures/item/" + itemId.getPath() + ".png");
        if (stack.getItem() instanceof GodDNA) {
            new MGuiGraphics.GUI(CIStateShardsHasBlack::getHasBlock,true).blit(guiGraphicsExtractor, texId,
                    x - 2, y - 2, 0, 0, 20,
                    20, 20,
                    20, Light.ARGB.color(255, 255, 255, 100));
        }
    }
    @Override
    public int maxSize() {
        return 2;
    }

    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                Items.calcareous.asItem(),
                Items.frontal_lobe.asItem(),
                Items.high_energy.asItem(),
                Items.surge.asItem()
        ));
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("moonstone.jei.god_dna").withStyle(ChatFormatting.GOLD));
    }
}
