package com.moonstone.moonstonemod.item.plague;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.DNAItems;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import com.moonstone.moonstonemod.item.necora;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class dna_box extends TheNecoraIC {
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (me.getCount() != 1) return false;
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.getItem() instanceof Iplague) {
                if (me.getItem() instanceof dna_box) {
                    CompoundTag tag = me.getTag();
                    if (tag != null){
                        ResourceLocation Dna = BuiltInRegistries.ITEM.getKey(Other.getItem());
                        String dnas = Dna.getPath();
                        if (!tag.getBoolean(dnas)) {
                            tag.putBoolean(dnas, true);

                            if (Other.getTag()!=null) {
                                if (Other.getTag().getBoolean(Difficulty.PEACEFUL.getKey())) {
                                    tag.putBoolean(Difficulty.PEACEFUL.getKey() + dnas, true);
                                }
                                if (Other.getTag().getBoolean(Difficulty.EASY.getKey())) {
                                    tag.putBoolean(Difficulty.EASY.getKey() + dnas, true);
                                }
                                if (Other.getTag().getBoolean(Difficulty.NORMAL.getKey())) {
                                    tag.putBoolean(Difficulty.NORMAL.getKey() + dnas, true);
                                }
                                if (Other.getTag().getBoolean(Difficulty.HARD.getKey())) {
                                    tag.putBoolean(Difficulty.HARD.getKey() + dnas, true);
                                }
                                if (Other.getTag().getBoolean(NewEvent.lootTable)) {
                                    tag.putBoolean(NewEvent.lootTable + dnas, true);
                                }

                            }
                            Other.shrink(1);
                            return true;
                        }

                    }else {
                        me.getOrCreateTag();
                    }
                }
            }
            if (Other.getItem() instanceof medIC necoraIC) {
                if (me.getItem() instanceof dna_box) {
                    CompoundTag tag = me.getTag();
                    if (tag != null){
                        ResourceLocation Dna = BuiltInRegistries.ITEM.getKey(necoraIC);
                        String dnas = Dna.getPath();
                        if (!tag.getBoolean(dnas)) {
                            tag.putBoolean(dnas, true);
                            Other.shrink(1);
                            return true;
                        }
                    }else {
                        me.getOrCreateTag();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 10;
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

        int count = Mth.nextInt(RandomSource.create(),3,9);
        int count1 = Mth.nextInt(RandomSource.create(),2,6);
        int count2 = Mth.nextInt(RandomSource.create(),3,7);
        int count3 = Mth.nextInt(RandomSource.create(),2,8);
        int count4 = Mth.nextInt(RandomSource.create(),1,2);
        int cn = Mth.nextInt(RandomSource.create(),2,4);
        int cn1 = Mth.nextInt(RandomSource.create(),3,5);
        int cn2 = Mth.nextInt(RandomSource.create(),1,5);
        int cn3 = Mth.nextInt(RandomSource.create(),2,6);
        int cn4 = Mth.nextInt(RandomSource.create(),1,6);

        int count5 = Mth.nextInt(RandomSource.create(),1,2);
        if (stack.getTag()!=null){

            while (stack.getTag().getAllKeys().iterator().hasNext()) {

                String itemId = stack.getTag().getAllKeys().iterator().next();
                ResourceLocation resourceLocation = new  ResourceLocation(MoonStoneMod.MODID, itemId);


                if (BuiltInRegistries.ITEM.containsKey(resourceLocation)) {
                    Item Dna = BuiltInRegistries.ITEM.get(resourceLocation);
                    if (livingEntity instanceof Player player) {
                        if (Dna instanceof necora) {
                            player.addItem(new ItemStack(DNAItems.atp_height.get(), count * 2));
                            player.addItem(new ItemStack(DNAItems.cell_inheritance.get(), count1 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_big_boom.get(), count2 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_disorder.get(), count3 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_in_air.get(), count4 * 2));
                            player.addItem(new ItemStack(DNAItems.cell_off_on.get(), count5 * 2));

                            stack.getTag().getAllKeys().remove(itemId);
                        }
                        if (Dna instanceof Iplague) {
                            switch (Mth.nextInt(RandomSource.create(), 1, 13)) {
                                case 1:
                                    player.addItem(new ItemStack(DNAItems.atp_height.get(), count));
                                    break;
                                case 2:
                                    player.addItem(new ItemStack(DNAItems.cell_big_boom.get(), count1));
                                    break;
                                case 3:
                                    player.addItem(new ItemStack(DNAItems.cell_disorder.get(), count2));
                                    break;
                                case 4:
                                    player.addItem(new ItemStack(DNAItems.cell_off_on.get(), count));
                                    break;
                                case 5:
                                    player.addItem(new ItemStack(DNAItems.cell_darwin.get(), count3));
                                    break;
                                case 6:
                                    player.addItem(new ItemStack(DNAItems.cell_god.get(), count5));
                                    break;
                                case 7:
                                    player.addItem(new ItemStack(DNAItems.speed_metabolism.get(), count4));
                                    break;
                                case 8:
                                    player.addItem(new ItemStack(DNAItems.cell_oxygen.get(), count));
                                    break;
                                case 9:
                                    player.addItem(new ItemStack(DNAItems.cell_break_down_water.get(), count4));
                                    break;
                                case 10:
                                    player.addItem(new ItemStack(DNAItems.cell_ground.get(), count3));
                                    break;
                                case 11:
                                    player.addItem(new ItemStack(DNAItems.cell_in_water.get(), count2));
                                    break;
                                case 12:
                                    player.addItem(new ItemStack(DNAItems.cell_inheritance.get(), count1));
                                    break;
                                case 13:
                                    player.addItem(new ItemStack(DNAItems.cell_in_air.get(), count));
                                    break;
                            }
                            stack.getTag().getAllKeys().remove(itemId);
                        }
                    }

                }else if (livingEntity instanceof Player player) {
                    if (itemId.contains(Difficulty.PEACEFUL.getKey())) {
                        int b = 1;
                        switch (Mth.nextInt(RandomSource.create(), 1, 13)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height.get(), count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom.get(), count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder.get(), count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on.get(), count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin.get(), count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god.get(), count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism.get(), count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen.get(), count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water.get(), count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground.get(), count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water.get(), count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance.get(), count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air.get(), count * b));
                                break;
                        }
                        stack.getTag().getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(Difficulty.EASY.getKey())) {
                        int b = 2;
                        switch (Mth.nextInt(RandomSource.create(), 1, 28)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height.get(), count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom.get(), count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder.get(), count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on.get(), count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin.get(), count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god.get(), count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism.get(), count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen.get(), count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water.get(), count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground.get(), count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water.get(), count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance.get(), count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air.get(), count * b));
                                break;

                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis.get(), cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;

                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial.get(), count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression.get(), count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive.get(), count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis.get(), count5 * b));
                                break;
                        }
                        stack.getTag().getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(Difficulty.NORMAL.getKey())) {
                        int b = 3;
                        switch (Mth.nextInt(RandomSource.create(), 1, 28)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height.get(), count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom.get(), count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder.get(), count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on.get(), count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin.get(), count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god.get(), count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism.get(), count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen.get(), count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water.get(), count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground.get(), count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water.get(), count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance.get(), count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air.get(), count * b));
                                break;

                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis.get(), cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;
                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial.get(), count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression.get(), count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive.get(), count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis.get(), count5 * b));
                                break;
                        }
                        stack.getTag().getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(Difficulty.HARD.getKey())) {
                        int b = 4;
                        switch (Mth.nextInt(RandomSource.create(), 1, 28)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.atp_height.get(), count * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_big_boom.get(), count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_disorder.get(), count2 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_off_on.get(), count * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_darwin.get(), count3 * b));
                                break;
                            case 6:
                                player.addItem(new ItemStack(DNAItems.cell_god.get(), count5 * b));
                                break;
                            case 7:
                                player.addItem(new ItemStack(DNAItems.speed_metabolism.get(), count4 * b));
                                break;
                            case 8:
                                player.addItem(new ItemStack(DNAItems.cell_oxygen.get(), count * b));
                                break;
                            case 9:
                                player.addItem(new ItemStack(DNAItems.cell_break_down_water.get(), count4 * b));
                                break;
                            case 10:
                                player.addItem(new ItemStack(DNAItems.cell_ground.get(), count3 * b));
                                break;
                            case 11:
                                player.addItem(new ItemStack(DNAItems.cell_in_water.get(), count2 * b));
                                break;
                            case 12:
                                player.addItem(new ItemStack(DNAItems.cell_inheritance.get(), count1 * b));
                                break;
                            case 13:
                                player.addItem(new ItemStack(DNAItems.cell_in_air.get(), count * b));
                                break;

                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis.get(), cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;

                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial.get(), count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression.get(), count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive.get(), count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis.get(), count5 * b));
                                break;
                        }

                        stack.getTag().getAllKeys().remove(itemId);
                        break;
                    }
                    if (itemId.contains(NewEvent.lootTable)) {
                        int b = 3;
                        switch (Mth.nextInt(RandomSource.create(), 1, 5)) {
                            case 1:
                                player.addItem(new ItemStack(DNAItems.cell_compress.get(), count1 * b));
                                break;
                            case 2:
                                player.addItem(new ItemStack(DNAItems.cell_preferential.get(), count1 * b));
                                break;
                            case 3:
                                player.addItem(new ItemStack(DNAItems.cell_flu.get(), count1 * b));
                                break;
                            case 4:
                                player.addItem(new ItemStack(DNAItems.cell_constant.get(), count1 * b));
                                break;
                            case 5:
                                player.addItem(new ItemStack(DNAItems.cell_chromosome.get(), count1 * b));
                                break;
                        }
                        switch (Mth.nextInt(RandomSource.create(), 14, 28)) {
                            case 14:
                                player.addItem(new ItemStack(DNAItems.cell_necrosis.get(), cn * b));
                                break;
                            case 15:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 16:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 17:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 18:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 19:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;

                            case 20:
                                player.addItem(new ItemStack(DNAItems.cell_digestion.get(), cn1 * b));
                                break;
                            case 21:
                                player.addItem(new ItemStack(DNAItems.cell_acid.get(), cn2 * b));
                                break;
                            case 22:
                                player.addItem(new ItemStack(DNAItems.cell_eyes.get(), cn3 * b));
                                break;
                            case 23:
                                player.addItem(new ItemStack(DNAItems.cell_bone_add.get(), cn4 * b));
                                break;
                            case 24:
                                player.addItem(new ItemStack(DNAItems.cell_sense.get(), count5 * b));
                                break;


                            case 25:
                                player.addItem(new ItemStack(DNAItems.cell_cranial.get(), count5 * b));
                                break;
                            case 26:
                                player.addItem(new ItemStack(DNAItems.cell_dna_suppression.get(), count5 * b));
                                break;
                            case 27:
                                player.addItem(new ItemStack(DNAItems.cell_putrefactive.get(), count5 * b));
                                break;
                            case 28:
                                player.addItem(new ItemStack(DNAItems.cell_synthesis.get(), count5 * b));
                                break;
                        }
                        stack.getTag().getAllKeys().remove(itemId);
                        break;
                    }
                    break;
                }
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.moonstone.medicinebox.tool").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.moonstone.medicinebox.tool.2").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        pTooltipComponents.add(Component.literal(""));
        CompoundTag tag = pStack.getTag();
        if (tag != null){
            for (String s : tag.getAllKeys()) {
                pTooltipComponents.add(Component.translatable(s).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
            }
        }
    }
}
