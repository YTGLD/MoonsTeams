package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.GodDNA;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;

import java.util.List;

/**
 * 蚓状肌强化
 * <p>
 * 攻击不会再造成击退
 * <p>
 *  攻击有%d%%的概率使伤害翻倍
 */
public class GodHypertrophy extends GodDNA {
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.GodHypertrophy")
                    .defineInRange("GodHypertrophy", 1f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("GodHypertrophy",
                    "蚓状肌强化", "暴击概率会乘以这个值"));
        }
    }

    public static final float damage (){
        return (float) (25 * GodHypertrophy.ConfigItem.intValue.get());
    };
    public GodHypertrophy(Properties properties) {
        super(properties);
    }
    public static void damageAttack(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.god_hypertrophy.asItem())) {
                if (Mth.nextInt(player.getRandom(), 0, 100) <= GodHypertrophy.damage()) {
                    event.setNewDamage(event.getNewDamage() * 2);
                }
                return;
            }
            if (Handler.hascurio(player, Items.hypertrophy.asItem())) {
                if (Mth.nextInt(player.getRandom(), 0, 100) <= Hypertrophy.damage()) {
                    event.setNewDamage(event.getNewDamage() * 2);
                }
                return;
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.moonstone.hypertrophy.string.2",this.damage()).withStyle(ChatFormatting.RED));
    }
    public static class Hypertrophy extends TheNecora implements CanUPLevel {
        public static final float damage (){
            return (float) (10 * GodHypertrophy.ConfigItem.intValue.get());
        };
        public Hypertrophy(Properties properties) {
            super(properties);
        }
        @Override
        public Item upLevelItem() {
            return Items.god_hypertrophy.asItem();
        }
        @Override
        public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
            pTooltipComponents.add(Component.translatable("item.moonstone.hypertrophy.string.2",this.damage()).withStyle(ChatFormatting.RED));
        }
    }
}
