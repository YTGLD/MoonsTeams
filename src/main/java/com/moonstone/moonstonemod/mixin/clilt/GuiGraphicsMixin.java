package com.moonstone.moonstonemod.mixin.clilt;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.item.buyme.wind_and_rain;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {


    /*

    @Shadow @Final private PoseStack pose;

    @Shadow public abstract int guiWidth();

    @Shadow public abstract int guiHeight();

    @Shadow private ItemStack tooltipStack;

    @Inject(at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;drawManaged(Ljava/lang/Runnable;)V"),method = "renderTooltipInternal(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;)V")
    public void moonstone$GuiGraphicsMixin(Font p_282675_, List<ClientTooltipComponent> p_282615_, int p_283230_, int p_283417_, ClientTooltipPositioner p_282442_, CallbackInfo ci) {

        GuiGraphics guiGraphics = (GuiGraphics)(Object) this;

        if (tooltipStack.getTag()!= null){
            if (tooltipStack.getTag().getBoolean(wind_and_rain.wind)){
                p_283230_+=6;
                guiGraphics.blit(new ResourceLocation(MoonStoneMod.MODID, "textures/item/wind_and_rain.png"),p_283230_ - 16,p_283417_-24, 0, 0, 16, 16, 16,16);

            }
        }


    }
    */
}