package com.moonstone.tbl.client.event;

import com.moonstone.tbl.client.handler.ShaderHandler;
import com.moonstone.tbl.client.shader.ShaderHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterShadersEvent;

public class MoonstoneBLShaders {

	static void registerShaders(RegisterShadersEvent event) {
		ShaderHandler.loadWorldShader(Minecraft.getInstance().getResourceManager());
		ShaderHelper.INSTANCE.initShaders(Minecraft.getInstance().getResourceManager());
	}
}
