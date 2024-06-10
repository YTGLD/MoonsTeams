package com.moonstone.moonstonemod.item.gorilla;

import com.moonstone.moonstonemod.moonstoneitem.Gorill;
import com.moonstone.moonstonemod.moonstoneitem.IDoom;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class gorillacake  extends Item implements Gorill {
    public gorillacake() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON).food(
                new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(10).build()));
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    public static final UUID uuid = UUID.fromString("64b1f788-7df1-3200-9eb6-3f1908ccafaa");
    public static final String gorillacake = "Gorilla_Cake";
    public static AttributeModifier attributeModifier (){
        return new AttributeModifier(uuid, gorillacake, 0, AttributeModifier.Operation.ADDITION);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
        ItemStack stack = super.finishUsingItem(s, level, living);
        if (living instanceof Player player) {

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("curio").ifPresent(stacks -> {
                stacks.addPermanentModifier(attributeModifier());
            }));
        }
        return stack;
    }
}

