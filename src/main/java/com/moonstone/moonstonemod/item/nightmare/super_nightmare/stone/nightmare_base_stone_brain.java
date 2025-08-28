package com.moonstone.moonstonemod.item.nightmare.super_nightmare.stone;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class nightmare_base_stone_brain  extends nightmare implements SuperNightmare {
   
    public static void hurts(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_base_stone_brain.get())){
                if (player.getHealth()>= player.getMaxHealth()){
                    event.setAmount(0);
                }else {
                    event.setAmount(event.getAmount()*1.5f);
                }
            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_stone_brain.get())){
                if (!(player.getHealth() >= player.getMaxHealth())){
                    event.setAmount(event.getAmount()/2);
                }
            }
        }
    }
     @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_brain.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_brain.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_brain.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}

