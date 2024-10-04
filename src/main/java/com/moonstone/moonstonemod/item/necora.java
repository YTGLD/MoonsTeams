package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.catalyzer;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class necora extends TheNecoraIC {
    public necora() {
    }

    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (!Other.isEmpty()) {
                if (Other.getItem() instanceof catalyzer) {
                    p_150744_.set(new ItemStack(Items.bloodvirus.get()));
                    Other.shrink(1);

                    return true;
                }
            }
        }
        return false;
    }

    private Multimap<Attribute, AttributeModifier> Head(Player player, ItemStack stack){
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        double acc = 0.8;
        if (Handler.hascurio(player, Items.autolytic.get())){
            acc = 0;
        }
        multimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(
                UUID.fromString("00000000-0000-300f-95e1-2830b5159532"),
                "ara",
                -acc,
                AttributeModifier.Operation.MULTIPLY_BASE));

        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                UUID.fromString("00000000-0000-300f-95e1-2830b5159532"),
                "ara",
                3,
                AttributeModifier.Operation.ADDITION));


        return multimap;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(Head(player,stack));

            if (player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
                    (player.level().isDay() &&
                            player.level().canSeeSky(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()))))
            {
                player.setSecondsOnFire(2);
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "ncrdna", uuid, 2, AttributeModifier.Operation.ADDITION);

        CuriosApi
                .addSlotModifier(multimap, "dna", uuid, 2, AttributeModifier.Operation.ADDITION);
        return multimap;
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.bloodvirus.get())) {
                return false;
            }
            if (Handler.hascurio(player, Items.medicinebox.get())) {
                return false;
            }
            return !Handler.hascurio(player, stack.getItem());

        }
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.necora.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.4").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.5").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.necora.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.7").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.8").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.9").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.10").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.11").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
        }else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.translatable("item.necora.tool.string.12").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.13").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.14").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.15").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
        }
    }
}
