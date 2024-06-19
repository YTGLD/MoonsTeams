package com.moonstone.moonstonemod.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;

public class MRender extends RenderType {
    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }
    private static ShaderInstance ShaderInstance_gateway;
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_gateway);

    private static final RenderType GATEWAY =
            create("gateway",
            DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/necr_image.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/necr_image.png"),
                                            false, false).build()).createCompositeState(false));


    public static RenderType gateways() {
        return GATEWAY;
    }

    public static ShaderInstance getShaderInstance_gateway() {
        return ShaderInstance_gateway;
    }

    public static void setShaderInstance_gateway(ShaderInstance instance) {
        ShaderInstance_gateway = instance;
    }
}
