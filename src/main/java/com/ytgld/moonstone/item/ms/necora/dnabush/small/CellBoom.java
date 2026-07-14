package com.ytgld.moonstone.item.ms.necora.dnabush.small;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class CellBoom extends TheNecora {
    public static final String cb = " CellBoom";

    public CellBoom(Properties properties) {
        super(properties);
    }

    public static void Boom(LivingDamageEvent.Pre event) {
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player, Items.cell_boom.get())) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setNewDamage(0);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.cell_boom.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}

