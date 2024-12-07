package com.moonstone.moonstonemod.item.maxitem.book;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.BookSkill;
import com.moonstone.moonstonemod.moonstoneitem.IDoom;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nine_sword_book extends BookSkill implements IDoom {
    public static final String size = "swordSize";
    public static final String lvl = "nineSwordBookSwordLvl";
    public static final String attackLvlsmall = "nineSwordBookSwordLvlSmall_attackLvlsmall";
    public static final String attackSpeedLvlSmall = "nineSwordBookSwordLvlSmall_attackSpeedLvlSmall";
    public static final int maxLvl = 10;
    public static final int addLvl = 100;
    public static final String small = "nineSwordBookSwordLvlSmall";

    public static int test = 1;
    /*
        在剑仙7重境内击中击中生物+1“伤害“修炼值（可以杀死敌对生物来获取更多）
        若大于7重境，则必须杀死敌对生物来获取修炼值（1个生物为5点）

        在剑神1重境内击中击中生物+1“攻速“修炼值（对这个物品右键钻石来获取更多）{
            可以使用Mixin来修改以下数值：{
                钻石->可以改为任意物品
                数值->可以改为任意数值且可以判断什么物品加多少（例如钻石+10，泥土+1，其他模组物品：+100）
            },你只需要Mixin这个类的addOlofDiamondOrItem方法
        }
        若大于剑神1重境，啧必须献祭物品来获取修炼值




     */
    public static void  att(LivingHurtEvent event){
        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player){
            if (!player.level().isClientSide) {
                if (Handler.hascurio(player, Items.nine_sword_book.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (!stack.isEmpty() && stack.is(Items.nine_sword_book.get())) {
                                    if (stack.getTag() != null) {
                                        if (event.getEntity() instanceof Mob mob) {
                                            if (mob.getTarget() != null && mob.getTarget().is(player)) {
                                                if (stack.getTag().getInt(attackLvlsmall) <= 2 * addLvl) {
                                                    if (Mth.nextInt(RandomSource.create(), 1, 4) == 1) {
                                                        stack.getTag().putInt(attackLvlsmall, stack.getTag().getInt(attackLvlsmall) + 1);
                                                    }
                                                }
                                            }
                                        }
                                        if (stack.getTag().getInt(attackSpeedLvlSmall) <= 3 * addLvl) {
                                            if (Mth.nextInt(RandomSource.create(), 1, 4) == 1) {
                                                stack.getTag().putInt(attackSpeedLvlSmall, stack.getTag().getInt(attackSpeedLvlSmall) + 1 * test);
                                            }
                                        }


                                        if (stack.getTag().getInt(small) <= maxLvl * addLvl) {
                                            if (Mth.nextInt(RandomSource.create(), 1, 4) == 1) {
                                                stack.getTag().putInt(small, stack.getTag().getInt(small) + 1 * test);
                                            }
                                        }
                                        if (stack.getTag().getInt(small) % addLvl == 0) {
                                            stack.getTag().putInt(lvl, stack.getTag().getInt(lvl) + 1);
                                        }


                                        int s = 5 * stack.getTag().getInt(size);
                                        if (Mth.nextInt(RandomSource.create(), 0, 100) <= s) {
                                            for (int j = 0; j < stack.getTag().getInt(size); j++) {
                                                float lvl = Mth.nextFloat(RandomSource.create(), -0.3f, 0.3f);

                                                as_sword as_sword = new as_sword(EntityTs.as_sword.get(), player.level());
                                                as_sword.setPos(player.position().x, player.position().y + 1, player.position().z);
                                                Vec3 forward = player.getLookAngle();
                                                double speed = 0.15f;

                                                as_sword.setDeltaMovement(-forward.add(lvl, lvl, lvl).x * speed, -forward.add(lvl, lvl, lvl).y * speed, -forward.add(lvl, lvl, lvl).z * speed);
                                                as_sword.setOwner(player);

                                                player.level().addFreshEntity(as_sword);
                                                as_sword.setTarget(event.getEntity());

                                                stack.getTag().putInt(size, stack.getTag().getInt(size) - 1);
                                            }
                                        }
                                    }
                                    List<Float> floats = new ArrayList<>();
                                    for (int j = 0; j < 9; j++) {
                                        ItemStack sword = player.getInventory().items.get(j);
                                        if (sword.getItem() instanceof SwordItem swordItem) {
                                            floats.add(swordItem.getTier().getAttackDamageBonus() + 4);
                                        }
                                    }
                                    float damage = 0;
                                    for (float all : floats) {
                                        damage += all;
                                    }
                                    if (!player.getCooldowns().isOnCooldown(Items.nine_sword_book.get())) {
                                        event.setAmount(damage);
                                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 2, 2);
                                        player.getCooldowns().addCooldown(Items.nine_sword_book.get(), 100);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }
    public static void die(LivingDeathEvent event){
        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player){
            if (!player.level().isClientSide) {
                if (Handler.hascurio(player, Items.nine_sword_book.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (!stack.isEmpty() && stack.is(Items.nine_sword_book.get())) {
                                    if (stack.getTag() != null) {
                                        if (event.getEntity() instanceof Mob mob) {
                                            if (mob.getTarget() != null && mob.getTarget().is(player)) {
                                                if (stack.getTag().getInt(attackLvlsmall) <= maxLvl * addLvl) {
                                                    stack.getTag().putInt(attackLvlsmall, stack.getTag().getInt(attackLvlsmall) + 1);
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
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                if (stack.getTag() != null) {
                    List<Integer> integers = new ArrayList<>();
                    for (int i = 0; i < 9; i++) {
                        ItemStack sword = player.getInventory().items.get(i);
                        if (sword.getItem() instanceof SwordItem || BuiltInRegistries.ITEM.getKey(sword.getItem()).getPath().contains("sword")) {
                            integers.add(1);
                        }
                    }
                    int swSize = integers.size();
                    if (player.tickCount % 40 == 0) {
                        if (stack.getTag().getInt(size) < swSize) {
                            stack.getTag().putInt(size, stack.getTag().getInt(size) + 1);
                        }
                    }

                } else {
                    stack.getOrCreateTag().putInt(lvl, 1);
                }
            }
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(),Items.the_blood_book.get())){
            return false;
        }
        return !Handler.hascurio(slotContext.entity(),this);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                if (stack.getTag() != null) {
                    player.getAttributes().addTransientAttributeModifiers(Head(player, stack));
                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(Head(player,stack));
        }
    }

    public int addOlofDiamondOrItem(Item item){
        if (item == net.minecraft.world.item.Items.DIAMOND){
            return 2;
        }
        return 0;
    }
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack p_150743_, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {

            if (!p_150746_.level().isClientSide) {
                if (me.getTag() != null && me.getTag().getInt(attackSpeedLvlSmall) < maxLvl * addLvl) {
                    me.getTag().putInt(attackSpeedLvlSmall,
                            me.getTag().getInt(attackSpeedLvlSmall) + addOlofDiamondOrItem(p_150743_.getItem()) * test);
                }
                p_150743_.shrink(1);
                return true;
            }
        }
        return false;
    }

    private Multimap<Attribute, AttributeModifier> Head(Player player,ItemStack stack){
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (stack.getTag()!=null) {
            float level =2*((stack.getTag().getInt(lvl))/10f);//0~2

            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                ItemStack sword = player.getInventory().items.get(i);
                if (sword.getItem() instanceof SwordItem|| BuiltInRegistries.ITEM.getKey(sword.getItem()).getPath().contains("sword")) {
                    integers.add(1);
                }
            }
            float attLevel =(((stack.getTag().getInt(attackLvlsmall)/100f))/10f);
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                     UUID.fromString("492dc575-b72e-3d83-b2fd-33ab63727150"),
                        "s",
                    ((integers.size() / 10f /3f)*attLevel)-0.25,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            float attackSpeedLevel =(((stack.getTag().getInt(attackSpeedLvlSmall)/100f)-1)/10f);
            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                     UUID.fromString("492dc575-b72e-3d83-b2fd-33ab63727150"),
                        "s",
                    (integers.size() / 20F /3f)*attackSpeedLevel,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                     UUID.fromString("492dc575-b72e-3d83-b2fd-33ab63727150"),
                        "s",
                    (integers.size() / 35F /3f)*level,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.heal.get(), new AttributeModifier(
                    UUID.fromString("492dc575-b72e-3d83-b2fd-33ab63727150"),
                    "s",
                    (integers.size() / 100F /3f)*level,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                     UUID.fromString("492dc575-b72e-3d83-b2fd-33ab63727150"),
                        "s",
                    -0.5,
                    AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pStack.getTag()!=null) {
            int lg = pStack.getTag().getInt(small);
            int ls = pStack.getTag().getInt(attackSpeedLvlSmall);
            int ld = pStack.getTag().getInt(attackLvlsmall);

            pTooltipComponents.add(Component.literal(""));
            if ((lg + ls + ld) / 3<=300){
                addNme(pStack,pTooltipComponents,"item.nine_sword_book_skill.tool.string.1");
            }else if ((lg + ls + ld) / 3>300&&(lg + ls + ld) / 3<=600){
                addNme(pStack,pTooltipComponents,"item.nine_sword_book_skill.tool.string.2");
            }else if ((lg + ls + ld) / 3>600){
                addNme(pStack,pTooltipComponents,"item.nine_sword_book_skill.tool.string.3");
            }

            pTooltipComponents.add(Component.translatable("item.nine_sword_book_lvl.tool.string.2").append(" " + pStack.getTag().getInt(small)).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
            pTooltipComponents.add(Component.translatable("item.nine_sword_book_lvl.tool.string.3").append(" " + pStack.getTag().getInt(attackSpeedLvlSmall)).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
            pTooltipComponents.add(Component.translatable("item.nine_sword_book_lvl.tool.string.4").append(" " + pStack.getTag().getInt(attackLvlsmall)).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
        }
    }

    private void addNme(ItemStack pStack, List<Component> pTooltipComponents, String translatable) {

        if (pStack.getTag()!=null) {
            int lg = pStack.getTag().getInt(small);
            int ls = pStack.getTag().getInt(attackSpeedLvlSmall);
            int ld = pStack.getTag().getInt(attackLvlsmall);

            int l = (lg + ls + ld) / 3;
            String ss = "small";
            int displayValue = pStack.getTag().getInt(ss); // 用于存放要显示的值

            if (l > 0 && l < 300) {
                pStack.getTag().putInt(ss, l / 30);// 计算0到299之间的值
            } else if (l >= 300 && l < 600) {
                pStack.getTag().putInt(ss, (l - 300) / 30); // 计算300到599之间的值，显示从1开始
            } else if (l >= 600 && l <= 10000) {
                pStack.getTag().putInt(ss, (l - 600) / 30); // 计算600到899之间的值，显示从1开始
            }

            // 限制 displayValue 在 1 到 10 之间
            if (pStack.getTag().getInt(ss) < 1) {
                pStack.getTag().putInt(ss, 1);// 计算0到299之间的值
            } else if (pStack.getTag().getInt(ss) > 10) {
                pStack.getTag().putInt(ss, 10);
            }

            // 添加到tooltip中
            pTooltipComponents.add(Component.translatable(translatable)
                    .append(String.valueOf(displayValue))
                    .append(Component.translatable("sword.moonstone.lvl"))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF6A5ACD))));
        }
    }


}
