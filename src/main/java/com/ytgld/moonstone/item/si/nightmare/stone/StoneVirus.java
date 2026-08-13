package com.ytgld.moonstone.item.si.nightmare.stone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

import static com.ytgld.moonstone.item.si.nightmare.NightmareBase.ITEMCategory;

public class StoneVirus extends NightmareSmall {

    public StoneVirus(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("moonstone.config.StoneVirus")
                    .defineInRange("StoneVirus", 33f, 0, Integer.MAX_VALUE);
            intValue2 = builder.translation("moonstone.config.StoneVirus2")
                    .defineInRange("StoneVirus2", 10f, 0, Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(
                    new CIString("StoneVirus",
                            "融合病毒", "属性"),
                    new CIString("StoneVirus2",
                            "融合病毒2", "生命值")
            );
        }
    }

    public static void h(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone_virus.get())) {
                player.setHealth((float) (player.getHealth() - player.getMaxHealth() * (ConfigItem.intValue2.get().doubleValue() / 100f)));
            }
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = HashMultimap.create();
        float v = ConfigItem.intValue.get().floatValue();
        v /= 100f;
        attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()), v, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attributeModifiers.put(AttReg.heal, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()), v, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attributeModifiers.put(AttReg.cit, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId()), v, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return attributeModifiers;

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext, prevStack, stack);
        if (SIHandler.hascurio(slotContext.entity(), this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers());
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers());

    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        float v = ConfigItem.intValue.get().floatValue();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string", v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.1", v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.2", v).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.3").withStyle(ChatFormatting.DARK_RED));
    }

}
