package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ytgld_virus extends TheNecoraIC {
    /**
     * 研究点数：当玩家研究点数到最大值后，清除负面效果，获得所有加强
     */
    public static final String research = "research";//研究点数
    /**
     * researchMaxSize是最大的研究点数
     */
    public static final int researchMaxSize = 10000;//最大研究点数
    /**
     * 研究点数一次基于多少个
     */
    public static final int researchAddSize = 10;//每次增加的点数
    /**
     * 诅咒点数：玩家只要佩戴了病毒就会增加，直到最大值
     * 这个点数减少玩家属性
     */
    public static final String curse = "curse";//病毒
    /**
     * 诅咒的最大值
     */
    public static final int curseMaxSize = 10000;//最大病毒
    /**
     * 每隔几秒来增加一下诅咒点数，10就是0.5秒
     */
    public static final int curseAddTime = 15;//每隔多少秒加病毒点数
    /**
     * 病毒阶数
     */
    public static final int Rating = 3;

    /**
     * 病毒的等级ID
     */
    public static final String RatingID = "RatingID";

    public static void addLvl (ItemStack ytgldV, String canNot){
        if (ytgldV.getTag() != null) {
            if (!ytgldV.getTag().getBoolean(canNot)) {
                ytgldV.getTag().putInt(RatingID, ytgldV.getTag().getInt(RatingID) + 1);
                ytgldV.getTag().putBoolean(canNot,true);
            }
        }
    }
    public static int getLvl (ItemStack ytgldV){
        if (ytgldV.getTag() != null) {
            return  ytgldV.getTag().getInt(RatingID);
        }
        return  0;
    }
    public static void ytgld_virusLivingDeathEvent(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.ytgld_virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.ytgld_virus.get())) {
                                CompoundTag compoundTag = stack.getTag();
                                if (compoundTag != null) {
                                    if (getLvl(stack)==1) {
                                        if (compoundTag.getInt(research)<researchMaxSize/3) {
                                            compoundTag.putInt(research, compoundTag.getInt(research) + Config.SERVER.ytgld_research.get());
                                        }else {
                                            if (event.getEntity() instanceof WitherBoss){
                                                addLvl(stack,"WitherBoss");
                                            }
                                        }
                                    }else if (getLvl(stack)==2){
                                        if (event.getEntity().isInvertedHealAndHarm()){
                                            if (compoundTag.getInt(research)<(researchMaxSize/3)*2) {
                                                compoundTag.putInt(research, compoundTag.getInt(research) + Config.SERVER.ytgld_research.get());
                                            }

                                        }
                                        if (!(compoundTag.getInt(research) <(researchMaxSize/3)*2)) {
                                            if (event.getEntity() instanceof EnderDragon enderDragon) {
                                                addLvl(stack, "enderDragon");
                                            }
                                        }
                                    }else if (getLvl(stack)==3) {
                                        if (compoundTag.getInt(research) < ((researchMaxSize / 3) * 3)+10) {
                                            compoundTag.putInt(research, compoundTag.getInt(research) + Config.SERVER.ytgld_research.get());
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
        if (event.getEntity() instanceof Player player){
            if (Config.SERVER.giveYtgld.get()) {
                if (!player.getTags().contains("YtgldV")) {
                    player.addItem(new ItemStack(Items.ytgld_virus.get()));
                    player.addTag("YtgldV");
                }
            }



            if (Handler.hascurio(player, Items.ytgld_virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.ytgld_virus.get())) {
                                CompoundTag compoundTag = stack.getTag();
                                if (compoundTag != null) {
                                    if (getLvl(stack)==2){
                                        if (compoundTag.getInt(research)>0) {
                                            compoundTag.putInt(research, compoundTag.getInt(research) - researchMaxSize/100);
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
    public static void Finish(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.ytgld_virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.ytgld_virus.get())) {
                                if (stack.getTag() != null) {
                                    CompoundTag compoundTag = stack.getTag();
                                    if (compoundTag!=null){
                                        if (getLvl(stack)==0) {
                                            if (event.getItem().is(Items.ectoplasmcube.get())) {
                                                addLvl(stack, "ectoplasmcube");
                                            }
                                            if (event.getItem().is(net.minecraft.world.item.Items.GOLDEN_APPLE)){
                                                if (compoundTag.getInt(curse) > 0) {
                                                    compoundTag.putInt(curse,compoundTag.getInt(curse)-curseMaxSize/100);
                                                }
                                            }
                                        }else if (getLvl(stack)==2){
                                            if (event.getItem().is(Items.ectoplasmcube.get())){
                                                if (compoundTag.getInt(curse) > 0) {
                                                    compoundTag.putInt(curse,compoundTag.getInt(curse)-curseMaxSize/100);
                                                }
                                            }
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
    public static void LivingHurt(LivingHurtEvent  event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.ytgld_virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.ytgld_virus.get())) {
                                if (stack.getTag() != null) {
                                    CompoundTag compoundTag = stack.getTag();
                                    if (compoundTag!=null&&compoundTag.getInt(research)>=researchMaxSize){
                                        LivingEntity entity = event.getEntity();
                                        if (entity instanceof Player p){
                                            event.setAmount(event.getAmount()*3);
                                        }
                                        if (entity instanceof Animal p){
                                            event.setAmount(event.getAmount()*3.25F);
                                        }

                                        if (entity instanceof Mob p){
                                            event.setAmount(event.getAmount()*3.5F);
                                        }
                                        if (entity .getHealth()>player.getMaxHealth()*2){
                                            event.setAmount(event.getAmount()*4);
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
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        if (Config.SERVER.off_or_on_ytgld.get()) {
            CompoundTag compoundTag = stack.getTag();
            if (compoundTag != null) {
                if ((compoundTag.getInt(research) >= researchMaxSize)) {

                    slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
                    slotContext.entity().getAttributes().removeAttributeModifiers(getCurseAttributeModifiers(stack));


                    slotContext.entity().getAttributes().addTransientAttributeModifiers(getMax(stack));
                } else {
                    slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
                    slotContext.entity().getAttributes().addTransientAttributeModifiers(getCurseAttributeModifiers(stack));
                }
            } else {
                stack.getOrCreateTag();
            }
            if (getLvl(stack) == 0) {
                return;
            }
            if (compoundTag != null && compoundTag.getInt(curse) < curseMaxSize && compoundTag.getInt(research) < researchMaxSize) {
                float l = Config.SERVER.ytgld_curse.get();
                if (getLvl(stack)==2){
                    l *= 0.75f;
                }
                if (getLvl(stack)==3){
                    l *= 0.5f;
                }
                if (slotContext.entity().tickCount % (int)l == 1) {
                    compoundTag.putInt(curse, compoundTag.getInt(curse) + 1);
                }
            }
        }
    }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag() != null) {
            if (stack.getTag().getInt(research)>=researchMaxSize){
                return true;
            }
        }
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return com.moonstone.moonstonemod.Config.SERVER.canUnequipMoonstoneItem.get();
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
        slotContext.entity().getAttributes().removeAttributeModifiers(getCurseAttributeModifiers(stack));
        slotContext.entity().getAttributes().removeAttributeModifiers(getMax(stack));
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag!=null) {
            float res = -compoundTag.getInt(research) / 10000f;
            if (compoundTag.getInt(research)<researchMaxSize) {
                modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                        res*0.5F, AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                        res, AttributeModifier.Operation.MULTIPLY_TOTAL));
                modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                        res*0.5F, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
        return modifierMultimap;
    }

    public Multimap<Attribute, AttributeModifier> getMax(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag!=null) {
            if (compoundTag.getInt(research)>=researchMaxSize) {
                modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        1.5, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        1, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        1, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.8, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.7, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.65, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.6, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(Attributes.LUCK, new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.4, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));

                modifierMultimap.put(AttReg.hurt.get(), new AttributeModifier(UUID.fromString("5d6f5056-9d82-3950-9ee4-6a4e4ed3cf33"), "a",
                                        -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));



            }
        }
        return modifierMultimap;
    }
    public Multimap<Attribute, AttributeModifier> getCurseAttributeModifiers(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        CompoundTag compoundTag = stack.getTag();
        float s = 1;
        if (getLvl(stack) == 3) {
            s*=1.5f;
        }
        if (compoundTag!=null) {
            float curs = -compoundTag.getInt(curse) / 10000f;
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                    curs*0.4*s, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                    curs*0.5*s, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                    curs*0.6*s, AttributeModifier.Operation.MULTIPLY_TOTAL));
            modifierMultimap.put(AttReg.hurt.get(), new AttributeModifier(UUID.fromString("b8c5ee80-8fcd-32b0-a4c2-2c2e7de58468"),"a",
                    -curs*0.3*s, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag!=null){
            if (getLvl(stack)!=0) {
                if (compoundTag.getInt(research) < researchMaxSize) {
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.1").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
                    tooltipComponents.add(Component.literal(""));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.2").append(compoundTag.getInt(research) / 100 + "%").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDC143C))));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.3").append(compoundTag.getInt(curse) / 100 + "%").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDC143C))));
                    tooltipComponents.add(Component.literal(""));

                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.4").withStyle(ChatFormatting.RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.5").withStyle(ChatFormatting.RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.6").withStyle(ChatFormatting.RED));
                    tooltipComponents.add(Component.literal(""));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.7").withStyle(ChatFormatting.RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.8").withStyle(ChatFormatting.RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.9").withStyle(ChatFormatting.RED));
                    tooltipComponents.add(Component.literal(""));

                    if (getLvl(stack) == 1) {
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.37").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.38").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.39").withStyle(ChatFormatting.RED));
                        if (compoundTag.getInt(research) >= researchMaxSize / 3) {
                            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.40").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                    }

                    if (getLvl(stack) == 2) {
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.41").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.42").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.43").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.44").withStyle(ChatFormatting.RED));
                        if (compoundTag.getInt(research) >= (researchMaxSize / 3) * 2) {
                            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.45").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                    }
                    if (getLvl(stack) == 3) {
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.46").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.47").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.48").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.49").withStyle(ChatFormatting.RED));

                    }


                    float res = -compoundTag.getInt(research) / 10000f * 100;
                    float cur = -compoundTag.getInt(curse) / 10000f * 100;

                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.10").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.11").append(res * 0.5f + "%").withStyle(ChatFormatting.DARK_RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.12").append(res + "%").withStyle(ChatFormatting.DARK_RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.13").append(res * 0.5f + "%").withStyle(ChatFormatting.DARK_RED));
                        tooltipComponents.add(Component.translatable(""));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.14").withStyle(ChatFormatting.RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.15").append(cur * 0.4f + "%").withStyle(ChatFormatting.DARK_RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.16").append(cur * 0.6f + "%").withStyle(ChatFormatting.DARK_RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.17").append(cur * 0.3f + "%").withStyle(ChatFormatting.DARK_RED));
                        tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.18").append(cur * 0.5f + "%").withStyle(ChatFormatting.DARK_RED));
                    } else {
                        tooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
                    }

                } else {
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.19").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
                    tooltipComponents.add(Component.literal(""));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.20").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.21").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.22").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.23").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.24").withStyle(ChatFormatting.DARK_RED));

                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.25").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.26").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.27").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.28").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.29").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.30").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.32").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.literal("+40% ").append(Component.translatable("attribute.name.generic.luck")).withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.33").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.34").withStyle(ChatFormatting.DARK_RED));
                    tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.31").withStyle(ChatFormatting.DARK_RED));
                }
            } else {
                tooltipComponents.add(Component.translatable( "item.ytgld_virus.tool.string.eat").withStyle(ChatFormatting.RED));
            }

        }else {
            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.4").withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.5").withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.6").withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.7").withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.8").withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.translatable("item.ytgld_virus.tool.string.9").withStyle(ChatFormatting.RED));
        }
    }
}
