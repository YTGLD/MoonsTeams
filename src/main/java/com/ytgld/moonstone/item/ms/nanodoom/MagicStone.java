package com.ytgld.moonstone.item.ms.nanodoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.Doom;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class MagicStone extends Doom {
    public final String magic = "Magic";

    public MagicStone(Properties properties) {
        super(properties);
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof Player) {
            final int damage = Mth.nextInt(RandomSource.create(), -7, 21);
            final int kok = Mth.nextInt(RandomSource.create(), -13, 36);
            final int regs = Mth.nextInt(RandomSource.create(), -3, 9);
            if (!stack.get(DataReg.tag).getBooleanOr(magic,false)){
                stack.get(DataReg.tag).putInt("damage", damage);
                stack.get(DataReg.tag).putInt("kok", kok);
                stack.get(DataReg.tag).putInt("regs", regs);
                stack.get(DataReg.tag).putBoolean(magic, true);
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(identifier(), stack.get(DataReg.tag).getIntOr("damage",0) / 100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(identifier(), stack.get(DataReg.tag).getIntOr("kok",0) / 100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(identifier(),  stack.get(DataReg.tag).getIntOr("regs",0) / 100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (stack.get(DataReg.tag)!= null) {
            tooltip.add(Component.translatable("attribute.name.generic.attack_damage").append(stack.get(DataReg.tag).getIntOr("damage",0) + "%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("attribute.name.generic.armor").append(stack.get(DataReg.tag).getIntOr("regs",0) + "%").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("attribute.name.generic.knockback_resistance").append( stack.get(DataReg.tag).getIntOr("kok",0) + "%").withStyle(ChatFormatting.GOLD));
        }
    }
}
