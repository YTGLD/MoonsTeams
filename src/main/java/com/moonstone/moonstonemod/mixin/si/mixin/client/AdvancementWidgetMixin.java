package com.moonstone.moonstonemod.mixin.si.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.client.IAdvancementWidget;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;
@Mixin(AdvancementWidget.class)
public class AdvancementWidgetMixin implements IAdvancementWidget {
    @Shadow @Final private int x;

    @Shadow @Final private int width;

    @Shadow @Final private AdvancementTab tab;

    @Shadow @Nullable private AdvancementProgress progress;

    @Shadow @Final private List<FormattedCharSequence> description;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private int y;


    @Shadow @Final private DisplayInfo display;

    @Shadow @Final private FormattedCharSequence title;

    @Shadow @Final private List<AdvancementWidget> children;


    @Override
    public void seekingImmortals$draw(GuiGraphics p_281958_, int p_281323_, int p_283679_) {
        if (!this.display.isHidden() || this.progress != null && this.progress.isDone()) {
            float f = this.progress == null ? 0.0F : this.progress.getPercent();
            AdvancementWidgetType advancementwidgettype;
            if (f >= 1.0F) {
                advancementwidgettype = AdvancementWidgetType.OBTAINED;
            } else {
                advancementwidgettype = AdvancementWidgetType.UNOBTAINED;
            }

            p_281958_.blit(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/widgets.png"), p_281323_ + this.x + 3, p_283679_ + this.y, this.display.getFrame().getTexture(), 128 + advancementwidgettype.getIndex() * 26, 26, 26);
            p_281958_.renderFakeItem(this.display.getIcon(), p_281323_ + this.x + 8, p_283679_ + this.y + 5);
        }

        for(AdvancementWidget advancementwidget : this.children) {

            if (advancementwidget instanceof IAdvancementWidget widget){
                widget.seekingImmortals$draw(p_281958_, p_281323_, p_283679_);

            }
        }

    }

    @Override
    public void seekingImmortals$drawHover(GuiGraphics p_283068_, int p_281304_, int p_281253_, float p_281848_, int p_282097_, int p_281537_) {
        boolean flag = p_282097_ + p_281304_ + this.x + this.width + 26 >= this.tab.getScreen().width;
        String s = this.progress == null ? null : this.progress.getProgressText();
        int i = s == null ? 0 : this.minecraft.font.width(s);
        boolean flag1 = 113 - p_281253_ - this.y - 26 <= 6 + this.description.size() * 9;
        float f = this.progress == null ? 0.0F : this.progress.getPercent();
        int j = Mth.floor(f * (float)this.width);
        AdvancementWidgetType advancementwidgettype;
        AdvancementWidgetType advancementwidgettype1;
        AdvancementWidgetType advancementwidgettype2;
        if (f >= 1.0F) {
            j = this.width / 2;
            advancementwidgettype = AdvancementWidgetType.OBTAINED;
            advancementwidgettype1 = AdvancementWidgetType.OBTAINED;
            advancementwidgettype2 = AdvancementWidgetType.OBTAINED;
        } else if (j < 2) {
            j = this.width / 2;
            advancementwidgettype = AdvancementWidgetType.UNOBTAINED;
            advancementwidgettype1 = AdvancementWidgetType.UNOBTAINED;
            advancementwidgettype2 = AdvancementWidgetType.UNOBTAINED;
        } else if (j > this.width - 2) {
            j = this.width / 2;
            advancementwidgettype = AdvancementWidgetType.OBTAINED;
            advancementwidgettype1 = AdvancementWidgetType.OBTAINED;
            advancementwidgettype2 = AdvancementWidgetType.UNOBTAINED;
        } else {
            advancementwidgettype = AdvancementWidgetType.OBTAINED;
            advancementwidgettype1 = AdvancementWidgetType.UNOBTAINED;
            advancementwidgettype2 = AdvancementWidgetType.UNOBTAINED;
        }

        int k = this.width - j;
        RenderSystem.enableBlend();
        int l = p_281253_ + this.y;
        int i1;
        if (flag) {
            i1 = p_281304_ + this.x - this.width + 26 + 6;
        } else {
            i1 = p_281304_ + this.x;
        }

        int j1 = 32 + this.description.size() * 9;
        if (!this.description.isEmpty()) {
            if (flag1) {
                p_283068_.blitNineSliced(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/widgets.png"), i1, l + 26 - j1, this.width, j1, 10, 200, 26, 0, 52);
            } else {
                p_283068_.blitNineSliced(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/widgets.png"), i1, l, this.width, j1, 10, 200, 26, 0, 52);
            }
        }

        p_283068_.blit(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/widgets.png"), i1, l, 0, advancementwidgettype.getIndex() * 26, j, 26);
        p_283068_.blit(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/widgets.png"), i1 + j, l, 200 - k, advancementwidgettype1.getIndex() * 26, k, 26);
        p_283068_.blit(new ResourceLocation(SeekingImmortalsMod.MODID,"textures/gui/widgets.png"), p_281304_ + this.x + 3, p_281253_ + this.y, this.display.getFrame().getTexture(), 128 + advancementwidgettype2.getIndex() * 26, 26, 26);
        if (flag) {
            p_283068_.drawString(this.minecraft.font, this.title, i1 + 5, p_281253_ + this.y + 9, -1);
            if (s != null) {
                p_283068_.drawString(this.minecraft.font, s, p_281304_ + this.x - i, p_281253_ + this.y + 9, -1);
            }
        } else {
            p_283068_.drawString(this.minecraft.font, this.title, p_281304_ + this.x + 32, p_281253_ + this.y + 9, -1);
            if (s != null) {
                p_283068_.drawString(this.minecraft.font, s, p_281304_ + this.x + this.width - i - 5, p_281253_ + this.y + 9, -1);
            }
        }

        if (flag1) {
            for(int k1 = 0; k1 < this.description.size(); ++k1) {
                p_283068_.drawString(this.minecraft.font, this.description.get(k1), i1 + 5, l + 26 - j1 + 7 + k1 * 9, -5592406, false);
            }
        } else {
            for(int l1 = 0; l1 < this.description.size(); ++l1) {
                p_283068_.drawString(this.minecraft.font, this.description.get(l1), i1 + 5, p_281253_ + this.y + 9 + 17 + l1 * 9, -5592406, false);
            }
        }

        p_283068_.renderFakeItem(this.display.getIcon(), p_281304_ + this.x + 8, p_281253_ + this.y + 5);
    }

}
