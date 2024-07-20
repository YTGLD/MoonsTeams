package com.moonstone.moonstonemod.entity.entitys.nightmare;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import com.moonstone.moonstonemod.entity.nightmare_giant;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.behavior.warden.ForceUnmount;
import net.minecraft.world.entity.ai.behavior.warden.SetWardenLookTarget;
import net.minecraft.world.entity.ai.behavior.warden.TryToSniff;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;

import java.util.List;

public class AInightmare {
    private static final float SPEED_MULTIPLIER_WHEN_IDLING = 0.5F;
    private static final float SPEED_MULTIPLIER_WHEN_INVESTIGATING = 0.7F;
    private static final float SPEED_MULTIPLIER_WHEN_FIGHTING = 1.2F;
    private static final int MELEE_ATTACK_COOLDOWN = 18;
    private static final int DIGGING_DURATION = Mth.ceil(100.0F);
    public static final int EMERGE_DURATION = Mth.ceil(133.59999F);
    public static final int ROAR_DURATION = Mth.ceil(84.0F);
    private static final int SNIFFING_DURATION = Mth.ceil(83.2F);
    public static final int DIGGING_COOLDOWN = 1200;
    private static final int DISTURBANCE_LOCATION_EXPIRY_TIME = 100;
    private static final List<SensorType<? extends Sensor<? super nightmare_giant>>> SENSOR_TYPES = List.of(SensorType.NEAREST_PLAYERS);
    private static final List<MemoryModuleType<?>> MEMORY_TYPES = List.of(MemoryModuleType.NEAREST_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.ROAR_TARGET, MemoryModuleType.DISTURBANCE_LOCATION, MemoryModuleType.RECENT_PROJECTILE, MemoryModuleType.IS_SNIFFING, MemoryModuleType.IS_EMERGING, MemoryModuleType.ROAR_SOUND_DELAY, MemoryModuleType.DIG_COOLDOWN, MemoryModuleType.ROAR_SOUND_COOLDOWN, MemoryModuleType.SNIFF_COOLDOWN, MemoryModuleType.TOUCH_COOLDOWN, MemoryModuleType.VIBRATION_COOLDOWN, MemoryModuleType.SONIC_BOOM_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_DELAY);
    private static final BehaviorControl<nightmare_giant> DIG_COOLDOWN_SETTER = BehaviorBuilder.create((p_258953_) -> {
        return p_258953_.group(p_258953_.registered(MemoryModuleType.DIG_COOLDOWN)).apply(p_258953_, (p_258960_) -> {
            return (p_258956_, p_258957_, p_258958_) -> {
                if (p_258953_.tryGet(p_258960_).isPresent()) {
                    p_258960_.setWithExpiry(Unit.INSTANCE, 1200L);
                }

                return true;
            };
        });
    });

    public static void updateActivity(nightmare_giant p_219513_) {
        p_219513_.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.EMERGE, Activity.DIG, Activity.ROAR, Activity.FIGHT, Activity.INVESTIGATE, Activity.SNIFF, Activity.IDLE));
    }

    public static Brain<?> makeBrain(nightmare_giant p_219521_, Dynamic<?> p_219522_) {
        Brain.Provider<nightmare_giant> provider = Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
        Brain<nightmare_giant> brain = provider.makeBrain(p_219522_);
        initCoreActivity(brain);
        initEmergeActivity(brain);
        initIdleActivity(brain);
        initRoarActivity(brain);
        initFightActivity(p_219521_, brain);
        initInvestigateActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<nightmare_giant> p_219511_) {
        p_219511_.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim(0.8F), SetWardenLookTarget.create(), new LookAtTargetSink(45, 90), new MoveToTargetSink()));
    }

    private static void initEmergeActivity(Brain<nightmare_giant> p_219527_) {
        p_219527_.addActivityAndRemoveMemoryWhenStopped(Activity.EMERGE, 5, ImmutableList.of(new Emerging<>(EMERGE_DURATION)), MemoryModuleType.IS_EMERGING);
    }

    private static void initDiggingActivity(Brain<nightmare_giant> p_219532_) {
        p_219532_.addActivityWithConditions(Activity.DIG, ImmutableList.of(Pair.of(0, new ForceUnmount()), Pair.of(1, new Digging<>(DIGGING_DURATION))), ImmutableSet.of(Pair.of(MemoryModuleType.ROAR_TARGET, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.DIG_COOLDOWN, MemoryStatus.VALUE_ABSENT)));
    }

    private static void initIdleActivity(Brain<nightmare_giant> p_219537_) {
        p_219537_.addActivity(Activity.IDLE, 10, ImmutableList.of(SetRoarTarget.create(nightmare_giant::getEntityAngryAt), TryToSniff.create(), new RunOne<>(ImmutableMap.of(MemoryModuleType.IS_SNIFFING, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(RandomStroll.stroll(0.5F), 2), Pair.of(new DoNothing(30, 60), 1)))));
    }

    private static void initInvestigateActivity(Brain<nightmare_giant> p_219542_) {
        p_219542_.addActivityAndRemoveMemoryWhenStopped(Activity.INVESTIGATE, 5, ImmutableList.of(SetRoarTarget.create(nightmare_giant::getEntityAngryAt), GoToTargetLocation.create(MemoryModuleType.DISTURBANCE_LOCATION, 2, 0.7F)), MemoryModuleType.DISTURBANCE_LOCATION);
    }

    private static void initSniffingActivity(Brain<nightmare_giant> p_219544_) {
        p_219544_.addActivityAndRemoveMemoryWhenStopped(Activity.SNIFF, 5, ImmutableList.of(SetRoarTarget.create(nightmare_giant::getEntityAngryAt), new Sniffing<>(SNIFFING_DURATION)), MemoryModuleType.IS_SNIFFING);
    }

    private static void initRoarActivity(Brain<nightmare_giant> p_219546_) {
        p_219546_.addActivityAndRemoveMemoryWhenStopped(Activity.ROAR, 10, ImmutableList.of(new Roar()), MemoryModuleType.ROAR_TARGET);
    }

    private static void initFightActivity(nightmare_giant p_219518_, Brain<nightmare_giant> p_219519_) {
        p_219519_.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.of(new SonicBoom()), MemoryModuleType.ATTACK_TARGET);
    }

    private static boolean isTarget(nightmare_giant p_219515_, LivingEntity p_219516_) {
        return p_219515_.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).filter((p_219509_) -> {
            return p_219509_ != p_219516_;
        }).isPresent();
    }

    private static void onTargetInvalid(nightmare_giant p_219529_, LivingEntity p_219530_) {
        if (!p_219529_.canTargetEntity(p_219530_)) {
            p_219529_.clearAnger(p_219530_);
        }

        setDigCooldown(p_219529_);
    }

    public static void setDigCooldown(LivingEntity p_219506_) {
        if (p_219506_.getBrain().hasMemoryValue(MemoryModuleType.DIG_COOLDOWN)) {
            p_219506_.getBrain().setMemoryWithExpiry(MemoryModuleType.DIG_COOLDOWN, Unit.INSTANCE, 1200L);
        }

    }

    public static void setDisturbanceLocation(nightmare_giant p_219524_, BlockPos p_219525_) {
        if (p_219524_.level().getWorldBorder().isWithinBounds(p_219525_) && !p_219524_.getEntityAngryAt().isPresent()) {
            setDigCooldown(p_219524_);
            p_219524_.getBrain().setMemoryWithExpiry(MemoryModuleType.SNIFF_COOLDOWN, Unit.INSTANCE, 100L);
            p_219524_.getBrain().setMemoryWithExpiry(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(p_219525_), 100L);
            p_219524_.getBrain().setMemoryWithExpiry(MemoryModuleType.DISTURBANCE_LOCATION, p_219525_, 100L);
            p_219524_.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        }
    }
}