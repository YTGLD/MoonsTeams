package com.ytgld.moonstone.item.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.config.ConfigPlugin;
import com.ytgld.moonstone.config.RegisterItemConfig;
import com.ytgld.moonstone.item.nightmare.AllTip;
import com.ytgld.moonstone.item.nightmare.NightmareBase;
import com.ytgld.moonstone.item.nightmare.ToolTip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;
import java.util.function.Consumer;

public class NightmareBaseFool extends NightmareBase implements AllTip {
    public NightmareBaseFool(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }
    @ConfigPlugin
    public static class ConfigItem implements RegisterItemConfig {
        public static ModConfigSpec.DoubleValue intValue ;
        @Override
        public void config(ModConfigSpec.Builder builder) {
            intValue =  builder.translation("chest_item.config.NightmareBaseFool")
                    .defineInRange("NightmareBaseFool",0.5f,0,Integer.MAX_VALUE);
        }

        @Override
        public String theCategory() {
            return ITEMCategory;
        }

        @Override
        public List<CIString> theLanguageProvider() {
            return List.of(new CIString("NightmareBaseFool",
                    "愚者之危","属性衰败"));
        }
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你似乎得到了两级的时运加成");
        map.put(2,"你似乎得到了两级的抢夺加成");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你似乎得到了2级的时运加成");
        map.put(2,"你似乎得到了2级的抢夺加成");
        return map;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;

    }

    @Override
    public int getLootingLevel(SlotContext slotContext, @Nullable LootContext lootContext, ItemStack stack) {
        return 2;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();

        {
            List<Integer> integersATTACK_DAMAGE = new ArrayList<>();
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            integersATTACK_DAMAGE.add(1);
                        }
                    }
                }
            });
            float dam = 0;
            for (int ignored : integersATTACK_DAMAGE) {
                dam++;
            }
            int j = 5;
//            if (Handler.hascurio(living, Items.nightmare_base_fool_soul.get())) {
//                j += 9;
//            }
            dam -= j;
            if (dam < 0) {
                dam = 0;
            }
            dam /= 100f;
            dam *= 2;

            dam = -dam;
            double s = ConfigItem.intValue.getAsDouble();
            if (dam <= -s) {
                dam = (float) -s;
            }
            linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.parse(this.descriptionId), dam, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        {
            List<Integer> integersHealth = new ArrayList<>();
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            integersHealth.add(1);
                        }
                    }
                }
            });
            float health = 0;
            for (int ignored : integersHealth) {
                health++;
            }
            int j = 2;
//            if (SIHandler.hascurio(living, Items.nightmare_base_fool_soul.get())) {
//                j += 7;
//            }
            health -= j;
            if (health < 0) {
                health = 0;
            }
            health /= 100f;
            health *= 1;
            health = -health;
            double s = ConfigItem.intValue.getAsDouble();

            if (health <= -s) {
                health = (float) -s;
            }
            linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.parse(this.descriptionId), health, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }


        return linkedHashMultimap;
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
       builder.accept(Component.translatable("item.nightmare_base_fool.tool.string").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.translatable("item.nightmare_base_fool.tool.string.1").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.literal(""));
       builder.accept(Component.translatable("item.nightmare_base_fool.tool.string.2").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.literal(""));
       builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
       builder.accept(Component.translatable("item.moonstone.nightmare_base_fool_betray").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.translatable("item.moonstone.nightmare_base_fool_bone").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.translatable("item.moonstone.nightmare_base_fool_soul").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.translatable("item.moonstone.apple").withStyle(ChatFormatting.DARK_RED));
       builder.accept(Component.literal(""));

       builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}

