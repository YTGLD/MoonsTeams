package com.moonstone.moonstonemod.mixin.si.mixin;


import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {

    @Shadow
    @Final
    private Container enchantSlots;
    @Shadow @Final private ContainerLevelAccess access;

    @Shadow @Final private DataSlot enchantmentSeed;

    @Shadow public abstract void slotsChanged(Container p_39461_);


    @Shadow protected abstract List<EnchantmentInstance> getEnchantmentList(ItemStack p_39472_, int p_39473_, int p_39474_);

    @Inject(at = @At("HEAD"), method = "clickMenuButton")
    public void moonstone$clickMenuButton(Player player, int p_39466_, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemstack = enchantSlots.getItem(0);
        if (SIHandler.hascurio(player, Items.nightmare_base_insight.get())) {
            EnchantmentMenu container = (EnchantmentMenu) (Object) this;
            access.execute((level, pos) -> {
                List<EnchantmentInstance> rolledEnchantments = getEnchantmentList(itemstack, p_39466_, container.costs[p_39466_]);
                player.onEnchantmentPerformed(itemstack, p_39466_ - Config.SERVER.nightmare_base_insight.get());
                for (EnchantmentInstance data : rolledEnchantments) {
                    itemstack.enchant(data.enchantment, data.level - Config.SERVER.nightmare_base_insight.get());
                    enchantSlots.setChanged();
                    enchantmentSeed.set(player.getEnchantmentSeed());
                    slotsChanged(enchantSlots);
                }
            });
        }
    }
}
