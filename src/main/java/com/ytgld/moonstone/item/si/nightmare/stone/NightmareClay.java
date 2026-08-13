package com.ytgld.moonstone.item.si.nightmare.stone;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

/**
 * 梦魇粘土
 * <p>
 * 你的四肢被高强度的梦魇粘土覆盖
 * <p>
 * 几乎完全免疫“死兆方尖碑”的受伤诅咒
 * <p>
 * 但这种粘土极易点燃，这使你受到的火焰类型伤害加倍
 */
public class NightmareClay extends NightmareSmall {
    public NightmareClay(Properties properties) {
        super(properties);
    }
    public static void hurts(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_clay.get())) {
                if (event.getSource().is(DamageTypes.ON_FIRE) ||
                        event.getSource().is(DamageTypes.IN_FIRE) ||
                        event.getSource().is(DamageTypes.LAVA)) {
                    event.setNewDamage(event.getNewDamage() * 2);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_clay.string.1").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_clay.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_clay.string.3").withStyle(Style.EMPTY.withColor(0xffff0000)));
    }
}
