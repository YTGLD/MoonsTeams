package com.moonstone.moonstonemod.item.maxitem.rage;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class rage_lock  extends Item implements ICurioItem,RAGE{
    public rage_lock() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    public static void LivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_lock.get())){
                player.heal(event.getEntity().getMaxHealth()*0.4f);
            }
        }
    }
    public static void LivingIncomingDamageEvent(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_lock.get())){
                event.setAmount(event.getAmount()*1.1f);

                player.heal(event.getAmount()*0.15f);

            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_lock.get())){
                if (player.getHealth()>=player.getMaxHealth()){
                    event.setAmount(event.getAmount()*0.75f);
                }
            }
        }
    }
    public static void LivingHealEvent(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_lock.get())){
                event.setAmount(event.getAmount()*0.25f);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.rage_lock.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_lock.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_lock.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_lock.tool.string.3").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_lock.tool.string.4").withStyle(ChatFormatting.GOLD));
    }
}


