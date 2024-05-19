package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.item.buyme.wind_and_rain;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At("HEAD"), method = "getUseDuration", cancellable = true)
    public void moonstone$getUseDuration(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.getTag()!=null){
            if (stack.getTag().getBoolean(wind_and_rain.wind)){
                cir.setReturnValue(72000);
            }
        }
    }
    @Inject(at = @At("HEAD"), method = "getUseAnimation", cancellable = true)
    public void moon$getUseAnimation(CallbackInfoReturnable<UseAnim> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.getTag()!=null){
            if (stack.getTag().getBoolean(wind_and_rain.wind)){
                cir.setReturnValue(UseAnim.BOW);
            }
        }
    }
}
