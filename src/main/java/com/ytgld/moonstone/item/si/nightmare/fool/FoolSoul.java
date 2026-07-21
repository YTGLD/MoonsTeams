package com.ytgld.moonstone.item.si.nightmare.fool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
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

public class FoolSoul extends NightmareSmall {
    public FoolSoul(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.FoolSoul")
                    .defineInRange("FoolSoul", 2f, 0, 1000);
            intValue2 = builder.translation("moonstone.config.FoolSoul2")
                    .defineInRange("FoolSoul2", 4f, 0, 1000);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("FoolSoul",
                            "灵魂", "生命"),
                    new CIString("FoolSoul2",
                            "灵魂2", "受伤")
            );
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (SIHandler.hascurio(slotContext.entity(), this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getsHEAL(slotContext));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
        slotContext.entity().getAttributes().removeAttributeModifiers(getsHEAL(slotContext));
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
                    if (!stack.isEmpty() && stack.getItem() instanceof NightmareSmall) {
                        integersHealth.add(1);
                    }
                }
            }
        });
        float health = 0;
        for (int ignored : integersHealth) {
            float ssa = ConfigItem.intValue.get().floatValue();
            health += ssa;
        }
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), health, AttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getsHEAL(SlotContext slotContext) {
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
                    if (!stack.isEmpty() && stack.getItem() instanceof NightmareSmall) {
                        integersHealth.add(1);
                    }
                }
            }
        });
        float health = 0;
        for (int ignored : integersHealth) {
            float ssa = ConfigItem.intValue2.get().floatValue();
            health += ssa;
        }
        health /= 100;
        health *= 1;
        linkedHashMultimap.put(AttReg.heal, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), health, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float ssa = ConfigItem.intValue.get().floatValue();
        float heal = ConfigItem.intValue2.get().floatValue();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_soul.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_soul.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_soul.tool.string.2", heal).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_soul.tool.string.3", ssa).withStyle(ChatFormatting.DARK_RED));
    }
}

