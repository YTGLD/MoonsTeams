package com.moonstone.moonstonemod;

import com.moonstone.moonstonemod.init.Items;
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
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static com.moonstone.moonstonemod.event.AllEvent.*;

public class Handler {

    public static <T extends TamableAnimal> void trySpawnMob(Player player, EntityType<T> p_216404_, MobSpawnType p_216405_, ServerLevel p_216406_, BlockPos p_216407_, int p_216408_, int p_216409_, int p_216410_, SpawnUtil.Strategy p_216411_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = p_216407_.mutable();

        for (int i = 0; i < p_216408_; ++i) {
            int j = Mth.randomBetweenInclusive(p_216406_.random, -p_216409_, p_216409_);
            int k = Mth.randomBetweenInclusive(p_216406_.random, -p_216409_, p_216409_);
            blockpos$mutableblockpos.setWithOffset(p_216407_, j, p_216410_, k);
            if (p_216406_.getWorldBorder().isWithinBounds(blockpos$mutableblockpos) && moveToPossibleSpawnPosition(p_216406_, p_216410_, blockpos$mutableblockpos, p_216411_)) {
                T t = p_216404_.create(p_216406_, null, null, blockpos$mutableblockpos, p_216405_, false, false);
                if (t != null) {
                    t.setOwnerUUID(player.getUUID());
                    if (Handler.hascurio(player,Items.anaerobic_cell.get())){
                        t.addTag(Giant_Time);
                    }
                    if (Handler.hascurio(player,Items.giant_boom_cell.get())){
                        t.addTag(Giant_Boom);
                    }
                    if (Handler.hascurio(player,Items.not_blood_cell.get())){
                        t.addTag(Not_Giant_BLOOD);
                    }
                    if (Handler.hascurio(player,Items.subspace_cell.get())){
                        t.addTag(Subspace_Giant);
                    }
                    if (Handler.hascurio(player,Items.bone_cell.get())){
                        t.addTag(Bone_Giant);
                    }
                    if (Handler.hascurio(player,Items.parasitic_cell.get())){
                        t.addTag(Parasitic_cell_Giant);
                    }
                    if (Handler.hascurio(player,Items.disgusting_cells.get())){
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


    public static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity, Item item) {
        return findCurios(livingEntity, (stack) -> stack.getItem() == item);
    }

    public static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity,
                                              Predicate<ItemStack> filter) {
        return CuriosApi.getCuriosInventory(livingEntity).map(inv -> inv.findCurios(filter))
                .orElse(Collections.emptyList());
    }
}
