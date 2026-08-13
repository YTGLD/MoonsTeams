package com.ytgld.moonstone.item.ms.necora.dna;

import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Ambush extends TheNecora implements CanUPLevel {
    public Ambush(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide()) {
                player.addTag("canStandOnFluidTrue");
            }
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide()) {
                player.removeTag("canStandOnFluidTrue");
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.ambush.tool.string").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
    }

    @Override
    public Item upLevelItem() {
        return Items.GodAmbush.asItem();
    }
}

