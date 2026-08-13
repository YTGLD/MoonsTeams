package com.ytgld.moonstone.event.key;

import com.ytgld.moonstone.event.itemevent.ZombieHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClientEvent {
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post evt) {
        if (Keys.KEY_MAPPING_LAZY_R.isDown()) {
            PacketDistributor.sendToServer(new UseCuriosHandler.UseChestCurios());
        }
        if (Keys.ZombieC.isDown()) {
            PacketDistributor.sendToServer(new ZombieHandler.UseChestCurios());
        }
    }

}
