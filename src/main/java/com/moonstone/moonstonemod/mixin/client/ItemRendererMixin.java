package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow public abstract void renderModelLists(BakedModel p_115190_, ItemStack p_115191_, int p_115192_, int p_115193_, PoseStack p_115194_, VertexConsumer p_115195_);

    @Inject(at = @At("RETURN"), method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V")
    public void init(ItemStack p_115144_, ItemDisplayContext p_270188_, boolean p_115146_, PoseStack p_115147_, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_, CallbackInfo ci) {


        if (p_115144_.is(Items.gorillacake.get())) {
            boolean flag1;
            if (p_270188_ != ItemDisplayContext.GUI && !p_270188_.firstPerson() && p_115144_.getItem() instanceof BlockItem) {
                Block block = ((BlockItem)p_115144_.getItem()).getBlock();
                flag1 = !(block instanceof HalfTransparentBlock) && !(block instanceof StainedGlassPaneBlock);
            } else {
                flag1 = true;
            }
            for (var model : p_115151_.getRenderPasses(p_115144_, flag1)) {
                for (var rendertype : model.getRenderTypes(p_115144_, flag1)) {
                    this.renderModelLists(model, p_115144_, p_115149_, p_115150_, p_115147_, p_115148_.getBuffer(MRender.ging()));

                }
            }
        }
    }
}
