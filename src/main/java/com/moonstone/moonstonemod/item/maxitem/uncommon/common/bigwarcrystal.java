package com.moonstone.moonstonemod.item.maxitem.uncommon.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import com.moonstone.moonstonemod.moonstoneitem.Iwar;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class bigwarcrystal extends CommonItem implements Iwar {

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.mayhemcrystal.get())){
                return false;
            }
            if (Handler.hascurio(player, Items.warcrystal.get())){
                return false;
            }
            if (Handler.hascurio(player,this)){
                return false;
            }
        }

        return true;
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID+":bigwarcrystal", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }
}
