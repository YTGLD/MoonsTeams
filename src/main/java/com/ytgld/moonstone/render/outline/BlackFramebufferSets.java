package com.ytgld.moonstone.render.outline;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.ResourceHandle;
    import com.ytgld.moonstone.Moonstone;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

public class BlackFramebufferSets implements PostChain.TargetBundle  {

    public  ResourceHandle<RenderTarget> entityOutlineFramebuffer;



    public  ResourceHandle<RenderTarget> mainFramebuffer = ResourceHandle.invalid();
        public static final Identifier MAIN =Identifier.fromNamespaceAndPath(Moonstone.MODID,"main");
        public static final Identifier ENTITY_OUTLINE = Identifier.fromNamespaceAndPath(Moonstone.MODID,"black");
        public static final Identifier WARPED = Identifier.fromNamespaceAndPath(Moonstone.MODID,"warped");
    @Override
    public ResourceHandle<RenderTarget> getOrThrow(Identifier id) {
        if (id .equals(ENTITY_OUTLINE) ) {
            return entityOutlineFramebuffer;
        }else if (id.equals(MAIN)){
            return mainFramebuffer;
        }
        return null;
    }


    @Override
    public void replace(Identifier id, ResourceHandle<RenderTarget> framebuffer) {
       if (id.equals(ENTITY_OUTLINE) ) {
            entityOutlineFramebuffer = framebuffer;
        }else if (id.equals(MAIN)){
            mainFramebuffer = framebuffer;
        }else {
            System.out.println(id);
        }
    }

    @Nullable
    @Override
    public ResourceHandle<RenderTarget> get(Identifier id) {
         if (id .equals(ENTITY_OUTLINE) ) {
            return entityOutlineFramebuffer;
        }else if (id.equals(MAIN)){
            return mainFramebuffer;
        }
        return null;
    }

}
