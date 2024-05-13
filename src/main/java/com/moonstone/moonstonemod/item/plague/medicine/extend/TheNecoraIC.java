package com.moonstone.moonstonemod.item.plague.medicine.extend;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.item.moonstoneitem.Iplague;
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

public class TheNecoraIC extends Item  implements Iplague, ICurioItem {
    public TheNecoraIC() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("TheNecoraIC", ChatFormatting.RED)));
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
