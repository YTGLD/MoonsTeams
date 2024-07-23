package com.moonstone.moonstonemod.entity.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;
import com.moonstone.moonstonemod.entity.other.cell_giant;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;

import java.util.List;

public class AIgiant {

    public static final int EMERGE_DURATION = Mth.ceil(133.59999F);
    public static final int ROAR_DURATION = Mth.ceil(84.0F);
    private static final List<SensorType<? extends Sensor<? super cell_giant>>> SENSOR_TYPES = List.of(SensorType.NEAREST_PLAYERS);
    private static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(MemoryModuleType.NEAREST_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.ROAR_TARGET, MemoryModuleType.DISTURBANCE_LOCATION, MemoryModuleType.RECENT_PROJECTILE, MemoryModuleType.IS_SNIFFING, MemoryModuleType.IS_EMERGING, MemoryModuleType.ROAR_SOUND_DELAY, MemoryModuleType.DIG_COOLDOWN, MemoryModuleType.ROAR_SOUND_COOLDOWN, MemoryModuleType.SNIFF_COOLDOWN, MemoryModuleType.TOUCH_COOLDOWN, MemoryModuleType.VIBRATION_COOLDOWN, MemoryModuleType.SONIC_BOOM_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_DELAY);
    private static final BehaviorControl<cell_giant> DIG_COOLDOWN_SETTER = BehaviorBuilder.create((p_258953_) -> {
        return p_258953_.group(p_258953_.registered(MemoryModuleType.DIG_COOLDOWN)).apply(p_258953_, (p_258960_) -> {
            return (p_258956_, p_258957_, p_258958_) -> {
                if (p_258953_.tryGet(p_258960_).isPresent()) {
                    p_258960_.setWithExpiry(Unit.INSTANCE, 1200L);
                }

                return true;
            };
        });
    });

    public static Brain<?> makeBrain(cell_giant p_219521_, Dynamic<?> p_219522_) {
        Brain.Provider<cell_giant> provider = Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
        Brain<cell_giant> brain = provider.makeBrain(p_219522_);
        initEmergeActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }
    private static void initEmergeActivity(Brain<cell_giant> p_219527_) {
        p_219527_.addActivityAndRemoveMemoryWhenStopped(Activity.EMERGE, 5, ImmutableList.of(new Emerging<>(EMERGE_DURATION)), MemoryModuleType.IS_EMERGING);
    }

}
