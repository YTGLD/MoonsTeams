package com.ytgld.moonstone.item.si.nightmare.stone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.function.Consumer;

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class EndBone extends NightmareSmall {
    public EndBone(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.EndBone")
                    .defineInRange("EndBone", 7f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("EndBone",
                    "末世脊骨", "伤害倍率"));
        }
    }

    public static void hurts(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.end_bone.get())) {
                if (player.getHealth() >= player.getMaxHealth()) {
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        float s = (float) ConfigItem.intValue.get().doubleValue();
                        living.hurt(living.damageSources().dryOut(), event.getNewDamage() * s);
                    }
                    event.setNewDamage(event.getNewDamage() * 0.2f);
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(slotContext.entity()));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        if (!slotContext.entity().level().isClientSide()) {
            slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(slotContext.entity()));
        }
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        float s = (float) ConfigItem.intValue.get().doubleValue();
        s *= 100f;
        builder.accept(Component.translatable("item.end_bone.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.end_bone.tool.string.1", s).withStyle(ChatFormatting.DARK_RED));

    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(LivingEntity living) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = HashMultimap.create();
        float s = 0;
        if (living.getHealth() >= living.getMaxHealth()) {
            s -= 0.5f;
        } else {
            s = 0;
        }
        attributeModifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        return attributeModifiers;

    }

}
