package com.moonstone.tbl.client.event;

import com.moonstone.tbl.client.handler.ShaderHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientEvents {

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(ShaderHandler::onRenderWeather);
	}


}
