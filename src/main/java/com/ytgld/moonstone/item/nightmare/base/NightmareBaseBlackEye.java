package com.ytgld.moonstone.item.nightmare.base;


import com.ytgld.moonstone.item.nightmare.AllTip;
import com.ytgld.moonstone.item.nightmare.NightmareBase;
import com.ytgld.moonstone.item.nightmare.ToolTip;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class NightmareBaseBlackEye extends NightmareBase implements AllTip {
    public NightmareBaseBlackEye(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"带上它 你将免疫黑暗");
        map.put(2,"带上它 你将免疫失明");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"带上它 你将免疫黑暗");
        map.put(2,"带上它 你将免疫失明");
        return map;
    }



    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_black_eye_eye").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_black_eye_red").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_black_eye_heart").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.tricky_puppets").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));

        builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
}
