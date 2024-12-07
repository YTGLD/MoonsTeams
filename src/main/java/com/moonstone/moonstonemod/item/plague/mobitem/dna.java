package com.moonstone.moonstonemod.item.plague.mobitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class dna extends TheNecoraIC implements ICurioItem , Iplague {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld", "ytgld");

    }
    //{
//
//        private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
//
//    public dna() {
//    }
//
//        public boolean overrideOtherStackedOnMe(ItemStack p_150742_, ItemStack p_150743_, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
//        if (p_150742_.getCount() != 1) return false;
//        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
//            int i = add(p_150742_, p_150743_);
//            if (i > 0) {
//                this.playInsertSound(p_150746_);
//                p_150743_.shrink(i);
//            }
//
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//        public InteractionResultHolder<ItemStack> use(Level p_150760_, Player p_150761_, InteractionHand p_150762_) {
//        ItemStack itemstack = p_150761_.getItemInHand(p_150762_);
//        if (dropContents(itemstack, p_150761_)) {
//            this.playDropContentsSound(p_150761_);
//            p_150761_.awardStat(Stats.ITEM_USED.get(this));
//            return InteractionResultHolder.sidedSuccess(itemstack, p_150760_.isClientSide());
//        } else {
//            return InteractionResultHolder.fail(itemstack);
//        }
//    }
//
//        public boolean isBarVisible(ItemStack p_150769_) {
//        return getContentWeight(p_150769_) > 0;
//    }
//
//        public int getBarWidth(ItemStack p_150771_) {
//        return Math.min(1 + 12 * getContentWeight(p_150771_) / 64, 13);
//    }
//
//        public int getBarColor(ItemStack p_150773_) {
//        return BAR_COLOR;
//    }
//
//        private static int add(ItemStack p_150764_, ItemStack p_150765_) {
//        if (!p_150765_.isEmpty() && p_150765_.getItem().canFitInsideContainerItems()) {
//            CompoundTag compoundtag = p_150764_.getOrCreateTag();
//            if (!compoundtag.contains("Items")) {
//                compoundtag.put("Items", new ListTag());
//            }
//
//            int i = getContentWeight(p_150764_);
//            int j = getWeight();
//            int k = Math.min(p_150765_.getCount(), (320 - i) / j);
//
//            if (k == 0) {
//                return 0;
//            } else {
//                ListTag listtag = compoundtag.getList("Items", 10);
//                Optional<CompoundTag> optional = getMatchingItem(p_150765_, listtag);
//                if (optional.isPresent()) {
//                    CompoundTag compoundtag1 = optional.get();
//                    ItemStack itemstack = ItemStack.of(compoundtag1);
//
//                    int newCount = itemstack.getCount() + k;
//                    if (newCount > 64) {
//                        return 0;
//                    }
//
//
//                    itemstack.grow(k);
//                    itemstack.save(compoundtag1);
//                    listtag.remove(compoundtag1);
//                    listtag.add(0, (Tag)compoundtag1);
//
//                } else {
//                    ItemStack itemstack1 = p_150765_.copyWithCount(k);
//                    CompoundTag compoundtag2 = new CompoundTag();
//                    itemstack1.save(compoundtag2);
//                    listtag.add(0, (Tag)compoundtag2);
//                }
//                return k;
//            }
//        } else {
//            return 0;
//        }
//    }
//
//
//        private static Optional<CompoundTag> getMatchingItem(ItemStack p_150757_, ListTag p_150758_) {
//        return p_150757_.is(Items.BUNDLE) ? Optional.empty() : p_150758_.stream().filter(CompoundTag.class::isInstance).map(CompoundTag.class::cast).filter((p_186350_) -> {
//            return ItemStack.isSameItemSameTags(ItemStack.of(p_186350_), p_150757_);
//        }).findFirst();
//    }
//
//        private static int getWeight() {
//        return 1;
//    }
//
//        private static int getContentWeight(ItemStack p_150779_) {
//        return getContents(p_150779_).mapToInt((p_186356_) -> {
//            return getWeight() * p_186356_.getCount();
//        }).sum();
//    }
//
//        private static boolean dropContents(ItemStack p_150730_, Player p_150731_) {
//        CompoundTag compoundtag = p_150730_.getOrCreateTag();
//        if (!compoundtag.contains("Items")) {
//            return false;
//        } else {
//            if (p_150731_ instanceof ServerPlayer) {
//                ListTag listtag = compoundtag.getList("Items", 10);
//
//                for(int i = 0; i < listtag.size(); ++i) {
//                    CompoundTag compoundtag1 = listtag.getCompound(i);
//                    ItemStack itemstack = ItemStack.of(compoundtag1);
//                    p_150731_.drop(itemstack, true);
//                }
//            }
//
//            p_150730_.removeTagKey("Items");
//            return true;
//        }
//    }
//
//        private static Stream<ItemStack> getContents(ItemStack p_150783_) {
//        CompoundTag compoundtag = p_150783_.getTag();
//        if (compoundtag == null) {
//            return Stream.empty();
//        } else {
//            ListTag listtag = compoundtag.getList("Items", 10);
//            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
//        }
//    }
//
//
//        public void appendHoverText(ItemStack p_150749_, Level p_150750_, List<Component> p_150751_, TooltipFlag p_150752_) {
//        p_150751_.add(Component.translatable("item.minecraft.bundle.fullness", getContentWeight(p_150749_), 320).withStyle(ChatFormatting.GRAY));
//    }
//
//        @Override
//        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
//        CompoundTag compoundtag = stack.getOrCreateTag();
//        ListTag listtag = compoundtag.getList("Items", 10);
//        for(int s = 0; s < listtag.size(); ++s) {
//            CompoundTag compoundtag1 = listtag.getCompound(s);
//
//            ItemStack itemStack = ItemStack.of(compoundtag1);
//            if (itemStack.is(DNAItems.atp_height)) {
//                int count = itemStack.getCount();
//                int a = count / 4;
//                multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        a,
//                        AttributeModifier.Operation.ADD_VALUE));
//            }
//
//            if (itemStack.is(DNAItems.cell_off_on)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//
//            if (itemStack.is(DNAItems.cell_oxygen)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                count *= 0.5F;
//                multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//
//
//            if (itemStack.is(DNAItems.cell_in_water)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(Attributes.WATER_MOVEMENT_EFFICIENCY, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_VALUE));
//            }
//
//            if (itemStack.is(DNAItems.cell_break_down_water)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                count *= 1.5F;
//                multimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//
//            if (itemStack.is(DNAItems.cell_in_air)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(Attributes.JUMP_STRENGTH, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//            if (itemStack.is(DNAItems.cell_ground)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                count *= 2F;
//                multimap.put(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//            if (itemStack.is(DNAItems.cell_necrosis)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(AttReg.heal, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//            if (itemStack.is(DNAItems.cell_bone_add)) {
//                float count = itemStack.getCount();
//                count /= 4f;
//                multimap.put(Attributes.ARMOR, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_VALUE));
//            }
//            if (itemStack.is(DNAItems.cell_sense)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(Attributes.OXYGEN_BONUS, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_VALUE));
//
//                multimap.put(Attributes.SUBMERGED_MINING_SPEED, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count * 10,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//            if (itemStack.is(DNAItems.cell_synthesis)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//            if (itemStack.is(DNAItems.cell_putrefactive)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(Attributes.BURNING_TIME, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        -count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//            if (itemStack.is(DNAItems.cell_dna_suppression)) {
//                float count = itemStack.getCount();
//                count /= 100f;
//                multimap.put(AttReg.cit, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//
//            if (itemStack.is(DNAItems.cell_preferential)) {
//                {
//                    float count = itemStack.getCount();
//                    count /= 100;
//                    multimap.put(AttReg.heal, new AttributeModifier(
//                            ResourceLocation.withDefaultNamespace("base_attack_damage_heal_cell_preferential" + this.getDescriptionId()),
//                            count,
//                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                {
//                    float count = itemStack.getCount();
//                    count /= 4;
//                    multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
//                            ResourceLocation.withDefaultNamespace("base_attack_damage_max_health_cell_preferential" + this.getDescriptionId()),
//                            count,
//                            AttributeModifier.Operation.ADD_VALUE));
//                }
//            }
//            if (itemStack.is(DNAItems.cell_chromosome)) {
//                float count = itemStack.getCount();
//                count /= 10;
//                multimap.put(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(
//                        ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
//                        count,
//                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//            }
//
//        }
//        return multimap;
//    }
//
//        public void onDestroyed(ItemEntity p_150728_) {
//        ItemUtils.onContainerDestroyed(p_150728_, getContents(p_150728_.getItem()));
//    }
//
//        private void playInsertSound(Entity p_186352_) {
//        p_186352_.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + p_186352_.level().getRandom().nextFloat() * 0.4F);
//    }
//
//        private void playDropContentsSound(Entity p_186354_) {
//        p_186354_.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + p_186354_.level().getRandom().nextFloat() * 0.4F);
//    }
//
//        @Override
//        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
//        stack.getOrCreateTag().putString("ytgld","ytgld");
//    }
//    }
}