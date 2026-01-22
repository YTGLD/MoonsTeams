package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.EntityTs;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AtSword extends SwordOfTwelve{

    public AtSword(EntityType<? extends AtSword> entityType, Level level) {
        super(entityType, level);

        this.setSoundEvent(SoundEvents.BONE_BLOCK_BREAK);
        this.setXRot(Mth.nextInt(RandomSource.create(),0,360));
    }

    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

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

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState blockstate = this.level().getBlockState(new BlockPos(this.getBlockX(),this.getBlockY()-1,this.getBlockZ()));
        this.level().levelEvent(2001, new BlockPos((int) this.getX(), (int) (this.getY() + 1), (int) this.getZ()), Block.getId(blockstate));
    }

    @Override
    protected void onHit(HitResult result) {
        super.  onHit(result);
        float is = 1.8f;
        Vec3 position = this.position();
        List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
        for (LivingEntity es : ess) {
            if (this.getOwner()!=null) {
                if (this.getOwner() instanceof Player player) {
                    if (!es.is(player)) {
                        es.addEffect(new MobEffectInstance(Effects.dead.get(), 200, 2));
                        es.invulnerableTime = 0;

                        es.hurt(es.damageSources().mobProjectile(this, player), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) /10+ 1));
                        this.discard();
                    }
                }
            }
        }
    }

    public void addParticles() {
        Vec3 vec3 = this.position();
        int i = this.random.nextIntBetweenInclusive(1, 3);

        for(int j = 0; j < i; ++j) {
            double d0 = 0.4;
            Vec3 vec31 = new Vec3(this.getX() + 0.4 * (this.random.nextGaussian() - this.random.nextGaussian()), this.getY() + 0.4 * (this.random.nextGaussian() - this.random.nextGaussian()), this.getZ() + 0.4 * (this.random.nextGaussian() - this.random.nextGaussian()));
            Vec3 vec32 = vec3.vectorTo(vec31);
            this.level().addParticle(ParticleTypes.BUBBLE_POP, vec3.x(), vec3.y(), vec3.z(), vec32.x(), vec32.y(), vec32.z());
        }

    }
    @Override
    public void playerTouch(Player entity) {

    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isNoGravity()) {
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

            addParticles();
            if (tickCount >= 600) {
                this.discard();
            }
            if (target == null || !target.isAlive()) {
                findNewTarget();
            }
            if (target != null) {
                if (tickCount%5==0) {
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
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            Set<ResourceLocation> blacklist = new HashSet<>();
            for (String aaa : Config.SERVER.attackTarget.get()) {
                String[] parts = aaa.split(":");
                if (parts.length > 0) {
                    blacklist.add( new ResourceLocation(parts[0],parts[1]));
                }
            }
            if (blacklist.contains(name)){
                return;
            }
            if (this.getOwner() != null) {
                if (!(entity instanceof OwnableEntity tamableAnimal
                        && tamableAnimal.getOwner() != null
                        && tamableAnimal.getOwner().equals(this.getOwner()))) {
                    if (!name.getNamespace().equals(MoonStoneMod.MODID) && !(entity.is(this.getOwner()))) {
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
