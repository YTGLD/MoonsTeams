package com.moonstone.moonstonemod.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class red extends TextureSheetParticle {
    public red(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
        super(level, x, y, z, movementX, movementY, movementZ);
        this.lifetime = 450;
        this.quadSize *= 0.8f;
        this.roll += Mth.nextFloat(RandomSource.create(), 1, 5);
        this.oRoll += Mth.nextFloat(RandomSource.create(), 1, 5);;
        this.setParticleSpeed(0,0,0);
    }
    @Override
    protected int getLightColor(float p_107249_) {
        return 240;
    }

    @Override
    public void render(VertexConsumer p_107678_, Camera p_107679_, float p_107680_) {

        super.render(p_107678_, p_107679_, p_107680_);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public void tick() {
        this.alpha -= 0.03f;
        this.roll += 0.1f;
        this.oRoll += 0.1f;
        if (alpha<=0){
            this.remove();
        }
        super.tick();

    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            red particle = new red(level, x, y, z, 0, 0, 0);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }

    public void fill(VertexConsumer vertexconsumer, float p_286234_, float p_286444_, float p_286244_, float p_286411_, float p_286671_, float p_286599_) {
        Matrix4f matrix4f =  new PoseStack().last().pose();
        if (p_286234_ < p_286244_) {
            float i = p_286234_;
            p_286234_ = p_286244_;
            p_286244_ = i;
        }

        if (p_286444_ < p_286411_) {
            float j = p_286444_;
            p_286444_ = p_286411_;
            p_286411_ = j;
        }

        float f3 = (float) FastColor.ARGB32.alpha((int) p_286599_) / 255.0F;
        float f = (float)FastColor.ARGB32.red((int) p_286599_) / 255.0F;
        float f1 = (float)FastColor.ARGB32.green((int) p_286599_) / 255.0F;
        float f2 = (float)FastColor.ARGB32.blue((int) p_286599_) / 255.0F;
        vertexconsumer.vertex(matrix4f, (float)p_286234_, (float)p_286444_, (float)p_286671_).color(f, f1, f2, f3).endVertex();
        vertexconsumer.vertex(matrix4f, (float)p_286234_, (float)p_286411_, (float)p_286671_).color(f, f1, f2, f3).endVertex();
        vertexconsumer.vertex(matrix4f, (float)p_286244_, (float)p_286411_, (float)p_286671_).color(f, f1, f2, f3).endVertex();
        vertexconsumer.vertex(matrix4f, (float)p_286244_, (float)p_286444_, (float)p_286671_).color(f, f1, f2, f3).endVertex();
        new PoseStack().pushPose();
    }
}
