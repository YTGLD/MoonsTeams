package com.ytgld.moonstone.item.nightmare.base;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.nightmare.AllTip;
import com.ytgld.moonstone.item.nightmare.NightmareBase;
import com.ytgld.moonstone.item.nightmare.ToolTip;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;
import java.util.function.Consumer;


public class NightmareBaseRedemption extends NightmareBase implements AllTip {
    public NightmareBaseRedemption(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"主啊 请赐予我力量");
        map.put(2,"增加十的伤害 生命 护甲");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"主啊 请赐予我力量");
        map.put(2,"增加十的伤害 生命 护甲");
        return map;
    }
//
//    @Override
//    public void curioTick(SlotContext slotContext, ItemStack stack) {
//        super.curioTick(slotContext, stack);
//        if (slotContext.entity() instanceof Player player) {
//            if (player.level() instanceof ServerLevel serverLevel) {
//                if (serverLevel.getRaidAt(player.blockPosition()) != null && serverLevel.getRaidAt(player.blockPosition()).isLoss()) {
//                    if (stack.get(DataReg.tag) != null && !stack.get(DataReg.tag).getBooleanOr(AdvancementEvt.nightmare_base_redemption_down_and_out)) {
//                        giveItem(player,new ItemStack(Items.nightmare_base_redemption_down_and_out.get()));
//                        stack.get(DataReg.tag).putBoolean(AdvancementEvt.nightmare_base_redemption_down_and_out, true);
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.nightmare_base_redemption.tool.string").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_redemption_deception").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_redemption_degenerate").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.nightmare_base_redemption_down_and_out").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.translatable("item.moonstone.hypocritical_self_esteem").withStyle(ChatFormatting.DARK_RED));
        builder.accept(Component.literal(""));

        builder.accept(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, Identifier id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = super.getAttributeModifiers(slotContext, id, stack);
        float c = 10;
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(id, c, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(id, c, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(id, c, AttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }
}

