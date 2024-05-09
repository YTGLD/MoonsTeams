package com.moonstone.moonstonemod.Item.NaNoDoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Init.Items;
import com.moonstone.moonstonemod.Item.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nanorobot extends Doom {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        if (slotContext.entity() instanceof Player player){
            int time = 600;
            if (Handler.hascurio(player, Items.battery.get())){
                time = 300;
            }
            ItemStack ss = player.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!ss.isEmpty()) {
                if (ss.getMaxDamage() != 0) {
                    if (ss.getDamageValue() > 0) {

                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            ss.setDamageValue(ss.getDamageValue() - 30);
                            player.getCooldowns().addCooldown(stack.getItem(), time);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.nanorobot.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.nanorobot.tool.string.1").withStyle(ChatFormatting.GOLD));

    }

}
