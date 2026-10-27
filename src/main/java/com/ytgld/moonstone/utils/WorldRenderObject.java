package com.ytgld.moonstone.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;import org.joml.Vector3f;

public abstract class WorldRenderObject {

    protected int age = 0;
    protected int maxTime = 10;

    protected RandomSource source  = RandomSource.create();
    protected Vec3 position;
    protected Vec3 previousPosition;

    public Vector3f vector3f = new Vector3f(0, 0, 0);

    protected float yaw;
    protected float pitch;

    public WorldRenderObject(Vec3 position) {
        this.position = position;
        this.previousPosition = position;
    }

    public abstract void render(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState cameraRenderState,
            float partialTick
    );

    public void clientTick() {
        previousPosition = position;

        // 移动
        position = position.add(
                vector3f.x(),
                vector3f.y(),
                vector3f.z()
        );

        vector3f.mul(0.9f);
    }
}