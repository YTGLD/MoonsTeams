package com.ytgld.moonstone.item.si.nightmare.reversal;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class ReversalMysterious extends NightmareSmall {

    public ReversalMysterious(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.ConfigValue<List<String>> intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("chest_item.config.ReversalMysterious")
                    .define("ReversalMysterious", new ArrayList<>(List.of("")));
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("ReversalMysterious",
                    "神秘之物", "黑名单")
            );
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        Multimap<Holder<Attribute>, AttributeModifier> get = HashMultimap.create();
        double as = -0.1f;
        Set<String> blacklist = new HashSet<>();
        for (String aaa : ConfigItem.intValue.get()) {
            String[] parts = aaa.split(":");
            if (parts.length > 0) {
                blacklist.add(parts[0] + ":" + parts[1]);
            }
        }
        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            Identifier attributeId = BuiltInRegistries.ATTRIBUTE.getKey(attribute.value());
            if (attributeId != null && !blacklist.contains(attributeId.toString())) {
                get.put(attribute, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), as, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            }
        }
        return get;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            CompoundTag tag = stack.get(DataReg.tag);
            if (tag != null) {
                if (SIHandler.hascurio(slotContext.entity(), this)) {
                    player.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers());
                }
            } else {
                stack.set(DataReg.tag, new CompoundTag());
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        if (slotContext.entity() instanceof Player player) {

            player.getAttributes().removeAttributeModifiers(this.getAttributeModifiers());
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal_mysterious.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}

