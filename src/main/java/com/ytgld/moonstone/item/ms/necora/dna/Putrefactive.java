package com.ytgld.moonstone.item.ms.necora.dna;


import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.item.ms.necora.NecoraHandler;
import com.ytgld.moonstone.item.ms.necora.dna.god.CanUPLevel;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.List;

import static com.ytgld.moonstone.item.Items.GodPutrefactive;

public class Putrefactive extends TheNecora implements CanUPLevel {
    public Putrefactive(Properties properties) {
        super(properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.putrefactive.tool.string").withStyle(ChatFormatting.RED));
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable(""));
        }
    }

    @Override
    public Item upLevelItem() {
        return GodPutrefactive.asItem();
    }
}


