package com.ytgld.moonstone.mixin.cilent;

import com.ytgld.moonstone.render.MoonstoneSettingsUniform;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Unique
    private final MoonstoneSettingsUniform true26_2_moonstone$moonstoneSettingsUniform = new MoonstoneSettingsUniform();
    @Inject(at = @At(value = "RETURN"), method = "render")
    public void render(DeltaTracker deltaTracker, boolean advanceGameTime, CallbackInfo ci) {
        true26_2_moonstone$moonstoneSettingsUniform.update();;
    }
    @Inject(at = @At(value = "RETURN"), method = "close")
    public void close(CallbackInfo ci) {
        true26_2_moonstone$moonstoneSettingsUniform.close();;
    }
}

