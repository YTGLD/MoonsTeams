package com.ytgld.moonstone.mixin.cilent.glow;

import com.ytgld.moonstone.render.outline.ISubmitNodeCollection;
import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.feature.phase.FeatureRenderPhase;
import net.minecraft.client.renderer.feature.phase.SimpleFeatureRenderPhase;
import net.minecraft.client.renderer.feature.phase.TranslucentFeatureRenderPhase;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
@Mixin(SubmitNodeCollection.class)
public abstract class SubmitNodeCollectionMixin
        implements ISubmitNodeCollection {

    @Unique
    private final SimpleFeatureRenderPhase chest_item$reactorGlow =
            new SimpleFeatureRenderPhase();

    @Mutable
    @Final
    @Shadow
    private List<FeatureRenderPhase<?>> allPhases;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void chest_item$addReactorGlow(
            boolean useImprovedTransparency,
            TranslucentFeatureRenderPhase seeThrough,
            CallbackInfo ci) {

        List<FeatureRenderPhase<?>> phases =
                new ArrayList<>(this.allPhases);

        phases.add(this.chest_item$reactorGlow);

        this.allPhases = List.copyOf(phases);
    }

    @Override
    public SimpleFeatureRenderPhase chest26_3$reactorGlow() {
        return this.chest_item$reactorGlow;
    }
}