package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodMagicBox extends BloodItem {

    public BloodMagicBox(Properties properties) {
        super(properties);
    }

    public static void Did(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.blood_magic_box.get())){

                ItemEntity blood = new ItemEntity(player.level(),player.getX(),player.getY(),player.getZ(),Items.blood.get().asItem().getDefaultInstance());
                blood.setDeltaMovement(Mth.nextDouble(RandomSource.create(),0.1,0.11),Mth.nextDouble(RandomSource.create(),0.095,0.1),Mth.nextDouble(RandomSource.create(),0.099,0.1));
                blood.setPos(event.getEntity().getX(),event.getEntity().getY()+1.5f,  event.getEntity().getZ());

                player.level().addFreshEntity(blood);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (p_41424_.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_magic_box.tool.string").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
