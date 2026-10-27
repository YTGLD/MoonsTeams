package com.ytgld.moonstone.enttiy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SwordOfTwelve extends TamableAnimal {

    public SwordOfTwelve(EntityType<? extends SwordOfTwelve> entityType, Level level) {
        super(entityType, level);
        this.setXRot(Mth.nextInt(RandomSource.create(),0,360));
    }

    private LivingEntity target;

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public LivingEntity getTarget() {
        return target;
    }

    @Override
    public void playerTouch(Player entity) {

    }
    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        tearDown();
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
    }
    @Override
    public void tick() {
        super.tick();
        if (tickCount>=400) {
            this.discard();
        }

        this.setDeltaMovement(new Vec3(0,-100,0));
    }

    @Override
    public boolean isInvulnerableTo(ServerLevel level, DamageSource source) {
        if (source.is(DamageTypes.FALL)) {
            Vec3 thisPos = this.position();
            int range = 8;
            List<LivingEntity> livingEntities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(thisPos.x - range, thisPos.y - range, thisPos.z - range, thisPos.x + range, thisPos.y + range, thisPos.z + range));
            for (LivingEntity living : livingEntities){
                if (living.is(this)) {
                    continue;
                }
                if (!(this.getOwner() instanceof Player player)) {
                    continue;
                }
                if (!living.is(player)) {
                    living.hurt(living.damageSources().playerAttack(player),
                            (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)
                                    + (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE)
                                    + (float) player.getAttributeValue(Attributes.MAX_HEALTH));
                    living.setInvulnerableTime(0);
                    living.damageCooldownTime = 0;
                }
            }
            this.level().playSound(null,blockPosition(),SoundEvents.MACE_SMASH_GROUND, SoundSource.MASTER,1,1);
            return true;
        }
        return true;
    }
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}

