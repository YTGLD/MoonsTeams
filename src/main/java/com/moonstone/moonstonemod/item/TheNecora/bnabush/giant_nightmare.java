package com.moonstone.moonstonemod.item.TheNecora.bnabush;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class giant_nightmare extends TheNecoraIC {
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.cell.get())){
                return false;
            }
            return !Handler.hascurio(player, stack.getItem());

        }
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.giant_nightmare.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.giant_nightmare.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}

