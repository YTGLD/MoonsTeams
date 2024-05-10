package com.moonstone.moonstonemod.Item.NaNoDoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Init.Items;
import com.moonstone.moonstonemod.Item.MoonStoneItem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nanocube extends Doom {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            int time = 900;
            if (Handler.hascurio(player, Items.battery.get())){
                time = 600;
            }
            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                ItemStack HEAD = player.getItemBySlot(EquipmentSlot.HEAD);
                if (!HEAD.isEmpty()) {
                    if (HEAD.getDamageValue() > 0) {
                        if (HEAD.getMaxDamage() != 0) {
                            HEAD.setDamageValue(HEAD.getDamageValue() - 30);
                        }
                    }
                }
                ItemStack CHEST = player.getItemBySlot(EquipmentSlot.CHEST);
                if (!CHEST.isEmpty()) {
                    if (CHEST.getMaxDamage() != 0) {
                        if (CHEST.getDamageValue() > 0) {
                            CHEST.setDamageValue(CHEST.getDamageValue() - 30);
                        }
                    }
                }
                ItemStack LEGS = player.getItemBySlot(EquipmentSlot.LEGS);
                if (!LEGS.isEmpty()) {
                    if (LEGS.getMaxDamage() != 0) {
                        if (LEGS.getDamageValue() > 0) {
                            LEGS.setDamageValue(LEGS.getDamageValue() - 30);
                        }
                    }
                }
                ItemStack FEET = player.getItemBySlot(EquipmentSlot.FEET);
                if (!FEET.isEmpty()) {
                    if (FEET.getMaxDamage() != 0) {
                        if (FEET.getDamageValue() > 0) {
                            FEET.setDamageValue(FEET.getDamageValue() - 30);
                        }
                    }
                }

                player.getCooldowns().addCooldown(stack.getItem(), time);

            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.nanocube.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.nanocube.tool.string.1").withStyle(ChatFormatting.GOLD));

    }


}
