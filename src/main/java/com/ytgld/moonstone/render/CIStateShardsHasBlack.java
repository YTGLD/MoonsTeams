package com.ytgld.moonstone.render;

import net.minecraft.client.renderer.ShaderInstance;

public class CIStateShardsHasBlack {
    public static ShaderInstance hasBlock;
    public static ShaderInstance getHasBlock() {
        return hasBlock;
    }
    public static void setHasBlock(ShaderInstance hasBlock) {
        CIStateShardsHasBlack.hasBlock = hasBlock;
    }
}
