package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.item.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class magicstone extends Doom {
    public final String magic = "Magic";


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            final int damage = Mth.nextInt(RandomSource.create(), -15, 15);
            final int kok = Mth.nextInt(RandomSource.create(), -25, 25);
            final int regs = Mth.nextInt(RandomSource.create(), -5, 5);

            if (!stack.getOrCreateTag().getBoolean(magic)){
                stack.getOrCreateTag().putInt("damage", damage);
                stack.getOrCreateTag().putInt("kok", kok);
                stack.getOrCreateTag().putInt("regs", regs);

                stack.getOrCreateTag().putBoolean(magic, true);

            }
        }
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (stack.getTag()!= null) {
            tooltip.add(Component.translatable(" Damage: "+  stack.getTag().getInt("damage") + "%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(" Resistance: "+  stack.getTag().getInt("regs") + "%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(" KnockBack Resistance: "+  stack.getTag().getInt("kok") + "%").withStyle(ChatFormatting.GOLD));
        }
    }
}
