package com.ytgld.moonstone.mixin.cilent.guiparticles;


import com.ytgld.moonstone.render.gui_particles.BlackParticlesRenderer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class ScreenMixin {
    @Shadow
    @Final
    private Minecraft minecraft;
    @Shadow
    @Final
    private GuiRenderState guiRenderState;
    @Unique
    private RandomSource true26_2_moonstone$source = RandomSource.create();
    @Inject(at = @At(value = "RETURN"),method = "extractRenderState")
    private void extractRenderState(DeltaTracker deltaTracker, boolean shouldRenderLevel, boolean resourcesLoaded, CallbackInfo ci) {
        int xMouse = (int)this.minecraft.mouseHandler.getScaledXPos(this.minecraft.getWindow());
        int yMouse = (int)this.minecraft.mouseHandler.getScaledYPos(this.minecraft.getWindow());
        GuiGraphicsExtractor graphics = new GuiGraphicsExtractor(this.minecraft, this.guiRenderState, xMouse, yMouse);
        BlackParticlesRenderer.onRenderGui(graphics,graphics.pose());
    }
}
