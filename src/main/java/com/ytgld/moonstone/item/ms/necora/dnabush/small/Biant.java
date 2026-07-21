package com.ytgld.moonstone.item.ms.necora.dnabush.small;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

public class Biant extends TheNecora {

    public Biant(Properties properties) {
        super(properties);
    }
    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("dnabush"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("dnabush").build())

        ), true);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.giant.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}
