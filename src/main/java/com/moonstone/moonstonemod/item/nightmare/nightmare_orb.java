package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmare_orb extends nightmare {
    public static void nightmare_orb_heal(LivingHealEvent event){
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player, Items.nightmare_orb.get())){
                if (player.getHealth()<= player.getMaxHealth() / 3){
                    event.setAmount(0);
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().getHealth()<= slotContext.entity().getMaxHealth() / 5){
            if (!slotContext.entity().level().isClientSide&&slotContext.entity().tickCount % 20 == 0){
                slotContext.entity().hurt(slotContext.entity().damageSources().fellOutOfWorld(), slotContext.entity().getMaxHealth() / 100);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_orb.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmare_orb.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmare_orb.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmare_orb.tool.string.3").withStyle(ChatFormatting.DARK_RED));


    }
}
