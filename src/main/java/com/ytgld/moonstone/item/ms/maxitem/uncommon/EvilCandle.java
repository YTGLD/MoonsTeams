package com.ytgld.moonstone.item.ms.maxitem.uncommon;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class EvilCandle extends UnCommonItem implements TextEvt.Twelve{
    public EvilCandle(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.addTag("canStandOnFluidTrue");
            player.clearFire();
        }
    }
    public static void fire(LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.evilcandle.asItem())) {
                if (event.getSource().is(DamageTypes.ON_FIRE)
                        || event.getSource().is(DamageTypes.ON_FIRE)
                        || event.getSource().is(DamageTypes.LAVA)) {
                    event.setNewDamage(event.getNewDamage() * 0.2f);
                }
            }
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.removeTag("canStandOnFluidTrue");
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        tooltip.add(Component.translatable("item.evilcandle.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.evilcandle.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.evilcandle.tool.string.2").withStyle(ChatFormatting.GOLD));

    }
}
