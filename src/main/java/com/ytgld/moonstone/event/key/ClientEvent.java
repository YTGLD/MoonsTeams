package com.ytgld.moonstone.event.key;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class ClientEvent {
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post evt) {
        if (Keys.KEY_MAPPING_LAZY_R.isDown()) {
            ClientPacketDistributor.sendToServer(new UseChestCurios(ItemStack.EMPTY));
        }
    }

}
