package com.ytgld.moonstone.item.ms.nanodoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ms.Doom;
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
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Wind extends Doom {

    private float abc;

    public Wind(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().addTransientAttributeModifiers(this.Head());
            if (player.isSprinting()) {
                if (abc < 0.75) {
                    abc += 0.02f;
                }
            } else if (abc > 0) {
                abc -= 0.015f;
            }
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.Head());
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head() {
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();


        multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(identifier(),
                abc,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.wind.tool.string").withStyle(ChatFormatting.GOLD));

    }
}

