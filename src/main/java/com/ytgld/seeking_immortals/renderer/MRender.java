package com.ytgld.seeking_immortals.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.OptionalDouble;
import java.util.function.BiFunction;

public class MRender extends RenderType {
    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    private static ShaderInstance ShaderInstance_p_blood;


    private static ShaderInstance ShaderInstance_gateway;
    private static ShaderInstance ShaderInstance_mls;
    private static ShaderInstance ShaderInstance_ging;
    private static ShaderInstance ShaderInstance_trail;
    public static ShaderInstance Shader_EYE;
    public static ShaderInstance Shader_snake;



    public static final RenderType DRAGON_RAYS = create(
            "lightning",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setCullState(NO_CULL)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(WEATHER_TARGET)
                    .createCompositeState(false)
    );
    protected static final ShaderStateShard RENDER_STATE_SHARD_p_blood = new ShaderStateShard(MRender::getShaderInstance_p_blood);

    protected static final ShaderStateShard RENDER_STATE_SHARD_Shader_EYE = new ShaderStateShard(MRender::getShaderInstance_Shader_EYE);

    protected static final ShaderStateShard RENDER_STATE_SHARD = new ShaderStateShard(MRender::getShaderInstance_gateway);
    protected static final ShaderStateShard RENDER_STATE_SHARD_MLS = new ShaderStateShard(MRender::getShaderInstance_mls);
    protected static final ShaderStateShard RENDER_STATE_SHARD_ging = new ShaderStateShard(MRender::getShaderInstance_ging);

    protected static final ShaderStateShard RENDER_STATE_SHARD_trail = new ShaderStateShard(MRender::getShaderInstance_trail);

    public static final BiFunction<ResourceLocation, Boolean, RenderType> beacon =
            Util.memoize((p_234330_, p_234331_) -> {
                CompositeState rendertype$compositestate =
                        CompositeState.builder().setShaderState(RENDERTYPE_BEACON_BEAM_SHADER)
                                .setTextureState(new TextureStateShard(p_234330_, false, false))
                                .setTransparencyState(p_234331_ ? TRANSLUCENT_TRANSPARENCY : NO_TRANSPARENCY)
                                .setWriteMaskState(p_234331_ ? COLOR_WRITE : COLOR_DEPTH_WRITE)
                                .createCompositeState(false);


                return create("beacon_beam", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 1536,
                        false, true, rendertype$compositestate);
            });
    public static final BiFunction<ResourceLocation, RenderStateShard.TransparencyStateShard, RenderType> EYES = Util.memoize(
            (p_311464_, p_311465_) -> {
                RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(p_311464_, false, false);
                return create(
                        "eyes",
                        DefaultVertexFormat.NEW_ENTITY,
                        VertexFormat.Mode.QUADS,
                        1536,
                        false,
                        true,
                        RenderType.CompositeState.builder()
                                .setShaderState(RENDERTYPE_EYES_SHADER)
                                .setTextureState(renderstateshard$texturestateshard)
                                .setTransparencyState(p_311465_)
                                .setWriteMaskState(COLOR_WRITE)
                                .createCompositeState(false)
                );
            }
    );
    public static final RenderType Bluer = create(
            "blue",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD_p_blood)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setCullState(NO_CULL)
                    .setDepthTestState(RenderStateShard.NO_DEPTH_TEST)
                    .setTextureState(MultiTextureStateShard.builder().
                            add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/blue.png"),
                                    false,
                                    false).add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/blue.png"),
                                    false, false).build()).createCompositeState(false));



    public static final RenderType Snake_render = create(
            "lines_render",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setTextureState(MultiTextureStateShard.builder().
                            add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/p_blood.png"),
                                    false,
                                    false).add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/p_blood.png"),
                                    false, false).build()).createCompositeState(false));


    public static final RenderType TRAIL =
            create("lines",
                    DefaultVertexFormat.POSITION_COLOR_NORMAL,
                    VertexFormat.Mode.LINES,
                    256,
                    false,
                    false,
                    CompositeState.builder().setShaderState(RENDER_STATE_SHARD_trail)
                            .setLineState(new LineStateShard(OptionalDouble.empty()))
                            .setLayeringState(VIEW_OFFSET_Z_LAYERING).setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                            .setOutputState(ITEM_ENTITY_TARGET).setWriteMaskState(COLOR_DEPTH_WRITE).setCullState(NO_CULL)
                            .createCompositeState(false));


    private static final RenderType EYE =
            create("eye",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_Shader_EYE)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/eye.png"),
                                            false,
                                            false).add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/eye.png"),
                                            false, false).build()).createCompositeState(false));

    private static final RenderType GATEWAY =
            create("gateway",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/necr_image.png"),
                                            false,
                                            false).add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/necr_image.png"),
                                            false, false).build()).createCompositeState(false));
    private static final RenderType GING =
            create("ging",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder().setCullState(NO_CULL)
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));

    public static final RenderType MLS =
            create("mls",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_MLS)
                            .setCullState(NO_CULL)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/mls.png"),
                                            false,
                                            false).add(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/mls.png"),
                                            false, false).build()).createCompositeState(false));

    public static RenderType ging() {
        return GING;
    }
    public static RenderType eye() {
        return EYE;
    }
    public static RenderType eye(ResourceLocation location) {
        return EYES.apply(location, ADDITIVE_TRANSPARENCY);
    }
    public static ShaderInstance getShaderInstance_Shader_EYE() {
        return Shader_EYE;
    }

    public static ShaderInstance getShaderInstance_p_blood() {
        return ShaderInstance_p_blood;
    }

    public static void setShaderInstance_p_blood(ShaderInstance shaderInstance_p_blood) {
        ShaderInstance_p_blood = shaderInstance_p_blood;
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
    public static void setShaderInstance_EYE(ShaderInstance shaderInstance_ging) {
        Shader_EYE = shaderInstance_ging;
    }
    public static void setShaderInstance_ging(ShaderInstance shaderInstance_ging) {
        ShaderInstance_ging = shaderInstance_ging;
    }
    public static void setShaderInstance_trail(ShaderInstance shaderInstance_ging) {
        ShaderInstance_trail = shaderInstance_ging;
    }

    public static void setShader_snake(ShaderInstance shader_snake) {
        Shader_snake = shader_snake;
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
