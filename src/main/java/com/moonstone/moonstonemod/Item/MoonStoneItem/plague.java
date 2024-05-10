package com.moonstone.moonstonemod.Item.MoonStoneItem;

import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class plague extends Item implements ICurioItem, Iplague {
    public plague() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("plague", ChatFormatting.RED)));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return !Handler.hascurio(player, stack.getItem());
        }
        return true;
    }
}

