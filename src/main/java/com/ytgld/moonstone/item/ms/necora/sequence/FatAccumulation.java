package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
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
 * 脂肪堆积
 * <p>
 * 免疫弹射物伤害
 * <p>
 * 在水中，你会不受控制的上浮
 */
public class FatAccumulation extends SequenceBase {
    public FatAccumulation(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.FatAccumulation","FatAccumulation",
                    0.04f, 0, Integer.MAX_VALUE);


        }
        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }
        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("FatAccumulation",
                            "脂肪堆积", "在水里上浮的速度")
            );
        }
    }

    public static void proArrow(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.fat_accumulation.asItem())){
                if (event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
                    event.setAmount(0);
                    event.setCanceled(true);
                }
            }
        }
    }
    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>(Set.of(
                InitItems.WarmApproachable.asItem(),
                InitItems.OceanAffinity.asItem(),
                InitItems.EarthAffinity.asItem()
        ));
    }
    @Override
    public int maxSize() {
        return 1;
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        super.curioTickUse(slotContext, stack);
        float speed = ConfigItem.intValue.get().floatValue();
        if (slotContext.entity() instanceof Player player) {
            if (player.isUnderWater()) {
                player.setDeltaMovement(player.getDeltaMovement().add(0,speed,0));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        addText(pTooltipComponents,Component.translatable("item.moonstone.fat_accumulation.string.1"),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.fat_accumulation.string.2"),false);
    }
}
