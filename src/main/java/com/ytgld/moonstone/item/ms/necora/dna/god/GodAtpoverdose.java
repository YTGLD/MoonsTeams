package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.GodDNA;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

public class GodAtpoverdose extends GodDNA {
    public GodAtpoverdose(Properties properties) {
        super(properties);
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("curio"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("curio").build())
        ), true);
    }

}
