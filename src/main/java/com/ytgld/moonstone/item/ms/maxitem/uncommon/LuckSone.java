package com.ytgld.moonstone.item.ms.maxitem.uncommon;

import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class LuckSone extends CommonItem  {

    public LuckSone(Properties properties) {
        super(properties);
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, @org.jspecify.annotations.Nullable LootContext lootContext, ItemStack stack) {
        int s = 0;
        if (slotContext.entity() instanceof Player player){
            s = (int) player.getLuck()/10;
        }
        return s;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        int s = 0;
        if (slotContext.entity() instanceof Player player){
            s = (int) player.getLuck()/10;
        }
        return s;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.luck_stone.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.luck_stone.tool.string.2").withStyle(ChatFormatting.GOLD));
    }
}
