package com.ytgld.moonstone.render.outline;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;

public interface ISubmitReactorGlow {
    void chest_item$submitReactorGlow(
            PoseStack poseStack,
            RenderType renderType,
            SubmitNodeCollector.CustomGeometryRenderer renderer
    );
}