package com.moonstone.moonstonemod.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class bolt extends cell_zombie{
    public long seed;
    public float timeB;

    public bolt(EntityType<? extends bolt> c, Level p_34272_) {
        super(c, p_34272_);
        this.seed = this.random.nextLong();
    }
    public boolean hurt(DamageSource p_27424_, float p_27425_) {
        return false;
    }
    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity p_27415_) {
    }

    public float size = 0.1f;
    public boolean light = true;

    public void setLight(boolean light) {
        this.light = light;
    }

    public void setSize(float size) {
        this.size = size;
    }

    protected void pushEntities() {
    }
    private int life = 60;
    private int flashes;
    public void tick() {
        super.tick();
        this.setNoAi(true);
        timeB++;
        --this.life;
        if (this.life < 0) {
            if (this.flashes == 0) {
                this.discard();
            } else if (this.life < -this.random.nextInt(10)) {
                --this.flashes;
                this.life = 1;
                this.seed = this.random.nextLong();
            }
        }

    }



}
