package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.event.key.Keys;
import com.ytgld.moonstone.item.IKet;
import com.ytgld.moonstone.item.InitItems;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 甲烷合成
 * <p>
 * 按下技能键引爆自身体内的甲烷
 * <p>
 * 受到的火焰类型伤害增加%d%%
 * <p>
 * 着火时被杀死会产生附带火焰效果的爆炸
 */
public class MethaneSynthesis extends SequenceBase implements IKet {
    public MethaneSynthesis(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.MethaneSynthesis","MethaneSynthesis",
                    5f, 0, Integer.MAX_VALUE);
            intValue2 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.MethaneSynthesis2","MethaneSynthesis2",
                    1.5f, 0, Integer.MAX_VALUE);

        }

        @Override
        public String theCategory() {
                return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("MethaneSynthesis",
                            "甲烷合成", "技能释放和死亡时产生的爆炸半径和强度"),
                    new CIString("MethaneSynthesis2",
                            "甲烷合成2", "受到的火焰伤害倍率"));
        }
    }

    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.methane_synthesis.asItem())){
                if (event.getSource().is(DamageTypes.IN_FIRE)
                        || event.getSource().is(DamageTypes.IN_FIRE)
                        || event.getSource().is(DamageTypes.LAVA)){
                    float damageAdd = ConfigItem.intValue2.get().floatValue();
                    event.setAmount(event.getAmount() * (1 + damageAdd));
                }
            }
        }
    }
    public static void die(LivingDeathEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.methane_synthesis.asItem())) {
                float stronger = ConfigItem.intValue.get().floatValue();
                boolean fire = false;
                if (player.getRemainingFireTicks() > 0 || event.getSource().is(DamageTypes.IN_FIRE)
                        || event.getSource().is(DamageTypes.IN_FIRE)
                        || event.getSource().is(DamageTypes.LAVA)) {
                    fire = true;
                }
                player.level().explode(player,player.getX(),player.getY(),player.getZ(),stronger,fire, Level.ExplosionInteraction.NONE);
            }
        }
    }
    public static void keyUSe(Player player){
        if (Handler.hascurio(player, DNAItems.methane_synthesis.asItem())) {
            if (!player.getCooldowns().isOnCooldown(DNAItems.methane_synthesis.asItem().getDefaultInstance())) {
                float stronger = ConfigItem.intValue.get().floatValue();
                player.level().explode(player,player.getX(),player.getY(),player.getZ(),stronger,false, Level.ExplosionInteraction.NONE);
                player.getCooldowns().addCooldown(DNAItems.methane_synthesis.asItem().getDefaultInstance(),1200);
            }
        }
    }
    @Override
    public int maxSize() {
        return 2;
    }
    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                InitItems.ambush.asItem(),
                InitItems.atpoverdose.asItem(),
                InitItems.autolytic.asItem(),
                InitItems.fermentation.asItem(),
                InitItems.putrefactive.asItem(),
                InitItems.regenerative.asItem()
        ));
    }
    @Override
    public int maxDNAValue() {
        return 1;
    }

    @Override
    public HashSet<Item> canUseHowSequence() {
        return new HashSet<>(Set.of(
                DNAItems.lactic_acid_accumulation.asItem(),
                DNAItems.necrotizing_cyst.asItem(),
                DNAItems.compound_dispersion.asItem(),
                DNAItems.muscle_manipulation.asItem()


        ));
    }

    @Override
    public KeyMapping theKeyMapping() {
        return Keys.KEY_MAPPING_LAZY_R;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int damageAdd = (int) (ConfigItem.intValue2.get().floatValue() * 100);
        addText(pTooltipComponents,Component.translatable("item.moonstone.methane_synthesis.string.1"),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.methane_synthesis.string.2",damageAdd),false);
        addText(pTooltipComponents,Component.translatable("item.moonstone.methane_synthesis.string.3"),false);
    }
}
