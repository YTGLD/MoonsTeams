package com.ytgld.moonstone.item.ms.nanodoom;

import com.ytgld.moonstone.item.ms.Doom;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class TheFruit extends Doom {
    public TheFruit(Properties properties) {
        super(properties.stacksTo(1).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(10).saturationModifier(10).build()));
    }

    public static void setNotTarget(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        if (entity.entityTags().contains("MoonstoneTheFruit")){
            cir.setReturnValue(false);
        }
    }
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        entity.addTag("MoonstoneTheFruit");
        return super.finishUsingItem(itemStack, level, entity);
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 32;
    }
}

