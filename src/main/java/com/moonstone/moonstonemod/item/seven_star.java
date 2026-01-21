package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.Targeting;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.TradeWithVillagerEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 *七星炼体术：
 * <p>
 * <p>
 * 	一星——神陨：免疫负面状态，但是杀死凋零后此星消亡
 * <p>
 * 	二星——苍戮：增加30%伤害和护甲，但是杀死高于400点生命值的生物后此星消亡
 * <p>
 * 	三星——天屠：受到致命伤害时恢复50%生命值，但是死亡真正死亡10次后此星消亡
 * <p>
 * 	四星——刑阎：减少90%村民交易的价格，但是交易超过1000次后此星消亡
 * <p>
 * 	五星——血煞：增加25%所有速度和100%跳跃高度，但是使用鞘翅或类似物品后此星消亡
 * <p>
 * 	六星——天神：宠物没有攻击目标时无敌且恢复生命，但是损失30只宠物后此星消亡
 * <p>
 * 	七星——诛圣：获得6级的时运和抢夺等级，但是累计消亡5星后，此星消亡
 *
 *
 */
public class seven_star extends ectoplasm {
    public static boolean hasEffectStar(ItemStack stack ,String name){
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag != null) {
            return compoundTag.getBoolean(name);
        }
        return true;
    }
    //熄灭一颗星，这个地方就加1
    public static final String dieStarNumber = "dieStarNumber";

    public static void dieStar(ItemStack targetItem,String name){
        CompoundTag compoundTag =targetItem.getTag();
        if (compoundTag != null) {
            if (compoundTag.getBoolean(name)) {
                compoundTag.putBoolean(name, false);
                dieStarNumberAdd(targetItem);
            }
        }
    }
    public static void dieStarNumberAdd(ItemStack targetItem){
        CompoundTag compoundTag =targetItem.getTag();
        if (compoundTag != null) {
            compoundTag.putInt(dieStarNumber,compoundTag.getInt(dieStarNumber)+1);
        }
    }
    public static final String theGodsFell = "theGodsFell";//神陨

    /**
     * ——神陨：免疫负面状态，但是杀死凋零后此星消亡
     */
    public static void killWither(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player entity) {
            if (Handler.hascurio(entity, Items.seven_star.get())) {
                CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.seven_star.get())) {
                                if (event.getEntity() instanceof WitherBoss){
                                    dieStar(stack,theGodsFell);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * ——神陨：免疫负面状态，但是杀死凋零后此星消亡
     */
    public static void canHasEffect(LivingEntity entity, MobEffectInstance effectInstance, CallbackInfoReturnable<Boolean> cir){
        if (Handler.hascurio(entity,Items.seven_star.get())){
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            if (hasEffectStar(stack, theGodsFell)) {
                                if (!effectInstance.getEffect().isBeneficial()) {
                                    cir.setReturnValue(false);
                                }
                            }
                        }
                    }
                }
            });
        }
    }


    /**
     * ——苍戮：增加30%伤害和护甲，但是杀死高于400点生命值的生物后此星消亡
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag() == null) {
            stack.getOrCreateTag();
        }
        if (!stack.getTag().getBoolean("isOne")){
            stack.getTag().putBoolean(theGodsFell,true);
            stack.getTag().putBoolean(cangZhi,true);
            stack.getTag().putBoolean(heavenlyButcher,true);
            stack.getTag().putBoolean(punishment,true);
            stack.getTag().putBoolean(bloodEvil,true);
            stack.getTag().putBoolean(devas,true);
            stack.getTag().putBoolean(killTheSaint,true);
            stack.getTag().putBoolean("isOne",true);
        }
        if (slotContext.entity() instanceof Player player) {
            flyTick(player,stack);
            petInvAndHeal(player);
            die5(stack);
        }

        if (!slotContext.entity().level().isClientSide){
            if (!hasEffectStar(stack,theGodsFell)&&
                    !hasEffectStar(stack,cangZhi)&&
                    !hasEffectStar(stack,heavenlyButcher)&&
                    !hasEffectStar(stack,punishment)&&
                    !hasEffectStar(stack,bloodEvil)&&
                    !hasEffectStar(stack,devas)&&
                    !hasEffectStar(stack,killTheSaint)){
                stack.shrink(1);
            }

            slotContext.entity().getAttributes().addTransientAttributeModifiers(attributeModifierMultimap(stack));
        }
    }
    /**
     * ——苍戮：增加30%伤害和护甲，但是杀死高于400点生命值的生物后此星消亡
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            slotContext.entity().getAttributes().removeAttributeModifiers(attributeModifierMultimap(stack));
        }
    }
    public static final String cangZhi = "cangZhi";//苍戮'

    /**
     * ——苍戮：增加30%伤害和护甲，但是杀死高于400点生命值的生物后此星消亡
     */
    public static void killHealth(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player entity) {
            if (Handler.hascurio(entity, Items.seven_star.get())) {
                CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.seven_star.get())) {
                                if (event.getEntity().getMaxHealth() > 400){
                                    dieStar(stack,cangZhi);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    /**
     * ——苍戮：增加30%伤害和护甲，但是杀死高于400点生命值的生物后此星消亡
     */
    public static Multimap<Attribute, AttributeModifier> attributeModifierMultimap(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        float s  =0;
        if (hasEffectStar(stack,cangZhi)){
            s = 0.3f;
        }
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("66867e94-dc9e-4263-8d24-a854f7025a98"),"as", s, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("66867e94-dc9e-4263-8d24-a854f7025a98"),"as", s, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }
    public static final String heavenlyButcher = "heavenlyButcher";//天屠
    public static final String heavenlyButcherDie = "heavenlyButcherDie";//天屠记录死亡次数的标签

    /**
     * ——天屠：受到致命伤害时恢复50%生命值，但是死亡真正死亡10次后此星消亡
     */
    public static void dieNumber(LivingDeathEvent event){
        if (event.getEntity() instanceof Player entity) {
            if (Handler.hascurio(entity, Items.seven_star.get())) {
                CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.seven_star.get())) {
                                CompoundTag compoundTag =stack.getTag();
                                if (compoundTag != null) {
                                    if (compoundTag.getInt(heavenlyButcherDie)<10) {
                                        compoundTag.putInt(heavenlyButcherDie, compoundTag.getInt(heavenlyButcherDie) + 1);
                                    }else {
                                        dieStar(stack,heavenlyButcher);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }



    /**
     * ——天屠：受到致命伤害时恢复50%生命值，但是死亡真正死亡10次后此星消亡
     */
    public static void LivingDamageEventPre(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (Handler.hascurio(entity,Items.seven_star.get())){
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            if (hasEffectStar(stack, heavenlyButcher)) {
                                if (entity instanceof Player player) {
                                    if (!player.getCooldowns().isOnCooldown(Items.seven_star.get())){
                                        if (event.getAmount()>player.getHealth()){
                                            player.heal(player.getMaxHealth()/2);
                                            event.setAmount(0);
                                            player.getCooldowns().addCooldown(Items.seven_star.get(),600);
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

    public static final String punishment = "punishment";//刑阎
    public static final String punishmentNumber = "punishmentNumber";//刑阎的记录
    public static void villagerDrop(TradeWithVillagerEvent event){
        Player player = event.getEntity();
        if (Handler.hascurio(player, Items.seven_star.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            CompoundTag compoundTag =stack.getTag();
                            if (compoundTag != null) {
                                if (compoundTag.getInt(punishmentNumber)<100) {
                                    compoundTag.putInt(punishmentNumber, compoundTag.getInt(punishmentNumber) + 1);
                                }else {
                                    dieStar(stack,punishment);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * ——刑阎：减少90%村民交易的价格，但是交易超过1000次后此星消亡
     */
    public static void attackVillager(Player player , MerchantOffers merchantOffers){
        if (Handler.hascurio(player,Items.seven_star.get())){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            if (hasEffectStar(stack, punishment)) {
                                for (MerchantOffer merchantoffer1 : merchantOffers) {
                                    merchantoffer1.setSpecialPriceDiff(-58);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public static final String bloodEvil = "bloodEvil";//血煞

    /**
     * ——血煞：增加25%所有速度和100%跳跃高度，但是使用鞘翅或类似物品后此星消亡
     */
    public static void flyTick(Player player,ItemStack stack){
        if (player.isFallFlying()){
            dieStar(stack,bloodEvil);
        }
    }
    /**
     * ——血煞：增加25%所有速度和100%跳跃高度，但是使用鞘翅或类似物品后此星消亡
     */
    public static void speedADD(CallbackInfoReturnable<Float> cir, LivingEntity entity){
        if (Handler.hascurio(entity,Items.seven_star.get())){
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            if (hasEffectStar(stack, bloodEvil)) {
                                cir.setReturnValue(cir.getReturnValue()*2);
                            }
                        }
                    }
                }
            });
        }
    }
    public static void jumpInv(DamageSource damageSource,Player living,CallbackInfoReturnable<Boolean> cir){
        if (damageSource.is(DamageTypes.FALL)) {
            if (Handler.hascurio(living, Items.seven_star.get())) {
                CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.seven_star.get())) {
                                if (hasEffectStar(stack, bloodEvil)) {
                                    cir.setReturnValue(true);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static final String devas = "devas";//天神
    public static final String devasNumber = "devasNumber";//天神记录

    /**
     * ——天神：宠物没有攻击目标时无敌且恢复生命，但是损失30只宠物后此星消亡
     * */
    public static void petDie(Player owner){
        if (Handler.hascurio(owner,Items.seven_star.get())){
            CuriosApi.getCuriosInventory(owner).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            CompoundTag compoundTag =stack.getTag();
                            if (compoundTag != null) {
                                if (compoundTag.getInt(devasNumber)<30) {
                                    compoundTag.putInt(devasNumber, compoundTag.getInt(devasNumber) + 1);
                                }else {
                                    dieStar(stack,devas);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    /**
     * ——天神：宠物没有攻击目标时无敌且恢复生命，但是损失30只宠物后此星消亡
     * */
    public static void petInvAndHeal(LivingEntity player){
        if (Handler.hascurio(player,Items.seven_star.get())){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.seven_star.get())) {
                            if (hasEffectStar(stack, devas)) {
                                Vec3 playerPos = player.position().add(0, 0.75, 0);
                                float range = 10;
                                List<LivingEntity> entities =
                                        player.level().getEntitiesOfClass(LivingEntity.class,
                                                new AABB(playerPos.x - range,
                                                        playerPos.y - range,
                                                        playerPos.z - range,
                                                        playerPos.x + range,
                                                        playerPos.y + range,
                                                        playerPos.z + range));

                                for (LivingEntity living : entities){
                                    if (living instanceof OwnableEntity ownableEntity){
                                        if (ownableEntity.getOwner() != null && ownableEntity.getOwner().is(player)) {
                                            if (living instanceof Targeting targeting){
                                                if (targeting.getTarget() == null) {
                                                    if (player.tickCount % 40 ==1) {
                                                        living.addEffect(new MobEffectInstance(MobEffects.HEAL, 100, 2,false,false));
                                                        living.addEffect(new MobEffectInstance(Effects.invulnerable.get(), 100, 2,false,false));
                                                    }
                                                }
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

    public static final String killTheSaint = "killTheSaint";//诛圣
    /**
     * ——诛圣：获得6级的时运和抢夺等级，但是累计消亡5星后，此星消亡
     */
    public static void die5(ItemStack stack){
        CompoundTag compoundTag =stack.getTag();
        if (compoundTag != null) {
            if (compoundTag.getInt(dieStarNumber) >= 5) {
                dieStar(stack,killTheSaint);
            }
        }
    }
    /**
     * ——诛圣：获得6级的时运和抢夺等级，但是累计消亡5星后，此星消亡
     */
    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        int l= 0;
        if (hasEffectStar(stack, killTheSaint)) {
            l = 6;
        }
        return l;
    }

    /**
     * ——诛圣：获得6级的时运和抢夺等级，但是累计消亡5星后，此星消亡
     */
    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        int l= 0;
        if (hasEffectStar(stack, killTheSaint)) {
            l = 6;
        }
        return l;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag!=null) {
            if (!compoundTag.getBoolean("isOne")){
                compoundTag.putBoolean(theGodsFell,true);
                compoundTag.putBoolean(cangZhi,true);
                compoundTag.putBoolean(heavenlyButcher,true);
                compoundTag.putBoolean(punishment,true);
                compoundTag.putBoolean(bloodEvil,true);
                compoundTag.putBoolean(devas,true);
                compoundTag.putBoolean(killTheSaint,true);
                compoundTag.putBoolean("isOne",true);
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (hasEffectStar(stack, theGodsFell)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.1").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.1").withStyle(ChatFormatting.GRAY));
        }
        if (hasEffectStar(stack, cangZhi)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.2").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.2").withStyle(ChatFormatting.GRAY));
        }
        if (hasEffectStar(stack, heavenlyButcher)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.3").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.3").withStyle(ChatFormatting.GRAY));
        }
        if (hasEffectStar(stack, punishment)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.4").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.4").withStyle(ChatFormatting.GRAY));
        }
        if (hasEffectStar(stack, bloodEvil)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.5").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.5").withStyle(ChatFormatting.GRAY));
        }
        if (hasEffectStar(stack, devas)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.6").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.6").withStyle(ChatFormatting.GRAY));
        }
        if (hasEffectStar(stack, killTheSaint)) {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.7").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.7").withStyle(ChatFormatting.GRAY));
        }
        tooltipComponents.add(Component.translatable("item.moonstone.seven_star.string.8").withStyle(ChatFormatting.LIGHT_PURPLE));

    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return Config.SERVER.canUnequipMoonstoneItem.get();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID id, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio", id, 1, AttributeModifier.Operation.ADDITION);
        if (hasEffectStar(stack, bloodEvil)) {
            linkedHashMultimap.put(AttReg.speed.get(), new AttributeModifier(id, "aa", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return linkedHashMultimap;
    }
}
