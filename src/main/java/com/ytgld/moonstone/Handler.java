package com.ytgld.moonstone;

import com.ytgld.moonstone.enttiy.CellGiant;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.DataReg;
import com.ytgld.moonstone.render.MRender;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

import static com.ytgld.moonstone.event.AllEvent.*;

public class Handler {

    public static void stackCreateTag(ItemStack stack){
        if (stack.get(DataReg.tag) == null){
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    public static boolean hasModifyFormItem(Player player,Item target){
        Set<ItemStack> stacks = new HashSet<>();
        var inv=  CuriosApi.getCuriosInventory(player);
        if (inv.isPresent()) {
            ICuriosItemHandler iCuriosItemHandler =  inv.get();
            Map<String, ICurioStacksHandler> curioStacksHandlers = iCuriosItemHandler.getCurios();
            for (ICurioStacksHandler iCurioStacksHandler : curioStacksHandlers.values()){
                for (int i = 0; i < iCurioStacksHandler.getStacks().getSlots(); i++) {
                    stacks.add(
                            iCurioStacksHandler.getStacks().getStackInSlot(i)
                    );
                }
            }
        }
        for (ItemStack stack : stacks){
            if (stack.getItem() instanceof ItemBase itemBase) {
                if (itemBase.canUSe().contains(target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void renderBack(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height
            , Identifier farmer, Identifier back , int colorF,int colorB) {
        int i = x - 3 - 9;
        int j = y - 3 - 9;
        int k = width + 3 + 3 + 18;
        int l = height + 3 + 3 + 18;
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, farmer, i, j, k, l,colorF);
        guiGraphics.blitSprite(MRender.RenderPs.GUI_TEXTURED, back, i, j, k, l,colorB);
    }
    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (entity != null) {
            Optional<ICuriosItemHandler> curiosItemHandlerOptional = CuriosApi.getCuriosInventory(entity);
            if (curiosItemHandlerOptional.isPresent()) {
                List<SlotResult> find = findCurios(entity, curio);
                for (SlotResult slotResult : find) {
                    if (slotResult.stack().is(curio)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity, Item item) {
        return findCurios(livingEntity, (stack) -> stack.getItem() == item);
    }

    private static List<SlotResult> findCurios(@Nonnull LivingEntity livingEntity,
                                              Predicate<ItemStack> filter) {
        return CuriosApi.getCuriosInventory(livingEntity).map(inv -> inv.findCurios(filter))
                .orElse(Collections.emptyList());
    }
    public static void trySpawnMob(Player player,EntityType<CellGiant> cellGiantEntityType, Vec3 position) {
        CellGiant cellGiant = new CellGiant(cellGiantEntityType,player.level());
        cellGiant.setOwner(player);
        cellGiant.setPos(player.position());
        cellGiant.setPose(Pose.EMERGING);
        if (Handler.hascurio(player, Items.bone_cell.get())) {
            cellGiant.addTag(Bone_Giant);
        }
        if (Handler.hascurio(player, Items.parasitic_cell.get())) {
            cellGiant.addTag(Parasitic_cell_Giant);
        }
        if (Handler.hascurio(player, Items.disgusting_cells.get())) {
            cellGiant.addTag(Disgusting__cell_Giant);
        }
        if (Handler.hascurio(player, Items.bone_cell.get())) {
            cellGiant.addTag(Bone_Giant);
        }
        if (Handler.hascurio(player, Items.parasitic_cell.get())) {
            cellGiant.addTag(Parasitic_cell_Giant);
        }
        if (Handler.hascurio(player, Items.disgusting_cells.get())) {
            cellGiant.addTag(Disgusting__cell_Giant);
        }
        player.level().addFreshEntity(cellGiant);
    }


}
