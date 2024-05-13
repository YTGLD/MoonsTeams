package com.moonstone.moonstonemod.item.moonstoneitem;

import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class nightmare extends Item implements ICurioItem ,INightmare{
    public nightmare() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("nightmare", ChatFormatting.RED)));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return !Handler.hascurio(player, stack.getItem());
        }
        return true;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}

