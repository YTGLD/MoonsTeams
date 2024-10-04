package com.moonstone.moonstonemod.entity.necora;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.entitys.nightmare.SonicBoom;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.UUID;

public class small_zombie extends TamableAnimal {
    public AnimationState emergeAnimationState = new AnimationState();
    public AnimationState attackAnimationState = new AnimationState();

    public small_zombie(EntityType<? extends small_zombie> c  , Level p_34272_) {
        super(c, p_34272_);

        CuriosApi.getCuriosInventory(this).ifPresent(handler -> handler.getStacksHandler("zombie").ifPresent(stacks -> {
            stacks.addPermanentModifier(new AttributeModifier(uuid, "small_zombie", 3, AttributeModifier.Operation.ADDITION));
        }));
    }

    public void setAttackTarget(LivingEntity p_219460_) {
        ResourceLocation name = ForgeRegistries.ENTITY_TYPES.getKey(p_219460_.getType());
        if (name != null) {
            if (!name.getNamespace().contains(MoonStoneMod.MODID)) {
                this.setTarget(p_219460_);
            }
        }
    }
    @Override
    public void tick() {
        super.tick();
        this.getAttributes().addTransientAttributeModifiers(modifierMultimap(this));
        if (this.getOwner()!= null) {
            if (this.getOwner().getLastHurtByMob()!= null) {
                if (!this.getOwner().getLastHurtByMob().is(this)) {
                    this.setAttackTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker()!= null) {
                if (!this.getOwner().getLastAttacker().is(this)) {
                    this.setAttackTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob()!= null) {
                if (!this.getOwner().getLastHurtMob().is(this)) {
                    this.setAttackTarget(this.getOwner().getLastHurtMob());
                }

            }
        }
        if (Handler.hascurio(this,Items.atrophy.get())) {
            Vec3 playerPos = this.position().add(0, 0.75, 0);
            int range = 10;
            List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Mob mob : entities) {
                if (this.getTarget() == null) {
                    @Nullable ResourceLocation entity = ForgeRegistries.ENTITY_TYPES.getKey(mob.getType());
                    if (entity != null && !entity.getNamespace().equals(MoonStoneMod.MODID)) {
                        this.setTarget(mob);
                        break;
                    }
                }
            }
            if (this.getTarget() != null) {
                if (!this.getTarget().isAlive()) {
                    this.setTarget(null);
                }
            }
        }
    }

    public boolean doHurtTarget(Entity p_219472_) {
        this.level().broadcastEntityEvent(this, (byte)4);
        return super.doHurtTarget(p_219472_);
    }

    public void handleEntityEvent(byte p_219360_) {
        if (p_219360_ == 4) {
            this.attackAnimationState.start(this.tickCount);
        }
    }
    private Multimap<Attribute, AttributeModifier> modifierMultimap(LivingEntity livingEntity){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (Handler.hascurio(livingEntity, Items.acid.get())) {
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier((this.uuid), MoonStoneMod.MODID + "DamageCell", -0.95f, AttributeModifier.Operation.ADDITION));
        }

        return modifierMultimap;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        small_zombie wolf = EntityTs.small_zombie.get().create(p_146743_);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true);
            }
        }
        return wolf;
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) {
        if (DATA_POSE.equals(p_219422_)) {
            if (this.getPose() == Pose.EMERGING) {
                this.emergeAnimationState.start(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(p_219422_);
    }
    @Override
    protected void dropFromLootTable(DamageSource p_21335_, boolean p_21336_) {
        super.dropFromLootTable(p_21335_, p_21336_);
        this.spawnAtLocation(new ItemStack(Items.zombie_box_nobo.get(), 1));

        if (Handler.hascurio(this,Items.acid.get())){
            this.spawnAtLocation(new ItemStack(Items.acid.get(), 1));
        }
        if (Handler.hascurio(this,Items.compression.get())){
            this.spawnAtLocation(new ItemStack(Items.compression.get(), 1));
        }
        if (Handler.hascurio(this,Items.atrophy.get())){
            this.spawnAtLocation(new ItemStack(Items.atrophy.get(), 1));
        }
        if (Handler.hascurio(this,Items.enhancemen.get())){
            this.spawnAtLocation(new ItemStack(Items.enhancemen.get(), 1));
        }


    }
    @Override
    public void die(@NotNull DamageSource p_21809_) {
        super.die(p_21809_);
        if (Handler.hascurio(this,Items.acid.get())){
            if (this.getOwner()!=null&&this.getOwner() instanceof Player player){
                player.heal(20);
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,  1200, 2));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,  1200, 2));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,  1200, 2));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,  1200, 2));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,  1200, 2));
            }
        }
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 1000.0).add(Attributes.MOVEMENT_SPEED, 0.375f).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_KNOCKBACK, 1.5D).add(Attributes.ATTACK_DAMAGE, 6.0);
    }
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));
    }

}

