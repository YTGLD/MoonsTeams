package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class blood_magic_box extends Item implements ICurioItem, Blood {
    public blood_magic_box() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void Did(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.blood_magic_box.get())){

                blood blood = new blood(EntityTs.blood.get(),player.level());
                blood.setDeltaMovement(Mth.nextDouble(RandomSource.create(),0.1,0.11),Mth.nextDouble(RandomSource.create(),0.095,0.1),Mth.nextDouble(RandomSource.create(),0.099,0.1));
                blood.setOwner(player);
                blood.setPos(event.getEntity().getX(),event.getEntity().getY()+1.5f,  event.getEntity().getZ());

                player.level().addFreshEntity(blood);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_magic_box.tool.string").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }    }


}
