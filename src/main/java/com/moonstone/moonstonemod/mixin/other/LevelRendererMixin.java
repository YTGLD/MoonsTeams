package com.moonstone.moonstonemod.mixin.other;

import com.all.ILevelRender;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.PostChain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin implements ILevelRender {
    @Shadow @Nullable private PostChain transparencyChain;

    @Override
    public PostChain moonstone1_21_1$transparencyChain() {
        return transparencyChain;
    }
}
