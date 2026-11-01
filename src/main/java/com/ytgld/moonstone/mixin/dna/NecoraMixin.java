package com.ytgld.moonstone.mixin.dna;

import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ms.necora.Necora;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;

@Mixin(Necora.class)
public class NecoraMixin {
    @Inject(
            at = {@At("RETURN")},
            method = {"canUseHowSequence"}
    )
    public void canUseHowSequence(CallbackInfoReturnable<HashSet<Item>> cir) {
        HashSet<Item> add = new HashSet<>();
        add.add(DNAItems.muscle_manipulation.asItem());
        add.add(DNAItems.lactic_acid_accumulation.asItem());
        add.add(DNAItems.necrotizing_cyst.asItem());
        add.add(DNAItems.compound_dispersion.asItem());
        cir.getReturnValue().addAll(add);
    }
}
