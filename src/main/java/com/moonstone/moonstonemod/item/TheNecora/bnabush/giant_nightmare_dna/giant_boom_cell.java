package com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_nightmare_dna;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.necora.nightmare_giant;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class giant_boom_cell extends TheNecoraIC {
    public static void Boom(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.giant_boom_cell.get())){
                if (event.getSource()!= null&&event.getSource().getEntity() instanceof nightmare_giant){
                    event.setAmount(0);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.giant_boom_cell.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}


