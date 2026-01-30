package com.moonstone.moonstonemod.client.particle;

import com.all.MSParticleRenderType;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class RedOrbPart   extends TextureSheetParticle {
    public RedOrbPart(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
        super(level, x, y, z, movementX, movementY, movementZ);
        this.lifetime = 200;
        this.quadSize *= 3.2f;
        this.setParticleSpeed(movementX, movementY, movementZ);
        setColor(1,0.15f,0.1f);
    }
    public void tick() {
        alpha -= 0.0175f;
        quadSize = quadSize * 0.98f;
        if (quadSize <= 0.1f) {
            alpha -= 0.05f;
        }
        if (alpha<=0){
            this.remove();
        }
        super.tick();

    }

    @Override
    protected int getLightColor(float p_107249_) {
        return 255;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return MSParticleRenderType.MSParticleRenderType;
    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            RedOrbPart particle = new RedOrbPart(level, x, y, z, (float) xSpeed, (float) ySpeed, (float) zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}


