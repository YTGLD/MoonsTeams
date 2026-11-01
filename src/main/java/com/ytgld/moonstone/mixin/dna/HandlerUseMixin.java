package com.ytgld.moonstone.mixin.dna;

import com.ytgld.moonstone.event.key.UseCuriosHandler;
import com.ytgld.moonstone.item.ms.necora.sequence.MethaneSynthesis;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UseCuriosHandler.HandlerUse.class)
public class HandlerUseMixin {
    @Inject(
            at = {@At("RETURN")},
            method = {"handleOpenCurios"}
    )
    public void handleOpenCurios(UseCuriosHandler.UseChestCurios data, IPayloadContext ctx, CallbackInfo ci) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            MethaneSynthesis.keyUSe(player);
        });
    }
}
