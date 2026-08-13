package com.ytgld.moonstone.enttiy.render.zombie;

import com.ytgld.moonstone.enttiy.CellZombie;
import net.minecraft.client.model.geom.ModelPart;

public class ZombieModel <T extends CellZombie> extends AbstractZombieModel<T> {
    public ZombieModel(ModelPart p_171090_) {
        super(p_171090_);
    }

    public boolean isAggressive(T p_104155_) {
        return p_104155_.isAggressive();
    }
}
