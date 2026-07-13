package com.ytgld.moonstone.item.ms.maxitem.uncommon.common;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class GreedAmout extends CommonItem {

    public GreedAmout(Properties properties) {
        super(properties);
    }

    public static void greedamout(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.greedamout.get()) || Handler.hascurio(player,Items.maxamout.asItem())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));
                }
            }
        }
        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.greedamout.get()) || Handler.hascurio(player,Items.maxamout.asItem())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));
                    player.heal(4);
                }
            }
        }

    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.greedamout.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.greedamout.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}

