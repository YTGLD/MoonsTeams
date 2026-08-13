package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.GodDNA;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

/**
 * 酸性液体逆流
 * <p>
 * 攻击腐蚀目标护甲
 * <p>
 * 若目标护甲较少或不存在，则造成额外%d%%伤害
 */
public class GodAcidicReflux extends GodDNA {
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("com.ytgld.moonstone.config.GodAcidicReflux")
                    .defineInRange("GodAcidicReflux", 1f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("GodAcidicReflux",
                    "酸性液体逆流", "属性倍率"));
        }
    }

    public static final float damage (){
        return (float) (1.3f * ConfigItem.intValue.get());
    };
    public GodAcidicReflux(Properties properties) {
        super(properties);
    }
    public static void damageAttack(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.god_acidic_reflux.asItem())) {
                int value = (int) (GodAcidicReflux.damage() * 10);
                hurtArmor(event.getEntity(),EquipmentSlot.HEAD,value);
                hurtArmor(event.getEntity(),EquipmentSlot.CHEST,value);
                hurtArmor(event.getEntity(),EquipmentSlot.LEGS,value);
                hurtArmor(event.getEntity(),EquipmentSlot.FEET,value);
                if (event.getEntity().getArmorValue() <= 10) {
                    event.setNewDamage(event.getNewDamage() * GodAcidicReflux.damage());
                }
                return;
            }
            if (Handler.hascurio(player, Items.acidic_reflux.asItem())) {
                int value = (int) (AcidicReflux.damage() * 10);
                hurtArmor(event.getEntity(),EquipmentSlot.HEAD,value);
                hurtArmor(event.getEntity(),EquipmentSlot.CHEST,value);
                hurtArmor(event.getEntity(),EquipmentSlot.LEGS,value);
                hurtArmor(event.getEntity(),EquipmentSlot.FEET,value);
                if (event.getEntity().getArmorValue() <= 10) {
                    event.setNewDamage(event.getNewDamage() * AcidicReflux.damage());
                }
                return;
            }
        }
    }
    private static void hurtArmor(LivingEntity livingEntity, EquipmentSlot slot,int value){
        livingEntity.getItemBySlot(slot).hurtAndBreak(value,livingEntity,slot);
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.com.ytgld.moonstone.acidic_reflux.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.com.ytgld.moonstone.acidic_reflux.string.2",this.damage() * 100f - 100).withStyle(ChatFormatting.RED));
    }
    public static class AcidicReflux extends TheNecora implements CanUPLevel {
        public static final float damage (){
            return (float) (1.2f * ConfigItem.intValue.get());
        };
        public AcidicReflux(Properties properties) {
            super(properties);
        }
        @Override
        public Item upLevelItem() {
            return Items.god_acidic_reflux.asItem();
        }
        @Override
        public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
            pTooltipComponents.add(Component.translatable("item.com.ytgld.moonstone.acidic_reflux.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.com.ytgld.moonstone.acidic_reflux.string.2",this.damage() * 100f - 100).withStyle(ChatFormatting.RED));
        }
    }
}
