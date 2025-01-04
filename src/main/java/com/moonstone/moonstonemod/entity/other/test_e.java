package com.moonstone.moonstonemod.entity.other;

import com.moonstone.moonstonemod.entity.necora.cell_zombie;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class test_e extends cell_zombie {

    public test_e(EntityType<? extends test_e> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
        this.setNoAi(true);
    }
    public int time = 0;
    public void tick() {
        super.tick();
        this.time++;
        if (this.time > 50) {
            this.discard();
        }
    }
    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }
    @Override
    public void setItemInHand(InteractionHand p_21009_, ItemStack p_21010_) {

    }

    @Override
    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        return false;
    }
}


