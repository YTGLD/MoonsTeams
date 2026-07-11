package com.ytgld.moonstone.item.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.nightmare.AllTip;
import com.ytgld.moonstone.item.nightmare.NightmareBase;
import com.ytgld.moonstone.item.nightmare.ToolTip;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;
import java.util.function.Consumer;


public class NightmareBaseItemReversal extends NightmareBase implements AllTip {
    public NightmareBaseItemReversal(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.ConfigValue<List<? extends String>> intValue ;
        public static ModConfigSpec.IntValue intValue2;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            builder.push("NightmareBaseItemReversal");
            intValue =  builder.translation("chest_item.config.NightmareBaseItemReversal")
                    .define("number",new ArrayList<>());
            intValue2 =  builder.translation("chest_item.config.NightmareBaseItemReversal2")
                    .defineInRange("number2",3,0,100);
            builder.pop();
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("NightmareBaseItemReversal",
                    "颠倒之物","属性减少的值的黑名单"),
                    new CIString("NightmareBaseItemReversal2",
                    "颠倒之物2","死亡时跌的属性")
            );
        }
    }
    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"轮回之路注定坎坷");
        map.put(2,"复活时给予十秒无敌");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"轮回之路注定坎坷");
        map.put(2,"复活时给予十秒无敌");
        return map;
    }

    public static final String att = "Attrib";

    public static void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, InitItems.nightmare_base_reversal.asItem())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.nightmare_base_reversal.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    float s = 100 - ConfigItem.intValue2.get();
                                    stack.get(DataReg.tag).putInt(att, (int) s);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity().tickCount>=20) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(geta(stack));
            }else {
                slotContext.entity().invulnerableTime = 200;
            }
        }
        if (stack.get(DataReg.tag) != null) {
            if (stack.get(DataReg.tag).getIntOr(att,0) >= 4) {
                if (slotContext.entity() instanceof Player player && !player.getCooldowns().isOnCooldown(stack.getItem().getDefaultInstance())) {
                    stack.get(DataReg.tag).putInt(att, stack.get(DataReg.tag).getIntOr(att,0) - 4);
                    player.getCooldowns().addCooldown(stack.getItem().getDefaultInstance(), 20);
                }
            } else if (stack.get(DataReg.tag).getIntOr(att,0) < 0) {
                stack.get(DataReg.tag).putInt(att, 0);
            }
        } else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(geta(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> geta(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> get = HashMultimap.create();

        if (stack.get(DataReg.tag) != null) {
            double as = -stack.get(DataReg.tag).getIntOr(att,0);
            as /= 100;
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
                    if (attribute != (Attributes.MAX_HEALTH)) {
                        get.put(attribute, new AttributeModifier(Identifier.parse(this.getDescriptionId()), as, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                    }
                }
            }
        }
        return get;
    }


    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.nightmare_base_reversal.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.nightmare_base_reversal.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.nightmare_base_reversal.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_reversal_orb").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_reversal_card").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_reversal_mysterious").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.candle").withStyle(ChatFormatting.DARK_RED));

        builder.accept(Component.literal(""));

        builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
}

