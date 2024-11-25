package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class the_heart extends Item implements ICurioItem {
    public the_heart() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("the_heart", ChatFormatting.GOLD)));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return !Handler.hascurio(player, stack.getItem());
        }
        return true;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
    public boolean overrideOtherStackedOnMe(ItemStack p_150742_, ItemStack p_150743_, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150742_.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (p_150743_.isEmpty()) {
                this.playRemoveOneSound(p_150746_);
            } else {
                int i = add(p_150742_, p_150743_);
                if (i > 0) {
                    this.playInsertSound(p_150746_);
                    p_150743_.shrink(i);
                }
            }
            return true;
        }
        return false;
    }
    private static boolean dropContents(ItemStack p_150730_, Player p_150731_) {
        CompoundTag compoundtag = p_150730_.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return false;
        } else {
            if (p_150731_ instanceof ServerPlayer) {
                ListTag listtag = compoundtag.getList("Items", 10);

                for(int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    ItemStack itemstack = ItemStack.of(compoundtag1);
                    p_150731_.drop(itemstack, true);
                }
            }

            p_150730_.removeTagKey("Items");
            return true;
        }
    }
    public InteractionResultHolder<ItemStack> use(Level p_150760_, Player p_150761_, InteractionHand p_150762_) {
        ItemStack itemstack = p_150761_.getItemInHand(p_150762_);
        if (dropContents(itemstack, p_150761_)) {
            return InteractionResultHolder.sidedSuccess(itemstack, p_150760_.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public boolean isBarVisible(ItemStack p_150769_) {
        return getContentWeight(p_150769_) > 0;
    }

    public int getBarWidth(ItemStack p_150771_) {
        return Math.min(1 + 12 * getContentWeight(p_150771_) / 64, 13);
    }

    @Override
    public int getBarColor(ItemStack p_150901_) {
        float stackMaxDamage = this.getBarWidth(p_150901_);
        float f = Math.max(0.0F, (stackMaxDamage - (float)64 / stackMaxDamage));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    private int add(ItemStack p_150764_, ItemStack p_150765_) {
        if (!p_150765_.isEmpty() && p_150765_.getItem().canFitInsideContainerItems()) {
            CompoundTag compoundtag = p_150764_.getOrCreateTag();
            if (!compoundtag.contains("Items")) {
                compoundtag.put("Items", new ListTag());
            }

            int i = getContentWeight(p_150764_);
            int j = getWeight();
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

    public static Optional<CompoundTag> getMatchingItem(ItemStack p_150757_, ListTag p_150758_) {
        return p_150758_.stream().filter(CompoundTag.class::isInstance).map(CompoundTag.class::cast).filter((p_186350_) -> ItemStack.isSameItemSameTags(ItemStack.of(p_186350_), p_150757_)).findFirst();
    }

    private static int getWeight() {
        return 16;
    }

    private static int getContentWeight(ItemStack p_150779_) {
        return (getContents(p_150779_).mapToInt((p_186356_) -> getWeight() * p_186356_.getCount()).sum());
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

    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack p_150775_) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        getContents(p_150775_).forEach(nonnulllist::add);
        return Optional.of(new BundleTooltip(nonnulllist, getContentWeight(p_150775_)));
    }

    public void appendHoverText(ItemStack p_150749_, Level p_150750_, List<Component> p_150751_, TooltipFlag p_150752_) {
        p_150751_.add(Component.translatable("item.the_heart.tool.string").withStyle(ChatFormatting.GOLD));
        p_150751_.add(Component.translatable("item.the_heart.tool.string.1").withStyle(ChatFormatting.GOLD));
        p_150751_.add(Component.translatable("item.the_heart.tool.string.2").withStyle(ChatFormatting.GOLD));
        p_150751_.add(Component.translatable(""));
        p_150751_.add(Component.translatable("item.minecraft.bundle.fullness", (getContentWeight(p_150749_)/16), 4).withStyle(ChatFormatting.GOLD,ChatFormatting.BOLD));
    }

    public void onDestroyed(ItemEntity p_150728_) {
        ItemUtils.onContainerDestroyed(p_150728_, getContents(p_150728_.getItem()));
    }

    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.IRON_GOLEM_REPAIR, 0.8F, 0.8F + p_186343_.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity p_186352_) {
        p_186352_.playSound(SoundEvents.IRON_GOLEM_REPAIR, 0.8F, 0.8F + p_186352_.level().getRandom().nextFloat() * 0.4F);
    }

}
