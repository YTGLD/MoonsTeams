package com.ytgld.moonstone.item.ms.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class EctoplasmHorseshoe extends Ectoplasm {

    public EctoplasmHorseshoe(Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(identifier(), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.STEP_HEIGHT, new AttributeModifier(identifier(), 0.5, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

    public static void hurt(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.ectoplasmhorseshoe.get())) {
                if (event.getSource().is(DamageTypes.FALL)) {
                    event.setNewDamage(event.getNewDamage() / 10);
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmshild.get())) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setNewDamage(event.getNewDamage() * 0.7F);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.ectoplasmhorseshoe.tool.string").withStyle(ChatFormatting.GOLD));
    }
}


