package com.moonstone.moonstonemod.mixin;

import com.all.IPostChain;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(PostChain.class)
public class PostChainMixin implements IPostChain {
    @Shadow @Final private List<PostPass> passes;

    @Override
    public List<PostPass> moonstone1_21_1$passes() {
        return passes;
    }
}
