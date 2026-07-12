package com.ytgld.moonstone.item.ms.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.common.DropRule;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class PrisonOfSin extends BloodItem {
    public static final String killNameAndSize = "killNameAndSize";

    public PrisonOfSin(Properties properties) {
        super(properties);
    }

    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue;
        public static ModConfigSpec.DoubleValue intValue2;
        public static ModConfigSpec.DoubleValue intValue3;
        public static ModConfigSpec.ConfigValue<List<String>> intValue4;

        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue = builder.translation("chest_item.config.PrisonOfSin")
                    .defineInRange("PrisonOfSin", 4.5f, 0, Integer.MAX_VALUE);
            intValue2 = builder.translation("chest_item.config.PrisonOfSin2")
                    .defineInRange("PrisonOfSin2", 100F, 0, Integer.MAX_VALUE);

            intValue3 = builder.translation("chest_item.config.PrisonOfSin3")
                    .defineInRange("PrisonOfSin3", 0.8F, 0, Integer.MAX_VALUE);

            intValue4 = builder.translation("chest_item.config.PrisonOfSin4")
                    .define("PrisonOfSin4", new ArrayList<>(List.of("minecraft:max_health")));
        }

        @Override
        public String theCategory() {
            return ITEMCategoryBloodItem;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("PrisonOfSin",
                            "罪孽囚笼", "罪孽囚笼的属性增长倍数"),
                    new CIString("PrisonOfSin2",
                            "罪孽囚笼2", "罪孽囚笼的属性增长最大值，单位是“%”，（因为默认罪孽囚笼给予了10%，所以应该比默认值高10%）"),
                    new CIString("PrisonOfSin3",
                            "罪孽囚笼3", "罪孽囚笼的生命值和护甲的属性衰败"),
                    new CIString("PrisonOfSin4",
                            "罪孽囚笼4", "属性黑名单")
            );
        }
    }

    public static void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.the_prison_of_sin.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            if (stack.is(Items.the_prison_of_sin.get())) {
                                if (stack.get(DataReg.tag) != null
                                        && event.getEntity().getEncodeId() != null) {
                                    String name = event.getEntity().getEncodeId();

                                    if (stack.get(DataReg.tag).getString(name).isEmpty()) {
                                        int size = stack.get(DataReg.tag).size();
                                        float incrementFactor = (float) Math.sqrt(size);
                                        float s = (float) (double) ConfigItem.intValue.get().floatValue();
                                        incrementFactor *= s;
                                        stack.get(DataReg.tag).putFloat(killNameAndSize,
                                                incrementFactor);

                                        stack.get(DataReg.tag).putString(name, name);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
    }

    @Override
    public @NonNull DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return DropRule.ALWAYS_KEEP;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        Handler.stackCreateTag(stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s = 110;
        if (stack.get(DataReg.tag) != null) {
            float is = stack.get(DataReg.tag).getFloatOr(killNameAndSize, 0);
            ;
            if (is > ConfigItem.intValue2.get()) {
                is = (float) (double) ConfigItem.intValue2.get();
            }
            s += is;
        }
        Set<String> blacklist = new HashSet<>();
        for (String aaa : ConfigItem.intValue4.get()) {
            String[] parts = aaa.split(":");
            if (parts.length > 0) {
                blacklist.add(parts[0] + ":" + parts[1]);
            }
        }
        s -= 100f;
        s /= 100f;
        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            Identifier attributeId = BuiltInRegistries.ATTRIBUTE.getKey(attribute.value());
            if (attributeId != null && !blacklist.contains(attributeId.toString())) {
                modifierMultimap.put(attribute, new AttributeModifier(identifier(), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            }
        }
        return modifierMultimap;
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s = (float)(double) ConfigItem.intValue3.get().floatValue();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(identifier(), -s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(identifier(), -s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (pStack.get(DataReg.tag) != null) {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            float s = 110;
            if (pStack.get(DataReg.tag) != null) {
                float is = pStack.get(DataReg.tag).getFloatOr(killNameAndSize, 0);
                ;
                if (is > ConfigItem.intValue2.get()) {
                    is = (float) (double) ConfigItem.intValue2.get();
                }
                s += is;
            }
            s -= 100f;
            s /= 100f;
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.5").append(String.valueOf(s * 100)).append("%").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
        } else {

            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.the_prison_of_sin.tool.string.5").append(String.valueOf(0)).append("%").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
        }
    }
}
