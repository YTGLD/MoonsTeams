package com.moonstone.moonstonemod.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Anvil {
    @SubscribeEvent
    public void AnvilUpdateEvent(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty() && !you.isEmpty()) {
            if (zuo.is(Items.ENDER_EYE) && you.is(com.moonstone.moonstonemod.init.Items.ectoplasmprism.get())) {
                event.setCost(25);
                event.setMaterialCost(1);
                event.setOutput(com.moonstone.moonstonemod.init.Items.nightmareeye.get().getDefaultInstance());
            }
        }
    }
    @SubscribeEvent
    public void medicinebox(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty()) {
            if (you != null) {

                if (zuo.is(Items.CHEST) && you.is(com.moonstone.moonstonemod.init.Items.ectoplasmcube.get())) {
                    event.setCost(1);
                    event.setMaterialCost(1);
                    event.setOutput(com.moonstone.moonstonemod.init.Items.medicinebox.get().asItem().getDefaultInstance());
                }
            }
        }
    }
    @SubscribeEvent
    public void nightmareeye(AnvilUpdateEvent event) {
        ItemStack you = event.getRight();
        ItemStack zuo = event.getLeft().copy();
        if (!zuo.isEmpty()) {
            if (you != null) {
                if (zuo.is(Items.ENDER_EYE) && you.is(com.moonstone.moonstonemod.init.Items.ectoplasmcube.get())) {
                    event.setCost(1);
                    event.setMaterialCost(1);
                    event.setOutput(com.moonstone.moonstonemod.init.Items.medicinebox.get().asItem().getDefaultInstance());
                }
            }
        }
    }
}
