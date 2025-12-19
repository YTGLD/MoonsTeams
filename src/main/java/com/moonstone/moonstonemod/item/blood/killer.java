package com.moonstone.moonstonemod.item.blood;

import com.all.IRedItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

public class killer extends TheNecoraIC implements IRedItem {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()==null){
            stack.getOrCreateTag();
        }
        if (slotContext.entity() instanceof Player player){

            float lv = player.getHealth() / player.getMaxHealth();

            lv *= 100;
            int now = (int) (100 -(lv));
            if (stack.getTag()==null){
                stack.getOrCreateTag();
            }
            if (stack.getTag()!=null){
                stack.getTag().putInt(uDead,now);
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


    public Multimap<Attribute, AttributeModifier> ad(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.getTag()!=null) {
            int lvl = stack.getTag().getInt(uDead);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal.get(),new AttributeModifier(UUID.fromString("0dba4657-9c98-3a17-999f-e412cc5ea8b2"), "s",
                                    heal, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(UUID.fromString("0dba4657-9c98-3a17-999f-e412cc5ea8b2"),"a",
                        speed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(UUID.fromString("0dba4657-9c98-3a17-999f-e412cc5ea8b2"),"a",
                        damage, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_SPEED,new AttributeModifier(UUID.fromString("0dba4657-9c98-3a17-999f-e412cc5ea8b2"),"a",
                        attSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ARMOR,new AttributeModifier(UUID.fromString("0dba4657-9c98-3a17-999f-e412cc5ea8b2"),"a",
                        armor, AttributeModifier.Operation.MULTIPLY_BASE));



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
        if (p_41421_.getTag()!=null) {
            int lvl = p_41421_.getTag().getInt(uDead);
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

