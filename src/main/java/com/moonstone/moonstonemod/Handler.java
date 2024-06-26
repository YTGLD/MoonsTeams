package com.moonstone.moonstonemod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Handler {
    public static double getAttSize(LivingEntity entity, Attribute attribute){
        AttributeInstance attributeInstance =  entity.getAttribute(attribute);
        if (attributeInstance != null) {
            return attributeInstance.getValue();
        }
        return 0;
    }


    public static boolean hascurio(LivingEntity entity, Item curio) {

        if (entity != null) {
            List<SlotResult> find = findCurios(entity, curio);
            for (SlotResult slotResult : find) {
                if (slotResult.stack().is(curio)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity, Item item) {
        return findCurios(livingEntity, (stack) -> stack.getItem() == item);
    }

    public static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity,
                                              Predicate<ItemStack> filter) {
        return CuriosApi.getCuriosInventory(livingEntity).map(inv -> inv.findCurios(filter))
                .orElse(Collections.emptyList());
    }
}
