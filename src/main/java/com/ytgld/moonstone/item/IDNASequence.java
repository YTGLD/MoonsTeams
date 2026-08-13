package com.ytgld.moonstone.item;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface IDNASequence {
    int maxDNAValue();
    HashSet<Item> canUseHowSequence();
    default void addSequenceAttributes(ItemStack stack,Item other,
                                 Holder<Attribute> attributeHolder,
                                 AttributeModifier modifier,
                                 Multimap<Holder<Attribute>, AttributeModifier> modify){
        if (hasDNA(stack, other)) {
            modify.put(attributeHolder,modifier);
        }
    }
    default boolean hasDNA(ItemStack stack, Item form){
        Set<String> stringSet = stack.get(DataReg.dna_sequence.get());
        if (stringSet != null) {
            String identifier = BuiltInRegistries.ITEM.getKey(form).toString();
            return stringSet.contains(identifier);
        }
        return false;
    }
    default boolean addDNASequence(ItemStack stack , Item dna){
        Set<String> stringSet = stack.get(DataReg.dna_sequence.get());
        if (stringSet == null){
            stack.set(DataReg.dna_sequence.get(),new HashSet<>());
        }
        if (stringSet != null) {
            if (stringSet.size() < maxDNAValue()) {
                String identifier = BuiltInRegistries.ITEM.getKey(dna).toString();
                return stringSet.add(identifier);
            }
        }
        return false;
    }
    static Set<Item> getAllDNA(ItemStack stack){
        Set<Item> toReturnSet = new HashSet<>();
        Set<String> set = stack.get(DataReg.dna_sequence.get());
        if (set != null){
            for (String name : set){
                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(name));
                toReturnSet.add(item);
            }
        }
        return toReturnSet;
    }
    static boolean hasHowDNASequence(Player player, Item target){
        Set<ItemStack> stacks = new HashSet<>();
        var inv=  CuriosApi.getCuriosInventory(player);
        if (inv.isPresent()) {
            ICuriosItemHandler iCuriosItemHandler =  inv.get();
            Map<String, ICurioStacksHandler> curioStacksHandlers = iCuriosItemHandler.getCurios();
            for (ICurioStacksHandler iCurioStacksHandler : curioStacksHandlers.values()){
                for (int i = 0; i < iCurioStacksHandler.getStacks().getSlots(); i++) {
                    stacks.add(
                            iCurioStacksHandler.getStacks().getStackInSlot(i)
                    );
                }
            }
        }
        for (ItemStack stack : stacks) {
            if (getAllDNA(stack).contains(target)) {
                return true;
            }
        }
        return false;
    }
}
