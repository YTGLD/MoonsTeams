package com.moonstone.moonstonemod.client.entitys.nightmare;

import com.google.common.collect.ImmutableMap;
import com.moonstone.moonstonemod.entity.necora.nightmare_giant;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class Digging <E extends nightmare_giant> extends Behavior<E> {
    public Digging(int p_217515_) {
        super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), p_217515_);
    }

    protected boolean canStillUse(ServerLevel p_217527_, E p_217528_, long p_217529_) {
        return p_217528_.getRemovalReason() == null;
    }

    protected boolean checkExtraStartConditions(ServerLevel p_217524_, E p_217525_) {
        return p_217525_.onGround() || p_217525_.isInWater() || p_217525_.isInLava();
    }

    protected void start(ServerLevel p_217535_, E p_217536_, long p_217537_) {
        if (p_217536_.onGround()) {
            p_217536_.setPose(Pose.DIGGING);
            p_217536_.playSound(SoundEvents.WARDEN_DIG, 5.0F, 1.0F);
        } else {
            p_217536_.playSound(SoundEvents.WARDEN_AGITATED, 5.0F, 1.0F);
            this.stop(p_217535_, p_217536_, p_217537_);
        }

    }

    protected void stop(ServerLevel p_217543_, E p_217544_, long p_217545_) {
        if (p_217544_.getRemovalReason() == null) {
            p_217544_.remove(Entity.RemovalReason.DISCARDED);
        }

    }
}
