package com.ytgld.moonstone.item;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public interface ICanHasInItem {
    int maxSize();
    Set<Item>canUSe();
    default void modifyAttribute(ItemStack stack,Item other,
                                 Holder<Attribute> attributeHolder,
                                 AttributeModifier modifier,
                                 Multimap<Holder<Attribute>, AttributeModifier> modify){
        if (hasSetItemFormStack(stack, other)) {
            modify.put(attributeHolder,modifier);
        }
    }
    default Identifier id(ItemStack stack){
        return Identifier.fromNamespaceAndPath(Moonstone.MODID,stack.getItem().asItem().getDescriptionId() + "_modify");
    }
    default boolean addItemToStack(ItemStack stack , Item other){
        Set<String> set = stack.get(DataReg.theSetString.get());
        if (set == null){
            stack.set(DataReg.theSetString.get(),new HashSet<>());
        }
        if (set != null) {
            if (set.size() < maxSize()) {
                String identifier = BuiltInRegistries.ITEM.getKey(other).toString();
                return set.add(identifier);
            }
        }
        return false;
    }
    static Set<Item> getAll(ItemStack stack){
        Set<Item> hashSet = new HashSet<>();
        Set<String> set = stack.get(DataReg.theSetString.get());
        if (set != null){
            for (String name : set){
                Item item = BuiltInRegistries.ITEM.getValue(Identifier.parse(name));
                hashSet.add(item);
            }
        }
        return hashSet;
    }
    default boolean hasSetItemFormStack(ItemStack stack, Item form){
        Set<String> set = stack.get(DataReg.theSetString.get());
        if (set != null) {
            String identifier = BuiltInRegistries.ITEM.getKey(form).toString();
            return set.contains(identifier);
        }
        return false;
    }
}
