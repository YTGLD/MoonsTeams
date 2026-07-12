package com.ytgld.moonstone.item.ms.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.ms.BloodItem;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.text.DecimalFormat;
import java.util.List;

public class Killer extends BloodItem {
    public Killer(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Handler.stackCreateTag(stack);
        if (slotContext.entity() instanceof Player player){

            float lv = player.getHealth() / player.getMaxHealth();

            lv *= 100;
            int now = (int) (100 -(lv));
            if (stack.get(DataReg.tag)!=null){
                stack.get(DataReg.tag).putInt(uDead,now);
            }

            player.getAttributes().addTransientAttributeModifiers(ad(stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(ad(stack));
        }
    }


    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(DataReg.tag)!=null) {
            int lvl = stack.get(DataReg.tag).getIntOr(uDead,0);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal,new AttributeModifier(identifier(),
                                    heal, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(identifier(),
                        speed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(identifier(),
                        damage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED,new AttributeModifier(identifier(),
                        attSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR,new AttributeModifier(identifier(),
                        armor, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));



        }
        return modifiers;
    }
    public static final String  uDead = "undead";

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.killer.tool.string.1").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.killer.tool.string.2").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.killer.tool.string.3").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.killer.tool.string.4").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.killer.tool.string.5").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.killer.tool.string.6").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.literal(""));
        if (p_41421_.get(DataReg.tag)!=null) {
            int lvl = p_41421_.get(DataReg.tag).getIntOr(uDead,0);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;
            p_41423_.add(Component.translatable("item.killer.tool.string.7").withStyle(ChatFormatting.DARK_RED));
            DecimalFormat df = new DecimalFormat("#.###");

            p_41423_.add(Component.translatable("item.killer.tool.string.8").append(df.format(heal * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            p_41423_.add(Component.translatable("item.killer.tool.string.9").append(df.format(speed * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            p_41423_.add(Component.translatable("item.killer.tool.string.10").append(df.format(damage * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            p_41423_.add(Component.translatable("item.killer.tool.string.11").append(df.format(attSpeed * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            p_41423_.add(Component.translatable("item.killer.tool.string.12").append(df.format(armor * 100) +"%").withStyle(ChatFormatting.DARK_RED));
        }
        p_41423_.add(Component.literal("")); }

}

