package com.ytgld.moonstone.item.ms.maulice;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;

import java.util.List;

public class MOrb extends MLS {

    public MOrb(Properties properties) {
        super(properties);
    }

    public static void LivingExperienceDropEvent(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (Handler.hascurio(player, Items.morb.get())) {
            event.setDroppedExperience(((int) ((event.getDroppedExperience() * 1.5))) + 1);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.morb.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.morb.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));

    }

}

