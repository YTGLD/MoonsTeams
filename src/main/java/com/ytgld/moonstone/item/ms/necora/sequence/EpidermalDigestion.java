package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 表皮消化
 * <p>
 *攻击满血生物恢复大量饥饿值和些许生命值
 * <p>
 * 在疾跑状态下饥饿值更容易被消耗
 */
public class EpidermalDigestion extends SequenceBase {
    public EpidermalDigestion(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.EpidermalDigestion","EpidermalDigestion",
                    8, 0, Integer.MAX_VALUE);
            intValue2 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.EpidermalDigestion2","EpidermalDigestion2",
                    0.0125f, 0, Integer.MAX_VALUE);

        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("EpidermalDigestion",
                            "表皮消化", "恢复的饥饿值，将此值除以4得到恢复的生命值"),
                    new CIString("EpidermalDigestion2",
                            "表皮消化2", "疾跑时产生的饥饿疲劳度"));
        }
    }
    public static void attack(LivingIncomingDamageEvent event){
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.epidermal_digestion.asItem())) {
                if (event.getEntity().getHealth() >= event.getEntity().getMaxHealth()) {
                    int foodLvl = ConfigItem.intValue.get().intValue();
                    float heal = foodLvl / 4f;
                    player.getFoodData().eat(foodLvl, foodLvl / 10f);
                    player.heal(heal);
                    player.level().playSound(null,player.blockPosition(), SoundEvents.PANDA_EAT, SoundSource.PLAYERS,1,1);
                }
            }
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        super.curioTickUse(slotContext, stack);
        if (slotContext.entity() instanceof Player player) {
            if (player.isSprinting()) {
                float food = ConfigItem.intValue2.get().floatValue();
                player.causeFoodExhaustion(food);
            }
        }
    }

    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                InitItems.WarmApproachable.asItem(),
                InitItems.OceanAffinity.asItem(),
                InitItems.EarthAffinity.asItem(),
                DNAItems.biological_enzyme_synthesis.asItem()
        ));
    }
    public int maxSize() {
        return 2;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        addText(pTooltipComponents,Component.translatable("item.moonstone.epidermal_digestion.string.1"),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.epidermal_digestion.string.2"),false);
    }
}
