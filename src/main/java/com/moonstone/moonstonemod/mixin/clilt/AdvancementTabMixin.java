package com.moonstone.moonstonemod.mixin.clilt;

import com.moonstone.moonstonemod.MGuiGraphics;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Map;


@Mixin(AdvancementTab.class)
public abstract class AdvancementTabMixin {

    @Unique
    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(MoonStoneMod.MODID, "textures/tab.png");

    @Shadow public abstract boolean isMouseOver(int p_97155_, int p_97156_, double p_97157_, double p_97158_);

    @Shadow public abstract Advancement getAdvancement();

    @Shadow private double scrollX;

    @Shadow private double scrollY;

    @Shadow @Final private Map<Advancement, AdvancementWidget> widgets;

    @Shadow private float fade;

    @Shadow @Final private Advancement advancement;

    @Shadow @Final private ItemStack icon;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private AdvancementWidget root;

    @Shadow @Nullable public abstract AdvancementWidget getWidget(Advancement p_97181_);

    @Inject(at = @At("RETURN"), method = "drawIcon")
    public void init(GuiGraphics guiGraphics, int tx, int ty, CallbackInfo ci) {
        if (advancement.getRoot().getId().getNamespace().equals(MoonStoneMod.MODID)) {
            this.getAdvancement().getChildren().forEach((orb)-> {
                orb.getChildren().forEach((ec_cloub)->{
                    ec_cloub.getChildren().forEach((cube)->{
                        cube.getChildren().forEach((box->{
                            box.getChildren().forEach((necora)->{
                                if (necora.getDisplay().getIcon().is(Items.necora.get())){

                                    AdvancementWidget widget = this.getWidget(necora);
                                    int iconX = widget.getX()+Mth.floor(this.scrollX);
                                    int iconY = widget.getY()+Mth.floor(this.scrollY);

                                    MGuiGraphics.blit(guiGraphics, WIDGETS_LOCATION,iconX+64,iconY+64, 0, 0, 64, 64, 64, 64,1,0,0,1);

                                }
                            });
                        }));
                    });
                });
            });
        }
    }
}