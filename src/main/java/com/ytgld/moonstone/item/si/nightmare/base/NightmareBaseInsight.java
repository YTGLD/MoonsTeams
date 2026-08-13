package com.ytgld.moonstone.item.si.nightmare.base;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.AllTip;
import com.ytgld.moonstone.item.si.nightmare.NightmareBase;
import com.ytgld.moonstone.item.si.nightmare.ToolTip;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;
import java.util.function.Consumer;


public class NightmareBaseInsight extends NightmareBase implements AllTip {
    public NightmareBaseInsight(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this, stack));
    }

    public static void exp(LivingExperienceDropEvent event) {
        if (Handler.hascurio(event.getAttackingPlayer(), Items.nightmare_base_insight.get())) {
            event.setDroppedExperience(event.getDroppedExperience() * 2);
        }
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "你感受到了另类的气息");
        map.put(2, "增加百分之百经验掉落");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "你感受到了另类的气息");
        map.put(2, "增加百分之百经验掉落");
        return map;
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag) != null) {
            if (stack.get(DataReg.tag).getBoolean("give_nightmare_base_insight_drug")) {
                return;
            }
            if (!stack.get(DataReg.tag).getBoolean("give_nightmare_base_insight_drug")) {
                if (slotContext.entity() instanceof Player player) {
                    List<Integer> integers = new ArrayList<>();
                    int a = 0;
                    Collection<MobEffectInstance> collection = player.getActiveEffects();
                    if (!collection.isEmpty()) {
                        for (MobEffectInstance effectInstance : collection) {
                            if (effectInstance.getEffect().value().isBeneficial()) {
                                integers.add(1);
                            }
                        }
                    }
                    for (int ignored : integers) {
                        a++;
                    }
                    if (a >= 9) {
//                        giveItem(player,new ItemStack(Items.nightmare_base_insight_drug.get()));
                        stack.get(DataReg.tag).putBoolean("give_nightmare_base_insight_drug", true);
                    }
                }
            }
        } else {
            stack.set(DataReg.tag, new CompoundTag());
        }
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.nightmare_base_insight.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.nightmare_base_insight.tool.string.1").withStyle(ChatFormatting.DARK_RED));

        builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_insight_collapse").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_insight_insane").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_insight_drug").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.ring").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.hidden_blade").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

}
