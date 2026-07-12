package com.ytgld.moonstone.item.nightmare.start;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

import static com.ytgld.moonstone.item.nightmare.NightmareBase.ITEMCategory;

public class StartPower extends NightmareSmall {
    public StartPower(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.ConfigValue<List<String>> intValue ;
        public static ModConfigSpec.IntValue intValue2;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue =  builder.translation("chest_item.config.StartPower")
                    .define("StartPower",new ArrayList<>(List.of("minecraft:max_health")));
            intValue2 =  builder.translation("chest_item.config.StartPower2")
                    .defineInRange("StartPower2",2,0,100);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("StartPower",
                            "权力","属性减少的值的黑名单"),
                    new CIString("StartPower2",
                            "权力2","死亡时跌的属性")
            );
        }
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (SIHandler.hascurio(slotContext.entity(), this)) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
        }
    }

    public  Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();
        List<Integer> integersHealth = new ArrayList<>();
        for (MobEffectInstance effect : living.getActiveEffects()) {
            if (effect != null
                    && effect.getEffect().value().isBeneficial()) {
                integersHealth.add(1);
            }
        }
        float att = 0;
        for (int ignored : integersHealth) {
            float ssa =(float) (double)ConfigItem.intValue2.getAsInt();
            att += ssa;
        }
        att /= 100;
        Set<String> blacklist = new HashSet<>();
        for (String aaa : ConfigItem.intValue.get()) {
            String[] parts = aaa.split(":");
            if (parts.length > 0) {
                blacklist.add(parts[0] + ":" + parts[1]);
            }
        }
        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            if (attribute != null) {
                Identifier attributeId = BuiltInRegistries.ATTRIBUTE.getKey(attribute.value());
                if (attributeId != null && !blacklist.contains(attributeId.toString())) {
                    linkedHashMultimap.put(attribute, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.descriptionId), att, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
            }
        }

        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float ssa =(float) (double)ConfigItem.intValue2.get();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_power.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_power.tool.string.1",ssa).withStyle(ChatFormatting.DARK_RED));
    }
}


