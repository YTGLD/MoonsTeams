package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static com.moonstone.moonstonemod.event.AllEvent.*;

public class Handler {

    public static <T extends TamableAnimal> void trySpawnMob(LivingEntity player, EntityType<T> p_216404_, MobSpawnType p_216405_, ServerLevel p_216406_, BlockPos p_216407_, int p_216408_, int p_216409_, int p_216410_, SpawnUtil.Strategy p_216411_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = p_216407_.mutable();

        for (int i = 0; i < p_216408_; ++i) {
            int j = Mth.randomBetweenInclusive(p_216406_.random, -p_216409_, p_216409_);
            int k = Mth.randomBetweenInclusive(p_216406_.random, -p_216409_, p_216409_);
            blockpos$mutableblockpos.setWithOffset(p_216407_, j, p_216410_, k);
            if (p_216406_.getWorldBorder().isWithinBounds(blockpos$mutableblockpos) && moveToPossibleSpawnPosition(p_216406_, p_216410_, blockpos$mutableblockpos, p_216411_)) {
                if (player instanceof Player) {
                    T t = p_216404_.create(p_216406_, null, null, blockpos$mutableblockpos, p_216405_, false, false);
                    if (t != null) {
                        t.setOwnerUUID(player.getUUID());
                        if (Handler.hascurio(player, Items.anaerobic_cell.get())) {
                            t.addTag(Giant_Time);
                        }
                        if (Handler.hascurio(player, Items.giant_boom_cell.get())) {
                            t.addTag(Giant_Boom);
                        }
                        if (Handler.hascurio(player, Items.not_blood_cell.get())) {
                            t.addTag(Not_Giant_BLOOD);
                        }
                        if (Handler.hascurio(player, Items.subspace_cell.get())) {
                            t.addTag(Subspace_Giant);
                        }
                        if (Handler.hascurio(player, Items.bone_cell.get())) {
                            t.addTag(Bone_Giant);
                        }
                        if (Handler.hascurio(player, Items.parasitic_cell.get())) {
                            t.addTag(Parasitic_cell_Giant);
                        }
                        if (Handler.hascurio(player, Items.disgusting_cells.get())) {
                            t.addTag(Disgusting__cell_Giant);
                        }
                    }
                    if (t != null) {
                        if (net.minecraftforge.event.ForgeEventFactory.checkSpawnPosition(t, p_216406_, p_216405_)) {
                            p_216406_.addFreshEntityWithPassengers(t);
                            return;
                        }

                        t.discard();
                    }
                }else if (player instanceof TamableAnimal animal){
                    if (animal.getOwner()!=null) {
                        T t = p_216404_.create(p_216406_, null, null, blockpos$mutableblockpos, p_216405_, false, false);
                        if (t != null) {
                            t.setOwnerUUID(animal.getOwner().getUUID());
                            if (Handler.hascurio(player, Items.anaerobic_cell.get())) {
                                t.addTag(Giant_Time);
                            }
                            if (Handler.hascurio(player, Items.giant_boom_cell.get())) {
                                t.addTag(Giant_Boom);
                            }
                            if (Handler.hascurio(player, Items.not_blood_cell.get())) {
                                t.addTag(Not_Giant_BLOOD);
                            }
                            if (Handler.hascurio(player, Items.subspace_cell.get())) {
                                t.addTag(Subspace_Giant);
                            }
                            if (Handler.hascurio(player, Items.bone_cell.get())) {
                                t.addTag(Bone_Giant);
                            }
                            if (Handler.hascurio(player, Items.parasitic_cell.get())) {
                                t.addTag(Parasitic_cell_Giant);
                            }
                            if (Handler.hascurio(player, Items.disgusting_cells.get())) {
                                t.addTag(Disgusting__cell_Giant);
                            }
                        }
                        if (t != null) {
                            if (net.minecraftforge.event.ForgeEventFactory.checkSpawnPosition(t, p_216406_, p_216405_)) {
                                p_216406_.addFreshEntityWithPassengers(t);
                                return;
                            }

                            t.discard();
                        }
                    }
                }
            }
        }
    }

    private static boolean moveToPossibleSpawnPosition(ServerLevel p_216399_, int p_216400_, BlockPos.MutableBlockPos p_216401_, SpawnUtil.Strategy p_216402_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = (new BlockPos.MutableBlockPos()).set(p_216401_);
        BlockState blockstate = p_216399_.getBlockState(blockpos$mutableblockpos);

        for (int i = p_216400_; i >= -p_216400_; --i) {
            p_216401_.move(Direction.DOWN);
            blockpos$mutableblockpos.setWithOffset(p_216401_, Direction.UP);
            BlockState blockstate1 = p_216399_.getBlockState(p_216401_);
            if (p_216402_.canSpawnOn(p_216399_, p_216401_, blockstate1, blockpos$mutableblockpos, blockstate)) {
                p_216401_.move(Direction.UP);
                return true;
            }

            blockstate = blockstate1;
        }

        return false;
    }

    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (entity != null) {
            List<SlotResult> find = findCurios(entity, curio);
            for (SlotResult slotResult : find) {
                if (slotResult.stack().is(curio)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean BlackEntity(LivingEntity living ){
        for (String string : Config.SERVER.FlyingSword.get()){
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(string));
            if (living.getType() == entityType){
                return false;
            }
        }
        return true;
    }
    public static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity, Item item) {
        return findCurios(livingEntity, (stack) -> stack.getItem() == item);
    }

    public static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity,
                                              Predicate<ItemStack> filter) {
        return CuriosApi.getCuriosInventory(livingEntity).map(inv -> inv.findCurios(filter))
                .orElse(Collections.emptyList());
    }

    public static void renderColor(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType,float r,int red,int g, int b) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = r; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquareColor(vertexConsumer, poseStack, up1, up2, down1, down2, a,red,g,b);
        }
    }
    private static void addSquareColor(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha, int r, int g, int b) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.vertex(poseStack.last().pose(), (float)up1.x, (float)up1.y, (float)up1.z)
                .color(r, g, b, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();

        vertexConsumer.vertex(poseStack.last().pose(), (float)down1.x, (float)down1.y, (float)down1.z)
                .color(r, g, b, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();

        vertexConsumer.vertex(poseStack.last().pose(), (float)down2.x, (float)down2.y, (float)down2.z)
                .color(r, g, b, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();

        vertexConsumer.vertex(poseStack.last().pose(), (float)up2.x, (float)up2.y, (float)up2.z)
                .color(r, g, b, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();
    }

    public static void renderBlood(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType,float r) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = r; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    private static void addSquare(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.vertex(poseStack.last().pose(), (float)up1.x, (float)up1.y, (float)up1.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();

        vertexConsumer.vertex(poseStack.last().pose(), (float)down1.x, (float)down1.y, (float)down1.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();

        vertexConsumer.vertex(poseStack.last().pose(), (float)down2.x, (float)down2.y, (float)down2.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();

        vertexConsumer.vertex(poseStack.last().pose(), (float)up2.x, (float)up2.y, (float)up2.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .uv2(240, 240)
                .normal(0, 0, 1).endVertex();
    }

    public static void renderLine(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = 0.05f; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    public static void renderSnake(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        float radius = 0.075f; // 半径
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
}
