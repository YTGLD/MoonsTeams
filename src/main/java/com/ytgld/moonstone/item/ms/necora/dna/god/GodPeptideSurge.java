package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.GodDNA;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

/**
 * 肽合成增强
 * <p>
 * 攻击消耗额外的饥饿值
 * <p>
 * 并使造成的伤害提高%d%%
 */
public class GodPeptideSurge extends GodDNA {
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.GodPeptideSurge")
                    .defineInRange("GodPeptideSurge", 1f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("GodPeptideSurge",
                    "肽合成增强", "属性倍率"));
        }
    }

    public static final float damage (){
        return (float) (1.15f * ConfigItem.intValue.get());
    };
    public GodPeptideSurge(Properties properties) {
        super(properties);
    }
    public static void damageAttack(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.god_peptide_surge.asItem())) {
                player.causeFoodExhaustion(0.35f);
                event.setNewDamage(event.getNewDamage() * GodPeptideSurge.damage());
                return;
            }
            if (Handler.hascurio(player, Items.peptide_surge.asItem())) {
                player.causeFoodExhaustion(0.35f);
                event.setNewDamage(event.getNewDamage() * PeptideSurge.damage());
                return;
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.moonstone.peptide_surge.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.peptide_surge.string.2",this.damage() * 100f - 100).withStyle(ChatFormatting.RED));
    }
    public static class PeptideSurge extends TheNecora implements CanUPLevel {
        public static final float damage (){
            return 1.1f;
        };      
        public PeptideSurge(Properties properties) {
            super(properties);
        }
        @Override
        public Item upLevelItem() {
            return Items.god_peptide_surge.asItem();
        }
        @Override
        public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
            pTooltipComponents.add(Component.translatable("item.moonstone.peptide_surge.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.moonstone.peptide_surge.string.2",this.damage() * 100f - 100).withStyle(ChatFormatting.RED));
        }
    }
}
