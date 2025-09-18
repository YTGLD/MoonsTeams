package com.moonstone.moonstonemod.mixin.client;

import com.moonstone.moonstonemod.client.renderer.GuiHandler;
import com.moonstone.moonstonemod.client.renderer.IAbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin <T extends AbstractContainerMenu> extends Screen implements IAbstractContainerScreen {
    @Shadow @Nullable private Slot clickedSlot;

    @Shadow @Final protected T menu;

    protected AbstractContainerScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "renderSlot", at = @At("TAIL"))
    private void injectRenderSlotSetAnimation(GuiGraphics gui, Slot pSlot, CallbackInfo ci) {
        GuiHandler.preRenderSlotItem(pSlot);
    }

    @Inject(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;III)V"))
    private void injectRenderSlotScale(GuiGraphics gui, Slot pSlot, CallbackInfo ci) {
        GuiHandler.postRenderSlot();
    }


    @Override
    public boolean isHasItem() {
        if (GuiHandler.getCurrentlyRenderingSlot()!=null){
            if (GuiHandler.getCurrentlyRenderingSlot().getItem().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
