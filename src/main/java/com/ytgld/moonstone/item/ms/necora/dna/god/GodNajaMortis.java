package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.GodDNA;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

/**
 *
 * 眼镜蛇式锁定
 * <p>
 * 攻击同一个目标时伤害逐渐增加，最多增加%d%%
 * <p>
 * 此效果会在击中第二个目标时清除
 */
public class GodNajaMortis extends GodDNA {
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.GodNajaMortis")
                    .defineInRange("GodNajaMortis", 1f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("GodNajaMortis",
                    "眼镜蛇式锁定", "最大伤害会乘以这个值"));
        }
    }

    public static final float damage (){
        return (float) (45 * GodNajaMortis.ConfigItem.intValue.get());
    };
    public GodNajaMortis(Properties properties) {
        super(properties);
    }
    public static final String damageTag = "GodNajaMortiseDamage";
    public static void damageAttack(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.god_naja_mortis.asItem())) {
                CompoundTag compoundTag = player.getPersistentData();
                if (compoundTag.getIntOr(damageTag, 0) < GodNajaMortis.damage()) {
                    compoundTag.putInt(damageTag,compoundTag.getIntOr(damageTag, 0) + 5);
                }
                float dam = 1 + (compoundTag.getIntOr(damageTag, 0) / 100f);
                event.setNewDamage(event.getNewDamage() * dam);
                return;
            }
            if (Handler.hascurio(player, Items.naja_mortis.asItem())) {
                CompoundTag compoundTag = player.getPersistentData();
                if (compoundTag.getIntOr(damageTag, 0) < NajaMortis.damage()) {
                    compoundTag.putInt(damageTag,compoundTag.getIntOr(damageTag, 0) + 5);
                }
                float dam = 1 + (compoundTag.getIntOr(damageTag, 0) / 100f);
                event.setNewDamage(event.getNewDamage() * dam);
                return;
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.moonstone.naja_mortis.string.1",this.damage()).withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.naja_mortis.string.2").withStyle(ChatFormatting.RED));
    }
    public static class NajaMortis extends TheNecora implements CanUPLevel {
        public static final float damage (){
            return (float) (30 * GodNajaMortis.ConfigItem.intValue.get());
        };
        public NajaMortis(Properties properties) {
            super(properties);
        }
        @Override
        public Item upLevelItem() {
            return Items.god_naja_mortis.asItem();
        }
        @Override
        public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
            pTooltipComponents.add(Component.translatable("item.moonstone.naja_mortis.string.1",this.damage()).withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.moonstone.naja_mortis.string.2").withStyle(ChatFormatting.RED));
        }
    }
}

