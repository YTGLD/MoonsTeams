package com.moonstone.moonstonemod.entity.c.red;

import net.minecraft.client.model.geom.ModelPart;

public class ZombieModel <T extends com.moonstone.moonstonemod.entity.cell_zombie> extends AbstractZombieModel<T> {
    public ZombieModel(ModelPart p_171090_) {
        super(p_171090_);
    }

    public boolean isAggressive(T p_104155_) {
        return p_104155_.isAggressive();
    }
}
