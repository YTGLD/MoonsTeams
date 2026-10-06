package com.ytgld.moonstone.mixin.cilent;


import com.ytgld.moonstone.render.gui_particles.BlackParticlesRenderer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GUIMixin {

    @Inject(at = @At(value = "RETURN"),method = "render")
    private void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        guiGraphics.pose().pushPose();
        BlackParticlesRenderer.onRenderGui(guiGraphics,guiGraphics.pose());
        guiGraphics.pose().popPose();
    }
}
