package com.ytgld.moonstone.mixin.dna;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.event.DNAHandler;
import com.ytgld.moonstone.event.DNAModifyHandler;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DNAModifyHandler.class)
public class DNAModifyHandlerMixin {
    @Inject(at = {@At("RETURN")}, method = {"doStrongerDNAModifiers"})
    private static void doStrongerDNAModifiers(ItemBase itemBase,
                                               ItemStack stack,
                                               LivingEntity livingEntity,
                                               Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap,
                                               CallbackInfo ci) {
        DNAHandler.addModifyAttribute(itemBase, stack, livingEntity, attributeModifierMultimap);
    }
    @Inject(at = {@At("RETURN")}, method = {"modifyComponent"})
    private static void modifyComponent(List<Component> components, ItemStack stack, CallbackInfo ci) {
        DNAHandler.addModifyText(components,stack);
    }
}
