package com.ytgld.moonstone.mixin;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.InitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin {
    @Shadow
    @Final
    public FlowingFluid fluid;

    @Inject(at = @At("RETURN"), method = "getCollisionShape", cancellable = true)
    public void getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (context instanceof EntityCollisionContext entitycollisioncontext) {
            if (entitycollisioncontext.getEntity() instanceof Player player) {
                if (Handler.hascurio(player, InitItems.evilcandle.asItem())) {
                    if (fluid == Fluids.LAVA) {
                        cir.setReturnValue(Shapes.block());
                    }
                }
                if (Handler.hascurio(player, InitItems.GodAmbush.asItem()) || Handler.hascurio(player, InitItems.ambush.asItem())) {
                    cir.setReturnValue(Shapes.block());
                }
            }
        }
    }
}
