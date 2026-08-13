package com.ytgld.moonstone.item.ms.necora.dnabush.small;

import com.ytgld.moonstone.item.ms.necora.TheNecoraDNABush;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class cell_mummy extends TheNecoraDNABush {
    public static final String Mummy = " CellMummy";

    public cell_mummy(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.cell_mummy.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}
