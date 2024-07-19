package com.moonstone.moonstonemod.client.entitys.nightmare;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class Emerging<E extends com.moonstone.moonstonemod.entity.nightmare_giant> extends Behavior<E> {
    public Emerging(int p_217547_) {
        super(ImmutableMap.of(MemoryModuleType.IS_EMERGING, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), p_217547_);
    }

    protected boolean canStillUse(ServerLevel p_217553_, E p_217554_, long p_217555_) {
        return true;
    }

    protected void start(ServerLevel p_217561_, E p_217562_, long p_217563_) {
        p_217562_.setPose(Pose.EMERGING);
        p_217562_.playSound(SoundEvents.WARDEN_EMERGE, 5.0F, 1.0F);
    }

    protected void stop(ServerLevel p_217569_, E p_217570_, long p_217571_) {
        if (p_217570_.hasPose(Pose.EMERGING)) {
            p_217570_.setPose(Pose.STANDING);
        }

    }
}
