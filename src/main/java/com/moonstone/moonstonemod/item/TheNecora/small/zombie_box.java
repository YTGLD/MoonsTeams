package com.moonstone.moonstonemod.item.TheNecora.small;

import com.moonstone.moonstonemod.entity.necora.small_zombie;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class zombie_box extends TheNecoraIC {
    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        small_zombie small_zombie = new small_zombie(EntityTs.small_zombie.get(), p_41432_);
        small_zombie.setPos(p_41433_.position());
        small_zombie.setOwnerUUID(p_41433_.getUUID());
        p_41432_.addFreshEntity(small_zombie);
        p_41433_.getItemInHand(p_41434_).shrink(1);
        p_41433_.setItemInHand(p_41434_,new ItemStack(Items.zombie_box_nobo.get()));

        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
