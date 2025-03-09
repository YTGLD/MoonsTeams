package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class nightmare_base  extends nightmare {



    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!stack.getOrCreateTag().getBoolean("canDo")) {
            slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
            Random random = new Random();
            ArrayList<Item> items= new ArrayList<>(List.of(
                    Items.nightmare_base_stone.get(),
                    Items.nightmare_base_reversal.get(),
                    Items.nightmare_base_black_eye.get(),

                    Items.nightmare_base_redemption.get(),
                    Items.nightmare_base_fool.get(),
                    Items.nightmare_base_insight.get(),

                    Items.nightmare_base_start.get()
            ));
            for (int i = 0; i < Config.SERVER.nightmareBaseMaxItem.get(); i++) {

                if (!items.isEmpty()) {
                    int index = random.nextInt(items.size());
                    Item selectedItem = items.remove(index);
                    addLoot(slotContext.entity(), selectedItem, stack);
                }
            }
            stack.getOrCreateTag().putBoolean("canDo",true);
        }

    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext,stack));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi.addSlotModifier(linkedHashMultimap, "nightmare", uuid, 3, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }


    public Multimap<Attribute, AttributeModifier> gets(SlotContext slotContext, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        float s= -0.3f;
        if (Handler.hascurio(slotContext.entity(),Items.nightmare_base_reversal_mysterious.get())){
            s = 0;
        }
        if (Handler.hascurio(slotContext.entity(),Items.nightmare_base_redemption_down_and_out.get())){
            s += 0.35f;
        }
        if (Handler.hascurio(slotContext.entity(),Items.nightmare_base_redemption.get())){
            s -= 0.15f;
        }
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("6ac40561-f0e1-3533-ae6f-ce769a09b7f1"), "a", s, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("6ac40561-f0e1-3533-ae6f-ce769a09b7f1"), "a", s, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("6ac40561-f0e1-3533-ae6f-ce769a09b7f1"), "a", s, AttributeModifier.Operation.MULTIPLY_BASE));
        return linkedHashMultimap;
    }


    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return com.moonstone.moonstonemod.Config.SERVER.canUnequipMoonstoneItem.get();
    }
    private void addLoot(Entity entity ,
                         Item itemList,
                         ItemStack stack){
        if (entity instanceof Player player){
            if (stack.getTag()!=null) {
                player.addItem(itemList.getDefaultInstance());
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_,Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("item.nightmare_base.tool.string").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.translatable("item.nightmare_base.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));    }


}

