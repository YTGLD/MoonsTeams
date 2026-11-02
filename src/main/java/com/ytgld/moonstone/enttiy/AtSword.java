package com.ytgld.moonstone.enttiy;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.effect.Effects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AtSword extends Arrow {

    public AtSword(EntityType<? extends AtSword> entityType, Level level) {
        super(entityType, level);

        this.setSoundEvent(SoundEvents.BONE_BLOCK_BREAK);
        this.setXRot(Mth.nextInt(RandomSource.create(),0,360));
    }

    private final List<Vec3> trailPositions = new ArrayList<>();




    @Override
    protected void onHitEntity(EntityHitResult result) {
    }
    private LivingEntity target;

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public LivingEntity getTarget() {
        return target;
    }
    private boolean can = true;
    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (can) {

            float is = 1.8f;
            Vec3 position = this.position();
            List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
            for (LivingEntity es : ess) {
                if (this.getOwner() != null) {
                    if (this.getOwner() instanceof Player player) {
                        if (!es.is(player)) {
                            es.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, 2));
                            es.setInvulnerableTime(0);
                            es.damageCooldownTime = 0;
                            es.hurt(es.damageSources().mobProjectile(this, player), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) / 10 + 10));
                            can = false;
                        }
                    }
                }
            }
            BlockState blockstate = this.level().getBlockState(new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ()));
            this.level().levelEvent(2001, new BlockPos((int) this.getX(), (int) (this.getY() + 1), (int) this.getZ()), Block.getId(blockstate));
            can = false;
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (can) {
            float is = 1.8f;
            Vec3 position = this.position();
            List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
            for (LivingEntity es : ess) {
                if (this.getOwner() != null) {
                    if (this.getOwner() instanceof Player player) {
                        if (!es.is(player)) {
                            es.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, 2));
                            es.setInvulnerableTime(0);
                            es.damageCooldownTime = 0;
                            es.hurt(es.damageSources().mobProjectile(this, player), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) / 10 + 10));
                            can = false;
                        }
                    }
                }
            }
            BlockState blockstate = this.level().getBlockState(new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ()));
            this.level().levelEvent(2001, new BlockPos((int) this.getX(), (int) (this.getY() + 1), (int) this.getZ()), Block.getId(blockstate));
            can = false;
        }
    }
    @Override
    public void playerTouch(Player entity) {

    }
    public void tearDown() {
        Level var2 = this.level();
        if (var2 instanceof ServerLevel serverLevel) {
            AABB box = this.getBoundingBox();
            Vec3 center = box.getCenter();
            double xSpread = box.getXsize() * 0.3;
            double ySpread = box.getYsize() * 0.3;
            double zSpread = box.getZsize() * 0.3;
            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK_CRUMBLE,
                            Blocks.OBSIDIAN.defaultBlockState()), center.x, center.y, center.z,
                    50, xSpread, ySpread, zSpread, 0.0F);
            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK_CRUMBLE,
                            Blocks.CRYING_OBSIDIAN.defaultBlockState()), center.x, center.y, center.z,
                    25, xSpread, ySpread, zSpread, 0.0F);
        }

        this.remove(RemovalReason.DISCARDED);
    }
    private int timeCan = 0;
    @Override
    public void tick() {
        super.tick();

        if (!can) {
            timeCan++;
        }
        if (timeCan > 50) {
            tearDown();
        }

        if (!this.isNoGravity()) {

            this.setDeltaMovement(new Vec3(0,-100,0));
            trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

            if (trailPositions.size() > 50) {
                trailPositions.remove(0);
            }
            if (tickCount >= 150) {
                this.discard();
            }
            float is = 5;
            BlockState blockstate = this.level().getBlockState(new BlockPos(this.getBlockX(),this.getBlockY()-1,this.getBlockZ()));
            double d0 = this.getX() + (double)Mth.randomBetween(RandomSource.create(), -0.7F, 0.7F);
            double d1 = this.getY();
            double d2 = this.getZ() + (double)Mth.randomBetween(RandomSource.create(), -0.7F, 0.7F);
            this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK,blockstate ), d0, d1, d2, 0.0D, 0.0D, 0.0D);

        }else {
            if (tickCount >= 400) {
                this.discard();
            }
            if (target == null || !target.isAlive()) {
                findNewTarget();
            }
            if (target != null) {
                if (tickCount % 2 == 0) {
                    AtSword atSword = new AtSword(EntityTs.at_sword_entity.get(), this.level());
                    atSword.setPos(target.position().add(Mth.nextFloat(RandomSource.create(), -3, 3), Mth.nextFloat(RandomSource.create(), 6, 10), Mth.nextFloat(RandomSource.create(), -2.9f, 2.9f)));
                    if (this.getOwner()!=null) {
                        atSword.setOwner(this.getOwner());
                    }
                    target.level().addFreshEntity(atSword);
                }
            }
        }

    }
    private void findNewTarget() {
        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            Identifier name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (this.getOwner() != null) {
                if (!(entity instanceof OwnableEntity tamableAnimal
                        && tamableAnimal.getOwner() != null
                        && tamableAnimal.getOwner().equals(this.getOwner()))) {
                    if (!name.getNamespace().equals(Moonstone.MODID) && !(entity.is(this.getOwner()))) {
                        double distance = this.distanceToSqr(entity);
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestEntity = entity;
                        }
                    }
                }
            }
        }

        this.target = closestEntity;
    }
}
