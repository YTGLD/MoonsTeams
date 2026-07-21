package com.ytgld.moonstone.item.ms.necora.dna;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

import static com.ytgld.moonstone.item.Items.GodAtpoverdose;

public class Atpoverdose extends TheNecora implements CanUPLevel {
    public Atpoverdose(Properties properties) {
        super(properties);
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("curio"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                1, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("curio").build())
        ), true);
    }

    @Override
    public Item upLevelItem() {
        return GodAtpoverdose.asItem();
    }
}
