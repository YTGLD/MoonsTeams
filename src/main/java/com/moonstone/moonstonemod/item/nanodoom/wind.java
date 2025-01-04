package com.moonstone.moonstonemod.item.nanodoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
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
import java.util.UUID;

public class wind extends Doom {

    private float abc ;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(this.Head());
            if (player.isSprinting()){
                if (abc < 0.75) {
                    abc += 0.02f;
                }
            }else if (abc > 0){
                abc -= 0.015f;
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.Head());
    }

    private Multimap<Attribute, AttributeModifier> Head(){
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();


        multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                UUID.fromString("380df991-f603-344c-a090-369bad2a924a"),
                MoonStoneMod.MODID+":wind",
                abc,
                AttributeModifier.Operation.MULTIPLY_BASE));

        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.wind.tool.string").withStyle(ChatFormatting.GOLD));

    }
}

