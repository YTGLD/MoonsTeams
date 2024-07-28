package com.moonstone.moonstonemod.moonstoneitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class INanoBattery extends Doom {

    public int t;

    public int getT() {
        return t;
    }

    public void setT(int t, LivingEntity player, ItemStack stack) {
        if (Handler.hascurio(player, Items.battery.get())||
                Handler.hascurio(player, Items.soulbattery.get())||
                Handler.hascurio(player, Items.ectoplasmbattery.get())||
                Handler.hascurio(player, Items.mbattery.get()))
        {
            t = (int) (t *  0.7);
        }
        if (stack.getOrCreateTag().getBoolean("ALLBattery")){
            t = (int) (t *  0.8);
        }
        this.t = t;
    }


    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("moonstone.string.cooling").append(String.valueOf(getT())).withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal(""));
    }
}
