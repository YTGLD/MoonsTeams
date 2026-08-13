package com.ytgld.moonstone.item.ms.necora.medicine.med;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;


public class Calcification extends TheNecora {
    public Calcification(Properties properties) {
        super(properties);
    }

    public static void calcification(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.calcification.get())){
                event.setNewDamage(event.getNewDamage() * 0.89f);
            }
        }

    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.calcification.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
        tooltip.add(Component.translatable("item.calcification.tool.string.2").withStyle(ChatFormatting.RED));

    }


}


