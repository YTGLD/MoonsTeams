package com.ytgld.moonstone.item.si.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.AllTip;
import com.ytgld.moonstone.item.si.nightmare.NightmareBase;
import com.ytgld.moonstone.item.si.nightmare.ToolTip;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class NightmareBaseStart extends NightmareBase implements AllTip {
    public NightmareBaseStart(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.IntValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.NightmareBaseStart")
                    .defineInRange("NightmareBaseStart", 50, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("NightmareBaseStart",
                    "噩梦之起始", "治疗"));
        }
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this, stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "你被赐予了异常的恢复能力");
        map.put(2, "增加百分之五十治疗");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "你被赐予了异常的恢复能力");
        map.put(2, "增加百分之五十治疗");
        return map;
    }

    public static void damage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_start.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_start.get().getDefaultInstance())) {
                    if (player.getHealth() > 10) {
                        player.setHealth(player.getHealth() - 1);
                        player.getCooldowns().addCooldown(Items.nightmare_base_start.get().getDefaultInstance(), 10);
                    }
                }

            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(), this))
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        int s = ConfigItem.intValue.getAsInt();
        float d = s / 100f;

        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(
                Identifier.parse(this.descriptionId), -d, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        linkedHashMultimap.put(AttReg.heal, new AttributeModifier(Identifier.parse(this.descriptionId), 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return linkedHashMultimap;
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.nightmare_base_start.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.nightmare_base_start.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.nightmare_base_start.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_start_power").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_start_pod").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_start_egg").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.wolf").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));

        builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}


