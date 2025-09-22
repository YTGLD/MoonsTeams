package com.moonstone.moonstonemod.moonstoneitem;

import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BookSkill  extends Item implements ICurioItem {
    public BookSkill() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.RARE));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return CuriosApi.getCuriosInventory(slotContext.entity()).resolve().isPresent()
                && !CuriosApi.getCuriosInventory(slotContext.entity()).resolve().get().isEquipped(this);
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}

