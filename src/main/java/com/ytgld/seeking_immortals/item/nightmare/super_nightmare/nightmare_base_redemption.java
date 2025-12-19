package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.all.INightItem;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

import static com.ytgld.seeking_immortals.event.old.AdvancementEvt.giveItem;

public class nightmare_base_redemption extends nightmare implements SuperNightmare, AllTip, INightItem {
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
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).resolve().isPresent()
                    && CuriosApi.getCuriosInventory(player).resolve().get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
            if (Config.SERVER.canUnequipMoonstoneItem.get()) {
                return true;
            }
        }
        return false;
    }



    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof Player player) {
            if (player.level() instanceof ServerLevel serverLevel) {
                if (serverLevel.getRaidAt(player.blockPosition()) != null && serverLevel.getRaidAt(player.blockPosition()).isLoss()) {
                    if (stack.getTag() != null && !stack.getTag().getBoolean(AdvancementEvt.nightmare_base_redemption_down_and_out)) {
                        giveItem(player,new ItemStack(Items.nightmare_base_redemption_down_and_out.get()));
                        stack.getTag().putBoolean(AdvancementEvt.nightmare_base_redemption_down_and_out, true);
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_redemption_deception").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_redemption_degenerate").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_redemption_down_and_out").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.hypocritical_self_esteem").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
    public UUID uuid = UUID.fromString("53b25143-6fef-4b26-9dca-80ff22373591");
    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        AttributeModifier attributeModifier =  new AttributeModifier(uuid, this.getDescriptionId(), 1, AttributeModifier.Operation.ADDITION);
        if (p_41406_ instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("nightmare").ifPresent(stacks -> {
                for (UUID uuid : stacks.getModifiers().keySet()){
                    if (uuid == this.uuid){
                        return;
                    }
                }
                stacks.addPermanentModifier(attributeModifier);
            }));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid,"asd", 10, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"asd", 10, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid,"asd", 10, AttributeModifier.Operation.ADDITION));
        return linkedHashMultimap;
    }
}
