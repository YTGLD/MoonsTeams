package com.ytgld.moonstone.event;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ICanHasInItem;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class OtherItemModifyHandler {
    public static void doOtherModifiers(ItemBase itemBase, ItemStack stack, LivingEntity livingEntity,
                                    Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap) {
        itemBase.modifyAttribute(stack, Items.owner_blood_eye.asItem(), AttReg.owner_blood_blood_speed,new AttributeModifier(
                itemBase.id(stack),0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, Items.owner_blood_attack_eye.asItem(), AttReg.owner_blood_blood_speed,new AttributeModifier(
                itemBase.id(stack),0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, Items.owner_blood_speed_eye.asItem(), AttReg.owner_blood_attack_speed,new AttributeModifier(
                itemBase.id(stack),-0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, Items.owner_blood_boom_eye.asItem(), AttReg.cit,new AttributeModifier(
                itemBase.id(stack),0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, Items.owner_blood_effect_eye.asItem(), AttReg.speed,new AttributeModifier(
                itemBase.id(stack),0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, Items.owner_blood_vex.asItem(), AttReg.heal,new AttributeModifier(
                itemBase.id(stack),0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, Items.owner_blood_earth.asItem(), Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(
                itemBase.id(stack),0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);
    }

    public static void modifyOtherComponent(List<Component> components, ItemStack stack ) {
//        addModifyComponent(stack, Items.owner_blood_eye.asItem(), Component.translatable("moonstone.owner_blood_eye.modify"),components, true);
//        addModifyComponent(stack, Items.owner_blood_attack_eye.asItem(), Component.translatable("moonstone.owner_blood_attack_eye.modify"),components, true);
//        addModifyComponent(stack, Items.owner_blood_speed_eye.asItem(), Component.translatable("moonstone.owner_blood_speed_eye.modify"),components, true);
//        addModifyComponent(stack, Items.owner_blood_boom_eye.asItem(), Component.translatable("moonstone.owner_blood_boom_eye.modify"),components, true);
//        addModifyComponent(stack, Items.owner_blood_effect_eye.asItem(), Component.translatable("moonstone.owner_blood_effect_eye.modify"),components, true);
//        addModifyComponent(stack, Items.owner_blood_vex.asItem(), Component.translatable("moonstone.owner_blood_vex.modify"),components, true);
//        addModifyComponent(stack, Items.owner_blood_earth.asItem(), Component.translatable("moonstone.owner_blood_earth.modify"),components, true);
//

    }


    private static void addModifyComponent(ItemStack stack, Item item, Component component, List<Component> components,boolean isAdd){
        if (stack.getItem() instanceof ItemBase) {
            if (ICanHasInItem.getAll(stack).contains(item)) {
                if (isAdd) {
                    components.add(Component.literal("+").append(component));
                }else {
                    components.add(Component.literal("-").append(component));
                }
            }
        }
    }
    private static ResourceLocation modifyOtherID(Item item){
        return ResourceLocation.fromNamespaceAndPath(
                Moonstone.MODID,item.getDescriptionId() +
                        "_other_modify"
        );
    }
}
