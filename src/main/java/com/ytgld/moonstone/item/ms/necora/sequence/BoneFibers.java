package com.ytgld.moonstone.item.ms.necora.sequence;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.InitItems;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 骨纤维异常增生
 * <p>
 * 增加%d%%抗性
 * <p>
 * 生命值低于一半时额外增加%d%%抗性
 * <p>
 * 连续奔跑时，你的速度会越来越慢
 */
public class BoneFibers extends SequenceBase {
    public BoneFibers(Properties properties) {
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
                    "moonstone.config.BoneFibers","BoneFibers",
                    0.15f, 0, Integer.MAX_VALUE);
            intValue2 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.BoneFibers2","BoneFibers2",
                    0.1f, 0, Integer.MAX_VALUE);
            intValue3 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.BoneFibers3","BoneFibers3",
                    0.25f, 0, Integer.MAX_VALUE);

        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("BoneFibers",
                            "骨纤维异常增生", "增加的抗性，0.15就是15%"),
                    new CIString("BoneFibers2",
                            "骨纤维异常增生2", "低生命值的额外抗性，0.1就是10%"),
                    new CIString("BoneFibers3",
                            "骨纤维异常增生3", "最大奔跑速度的惩罚，0.4就是40%")
            );
        }
    }
    private static final String speedDown = "BoneFibersSpeed";

    public static void res(LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.bone_fibers.asItem())) {
                float res = ConfigItem.intValue.get().floatValue();
                if (player.getHealth() <= player.getMaxHealth() * 0.5f) {
                    res += ConfigItem.intValue2.get().floatValue();
                }
                event.setNewDamage(event.getNewDamage() * (1 - res));
            }
        }
    }
    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        super.curioTickUse(slotContext, stack);
        if (slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            CompoundTag compoundTag = player.getPersistentData();
            float max = ConfigItem.intValue3.get().floatValue();
            if (player.isSprinting()) {
                if (compoundTag.getFloatOr(speedDown, 0f) < max) {
                    compoundTag.putFloat(speedDown, compoundTag.getFloatOr(speedDown, 0f) + max / 100f);
                }
            }else {
                if (compoundTag.getFloatOr(speedDown, 0f) > 0) {
                    compoundTag.putFloat(speedDown, compoundTag.getFloatOr(speedDown, 0f) - max / 100f);
                }
            }
            player.getAttributes().addTransientAttributeModifiers(getAttributeModifiers(player));
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequipUse(slotContext, newStack, stack);
        if (slotContext.entity() instanceof Player player && !player.level().isClientSide()) {
            player.getAttributes().removeAttributeModifiers(getAttributeModifiers(player));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(Player player) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        CompoundTag compoundTag = player.getPersistentData();
        float down = compoundTag.getFloatOr(speedDown, 0f);
        modifierMultimap.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(identifier(),-down, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int res = (int) (ConfigItem.intValue.get().floatValue() * 100f);
        int resLow = (int) (ConfigItem.intValue2.get().floatValue() * 100f);
        addText(pTooltipComponents,Component.translatable("item.moonstone.bone_fibers.string.1",res),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.bone_fibers.string.2",resLow),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.bone_fibers.string.3"),false);
    }

    @Override
    public int maxDNAValue() {
        return 1;
    }

    @Override
    public HashSet<Item> canUseHowSequence() {
        return new HashSet<>(Set.of(
                DNAItems.lactic_acid_accumulation.asItem(),
                InitItems.spliced_activation.asItem()
        ));
    }


    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                InitItems.calcareous.asItem(),
                InitItems.frontal_lobe.asItem(),
                InitItems.high_energy.asItem(),
                InitItems.surge.asItem()
        ));
    }

    @Override
    public int maxSize() {
        return 2;
    }

}
