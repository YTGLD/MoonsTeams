package com.ytgld.moonstone.mixin.dna;

import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ms.necora.TheNecoraDNABush;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;

@Mixin(TheNecoraDNABush.class)
public class TheNecoraDNABushMixin {
    @Inject(
            at = {@At("RETURN")},
            method = {"canUSe"}
    )
    public void canUseHowSequence(CallbackInfoReturnable<HashSet<Item>> cir) {
        cir.getReturnValue().add(

                DNAItems.biological_enzyme_synthesis.asItem()

        );
    }
}
