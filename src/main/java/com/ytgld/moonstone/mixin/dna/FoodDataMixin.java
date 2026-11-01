package com.ytgld.moonstone.mixin.dna;


import com.ytgld.moonstone.item.ms.necora.sequence.BloodReconstruction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class FoodDataMixin {
    @Shadow
    private int tickTimer;

    @Inject(
            at = {@At("RETURN")},
            method = {"tick"}
    )
    public void tick(ServerPlayer player, CallbackInfo ci) {
        tickTimer = BloodReconstruction.foodToLife(player,tickTimer);
    }
}
