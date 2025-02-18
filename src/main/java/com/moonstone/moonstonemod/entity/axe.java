package com.moonstone.moonstonemod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class axe extends Arrow {
    public axe(EntityType<? extends axe> entityType, Level level) {
        super(entityType, level);
    }
    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (this.getOwner()!=null && this.getOwner() instanceof ServerPlayer player) {
            BlockPos blockpos = new BlockPos(this.blockPosition().getX(),this.blockPosition().getY()-2,this.blockPosition().getX());
            BlockState blockstate = this.level().getBlockState(blockpos);

            int i = 150;
            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), (int) this.position().x, (int) this.position().y, (int) this.position().z, i, 1.25, 1.25, 1.25, 0.15000000596046448);
            }
            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, player.getSoundSource(), 1.0F, 1.0F
            );}
        Vec3 position = this.position();
        float is = 5;
        List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
        for (LivingEntity es : ess){
            if (!es.is(this.getOwner())&&this.getOwner() instanceof Player player){
                es.hurt(player.damageSources().dryOut(), (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE)*3);
            }
        }

    }

    @Override
    public void playerTouch(Player entity) {

    }

    @Override
    public void tick() {
        super.tick();
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 50) {
            trailPositions.remove(0);
        }
        if (tickCount>=300){
            if (this.getOwner()!=null) {
                this.discard();
            }
        }
    }
}
