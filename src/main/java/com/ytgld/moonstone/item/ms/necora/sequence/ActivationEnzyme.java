package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 活化酶堆积
 * <p>
 * 未受到伤害时治疗速度逐渐增加，最多增加额外的%d%%
 * <p>
 * 受到伤害后的%d秒内无法受到治疗
 */
public class ActivationEnzyme extends SequenceBase {
    public ActivationEnzyme(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.ActivationEnzyme","ActivationEnzyme",
                    1.5f, 0, Integer.MAX_VALUE);
            intValue2 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.ActivationEnzyme2","ActivationEnzyme2",
                    50, 0, Integer.MAX_VALUE);

        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("ActivationEnzyme",
                            "活化酶堆积", "受到伤害后治疗终止的时常，单位：秒"),
                    new CIString("ActivationEnzyme2",
                            "活化酶堆积2", "最大治疗加成"));
        }
    }
    private static final String healApply = "ActivationEnzymeHeal";
    private static final String canNOtHealTime = "ActivationEnzymeNotHeal";

    public static void heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if (Handler.hascurio(player, DNAItems.activation_enzyme.asItem())){
                CompoundTag compoundTag = player.getPersistentData();
                int time = ConfigItem.intValue.get().intValue() * 20;
                float maxHeal = ConfigItem.intValue2.get().floatValue();
                if (player.hurtTime > 0) {
                    compoundTag.putInt(canNOtHealTime, time);
                }
                if (compoundTag.getInt(canNOtHealTime) > 0) {
                    event.setAmount(0);
                }
                if (compoundTag.getFloat(healApply) < maxHeal) {
                    if (event.getAmount() > 0 && compoundTag.getInt(canNOtHealTime) <= 0) {
                        compoundTag.putFloat(healApply, compoundTag.getFloat(healApply) + (maxHeal / 15f));
                        float doHeal = compoundTag.getFloat(healApply) / 100f;
                        event.setAmount(event.getAmount() * (1 + doHeal));
                    }
                }
            }
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        super.curioTickUse(slotContext, stack);
        if (slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            CompoundTag compoundTag = player.getPersistentData();
            if (compoundTag.getInt(canNOtHealTime) > 0) {
                compoundTag.putFloat(healApply, 0);
                compoundTag.putInt(canNOtHealTime, compoundTag.getInt(canNOtHealTime) - 1);
            }
        }
    }

    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<Item>(Set.of(
                InitItems.WarmApproachable.asItem(),
                InitItems.OceanAffinity.asItem(),
                InitItems.EarthAffinity.asItem(),
                DNAItems.biological_enzyme_synthesis.asItem()
        ));
    }
    @Override
    public int maxSize() {
        return 2;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int time = ConfigItem.intValue.get().intValue();
        int heal = (int) (ConfigItem.intValue2.get().floatValue());
        addText(pTooltipComponents,Component.translatable("item.moonstone.activation_enzyme.string.1",heal),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.activation_enzyme.string.2",time),false);
    }
}
