package com.ytgld.moonstone.mixin.cilent;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.*;
import com.ytgld.moonstone.item.si.nightmare.NightmareBase;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.RenderDNAItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @Shadow
    public abstract int guiWidth();

    @Shadow
    public abstract int guiHeight();

    @Shadow
    @Final
    private PoseStack pose;

    @Shadow
    private ItemStack tooltipStack;

    @Inject(at = @At(value = "HEAD"),method = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V")
    public void renderItem(LivingEntity entity, Level level, ItemStack stack, int x, int y, int seed, int guiOffset, CallbackInfo ci) {
        GuiGraphics guiGraphicsExtractor = (GuiGraphics) (Object) this;
        GodDNA.renderItem(guiGraphicsExtractor,stack,x,y);
        RenderDNAItem.renderItem(guiGraphicsExtractor,pose,stack,x,y,seed);
    }

    @Inject(at = @At(value = "RETURN"),method = "renderTooltipInternal(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;)V")
    public void moonstone1_21_1$ClientTooltipPositioner(Font p_282675_, List<ClientTooltipComponent> p_282615_, int x, int y, ClientTooltipPositioner p_282442_, CallbackInfo ci) {
        if (tooltipStack.getItem() instanceof ItemBase){
            RenderTooltipEvent.Pre preEvent = ClientHooks.onRenderTooltipPre(this.tooltipStack, (GuiGraphics) (Object) this, x, y, guiWidth(), guiHeight(), p_282615_, p_282675_, p_282442_);

            int i = 0;
            int j = p_282615_.size() == 1 ? -2 : 0;

            for (ClientTooltipComponent clienttooltipcomponent : p_282615_) {
                int k = clienttooltipcomponent.getWidth(preEvent.getFont());
                if (k > i) {
                    i = k;
                }

                j += clienttooltipcomponent.getHeight();
            }

            int i2 = i;
            int j2 = j;


            Vector2ic vector2ic = p_282442_.positionTooltip(this.guiWidth(), this.guiHeight(), preEvent.getX(), preEvent.getY(), i2, j2);

            int l = vector2ic.x();
            int i1 = vector2ic.y();
                if (tooltipStack.getItem() instanceof NightmareSmall || tooltipStack.getItem() instanceof NightmareBase) {
                    this.pose.pushPose();
                    moonstone1_21_1$renderBack((GuiGraphics) (Object) this, l, i1, i, j);
                    this.pose.popPose();
                }
                if (tooltipStack.getItem() instanceof Ectoplasm ectoplasm){
                    this.pose.pushPose();
                    ectoplasm.renderBack((GuiGraphics) (Object) this, l, i1, i, j);
                    this.pose.popPose();
                }
                if (tooltipStack.getItem() instanceof MLS mls){
                    this.pose.pushPose();
                    mls.renderBack((GuiGraphics) (Object) this, l, i1, i, j);
                    this.pose.popPose();
                }
                if (tooltipStack.getItem() instanceof BloodItem bloodItem){
                    this.pose.pushPose();
                    bloodItem.renderBack((GuiGraphics) (Object) this, l, i1, i, j);
                    this.pose.popPose();
                }
                if (tooltipStack.getItem() instanceof TheNecora item){
                    this.pose.pushPose();
                    item.renderBack((GuiGraphics) (Object) this, l, i1, i, j);
                    this.pose.popPose();
                }
                if (tooltipStack.getItem() instanceof Doom item){
                    this.pose.pushPose();
                    item.renderBack((GuiGraphics) (Object) this, l, i1, i, j);
                    this.pose.popPose();
                }
            }
        }

    @Unique
    public void moonstone1_21_1$renderBack(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        Handler.renderBack(guiGraphics, x, y, width, height,
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "tooltip/all/frame"),
                ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "tooltip/all/background"),

                Light.ARGB.color(255, 255, 0, 0),
                Light.ARGB.color(255, 25, 0, 0)
        );
    }
}
