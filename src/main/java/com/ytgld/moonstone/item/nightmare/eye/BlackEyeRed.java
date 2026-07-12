package com.ytgld.moonstone.item.nightmare.eye;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.nightmare.NightmareSmall;
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
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

import static com.ytgld.moonstone.item.nightmare.NightmareBase.ITEMCategory;

public class BlackEyeRed extends NightmareSmall {
    public static final String aty = "NightmareRed";

    public BlackEyeRed(Properties properties) {
        super(properties);
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue ;
        public static ModConfigSpec.ConfigValue<List<String>> intValue2;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue =  builder.translation("chest_item.config.BlackEyeRed")
                    .defineInRange("BlackEyeRed",20f,0,Integer.MAX_VALUE);
            intValue2 =  builder.translation("chest_item.config.BlackEyeRed2")
                    .define("BlackEyeRed2",new ArrayList<>());
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("BlackEyeRed",
                    "口红","最大属性"),
                    new CIString("BlackEyeRed2",
                            "口红2","黑名单属性"));
        }
    }
    public static void kill(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, InitItems.nightmare_base_black_eye_red.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.nightmare_base_black_eye_red.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    float s  = ConfigItem.intValue.get().floatValue();
                                    if (stack.get(DataReg.tag).getIntOr(aty,0) < s) {
                                        stack.get(DataReg.tag).putInt(aty, stack.get(DataReg.tag).getIntOr(aty,0) + 3);
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
        super.onEquip(slotContext,prevStack,stack);
        if (!slotContext.entity().level().isClientSide()) {
            if (SIHandler.hascurio(slotContext.entity(), this)) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag,new CompoundTag());
        } else {
            if (slotContext.entity().tickCount % 20 == 1) {
                if (stack.get(DataReg.tag).getIntOr(aty,0) > 0) {
                    stack.get(DataReg.tag).putInt(aty, stack.get(DataReg.tag).getIntOr(aty,0) - 1);
                }
            }
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> get = HashMultimap.create();
        if (stack.get(DataReg.tag) != null) {
            Set<String> blacklist = new HashSet<>();
            for (String aaa : ConfigItem.intValue2.get()) {
                String[] parts = aaa.split(":");
                if (parts.length > 0) {
                    blacklist.add(parts[0] + ":" + parts[1]);
                }
            }
            double as = stack.get(DataReg.tag).getIntOr(aty,0) / 100f;
            for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
                Identifier attributeId = BuiltInRegistries.ATTRIBUTE.getKey(attribute.value());
                if (attributeId != null && !blacklist.contains(attributeId.toString())) {
                    get.put(attribute, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.descriptionId), as, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
            }
        }
        return get;
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string").withStyle(ChatFormatting.DARK_RED));
        float s  = ConfigItem.intValue.get().floatValue();
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string.1",s).withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }
}
