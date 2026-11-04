package com.ytgld.moonstone.item.ms.necora;

import com.ytgld.moonstone.event.EquippedEvt;
import com.ytgld.moonstone.item.DNAItems;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class DnaBox extends TheNecora {
    public DnaBox(Properties properties) {
        super(properties);
    }

    private static final String theDnaBoxNumber = "theDnaBoxNumber";
    @Override
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (Other.getItem() instanceof DNAItems.DNAS) {
            return false;
        }
        if (me.getCount() != 1) return false;
        CompoundTag tag = me.get(DataReg.tag);
        if (tag == null) {
            me.set(DataReg.tag,new CompoundTag());
            return true;
        }
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.getItem() instanceof TheNecora) {
                if (me.getItem() instanceof DnaBox) {
                    int s = 1;
                    CompoundTag compoundTag =Other.get(DataReg.tag);
                    if (compoundTag != null) {
                        if (compoundTag.getBoolean(Difficulty.PEACEFUL.getSerializedName())){
                            s = 2;
                        }
                        if (compoundTag.getBoolean(Difficulty.EASY.getSerializedName())){
                            s = 3;
                        }
                        if (compoundTag.getBoolean(Difficulty.NORMAL.getSerializedName())){
                            s = 4;
                        }
                        if (compoundTag.getBoolean(Difficulty.HARD.getSerializedName())){
                            s = 5;
                        }
                        if (compoundTag.getBoolean(EquippedEvt.lootTable)){
                            s = 6;
                        }
                    }
                    tag.putInt(theDnaBoxNumber,tag.getInt(theDnaBoxNumber) + s);
                    Other.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 16;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return super.use(level, player, usedHand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null && livingEntity instanceof Player player) {
            int value = tag.getInt(theDnaBoxNumber);
            Stream<Item> items = BuiltInRegistries.ITEM.stream().filter((item)-> (item instanceof DNAItems.DNAS));
            List<Item> list = items.toList();
            if (value > 0) {
                for (int i = 0; i < value; i++) {
                    Item item = list.get(new Random().nextInt(list.size()));
                    player.addItem( new ItemStack(item,
                            Mth.nextInt(RandomSource.create(),1,3)));
                }
            }
            tag.putInt(theDnaBoxNumber,0);
        }

        return stack;
    }

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.moonstone.medicinebox.tool").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("item.moonstone.medicinebox.tool.2").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        builder.accept(Component.literal(""));
    }
}
