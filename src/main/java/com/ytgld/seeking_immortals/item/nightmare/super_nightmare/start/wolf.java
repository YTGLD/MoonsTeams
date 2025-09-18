package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 孤狼
 * <P>
 * 每杀死一种生物都会增加你1点生命和护甲
 * <P>
 * 你对曾被你杀死的生物造成额外25%的伤害
 * <P>
 * <P>
 * 标记附近被你巡抚的单位
 * <P>
 * 每次受伤都会转移到它们身上，若单位死亡则物品进入5秒冷却
 * <P>
 * <P>
 * <P>
 * 一叶孤舟
 */
public class wolf extends nightmare implements SuperNightmare {

    public static void kill(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.wolf.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.wolf.get())) {
                                CompoundTag compoundTag = stack.getTag();
                                if ( compoundTag!= null) {
                                    String string = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()).toString();
                                    if (compoundTag.getString(string).isEmpty()){
                                        compoundTag.putString(string,string);
                                    }
                                }else {
                                    stack.getOrCreateTag();
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void attack(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.wolf.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.wolf.get())) {
                                CompoundTag compoundTag = stack.getTag();
                                if (compoundTag!= null) {
                                    String string = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()).toString();
                                    if (compoundTag.getString(string).isEmpty()) {
                                        if (string.contains(compoundTag.getString(string))) {
                                            event.setAmount(event.getAmount()*1.25f);
                                        }
                                    }
                                }else {
                                    stack.getOrCreateTag();
                                }
                            }
                        }
                    }
                });
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.wolf.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.wolf.get())) {
                                CompoundTag compoundTag = stack.getTag();
                                if (compoundTag!= null) {
                                    if (!player.getCooldowns().isOnCooldown(Items.wolf.get())) {
                                        Vec3 playerPos = player.position();
                                        float range = 10;
                                        List<LivingEntity> entities =
                                                player.level().getEntitiesOfClass(LivingEntity.class,
                                                        new AABB(playerPos.x - range,
                                                                playerPos.y - range,
                                                                playerPos.z - range,
                                                                playerPos.x + range,
                                                                playerPos.y + range,
                                                                playerPos.z + range));
                                        for (LivingEntity living : entities) {
                                            if (living instanceof OwnableEntity ownableEntity) {
                                                if (ownableEntity.getOwner() != null && ownableEntity.getOwner().is(player)) {

                                                    living.hurt(living.damageSources().dryOut(), event.getAmount());

                                                    if (living.isDeadOrDying()) {
                                                        player.getCooldowns().addCooldown(Items.wolf.get(), 100);
                                                    }

                                                    event.setAmount(0);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    stack.getOrCreateTag();
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
        if (!slotContext.entity().level().isClientSide){
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide){
            slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
        }
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag!=null) {
            int a = compoundTag.size();
            attributeModifiers.put(Attributes.MAX_HEALTH,
                    new AttributeModifier(UUID.fromString("da37c59b-11ef-4c2f-8eb8-ca8cfe9b69c4"),"a", a, AttributeModifier.Operation.ADDITION));
            attributeModifiers.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(UUID.fromString("da37c59b-11ef-4c2f-8eb8-ca8cfe9b69c4"),"a", a/2F, AttributeModifier.Operation.ADDITION));
        }
        return attributeModifiers;

    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.wolf.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.wolf.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.wolf.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.wolf.tool.string.3").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));

        if (stack.getTag()!=null){
            pTooltipComponents.add(Component.translatable("effect.minecraft.health_boost").append(" : ").append(String.valueOf(stack.getTag().size())).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
            pTooltipComponents.add(Component.translatable("effect.minecraft.strength").append(" : ").append(String.valueOf(stack.getTag().size()/2F)).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }else {
            pTooltipComponents.add(Component.translatable("moonstone.item.kill").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }
    }
}
