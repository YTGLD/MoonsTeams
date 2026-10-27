package com.ytgld.moonstone.item.ms.necora;

import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EmbeddedDNA extends TheNecora {
    public EmbeddedDNA(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        String string = BuiltInRegistries.ITEM.getKey(this).getPath();
        pTooltipComponents.add(Component.translatable("moonstone."+string+".modify").withStyle(ChatFormatting.RED));
    }
}
