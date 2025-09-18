package com.moonstone.moonstonemod.mixin.si.mixin.client;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.client.IAdvancementWidget;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AdvancementTab.class)
public abstract class ATabMixin {

    @Shadow @Final private AdvancementWidget root;

    @Shadow @Final private ItemStack icon;

    @Shadow private boolean centered;

    @Shadow private double scrollX;

    @Shadow private double scrollY;

    @Shadow private int maxX;

    @Shadow private int minX;

    @Shadow private int minY;

    @Shadow private int maxY;

    @Shadow @Final private DisplayInfo display;

    @Shadow private float fade;

    @Shadow @Final private int index;

    @Shadow @Final private Advancement advancement;

    @Shadow @Final private Map<Advancement, AdvancementWidget> widgets;

    @Inject(at = @At("RETURN"), method = "drawContents(Lnet/minecraft/client/gui/GuiGraphics;II)V")
    public void drawContents(GuiGraphics p_282728_, int p_282962_, int p_281511_, CallbackInfo ci){
        if (this.advancement.getRoot().getId().equals(new ResourceLocation(SeekingImmortalsMod.MODID,"seeking_immortals/root"))){
            if (!this.centered) {
                this.scrollX = (double)(117 - (this.maxX + this.minX) / 2);
                this.scrollY = (double)(56 - (this.maxY + this.minY) / 2);
                this.centered = true;
            }

            p_282728_.enableScissor(p_282962_, p_281511_, p_282962_ + 234, p_281511_ + 113);
            p_282728_.pose().pushPose();
            p_282728_.pose().translate((float)p_282962_, (float)p_281511_, 0.0F);
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);


            this.root.drawConnectivity(p_282728_, i, j, true);
            this.root.drawConnectivity(p_282728_, i, j, false);
            if (root instanceof IAdvancementWidget widget) {
                widget.seekingImmortals$draw(p_282728_, i, j);
            }
            p_282728_.pose().popPose();
            p_282728_.disableScissor();
        }

    }

    @Inject(at = @At("RETURN"), method = "drawTooltips")
    public void drawTooltips(GuiGraphics p_282892_, int p_283658_, int p_282602_, int p_282652_, int p_283595_, CallbackInfo ci) {
        if (this.advancement.getRoot().getId().equals(new ResourceLocation(SeekingImmortalsMod.MODID,"seeking_immortals/root"))){
            p_282892_.pose().pushPose();
            p_282892_.pose().translate(0.0F, 0.0F, -200.0F);
            p_282892_.fill(0, 0, 234, 113, Mth.floor(this.fade * 255.0F) << 24);
            boolean flag = false;
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            if (p_283658_ > 0 && p_283658_ < 234 && p_282602_ > 0 && p_282602_ < 113) {
                for(AdvancementWidget advancementwidget : this.widgets.values()) {
                    if (advancementwidget.isMouseOver(i, j, p_283658_, p_282602_)) {
                        if (advancementwidget instanceof IAdvancementWidget advancementWidget){
                            flag = true;
                            advancementWidget.seekingImmortals$drawHover(p_282892_, i, j, this.fade, p_282652_, p_283595_);
                            break;
                        }

                    }
                }
            }

            p_282892_.pose().popPose();
            if (flag) {
                this.fade = Mth.clamp(this.fade + 0.02F, 0.0F, 0.3F);
            } else {
                this.fade = Mth.clamp(this.fade - 0.04F, 0.0F, 1.0F);
            }

        }
    }
}
