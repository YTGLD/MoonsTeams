package com.ytgld.moonstone.item.ms.necora;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.other.DataReg;
import com.ytgld.moonstone.other.MoonBundleContents;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.apache.commons.lang3.math.Fraction;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DNAPush extends TheNecora {
    public DNAPush(Properties properties) {
        super(properties);
    }

    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (!(other.getItem() instanceof DNAItems.DNAS)) {
            return false;
        }
        if (stack.getCount() != 1) {
            return false;
        } else if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
            if (bundlecontents == null) {
                stack.set(DataReg.BUNDLE_CONTENTS, MoonBundleContents.EMPTY);
                return false;
            } else {
                MoonBundleContents.Mutable bundlecontents$mutable = new MoonBundleContents.Mutable(bundlecontents);
                if (other.isEmpty()) {
                    ItemStack itemstack = bundlecontents$mutable.removeOne();
                    if (itemstack != null) {
                        this.playRemoveOneSound(player);
                        access.set(itemstack);
                    }
                } else {
                    int i = bundlecontents$mutable.tryInsert(other);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(DataReg.BUNDLE_CONTENTS, bundlecontents$mutable.toImmutable());
                return true;
            }
        } else {
            return false;
        }
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (dropContents(itemstack, player)) {
            this.playDropContentsSound(player);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.pass(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public boolean isBarVisible(ItemStack stack) {
        MoonBundleContents bundlecontents = stack.getOrDefault(DataReg.BUNDLE_CONTENTS, MoonBundleContents.EMPTY);
        return bundlecontents.weight().compareTo(Fraction.ZERO) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        MoonBundleContents bundlecontents = stack.getOrDefault(DataReg.BUNDLE_CONTENTS, MoonBundleContents.EMPTY);
        return Math.min(1 + Mth.mulAndTruncate(bundlecontents.weight(), 12), 13);
    }

    public int getBarColor(ItemStack stack) {
        return 0xffff0000;
    }

    private static boolean dropContents(ItemStack stack, Player player) {
        MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
        if (bundlecontents != null && !bundlecontents.isEmpty()) {
            stack.set(DataReg.BUNDLE_CONTENTS, MoonBundleContents.EMPTY);
            if (player instanceof ServerPlayer) {
                bundlecontents.itemsCopy().forEach((p_330078_) -> {
                    player.drop(p_330078_, true);
                });
            }

            return true;
        } else {
            return false;
        }
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
            if (bundlecontents != null) {
                bundlecontents.items().forEach((itemStack -> {
                    if (itemStack.is(DNAItems.speed_metabolism)) {
                        int count = itemStack.getCount();
                        int a = count / 32;
                        if (player.getFoodData().getSaturationLevel() < a&&player.getFoodData().getFoodLevel()>18) {
                            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                        }
                    }
                    if (itemStack.is(DNAItems.cell_disorder)) {
                        int count = itemStack.getCount();
                        int a = count * 10;
                        if (!player.level().isClientSide() &&player.tickCount % a == 0){
                            player.removeAllEffects();
                        }
                    }
                    if (itemStack.is(DNAItems.cell_eyes)) {
                        float count = itemStack.getCount();//64
                        count/=4;
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - count, playerPos.y - count, playerPos.z - count, playerPos.x + count, playerPos.y + count, playerPos.z + count));

                        for (LivingEntity living : entities){
                            living.addEffect(new MobEffectInstance(MobEffects.GLOWING,100,0,false,false));
                        }

                    }

                    if (itemStack.is(DNAItems.cell_flu)) {
                        float count = itemStack.getCount();//64
                        count/=4;
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - count, playerPos.y - count, playerPos.z - count, playerPos.x + count, playerPos.y + count, playerPos.z + count));

                        for (LivingEntity living : entities){
                            if (!living.is(player)) {
                                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false));
                                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1, false, false));
                                living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1, false, false));
                            }
                        }
                    }
                }));

            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    public  static void event(LivingEntityUseItemEvent.Start event){
        LivingEntity player = event.getEntity();
        if (Handler.hascurio(player, InitItems.dna.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is( InitItems.dna.get())) {
                            MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                            if (bundlecontents != null) {
                                bundlecontents.items().forEach((itemStack -> {

                                    if (itemStack.is(DNAItems.cell_big_boom)) {
                                        int count = itemStack.getCount();
                                        if (event.getItem().getUseAnimation() ==UseAnim.EAT){
                                            event.setDuration((int) (event.getDuration() * (1 - (count/100f))));
                                        }
                                    }
                                }));
                            }
                        }
                    }
                }
            });
        }
    }
    public  static void event(LivingEntityUseItemEvent.Finish event){
        LivingEntity kl = event.getEntity();
        if (kl instanceof Player player) {
            if (Handler.hascurio(player, InitItems.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.dna.get())) {
                                MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_digestion)) {

                                            int count = itemStack.getCount();
                                            if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+count/10);
                                                player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel()+count/10f);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public  static void event(LivingIncomingDamageEvent event){
        Entity p = event.getEntity();
        if (p instanceof Player player) {
            if (Handler.hascurio(player, InitItems.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.dna.get())) {
                                MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_inheritance)) {
                                            float s = itemStack.getCount();//64
                                            s/=100f;//0.64
                                            s/=3.2f;//0.2
                                            event.setAmount(event.getAmount()*(1-s));
                                        }
                                        if (itemStack.is(DNAItems.cell_cranial)) {
                                            float s = itemStack.getCount();//64
                                            s/=100f;//0.64
                                            if (event.getSource().is(DamageTypes.FALLING_ANVIL)
                                                    && event.getSource().is(DamageTypes.FALLING_STALACTITE)
                                                    && event.getSource().is(DamageTypes.FALLING_BLOCK)
                                                    && event.getSource().is(DamageTypes.MOB_PROJECTILE))
                                            {
                                                event.setAmount(event.getAmount()*(1-s));
                                            }
                                        }

                                        if (itemStack.is(DNAItems.cell_compress)) {
                                            float s = itemStack.getCount();//64
                                            s/=100f;//0.64
                                            if (event.getSource().getEntity() instanceof LivingEntity living){
                                                float dam = event.getAmount() * s;
                                                living.hurt(living.damageSources().dryOut(),dam);
                                            }
                                        }
                                        if (itemStack.is(DNAItems.cell_constant)) {
                                            if (!player.getCooldowns().isOnCooldown(DNAItems.cell_constant.get())) {
                                                float s = itemStack.getCount();//64
                                                s /= 100f;//0.64
                                                player.invulnerableTime = (int) (player.invulnerableTime + player.invulnerableTime * s);
                                                player.getCooldowns().addCooldown(DNAItems.cell_constant.get(), player.invulnerableTime);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, InitItems.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.dna.get())) {
                                MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_acid)) {
                                            int count = itemStack.getCount();//64
                                            ItemStack head = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
                                            if (!head.isEmpty()&&head.getMaxDamage()!=0){
                                                head.hurtAndBreak(count, event.getEntity(),EquipmentSlot.HEAD);
                                            }
                                            ItemStack CHEST = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
                                            if (!CHEST.isEmpty()&&CHEST.getMaxDamage()!=0){
                                                CHEST.hurtAndBreak(count, event.getEntity(),EquipmentSlot.CHEST);
                                            }
                                            ItemStack LEGS = event.getEntity().getItemBySlot(EquipmentSlot.LEGS);
                                            if (!LEGS.isEmpty()&&LEGS.getMaxDamage()!=0){
                                                LEGS.hurtAndBreak(count, event.getEntity(),EquipmentSlot.LEGS);
                                            }
                                            ItemStack FEET = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
                                            if (!FEET.isEmpty()&&FEET.getMaxDamage()!=0){
                                                FEET.hurtAndBreak(count, event.getEntity(),EquipmentSlot.HEAD);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public  static void event(LivingDeathEvent event){
        Entity p = event.getSource().getEntity();
        if (p instanceof Player player) {
            if (Handler.hascurio(player, InitItems.dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(InitItems.dna.get())) {
                                MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
                                if (bundlecontents != null) {
                                    bundlecontents.items().forEach((itemStack -> {
                                        if (itemStack.is(DNAItems.cell_darwin)) {
                                            float count = itemStack.getCount();
                                            if (Mth.nextInt(RandomSource.create(),1,2)==1){
                                                player.heal(count/8);
                                            }else {
                                                player.hurt(player.damageSources().magic(),count/32);
                                            }
                                        }
                                        if (itemStack.is(DNAItems.cell_god)) {
                                            float count = itemStack.getCount();
                                            player.heal(count/32);
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        MoonBundleContents moonBundleContents = stack.get(DataReg.BUNDLE_CONTENTS);
        if (moonBundleContents != null) {
            for (ItemStack itemStack : moonBundleContents.items()){
                if (itemStack.getItem() instanceof DNAItems.DNAS dnas){
                    DNAItems.DNAS.AttModify[] attModifies = dnas.getAttModify();
                    for (DNAItems.DNAS.AttModify attModify : attModifies) {
                        if (attModify != null) {
                            AttributeModifier attributeModifier = attModify.attributeModifier();
                            multimap.put(attModify.holder(), new AttributeModifier(
                                    attributeModifier.id(),attributeModifier.amount() * itemStack.getCount(),attributeModifier.operation()
                            ));
                        }
                    }
                }
            }
        }
        return multimap;
    }
    @Override
    public void text(ItemStack pStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(pStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.moonstone.dna_push.tool.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB22222))));
        builder.accept(Component.translatable("item.moonstone.dna_push.tool.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB22222))));
        if (tooltipFlag.hasShiftDown()) {
            MoonBundleContents bundlecontents = pStack.get(DataReg.BUNDLE_CONTENTS);
            if (bundlecontents != null) {
                bundlecontents.items().forEach((itemStack -> {
                    builder.accept(Component.translatable(itemStack.getItem().getDescriptionId()).append("："+ itemStack.getCount()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEE6363))));
                }));
            }
        }else {
            builder.accept(Component.translatable("key.keyboard.left.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB22222))));
            builder.accept(Component.literal(""));

        }
    }
}