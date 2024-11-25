package com.moonstone.moonstonemod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class ExtendEntityLiving extends TamableAnimal {
    protected ExtendEntityLiving(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
