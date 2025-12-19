package com.moonstone.moonstonemod.item.nanodoom;

import com.all.IBlueItem;
import com.all.IGreedyItem;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class doomswoud extends Doom implements IBlueItem {
    public static String canFlySword = "canFlySword";
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.isEmpty()) {
                if (me.getTag() == null) {
                    me.getOrCreateTag();
                }
                CompoundTag tag = me.getTag();
                boolean canFlySword = tag.getBoolean(doomswoud.canFlySword); // 假设"canFlySword"是一个字符串常量
                tag.putBoolean(doomswoud.canFlySword, !canFlySword);
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.doomswoud.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.doomswoud.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.doomswoud.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.moonstone.tool.string.sword").withStyle(ChatFormatting.GOLD));
        if (stack.getTag()!=null){
            if (!stack.getTag().getBoolean(canFlySword)){
                tooltip.add(Component.translatable("item.moonstone.tooltips.off").withStyle(ChatFormatting.GOLD));
            }else {
                tooltip.add(Component.translatable("item.moonstone.tooltips.on").withStyle(ChatFormatting.GOLD));
            }
        }else {
            tooltip.add(Component.translatable("item.moonstone.tooltips.off").withStyle(ChatFormatting.GOLD));
        }
    }
}
