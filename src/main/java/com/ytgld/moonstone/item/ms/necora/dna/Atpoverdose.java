package com.ytgld.moonstone.item.ms.necora.dna;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

import static com.ytgld.moonstone.item.InitItems.GodAtpoverdose;

public class Atpoverdose extends TheNecora implements CanUPLevel {
    public Atpoverdose(Properties properties) {
        super(properties);
    }

    @Override
    public void addSlot(Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap) {
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                        1, AttributeModifier.Operation.ADD_VALUE);

    }

    @Override
    public Item upLevelItem() {
        return GodAtpoverdose.asItem();
    }
}
