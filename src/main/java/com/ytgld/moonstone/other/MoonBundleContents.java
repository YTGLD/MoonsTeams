package com.ytgld.moonstone.other;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoonBundleContents {
    public static final MoonBundleContents EMPTY = new MoonBundleContents(List.of());
    public static final Codec<MoonBundleContents> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, MoonBundleContents> STREAM_CODEC;
    private static final Fraction BUNDLE_IN_BUNDLE_WEIGHT;
    private static final int NO_STACK_INDEX = -1;
    final List<ItemStack> items;
    final Fraction weight;

    MoonBundleContents(List<ItemStack> items, Fraction weight) {
        this.items = items;
        this.weight = weight;
    }

    public MoonBundleContents(List<ItemStack> items) {
        this(items, computeContentWeight(items));
    }

    private static Fraction computeContentWeight(List<ItemStack> content) {
        Fraction fraction = Fraction.ZERO;

        ItemStack itemstack;
        for(Iterator<ItemStack> var2 = content.iterator(); var2.hasNext(); fraction = fraction.add(getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)))) {
            itemstack = var2.next();
        }

        return fraction;
    }

    static Fraction getWeight(ItemStack stack) {
        MoonBundleContents bundlecontents = stack.get(DataReg.BUNDLE_CONTENTS);
        if (bundlecontents != null) {
            return BUNDLE_IN_BUNDLE_WEIGHT.add(bundlecontents.weight());
        }
        return Fraction.getFraction(1,320);
    }

    public Iterable<ItemStack> items() {
        return this.items;
    }
    public Iterable<ItemStack> itemsCopy() {
        return Lists.transform(this.items, ItemStack::copy);
    }

    public int size() {
        return this.items.size();
    }

    public Fraction weight() {
        return this.weight;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            boolean var10000;
            if (other instanceof MoonBundleContents) {
                MoonBundleContents bundlecontents = (MoonBundleContents)other;
                var10000 = this.weight.equals(bundlecontents.weight) && ItemStack.listMatches(this.items, bundlecontents.items);
            } else {
                var10000 = false;
            }

            return var10000;
        }
    }

    public int hashCode() {
        return ItemStack.hashStackList(this.items);
    }

    public String toString() {
        return "MoonBundleContents" + String.valueOf(this.items);
    }

    static {
        CODEC = ItemStack.CODEC.listOf().xmap(MoonBundleContents::new, (p_331551_) -> {
            return p_331551_.items;
        });
        STREAM_CODEC = ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()).map(MoonBundleContents::new, (p_331649_) -> {
            return p_331649_.items;
        });
        BUNDLE_IN_BUNDLE_WEIGHT = Fraction.getFraction(1, 128);
    }

    public static class Mutable {
        private final List<ItemStack> items;
        private Fraction weight;

        public Mutable(MoonBundleContents contents) {
            this.items = new ArrayList<>(contents.items);
            this.weight = contents.weight;
        }

        public MoonBundleContents.Mutable clearItems() {
            this.items.clear();
            this.weight = Fraction.ZERO;
            return this;
        }

        private int findStackIndex(ItemStack stack) {
            if (stack.isStackable()) {
                for (int i = 0; i < this.items.size(); ++i) {
                    if (ItemStack.isSameItemSameComponents(this.items.get(i), stack)) {
                        return i;
                    }
                }

            }
            return -1;
        }

        private int getMaxAmountToAdd(ItemStack stack) {
            Fraction fraction = Fraction.ONE.subtract(this.weight);
            return Math.clamp(fraction.divideBy(MoonBundleContents.getWeight(stack)).intValue(), 0, 64);
        }

        public int tryInsert(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canFitInsideContainerItems()) {

                int i = Math.min(stack.getCount(), this.getMaxAmountToAdd(stack));

                int totalItems = this.items.stream().mapToInt(ItemStack::getCount).sum();
                int availableSpace = 320 - totalItems;
                int maxAddable = Math.min(availableSpace, 64);

                i = Math.min(i, maxAddable);

                if (i == 0) {
                    return 0;
                } else {
                    this.weight = this.weight.add(MoonBundleContents.getWeight(stack).multiplyBy(Fraction.getFraction(i, 1)));
                    int j = this.findStackIndex(stack);

                    if (j != -1) {
                        ItemStack itemstack = this.items.remove(j);
                        int newCount = itemstack.getCount() + i;
                        if (newCount > 64) {
                            newCount = 64;
                            i = 64 - itemstack.getCount(); // 更新可添加的数量
                        }

                        ItemStack itemstack1 = itemstack.copyWithCount(newCount);
                        stack.shrink(i);
                        this.items.addFirst(itemstack1);
                    } else {
                        this.items.addFirst(stack.split(i));
                    }

                    return i;
                }
            } else {
                return 0;
            }
        }

        @Nullable
        public ItemStack removeOne() {
            if (this.items.isEmpty()) {
                return null;
            } else {
                ItemStack itemstack = this.items.removeFirst().copy();
                this.weight = this.weight.subtract(MoonBundleContents.getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)));
                return itemstack;
            }
        }

        public Fraction weight() {
            return this.weight;
        }

        public MoonBundleContents toImmutable() {
            return new MoonBundleContents(List.copyOf(this.items), this.weight);
        }
    }
}
