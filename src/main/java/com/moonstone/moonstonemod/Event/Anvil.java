package com.moonstone.moonstonemod.Event;

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
            if (zuo.is(Items.ENDER_EYE) && you.is(com.moonstone.moonstonemod.Init.Items.ectoplasmprism.get())) {
                event.setCost(25);
                event.setMaterialCost(1);
                event.setOutput(com.moonstone.moonstonemod.Init.Items.nightmareeye.get().getDefaultInstance());
            }
        }
    }
}
