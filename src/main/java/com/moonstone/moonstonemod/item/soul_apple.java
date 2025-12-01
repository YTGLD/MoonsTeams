package com.moonstone.moonstonemod.item;

import com.moonstone.moonstonemod.moonstoneitem.Doom;
import com.moonstone.moonstonemod.moonstoneitem.IDoom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.UUID;

public class soul_apple extends Item implements IDoom {
    public soul_apple() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("soul_apple", ChatFormatting.AQUA)).food(
                new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(1).build()));
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    public final UUID uuid = UUID.fromString("ae7b5efd-7fd1-3975-a19d-4e69aea8f757");
    public AttributeModifier attributeModifier (){

        return new AttributeModifier(uuid, "soul_apple", 1, AttributeModifier.Operation.ADDITION);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
        ItemStack stack = super.finishUsingItem(s, level, living);
        if (living instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,12000,1));
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("curio").ifPresent(stacks -> {
                stacks.addPermanentModifier(this.attributeModifier());
            }));
        }

        return stack;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.soul_apple.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
