package com.moonstone.moonstonemod.Item.Common;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Item.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class badgeofthedead extends CommonItem {
    public badgeofthedead(){
        MinecraftForge.EVENT_BUS.addListener(this::aaa);
    }

    private void aaa(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, this)){
                if (event.getEntity()  instanceof Mob mob){
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount()*1.25f);
                    }
                }
            }
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.badgeofthedead.tool.string").withStyle(ChatFormatting.GOLD));
    }
}



