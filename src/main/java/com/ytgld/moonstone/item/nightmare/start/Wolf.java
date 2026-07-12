package com.ytgld.moonstone.item.nightmare.start;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
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
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

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
public class Wolf extends NightmareSmall {

    public Wolf(Properties properties) {
        super(properties);
    }

    public static void kill(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, InitItems.wolf.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.wolf.get())) {
                                CompoundTag compoundTag = stack.get(DataReg.tag);
                                if ( compoundTag!= null) {
                                    String string = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()).toString();
                                    if (!compoundTag.getBooleanOr(string,false)){
                                        compoundTag.putBoolean(string,true);
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void attack(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, InitItems.wolf.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.wolf.get())) {
                                CompoundTag compoundTag = stack.get(DataReg.tag);
                                if (compoundTag!= null) {
                                    String string = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()).toString();
                                    if (!string.isEmpty()&&compoundTag.getBooleanOr(string,false)) {
                                        event.setNewDamage(event.getNewDamage()*1.25f);
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                });
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, InitItems.wolf.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.wolf.get())) {
                                CompoundTag compoundTag = stack.get(DataReg.tag);
                                if (compoundTag!= null) {
                                    if (!player.getCooldowns().isOnCooldown(InitItems.wolf.get().getDefaultInstance())) {
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

                                                    living.hurt(living.damageSources().dryOut(), event.getNewDamage());

                                                    if (living.isDeadOrDying()) {
                                                        player.getCooldowns().addCooldown(InitItems.wolf.get().getDefaultInstance(), 100);
                                                    }

                                                    event.setNewDamage(0);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    stack.set(DataReg.tag,new CompoundTag());
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
        if (!slotContext.entity().level().isClientSide()){
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()){
            slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = HashMultimap.create();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag!=null) {
            int a = compoundTag.size();
            attributeModifiers.put(Attributes.MAX_HEALTH,
                    new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.descriptionId),
                            a, AttributeModifier.Operation.ADD_VALUE));
            attributeModifiers.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.descriptionId),
                            (a/2F) / 80f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
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

        if (stack.get(DataReg.tag)!=null){
            pTooltipComponents.add(Component.translatable("effect.minecraft.health_boost").append(" : ").append(String.valueOf(stack.get(DataReg.tag).size())).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
            pTooltipComponents.add(Component.translatable("effect.minecraft.strength").append(" : ").append(String.valueOf(stack.get(DataReg.tag).size()/2F)).append("%").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }else {
            pTooltipComponents.add(Component.translatable("moonstone.item.kill").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }
    }
}
