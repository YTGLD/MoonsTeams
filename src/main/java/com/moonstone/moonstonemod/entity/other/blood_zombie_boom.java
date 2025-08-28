package com.moonstone.moonstonemod.entity.other;

import com.moonstone.moonstonemod.entity.necora.cell_zombie;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class blood_zombie_boom  extends cell_zombie {

    public blood_zombie_boom(EntityType<? extends blood_zombie_boom> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
        this.setNoAi(true);
    }
    public int time = 0;
    public void tick() {

        super.tick();
        this.setNoAi(true);
        this.setNoGravity(true);
        this.time++;
        if (this.time > 20) {
            this.discard();
        }
    }

    public boolean isPushable() {
        return false;
    }
    @Override
    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        return false;
    }
    protected void doPush(Entity p_27415_) {
    }
    protected void pushEntities() {
    }

}



