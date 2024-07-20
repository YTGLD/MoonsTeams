package com.moonstone.moonstonemod.entity.entitys.zomb;

import com.moonstone.moonstonemod.entity.cell_zombie;
import net.minecraft.client.model.geom.ModelPart;

public class ZombieModel <T extends cell_zombie> extends AbstractZombieModel<T> {
    public ZombieModel(ModelPart p_171090_) {
        super(p_171090_);
    }

    public boolean isAggressive(T p_104155_) {
        return p_104155_.isAggressive();
    }
}
