package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import com.ytgld.moonstone.Handler;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.List;

/**
 * 粘膜损伤
 * <p>
 * 将%d%%吃下食物的饥饿值直接转换为生命值
 * <p>
 * 吃下食物会获得饥饿效果
 */
public class MucosalInjury extends SequenceBase {
    public MucosalInjury(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.MucosalInjury","MucosalInjury",
                    0.33, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(

                    new CIString("MucosalInjury",
                            "粘膜损伤", "转换多少饥饿值变为生命值，0.33就是33%")


            );
        }
    }
    public static void eat(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player) {
            if (event.getItem().getUseAnimation() == ItemUseAnimation.EAT) {
                if (Handler.hascurio(player, DNAItems.mucosal_injury.asItem())) {
                    float floatValue = ConfigItem.intValue.get().floatValue();
                    FoodProperties properties = event.getItem().get(DataComponents.FOOD);
                    if (properties != null) {
                        int lvl = properties.nutrition();
                        float s = properties.saturation();
                        float doIt = (lvl + s) * floatValue;
                        player.heal(doIt);
                        player.addEffect(new MobEffectInstance(MobEffects.HUNGER,300,1));
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int d= (int) (ConfigItem.intValue.get().floatValue() * 100f);
        addText(pTooltipComponents,Component.translatable("item.moonstone.mucosal_injury.string.1",d),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.mucosal_injury.string.2"),false);
    }
}
