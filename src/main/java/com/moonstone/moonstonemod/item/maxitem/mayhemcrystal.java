package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.Iwar;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class mayhemcrystal extends UnCommonItem  implements Iwar, Die {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {


        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s  = 0.3f;
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_stone_meet.get())) {
            s*=1.5f;
        }
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"uuid", s, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }
}

