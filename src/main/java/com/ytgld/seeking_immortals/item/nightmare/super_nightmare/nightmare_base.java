package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.base.blood_god;
import com.ytgld.seeking_immortals.item.nightmare.base.lead;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.renderer.Light;
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
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public class nightmare_base extends nightmare {

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));

        if (slotContext.entity() instanceof Player player) {
            int kill = SIHandler.getTagNumber(stack, blood_god.giveName_kill);
            int heal = SIHandler.getTagNumber(stack,blood_god.giveName_heal);
            int damage = SIHandler.getTagNumber(stack,blood_god.giveName_damage);
            if (stack.getTag()!=null) {

                if (!stack.getTag().getBoolean(lead.gangBoolean)){
                    if (SIHandler.getTagNumber(stack, lead.dieGive) > 200) {
                        player.addItem(new ItemStack(Items.lead.get()));
                        stack.getTag().putBoolean(lead.gangBoolean,true);
                    }
                }


                if (!stack.getTag().getBoolean(blood_god.give_End)) {
                    if (kill >= Config.SERVER.blood_god_kill.get()
                            && heal >= Config.SERVER.blood_god_heal.get()
                            && damage >= Config.SERVER.blood_god_damage.get()) {
                        player.addItem(new ItemStack(Items.blood_god.get()));
                        stack.getTag().putBoolean(blood_god.give_End,true);
                    }
                }
            }
        }

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getTag() == null) {
            slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
            stack.getOrCreateTag();
        }


        if (!stack.getTag().getBoolean("canDo")) {
            Random random = new Random();
            ArrayList<Item> items = new ArrayList<>(List.of(
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
            stack.getTag().putBoolean("canDo", true);
        }
    }
    public static void healGive(LivingHealEvent event){}
    public static void damageGive(LivingDamageEvent event){}
    public static void killGive(LivingDeathEvent event){}
    public static void die(LivingDeathEvent event){}
    public  Multimap<Attribute, AttributeModifier> gets(SlotContext slotContext) {
         Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        float s = -0.3f;
        if (SIHandler.hascurio(slotContext.entity(), Items.nightmare_base_reversal_mysterious.get())) {
            s = 0;
        }
        if (SIHandler.hascurio(slotContext.entity(), Items.nightmare_base_redemption_down_and_out.get())) {
            s += 0.35f;
        }
        if (SIHandler.hascurio(slotContext.entity(), Items.nightmare_base_redemption.get())) {

            double ssa = Config.SERVER.nightmare_base_redemption.get();
            ssa/= 100f;
            s -= (float) ssa;
        }
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("dfcd300e-b19e-40cf-816d-163b4932832e"),"a", s, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("dfcd300e-b19e-40cf-816d-163b4932832e"),"a", s, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("dfcd300e-b19e-40cf-816d-163b4932832e"),"a", s, AttributeModifier.Operation.MULTIPLY_BASE));
        return linkedHashMultimap;
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).resolve().isPresent()
                    && CuriosApi.getCuriosInventory(player).resolve().get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    private void addLoot(Entity entity,
                         Item itemList,
                         ItemStack stack) {
        if (entity instanceof Player player) {
            if (stack.getTag() != null) {
                player.addItem(itemList.getDefaultInstance());
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare",
                        uuid, 3, AttributeModifier.Operation.ADDITION);

        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base.tool.string").withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,255,50,255);
    }
}

