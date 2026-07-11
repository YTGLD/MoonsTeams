package com.ytgld.moonstone;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

public class Handler {


    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (entity != null) {
            Optional<ICuriosItemHandler> curiosItemHandlerOptional = CuriosApi.getCuriosInventory(entity);
            if (curiosItemHandlerOptional.isPresent()) {
                List<SlotResult> find = findCurios(entity, curio);
                for (SlotResult slotResult : find) {
                    if (slotResult.stack().is(curio)) {
                        return true;
                    }
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
