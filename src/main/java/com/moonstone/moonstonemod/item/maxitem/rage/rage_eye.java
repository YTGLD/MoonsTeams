package com.moonstone.moonstonemod.item.maxitem.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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

import java.util.List;
import java.util.UUID;

public class rage_eye extends Item implements ICurioItem, Blood {
    public rage_eye() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    public static final String health= "RageHealth";
    public static final String damage= "RageDamage";
    public static final String armor= "RageArmor";

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        double b  = Config.SERVER.rage_eye.get();
        float a  = (float) b;
        double c  =  Config.SERVER.rage_eye_copy.get();
        float copy  = (float) c;

        if (stack.getTag()==null){
            stack.getOrCreateTag();
        }
        if (!(entity instanceof Player )){
            if (stack.getTag()!=null) {
                if (entity instanceof LivingEntity living) {
                    if (living.getAttribute(Attributes.MAX_HEALTH) != null) {
                        if (living.getAttribute(Attributes.MAX_HEALTH).getBaseValue()*copy < player.getAttributeValue(Attributes.MAX_HEALTH) * a) {
                            stack.getTag().putFloat(health, (float) living.getAttribute(Attributes.MAX_HEALTH).getBaseValue()*copy);
                        } else {
                            stack.getTag().putFloat(health, (float) player.getAttributeValue(Attributes.MAX_HEALTH) * a);
                        }
                    }
                    if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                        if (living.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue()*copy < player.getAttributeValue(Attributes.ATTACK_DAMAGE) * a) {
                            stack.getTag().putFloat(damage, (float) living.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue()*copy);
                        } else {
                            stack.getTag().putFloat(damage, (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * a);

                        }
                    }
                    if (living.getAttribute(Attributes.ARMOR) != null) {
                        if (living.getAttribute(Attributes.ARMOR).getBaseValue()*copy < player.getAttributeValue(Attributes.ARMOR) * a) {
                            stack.getTag().putFloat(armor, (float) living.getAttribute(Attributes.ARMOR).getBaseValue()*copy);
                        } else {
                            stack.getTag().putFloat(armor, (float) player.getAttributeValue(Attributes.ARMOR) * a);
                        }
                    }
                }
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("item.rage_eye.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.rage_eye.tool.string.4").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));

    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (stack.getTag()!=null) {
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid,"a", stack.getTag().getFloat(health), AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid,"a",  stack.getTag().getFloat(armor), AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"a",  stack.getTag().getFloat(damage), AttributeModifier.Operation.ADDITION));

        }
        return modifierMultimap;
    }

}

