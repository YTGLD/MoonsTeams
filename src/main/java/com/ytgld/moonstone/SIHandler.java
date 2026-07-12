package com.ytgld.moonstone;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;
import java.util.Optional;

public class SIHandler {
    public static boolean hascurio(LivingEntity entity, Item curio) {
        return Handler.hascurio(entity,curio);
    }
}
