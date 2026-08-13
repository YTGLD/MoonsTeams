package com.ytgld.moonstone.item.si.nightmare.insight;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class InsightDrug extends NightmareSmall {
    public InsightDrug(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.InsightDrug")
                    .defineInRange("InsightDrug", 60f, 0, 1000);
            intValue2 = builder.translation("moonstone.config.InsightDrug2")
                    .defineInRange("InsightDrug2", 5f, 0, 1000);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("InsightDrug",
                            "疯狂药剂", "属性"),
                    new CIString("InsightDrug2",
                            "疯狂药剂", "每次装备物品减少的属性")
            );
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (SIHandler.hascurio(slotContext.entity(), this))
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();
        List<Integer> integersHealth = new ArrayList<>();
        CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        integersHealth.add(1);
                    }
                }
            }
        });
        float health = (float) ConfigItem.intValue.getAsDouble();
        for (int ignored : integersHealth) {
            health -= ConfigItem.intValue2.get();
        }
        if (health < 10) {
            health = 10;
        }
        health /= 100;


        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()), health, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()), health, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()), health, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_drug.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_drug.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}


