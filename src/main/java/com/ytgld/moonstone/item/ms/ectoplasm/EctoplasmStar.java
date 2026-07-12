package com.ytgld.moonstone.item.ms.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class EctoplasmStar extends Ectoplasm {
    public EctoplasmStar(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("chest_item.config.EctoplasmStar")
                    .defineInRange("EctoplasmStar", 50f, 0, Integer.MAX_VALUE);
            intValue2 = builder.translation("chest_item.config.EctoplasmStar2")
                    .defineInRange("EctoplasmStar2", 2f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategoryEctoplasm;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("EctoplasmStar",
                            "幸运星", "最大转换幸运值"),
                    new CIString("EctoplasmStar2",
                            "幸运星2", "最大治疗属性"));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(att(player));
            slotContext.entity().getAttributes().addTransientAttributeModifiers(att2(player));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().removeAttributeModifiers(att(player));
            slotContext.entity().getAttributes().removeAttributeModifiers(att2(player));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> att(Player player) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        int s = 20;
        if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
            modifierMultimap.put(Attributes.LUCK, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId + "nightmare_base_stone_meet"),
                    20, AttributeModifier.Operation.ADD_VALUE));
        }
        modifierMultimap.put(Attributes.LUCK, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), s, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

    public Multimap<Holder<Attribute>, AttributeModifier> att2(Player player) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        int max = (int) ConfigItem.intValue.get().floatValue();
        float s = player.getLuck();
        if (s > max) {
            s = max;
        }
        s /= 100;
        if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
            modifierMultimap.put(AttReg.heal, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId + "nightmare_base_stone_meet"),
                    s * ConfigItem.intValue2.get(), AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), s / 2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.ectoplasmstar.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.ectoplasmstar.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
    }
}


