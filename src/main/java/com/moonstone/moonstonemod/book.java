package com.moonstone.moonstonemod;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public class    book extends UnCommonItem {


    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !Handler.hascurio(slotContext.entity(),this);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio",uuid, 1, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(Head(player,stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().removeAttributeModifiers(Head(player,stack));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()==null) {
            stack.getOrCreateTag();
        }
    }

    public static final String nineSword = "nineSword";
    public static final String MoDiBlood = "MoDiBlood";

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (ModList.get().isLoaded("patchouli")){

            if (p_41433_ instanceof ServerPlayer player){
                PatchouliAPI.get().openBookGUI(player,new ResourceLocation(MoonStoneMod.MODID,"soul_book"));
            }
        }else {
            p_41433_.displayClientMessage(Component.translatable("moonstone.book.error").withStyle(ChatFormatting.RED), true);
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.book.tool.string").withStyle(ChatFormatting.GOLD));
            if (pStack.getTag()!=null){
                if (pStack.getTag().getInt(nineSword)>=300){
                    pTooltipComponents.add(Component.translatable("item.book.tool.string.nine_sword").withStyle(ChatFormatting.AQUA));
                }
                if (pStack.getTag().getInt(MoDiBlood)>=100){
                    pTooltipComponents.add(Component.translatable("item.book.tool.string.modi_blood").withStyle(ChatFormatting.AQUA));
                }
            }

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.GOLD));
        }
    }
    private Multimap<Attribute, AttributeModifier> Head(Player player, ItemStack stack){
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();

        if (player.getMainHandItem().getItem() instanceof SwordItem) {
            if (stack.getTag()!=null&&stack.getTag().getInt(nineSword)>=300) {
                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        UUID.fromString("abd58e08-2462-379a-81f3-fa94df810a52"),
                        "s",
                        0.1,
                        AttributeModifier.Operation.MULTIPLY_BASE));

                multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                        UUID.fromString("abd58e08-2462-379a-81f3-fa94df810a52"),
                        "s",
                        0.1,
                        AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }

        return multimap;
    }

    public static void hurt(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, com.moonstone.moonstonemod.init.Items.book.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()&&stack.is(  com.moonstone.moonstonemod.init.Items.book.get())){
                                if (stack.getTag()!=null){
                                    if (player.getMainHandItem().isEmpty()) {
                                        if (stack.getTag().getInt(MoDiBlood) <= 100) {
                                            stack.getTag().putInt(MoDiBlood, stack.getTag().getInt(MoDiBlood) + 1);
                                        }
                                    }
                                }


                                if (player.getMainHandItem().getItem() instanceof SwordItem){
                                    if (stack.getTag()!=null){
                                        if (stack.getTag().getInt(nineSword)<=300){
                                            stack.getTag().putInt(nineSword,stack.getTag().getInt(nineSword)+1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
