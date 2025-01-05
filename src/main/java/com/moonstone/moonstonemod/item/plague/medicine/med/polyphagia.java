package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class polyphagia extends medIC {
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_41452_) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (Handler.hascurio(p_41433_, Items.medicinebox.get())) {
            p_41433_.startUsingItem(p_41434_);
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
        p_41409_.shrink(1);
        return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.polyphagia.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.polyphagia.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
    }

}

