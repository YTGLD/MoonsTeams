package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import com.ytgld.moonstone.other.IMobEffectInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 血运重构
 * <p>
 * 负面效果的维持时间降低%d%%
 * <p>
 * 饥饿值转换为生命值的速度提高%d%%
 * <p>
 * 受到治疗效果降低%d%%
 */
public class BloodReconstruction extends SequenceBase {
    public BloodReconstruction(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;
        public static ModConfigSpec.DoubleValue intValue3;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.BloodReconstruction","BloodReconstruction",
                    1, 0, Integer.MAX_VALUE);
            intValue2 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.BloodReconstruction2","BloodReconstruction2",
                    2, 0, Integer.MAX_VALUE);
            intValue3 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.BloodReconstruction3","BloodReconstruction3",
                    0.15, 0, Integer.MAX_VALUE);

        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("BloodReconstruction",
                            "血运重构", "降低的负面效果持续时间，1是降低50%，2是降低66%，3是降低75%，以此类推"),
                    new CIString("BloodReconstruction2",
                            "血运重构2", "饥饿值转换为生命值的速度，1就是增加一倍，2是增加两倍，以此类推"),
                    new CIString("BloodReconstruction3",
                            "血运重构3", "降低的治疗效果，0.25就是25%")
            );
        }
    }

    public static void effect(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.blood_reconstruction.asItem())) {
                float timeBuff = 1 / (1 + ConfigItem.intValue.get().floatValue());
                MobEffectInstance mobEffectInstance =  event.getEffectInstance();
                if (mobEffectInstance instanceof IMobEffectInstance iMobEffectInstance) {
                    iMobEffectInstance.moonstoneGeneSequence$setTime((int) (mobEffectInstance.getDuration() * timeBuff));
                }
            }
        }
    }
    public static int foodToLife(Player player ,int time){
        if (Handler.hascurio(player, DNAItems.blood_reconstruction.asItem())) {
            int t = ConfigItem.intValue2.get().intValue();
            return time + t;
        }
        return time;
    }
    public static void heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.blood_reconstruction.asItem())) {
                float down = ConfigItem.intValue3.get().floatValue();
                event.setAmount(event.getAmount() * (1 - down));
            }
        }
    }

    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                InitItems.WarmApproachable.asItem(),
                InitItems.OceanAffinity.asItem(),
                InitItems.EarthAffinity.asItem(),
                DNAItems.biological_enzyme_synthesis.asItem()
        ));
    }
    @Override
    public int maxSize() {
        return 3;
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int timeBuff = 100 -  (int) (1 / (1 + ConfigItem.intValue.get().floatValue()) * 100f);
        int food = (int) (ConfigItem.intValue2.get().floatValue() * 100f);
        int heal = (int) (ConfigItem.intValue3.get().floatValue() * 100f);
        addText(pTooltipComponents,Component.translatable("item.moonstone.blood_reconstruction.string.1",timeBuff),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.blood_reconstruction.string.2",food),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.blood_reconstruction.string.3",heal),false);
    }
}
