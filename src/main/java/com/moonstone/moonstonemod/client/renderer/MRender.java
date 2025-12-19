package com.moonstone.moonstonemod.client.renderer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.OptionalDouble;
import java.util.function.Function;

import static org.lwjgl.opengl.GL11C.GL_LEQUAL;
import static org.lwjgl.opengl.GL11C.GL_LESS;

public class MRender extends RenderType {
    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }
    private static ShaderInstance ShaderInstance_gateway;
    private static ShaderInstance ShaderInstance_mls;
    private static ShaderInstance ShaderInstance_ging;
    private static ShaderInstance ShaderInstance_trail;
    public static ShaderInstance meteorTrailShader;


    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_gateway);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_MLS = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_mls);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_ging = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_ging);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_trail = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_trail);
    public static final RenderType TRAIL =
            create("lines",
                    DefaultVertexFormat.POSITION_COLOR_NORMAL,
                    VertexFormat.Mode.LINES,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder().setShaderState(RENDER_STATE_SHARD_trail)
                            .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.empty()))
                            .setLayeringState(VIEW_OFFSET_Z_LAYERING).setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                            .setOutputState(ITEM_ENTITY_TARGET).setWriteMaskState(COLOR_DEPTH_WRITE).setCullState(NO_CULL)
                            .createCompositeState(false));


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
    protected static final OutputStateShard setOutputState = new OutputStateShard("set", () -> {
        RenderTarget target = MoonPost.getRenderTargetFor(MoonStoneMod.POST);
        if (target != null) {
            target.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
            target.bindWrite(false);
        }
    }, () -> {
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    });
    public static final RenderType out =
            create("out",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD)
                            .setOutputState(setOutputState)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/necr_image_1.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/necr_image_1.png"),
                                            false, false).build()).createCompositeState(false));

    public static final RenderType out_nig =
            create("out_nig",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD)
                            .setOutputState(setOutputState)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));

    public static final RenderType out_nig_cansee =
            create("out_nig_cansee",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD)
                            .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                            .setDepthTestState(RenderStateShard.NO_DEPTH_TEST)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(new ResourceLocation(MoonStoneMod.MODID,"textures/ging.png"),
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

    public static void set_meteorTrailShader(ShaderInstance shaderInstance_ging) {
        meteorTrailShader = shaderInstance_ging;
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
    public static final RenderType Bluer = create(
            "blue",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD_MLS)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(setOutputState)
                    .setTextureState(RenderStateShard.
                            MultiTextureStateShard.builder().
                            add(new ResourceLocation (MoonStoneMod.MODID,"textures/sword.png"),
                                    false,
                                    false).add(new ResourceLocation (MoonStoneMod.MODID,"textures/sword.png"),
                                    false, false).build()).createCompositeState(false));

    public static ShaderInstance getShaderInstance_gateway() {
        return ShaderInstance_gateway;
    }
    public static void setShaderInstance_gateway(ShaderInstance instance) {
        ShaderInstance_gateway = instance;
    }


    public static final TransparencyStateShard UNIFIED_TRANSPARENCY_STATE = new TransparencyStateShard("unified_transparency", () -> {
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        RenderSystem.depthFunc(GL_LESS);
        RenderSystem.depthMask(false);

    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(GL_LEQUAL);
        RenderSystem.disableDepthTest();
    });


    public static final Function<ResourceLocation, RenderType> GUI = Util.memoize((p_286155_) -> {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_ITEM_ENTITY_TRANSLUCENT_CULL_SHADER).setTextureState(new RenderStateShard.TextureStateShard(p_286155_,
                        false, false)).setTransparencyState(UNIFIED_TRANSPARENCY_STATE).setOutputState(ITEM_ENTITY_TARGET)
                .setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .createCompositeState(true);
        return create("item_entity_translucent_cull", DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS, 256, true, true, rendertype$compositestate);
    });

}
