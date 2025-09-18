package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.renderer.MRender;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class tricky_puppets extends nightmare implements SuperNightmare {
    public static void blockLight(@NotNull PoseStack matrices,
                            @NotNull MultiBufferSource vertexConsumers,
                            @NotNull BlockEntity ownerBlood) {

        BlockPos playerPos = ownerBlood.getBlockPos();
        Vec3 playerVec = new Vec3(playerPos.getX(), playerPos.getY(), playerPos.getZ());

        if (ownerBlood.getLevel()!=null) {
            float range = 20;
            List<LivingEntity> entities =
                    ownerBlood.getLevel().getEntitiesOfClass(LivingEntity.class,
                            new AABB(playerPos.getX() - range,
                                    playerPos.getY() - range,
                                    playerPos.getZ() - range,
                                    playerPos.getX() + range,
                                    playerPos.getY() + range,
                                    playerPos.getZ() + range));

            BlockState blockState = ownerBlood.getLevel().getBlockState(playerPos);

            if (!blockState.is(Blocks.AIR)) {
                if (blockState.getBlock() instanceof AbstractChestBlock<?>) {
                    for (LivingEntity entity : entities){
                        if (entity instanceof Player player) {

                            if (Handler.hascurio(player, Items.tricky_puppets.get())) {
                                double distance = playerVec.distanceTo(Vec3.atLowerCornerOf(player.blockPosition()));
                                float alp = Math.max(0, 1 - (float) distance / range);

                                matrices.pushPose();
                                matrices.translate(0.5f,0.5f,0.5f);
                                renderSphere1(matrices, vertexConsumers, 240, alp, alp);
                                matrices.popPose();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s,float alp) {

        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.Bluer);
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.vertex(matrices.last().pose(), x0, y0, z0).color(186/255f, 85/255f, 211/255f, alp).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x1, y1, z1).color(186/255f, 85/255f, 211/255f, alp).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x2, y2, z2).color(186/255f, 85/255f, 211/255f, alp).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
                vertexConsumer.vertex(matrices.last().pose(), x3, y3, z3).color(186/255f, 85/255f, 211/255f, alp).overlayCoords(OverlayTexture.NO_OVERLAY).uv(0, 0).uv2(light, light).normal(1, 0, 0).endVertex();
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.tricky_puppets.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.tricky_puppets.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.tricky_puppets.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }


}
