package com.ytgld.moonstone.item.ms.nanodoom;

import com.ytgld.moonstone.item.ms.Doom;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

public class TheFruit extends Doom {
    public TheFruit(Properties properties) {
        super(properties.stacksTo(1).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(10).saturationModifier(10).build()));
    }
    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 32;
    }
}

