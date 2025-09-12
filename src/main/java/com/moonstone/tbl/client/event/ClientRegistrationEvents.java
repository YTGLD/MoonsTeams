package com.moonstone.tbl.client.event;


import net.minecraftforge.eventbus.api.IEventBus;

public class ClientRegistrationEvents {
	public static void initClient(IEventBus eventbus) {
		ClientEvents.init();
		eventbus.addListener(MoonstoneBLShaders::registerShaders);
	}
}
