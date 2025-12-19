package com.moonstone.moonstonemod.mixin.si.mixin.client;

import com.ytgld.seeking_immortals.item.nightmare.Terror;
import com.ytgld.seeking_immortals.renderer.IAbstractContainerScreen;
import com.ytgld.seeking_immortals.renderer.IGuiGraphics;
import com.ytgld.seeking_immortals.renderer.Light;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> extends Screen implements IAbstractContainerScreen {
    @Shadow protected int leftPos;
    @Shadow protected int topPos;
    @Shadow private ItemStack draggingItem;
    @Shadow @Final protected T menu;

    @Unique
    private List<Vec2> seekingImmortals$vec2 = new ArrayList<>();
    @Unique
    private Integer cI1_21_9$integerList = Light.ARGB.color(255,255,255,255);

    protected AbstractContainerScreenMixin(Component title) {
        super(title);
    }

    @Inject(at = @At(value = "RETURN"), method = "render")
    public void Lnet(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        ItemStack itemstack = this.menu.getCarried();
        if (!itemstack.isEmpty()) {
            if (itemstack.getItem() instanceof Terror) {
                seekingImmortals$vec2.add(new Vec2(mouseX, mouseY));
                if (itemstack.getItem() instanceof Terror terror) {
                    cI1_21_9$integerList = terror.color(itemstack);
                }
            }
        }
        if (!seekingImmortals$vec2.isEmpty()) {
            if (seekingImmortals$vec2.size() > 100) {
                seekingImmortals$vec2.remove(0);
            }
            if (itemstack.isEmpty()|| !(itemstack.getItem() instanceof Terror)) {
                seekingImmortals$vec2.remove(0);
            }
        }

    }
    @Inject(at = @At(value = "RETURN"), method = "render")
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        ItemStack itemstack = this.menu.getCarried();
        if (guiGraphics instanceof IGuiGraphics iGuiGraphics) {
            iGuiGraphics.seekingImmortals$addW(itemstack);
        }
    }

    @Override
    public Integer moons1_20_1__$color() {
        return cI1_21_9$integerList;
    }

    @Override
    public List<Vec2> seekingImmortals$xy() {
        return seekingImmortals$vec2;
    }
}
