package com.ytgld.seeking_immortals.item.nightmare;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Map;

public interface Terror {
    ResourceLocation image(@Nullable LivingEntity entity);
    @Nullable
    Map<Integer ,Component> describe(ItemStack stack);
    int maxLevel(ItemStack stack);
    int nowLevel(ItemStack stack);

    int color(ItemStack stack);

}
