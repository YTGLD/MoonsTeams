package com.moonstone.moonstonemod.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

public class MRender extends RenderType {
    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }
    private static ShaderInstance ShaderInstance_gateway;
    private static ShaderInstance ShaderInstance_mls;
    private static ShaderInstance ShaderInstance_ging;
    private static ShaderInstance ShaderInstance_trail;

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_gateway);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_MLS = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_mls);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_ging = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_ging);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_trail = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_trail);

    public static final RenderType TRAIL =
            create("tests",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_trail)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));
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
    private static final RenderType GING =
            create("ging",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));

    private static final RenderType MLS =
            create("mls",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_MLS)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/mls.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/mls.png"),
                                            false, false).build()).createCompositeState(false));

    public static RenderType gateways() {
        return GATEWAY;
    }
    public static RenderType t() {
        return TRAIL;
    }
    public static RenderType getMls() {
        return MLS;
    }
    public static RenderType ging() {
        return GING;
    }


    public static ShaderInstance getShaderInstance_mls() {
        return ShaderInstance_mls;
    }
    public static ShaderInstance getShaderInstance_ging() {
        return ShaderInstance_ging;
    }
    public static ShaderInstance getShaderInstance_trail() {
        return ShaderInstance_trail;
    }

    public static void setShaderInstance_ging(ShaderInstance shaderInstance_ging) {
        ShaderInstance_ging = shaderInstance_ging;
    }
    public static void setShaderInstance_trail(ShaderInstance shaderInstance_ging) {
        ShaderInstance_trail = shaderInstance_ging;
    }

    public static void setShaderInstance_mls(ShaderInstance shaderInstance_mls) {
        ShaderInstance_mls = shaderInstance_mls;
    }

    public static ShaderInstance getShaderInstance_gateway() {
        return ShaderInstance_gateway;
    }

    public static void setShaderInstance_gateway(ShaderInstance instance) {
        ShaderInstance_gateway = instance;
    }
}
