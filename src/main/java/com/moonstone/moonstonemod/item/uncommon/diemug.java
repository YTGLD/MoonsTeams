package com.moonstone.moonstonemod.item.uncommon;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class diemug extends Item {

    private final String size ="DiemugSize";
    public diemug() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC).food(
                new FoodProperties.Builder().alwaysEat().nutrition(0).saturationMod(0.0f).build()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_150730_, Level level, LivingEntity living){

        CompoundTag compoundtag = p_150730_.getOrCreateTag();

        if (!compoundtag.contains("Items")) {



        }else {
            if (living instanceof ServerPlayer) {
                ListTag listtag = compoundtag.getList("Items", 10);
                for(int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    ItemStack itemstack = ItemStack.of(compoundtag1);

                    List<MobEffectInstance> mobEffect = PotionUtils.getMobEffects(itemstack);

                    if (!mobEffect.isEmpty()){
                        for (MobEffectInstance mobEffectInstance : mobEffect){
                            MobEffect getEffect  =   mobEffectInstance.getEffect();

                            if (!getEffect.isInstantenous()) {
                                living.addEffect(new MobEffectInstance(getEffect, mobEffectInstance.getDuration(), mobEffectInstance.getAmplifier()));
                            }
                        }



                    }
                }
            }
        }


        return this.getDefaultInstance();
    }


    public boolean overrideOtherStackedOnMe(ItemStack p_150742_, ItemStack p_150743_, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150742_.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (p_150743_.isEmpty()) {
                if (p_150743_.getItem() instanceof PotionItem) {
                    removeOne(p_150742_).ifPresent((p_186347_) -> {
                        this.playRemoveOneSound(p_150746_);
                        p_150747_.set(p_186347_);
                    });
                }
            } else {
                if (p_150743_.getItem() instanceof PotionItem) {
                    int i = add(p_150742_, p_150743_);
                    if (i > 0) {
                        this.playInsertSound(p_150746_);
                        p_150743_.shrink(i);
                    }
                }
            }
            return true;
        }
        return false;
    }
    private static Optional<ItemStack> removeOne(ItemStack p_150781_) {
        CompoundTag compoundtag = p_150781_.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return Optional.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            if (listtag.isEmpty()) {
                return Optional.empty();
            } else {
                int i = 0;
                CompoundTag compoundtag1 = listtag.getCompound(0);
                ItemStack itemstack = ItemStack.of(compoundtag1);
                listtag.remove(0);
                if (listtag.isEmpty()) {
                    p_150781_.removeTagKey("Items");
                }

                return Optional.of(itemstack);
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_150760_, Player p_150761_, InteractionHand p_150762_) {
        ItemStack itemstack = p_150761_.getItemInHand(p_150762_);
        p_150761_.startUsingItem(p_150762_);
        return InteractionResultHolder.sidedSuccess(itemstack, p_150760_.isClientSide());

    }

    public boolean isBarVisible(ItemStack p_150769_) {
        return getContentWeight(p_150769_) > 0;
    }

    public int getBarWidth(ItemStack p_150771_) {
        return Math.min(1 + 12 * getContentWeight(p_150771_) / 64, 13);
    }



    private static int add(ItemStack p_150764_, ItemStack p_150765_) {
        if (!p_150765_.isEmpty() && p_150765_.getItem().canFitInsideContainerItems()) {
            CompoundTag compoundtag = p_150764_.getOrCreateTag();
            if (!compoundtag.contains("Items")) {
                compoundtag.put("Items", new ListTag());
            }

            int i = getContentWeight(p_150764_);
            int j = getWeight(p_150765_);
            int k = Math.min(p_150765_.getCount(), (64 - i) / j);
            if (k == 0) {
                return 0;
            } else {
                ListTag listtag = compoundtag.getList("Items", 10);
                Optional<CompoundTag> optional = getMatchingItem(p_150765_, listtag);
                if (optional.isPresent()) {
                    CompoundTag compoundtag1 = optional.get();
                    ItemStack itemstack = ItemStack.of(compoundtag1);
                    itemstack.grow(k);
                    itemstack.save(compoundtag1);
                    listtag.remove(compoundtag1);
                    listtag.add(0, (Tag)compoundtag1);
                } else {
                    ItemStack itemstack1 = p_150765_.copyWithCount(k);
                    CompoundTag compoundtag2 = new CompoundTag();
                    itemstack1.save(compoundtag2);
                    listtag.add(0, (Tag)compoundtag2);
                }

                return k;
            }
        } else {
            return 0;
        }
    }

    private static Optional<CompoundTag> getMatchingItem(ItemStack p_150757_, ListTag p_150758_) {
        return p_150757_.is(Items.BUNDLE) ? Optional.empty() : p_150758_.stream().filter(CompoundTag.class::isInstance).map(CompoundTag.class::cast).filter((p_186350_) -> {
            return ItemStack.isSameItemSameTags(ItemStack.of(p_186350_), p_150757_);
        }).findFirst();
    }

    private static int getWeight(ItemStack p_150777_) {
        return 8;
    }

    private static int getContentWeight(ItemStack p_150779_) {
        return getContents(p_150779_).mapToInt((p_186356_) -> getWeight(p_186356_) * p_186356_.getCount()).sum();
    }



    private static Stream<ItemStack> getContents(ItemStack p_150783_) {
        CompoundTag compoundtag = p_150783_.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    public Optional<TooltipComponent> getTooltipImage(ItemStack p_150775_) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        getContents(p_150775_).forEach(nonnulllist::add);
        return Optional.of(new BundleTooltip(nonnulllist, getContentWeight(p_150775_)));
    }

    public void appendHoverText(ItemStack p_150749_, Level p_150750_, List<Component> p_150751_, TooltipFlag p_150752_) {
        p_150751_.add(Component.translatable("item.minecraft.bundle.fullness", getContentWeight(p_150749_), 64).withStyle(ChatFormatting.GRAY));
        p_150751_.add(Component.translatable("item.diemug.tool.string").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        p_150751_.add(Component.translatable("item.diemug.tool.string.1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        p_150751_.add(Component.translatable(""));
        p_150751_.add(Component.translatable("item.diemug.tool.string.2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
    }

    public void onDestroyed(ItemEntity p_150728_) {
        ItemUtils.onContainerDestroyed(p_150728_, getContents(p_150728_.getItem()));
    }

    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + p_186343_.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity p_186352_) {
        p_186352_.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + p_186352_.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity p_186354_) {
        p_186354_.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + p_186354_.level().getRandom().nextFloat() * 0.4F);
    }

}


