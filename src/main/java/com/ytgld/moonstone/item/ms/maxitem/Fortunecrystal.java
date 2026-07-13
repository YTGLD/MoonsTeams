package com.ytgld.moonstone.item.ms.maxitem;

import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Fortunecrystal extends UnCommonItem implements TextEvt.Twelve {

    public Fortunecrystal(Properties properties) {
        super(properties);
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 3;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.fortunecrystal.tool.string").withStyle(ChatFormatting.GOLD));
    }
}

