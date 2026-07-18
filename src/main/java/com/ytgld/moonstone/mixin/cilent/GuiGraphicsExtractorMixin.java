package com.ytgld.moonstone.mixin.cilent;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.*;
import com.ytgld.moonstone.item.si.nightmare.NightmareBase;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import org.joml.Matrix3x2fStack;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {

    @Shadow
    public abstract int guiWidth();

    @Shadow
    public abstract int guiHeight();

    @Shadow
    @Final
    private Matrix3x2fStack pose;

    @Inject(at = @At(value = "RETURN"), method = "tooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;Lnet/minecraft/resources/Identifier;Lnet/minecraft/world/item/ItemStack;)V")
    public void ytgld$ClientTooltipPositioner(Font font, List<ClientTooltipComponent> components, int x, int y, ClientTooltipPositioner positioner, Identifier background, ItemStack tooltipStack, CallbackInfo ci) {
        if (tooltipStack.getItem() instanceof ItemBase){
            RenderTooltipEvent.Pre preEvent = ClientHooks.onRenderTooltipPre(tooltipStack, (GuiGraphicsExtractor) (Object) this, x, y, this.guiWidth(), this.guiHeight(), components, font, positioner);
            if (!preEvent.isCanceled()) {
                font = preEvent.getFont();
                x = preEvent.getX();
                y = preEvent.getY();
                int i = 0;
                int j = components.size() == 1 ? -2 : 0;

                ClientTooltipComponent clienttooltipcomponent;
                for (Iterator<ClientTooltipComponent> var11 = components.iterator(); var11.hasNext(); j += clienttooltipcomponent.getHeight(font)) {
                    clienttooltipcomponent = var11.next();
                    int k = clienttooltipcomponent.getWidth(font);
                    if (k > i) {
                        i = k;
                    }
                }
                Vector2ic vector2ic = positioner.positionTooltip(this.guiWidth(), this.guiHeight(), x, y, i, j);
                int l = vector2ic.x();
                int i1 = vector2ic.y();
                if (tooltipStack.getItem() instanceof NightmareSmall || tooltipStack.getItem() instanceof NightmareBase) {
                    this.pose.pushMatrix();
                    moonstone$renderItemBlackShadowTooltipBackground((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                     moonstone$renderItemBlackShadowTooltipBackground_CHAOS((GuiGraphicsExtractor) (Object) this, l, i1, i, j, 400);
                    this.pose.popMatrix();
                }
                if (tooltipStack.getItem() instanceof Ectoplasm ectoplasm){
                    this.pose.pushMatrix();
                    ectoplasm.renderBack((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                    this.pose.popMatrix();
                }
                if (tooltipStack.getItem() instanceof MLS mls){
                    this.pose.pushMatrix();
                    mls.renderBack((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                    this.pose.popMatrix();
                }
                if (tooltipStack.getItem() instanceof BloodItem bloodItem){
                    this.pose.pushMatrix();
                    bloodItem.renderBack((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                    this.pose.popMatrix();
                }
                if (tooltipStack.getItem() instanceof TheNecora item){
                    this.pose.pushMatrix();
                    item.renderBack((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                    this.pose.popMatrix();
                }
                if (tooltipStack.getItem() instanceof Doom item){
                    this.pose.pushMatrix();
                    item.renderBack((GuiGraphicsExtractor) (Object) this, l, i1, i, j);
                    this.pose.popMatrix();
                }
            }
        }
    }

    @Unique
    public void  moonstone$renderItemBlackShadowTooltipBackground_CHAOS(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, int z) {
        // 左上角
        int topLeftX = x - 3 - 9 + 2;
        int topLeftY = y - 3 - 9;
        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(0.0F, -2);
        guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED,
                Identifier.fromNamespaceAndPath(Moonstone.MODID,
                        "textures/gui/tooltip/tool_0_0.png"), topLeftX, topLeftY, 0, 0, 48, 48, 48, 48, Light.ARGB.color(255, 170, 50, 105));
        guiGraphics.pose().popMatrix();

        //中下
        int xXX = x + (width - 48) / 2;
        int yYY = y + height + 3 - 9 + 4;
        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(0.0F, 0);
        guiGraphics.blitSprite(MRender.RenderPs.GUI_TEXTURED,
                Identifier.fromNamespaceAndPath(Moonstone.MODID,
                        "tooltip/tool_down_0"), 48, 48, 0, 0, xXX, yYY, 48, 48, Light.ARGB.color(255, 170, 50, 105));
        guiGraphics.pose().popMatrix();

        // 右上角
        int topRightX = x + width + 3 - 48 + 6;
        int topRightY = y - 3 - 9;
        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().translate(0.0F, -2);
        guiGraphics.blit(MRender.RenderPs.GUI_TEXTURED,
                Identifier.fromNamespaceAndPath(Moonstone.MODID,
                        "textures/gui/tooltip/tool_0_1.png"), topRightX, topRightY, 0, 0, 48, 48, 48, 48, Light.ARGB.color(255, 170, 50, 105));
        guiGraphics.pose().popMatrix();
    }

    @Unique
    private void moonstone$renderItemBlackShadowTooltipBackground(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height) {
        int i = x - 3 - 9;
        int j = y - 3 - 9;
        int k = width + 3 + 3 + 18;
        int l = height + 3 + 3 + 18;
        guiGraphics.blitSprite(MRender.RenderPs.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                "tooltip/frame"), i, j, k, l);
        guiGraphics.blitSprite(MRender.RenderPs.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Moonstone.MODID,
                "tooltip/background"), i, j, k, l);
    }
}
