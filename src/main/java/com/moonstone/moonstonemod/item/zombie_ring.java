package com.moonstone.moonstonemod.item;

import com.moonstone.moonstonemod.entity.necora.small_zombie;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class zombie_ring extends TheNecoraIC {
//
//    private final String yes = "Yes";
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
//        Vec3 playerPos = player.position().add(0, 0.75, 0);
//        int range = 2;
//        List<small_zombie> entities = player.level().getEntitiesOfClass(small_zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
//        ItemStack t = player.getItemInHand(p_41434_);
//        if (!t.getOrCreateTag().getBoolean(yes)) {
//            for (small_zombie zombie : entities) {
//                if (zombie.isAlive()) {
//                    if (zombie.getOwner() != null && zombie.getOwner() == player) {
//                        CuriosApi.getCuriosInventory(zombie).ifPresent(handler -> {
//                            Map<String, ICurioStacksHandler> curios = handler.getCurios();
//                            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
//                                ICurioStacksHandler stacksHandler = entry.getValue();
//                                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
//                                for (int i = 0; i < stacksHandler.getSlots(); i++) {
//                                    ItemStack stack = stackHandler.getStackInSlot(i);
//                                    if (!stack.isEmpty()) {
//                                        t.getOrCreateTag().putString(stack.getDescriptionId(), stack.getDescriptionId());
//                                    }
//                                }
//                            }
//                        });
//                        t.getOrCreateTag().putBoolean(yes,false);
//                        zombie.discard();
//                    }
//                }
//            }
//        }else {
//            small_zombie small_zombie = new small_zombie(EntityTs.small_zombie.get(), level);
//            small_zombie.setPos(player.position());
//            small_zombie.setOwnerUUID(player.getUUID());
//
//            CuriosApi.getCuriosInventory(small_zombie).ifPresent(handler -> {
//                Map<String, ICurioStacksHandler> curios = handler.getCurios();
//                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
//                    ICurioStacksHandler stacksHandler = entry.getValue();
//                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
//                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
//                        ItemStack present = stackHandler.getStackInSlot(i);
//                        if (present.isEmpty()) {
//                            for (String s : present.getOrCreateTag().getAllKeys()) {
//                                if (ForgeRegistries.ITEMS.getValue(new ResourceLocation(s)) != null) {
//                                    ItemStack stack = ForgeRegistries.ITEMS.getValue(new ResourceLocation(s)).getDefaultInstance();
//                                    stackHandler.setStackInSlot(i, stack);
//
//                                }
//                            }
//                        }
//                    }
//                }
//            });
//            t.getOrCreateTag().putBoolean(yes,true);
//            level.addFreshEntity(small_zombie);
//        }
//        return super.use(level, player, p_41434_);
//    }
//    @Override
//    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
//        super.appendHoverText(stack, level, tooltip, flags);
//        for (String s : stack.getOrCreateTag().getAllKeys()) {
//            tooltip.add(Component.translatable(s).withStyle(ChatFormatting.DARK_RED));
//        }
//    }
}
