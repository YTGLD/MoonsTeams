package com.moonstone.moonstonemod.client.entitys.nightmare;

import com.google.common.collect.ImmutableMap;
import com.moonstone.moonstonemod.entity.nightmare_giant;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;


public class Roar extends Behavior<nightmare_giant> {
    private static final int TICKS_BEFORE_PLAYING_ROAR_SOUND = 25;
    private static final int ROAR_ANGER_INCREASE = 20;

    public Roar() {
        super(ImmutableMap.of(MemoryModuleType.ROAR_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.ROAR_SOUND_COOLDOWN, MemoryStatus.REGISTERED, MemoryModuleType.ROAR_SOUND_DELAY, MemoryStatus.REGISTERED), AInightmare.ROAR_DURATION);
    }

    protected void start(ServerLevel p_217580_, nightmare_giant p_217581_, long p_217582_) {
        Brain<nightmare_giant> brain = p_217581_.getBrain();
        brain.setMemoryWithExpiry(MemoryModuleType.ROAR_SOUND_DELAY, Unit.INSTANCE, 25L);
        brain.eraseMemory(MemoryModuleType.WALK_TARGET);
        LivingEntity livingentity = p_217581_.getBrain().getMemory(MemoryModuleType.ROAR_TARGET).get();
        BehaviorUtils.lookAtEntity(p_217581_, livingentity);
        p_217581_.setPose(Pose.ROARING);
        p_217581_.increaseAngerAt(livingentity, 20, false);
    }

    protected boolean canStillUse(ServerLevel p_217588_, nightmare_giant p_217589_, long p_217590_) {
        return true;
    }

    protected void tick(ServerLevel p_217596_, nightmare_giant p_217597_, long p_217598_) {
        if (!p_217597_.getBrain().hasMemoryValue(MemoryModuleType.ROAR_SOUND_DELAY) && !p_217597_.getBrain().hasMemoryValue(MemoryModuleType.ROAR_SOUND_COOLDOWN)) {
            p_217597_.getBrain().setMemoryWithExpiry(MemoryModuleType.ROAR_SOUND_COOLDOWN, Unit.INSTANCE, (long)(AInightmare.ROAR_DURATION - 25));
        }
    }

    protected void stop(ServerLevel p_217604_, nightmare_giant p_217605_, long p_217606_) {
        if (p_217605_.hasPose(Pose.ROARING)) {
            p_217605_.setPose(Pose.STANDING);
        }

        p_217605_.getBrain().getMemory(MemoryModuleType.ROAR_TARGET).ifPresent(p_217605_::setAttackTarget);
        p_217605_.getBrain().eraseMemory(MemoryModuleType.ROAR_TARGET);
    }
}
