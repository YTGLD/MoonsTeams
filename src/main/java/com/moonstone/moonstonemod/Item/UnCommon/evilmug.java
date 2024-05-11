package com.moonstone.moonstonemod.Item.UnCommon;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class evilmug extends Item {
    public static String blood = "blood";

    public evilmug() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("blood",ChatFormatting.RED)).food(
                new FoodProperties.Builder().alwaysEat().nutrition(0).saturationMod(0.0f).build()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.DRINK;
    }


    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living){


        if (s.getOrCreateTag().getInt(blood) > 0) {
            living.heal(10);
            if (s.getOrCreateTag().getInt(blood) >= 10) {
                s.getOrCreateTag().putInt(blood, s.getOrCreateTag().getInt(blood) - 10);
            }else {
                s.getOrCreateTag().putInt(blood, s.getOrCreateTag().getInt(blood) - s.getOrCreateTag().getInt(blood));
            }
        }else {
            living.hurt(living.damageSources().magic(), 10);
            s.getOrCreateTag().putInt(blood,s.getOrCreateTag().getInt(blood) + 10);
        }
        return s;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        if (stack.getOrCreateTag().getInt(blood)<= 0) {
            tooltip.add(Component.translatable("item.evilmug.tool.string").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.evilmug.tool.string.1").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.evilmug.tool.string.2").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));

        }else {
            tooltip.add(Component.translatable("item.evilmug.tool.string.3").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
        tooltip.add(Component.translatable("Blood : "+ stack.getOrCreateTag().getInt(blood)).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));

    }
}

