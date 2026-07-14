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
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
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

public class NightmareBaseStone extends NightmareBase implements AllTip {

    public NightmareBaseStone(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.IntValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.NightmareBaseStone")
                    .defineInRange("NightmareBaseStone", 5, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("NightmareBaseStone",
                    "死兆方尖碑", "伤害倍数"));
        }
    }

    public static void LivingHurtEvent(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.candle.asItem())) {
                return;
            }
            if (Handler.hascurio(player, Items.nightmare_base_stone.get())) {

                if (player.getHealth() >= player.getMaxHealth()) {

                    double d = ConfigItem.intValue.getAsInt();
                    event.setNewDamage((float) (event.getNewDamage() * d + 1));

                    if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_stone.get().getDefaultInstance())) {
                        if (event.getNewDamage() > player.getHealth()) {
                            event.setNewDamage(0);
                            player.setHealth(1);
                            player.getCooldowns().addCooldown(Items.nightmare_base_stone.get().getDefaultInstance(), 200);
                        }
                    }
                }
            }
        }
    }

    public static final String uDead = "undead";

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide()) {
                float lv = player.getHealth() / player.getMaxHealth();
                if (lv > 1) {
                    lv = 1;
                }
                lv *= 100;
                int now = (int) (100 - (lv));
                if (stack.get(DataReg.tag) == null) {
                    stack.set(DataReg.tag, new CompoundTag());
                }
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putInt(uDead, now);
                }
            }
            if (slotContext.entity() instanceof Player) {
                if (!player.level().isClientSide()) {
                    player.getAttributes().addTransientAttributeModifiers(ad(stack));
                }
            }
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        slotContext.entity().getAttributes().removeAttributeModifiers(ad(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(DataReg.tag) != null) {
            int lvl = stack.get(DataReg.tag).getIntOr(uDead, 0);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;


            modifiers.put(AttReg.heal, new AttributeModifier(Identifier.parse(this.descriptionId),
                    heal, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.parse(this.descriptionId),
                    speed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.parse(this.descriptionId),
                    damage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(Identifier.parse(this.descriptionId),
                    attSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(Identifier.parse(this.descriptionId),
                    armor, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


        }
        return modifiers;
    }


    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.nightmare_base_stone.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.nightmare_base_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.nightmare_base_stone.tool.string.1").withStyle(ChatFormatting.RED));

        builder.accept(Component.translatable("item.moonstone.nightmare_base_stone_virus").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_stone_meet").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_stone_brain").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.end_bone").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));

        builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this, stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "这是试炼  磨练你的意志");
        map.put(2, "生命值越低 各方面属性越高");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "这是试炼  磨练你的意志");
        map.put(2, "生命值越低 各方面属性越高");
        return map;
    }
}

