package com.ytgld.moonstone.item.ms.necora.sequence;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.necora.SequenceBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 刺状物突出
 * <p>
 * 获得%d%%的反伤能力
 * <p>
 * 并且反伤时附加%d%%自身攻击力的伤害
 * <p>
 * 增加%d%%抗性
 * <p>
 * 每穿着一件护甲都会增加%d%%的受到伤害
 */
public class SpikeProtrusion extends SequenceBase {
    public SpikeProtrusion(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;
        public static ModConfigSpec.DoubleValue intValue3;
        public static ModConfigSpec.DoubleValue intValue4;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.SpikeProtrusion","SpikeProtrusion",
                    0.33f, 0, Integer.MAX_VALUE);
            intValue2 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.SpikeProtrusion2","SpikeProtrusion2",
                    1.5f, 0, Integer.MAX_VALUE);
            intValue3 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.SpikeProtrusion3","SpikeProtrusion3",
                    0.3f, 0, Integer.MAX_VALUE);
            intValue4 = RegisterItemConfig.defineInRange(builder,
                    "moonstone.config.SpikeProtrusion4","SpikeProtrusion4",
                    0.2f, 0, Integer.MAX_VALUE);

        }

        @Override
        public String theCategory() {
            return ITEMCategoryTheNecora;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("SpikeProtrusion",
                            "刺状物突出", "获得的反伤能力，0.33就是33%"),
                    new CIString("SpikeProtrusion2",
                            "刺状物突出2", "反伤时造成的额外伤害倍率"),
                    new CIString("SpikeProtrusion3",
                            "刺状物突出3", "增加的抗性，0.3就是30%"),
                    new CIString("SpikeProtrusion4",
                            "刺状物突出4", "增加的受伤倍率，0.2就是20%")
            );
        }
    }
    public static void damageHurt(LivingDamageEvent.Post event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.spike_protrusion.asItem())){
                if (event.getSource().getDirectEntity() instanceof LivingEntity living
                        && !event.getSource().is(DamageTypes.THORNS)){
                    float damage = event.getOriginalDamage();
                    float hurt = ConfigItem.intValue.get().floatValue();
                    float damageOfPlayer = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    damageOfPlayer *= ConfigItem.intValue2.get().floatValue();
                    living.hurt(living.damageSources().thorns(player), (damage * hurt) + damageOfPlayer);
                }
            }
        }
    }
    public static void damageHurt(LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, DNAItems.spike_protrusion.asItem())){
                float hurtApply = ConfigItem.intValue3.get().floatValue();
                float armorDamage = applyDamageAdd(player,0);
                float odRes = 1 - hurtApply;
                event.setNewDamage(event.getNewDamage() * (odRes + armorDamage));
            }
        }
    }
    private static float applyDamageAdd(Player player ,float damage){
        float add = ConfigItem.intValue4.get().floatValue();
        if (hasSlot(player, EquipmentSlot.HEAD)) {
            damage += add;
        }
        if (hasSlot(player, EquipmentSlot.CHEST)) {
            damage += add;
        }
        if (hasSlot(player, EquipmentSlot.LEGS)) {
            damage += add;
        }
        if (hasSlot(player, EquipmentSlot.FEET)) {
            damage += add;
        }
        return damage;
    }

    private static boolean hasSlot(Player player , EquipmentSlot slot){
        return !player.getItemBySlot(slot).isEmpty();
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
                DNAItems.biological_enzyme_synthesis.asItem(),
                InitItems.putrefactive.asItem(),
                InitItems.regenerative.asItem()
        ));
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        int damageHurt = (int) (ConfigItem.intValue.get().floatValue() * 100);
        int attHurt = (int) (ConfigItem.intValue2.get().floatValue() * 100);
        int res = (int) (ConfigItem.intValue3.get().floatValue() * 100);
        int hurt = (int) (ConfigItem.intValue4.get().floatValue() * 100);


        addText(pTooltipComponents,Component.translatable("item.moonstone.spike_protrusion.string.1",damageHurt),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.spike_protrusion.string.2",attHurt),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.spike_protrusion.string.3",res),true);
        addText(pTooltipComponents,Component.translatable("item.moonstone.spike_protrusion.string.4",hurt),false);
    }
}
