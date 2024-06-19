package com.moonstone.moonstonemod.mixin.clilt;

import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlockRenderTypes.class)
public class ItemBlockRenderTypesMixin {
    @Inject(at = @At("HEAD"), method = "getRenderType(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/client/renderer/RenderType;", cancellable = true)
    private static void getRenderType(ItemStack itemStack, boolean p_109281_, CallbackInfoReturnable<RenderType> cir) {
        if (itemStack.is(Items.the_heart_image.get())){
            cir.setReturnValue(MRender.endPortal());
        }
    }
}
