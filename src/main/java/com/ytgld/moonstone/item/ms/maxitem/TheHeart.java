package com.ytgld.moonstone.item.ms.maxitem;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TheHeart extends BundleItem {

    public TheHeart(Properties properties) {
        super(properties);
    }

    private static Optional<ItemStack> removeOneItemFromBundle(ItemStack self, Player player, BundleContents initialContents) {
        BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
        ItemStack removed = contents.removeOne();
        if (removed != null) {
            self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
            return Optional.of(removed);
        } else {
            return Optional.empty();
        }
    }
    public static void the_heart(LivingDropsEvent event){
        if ((event.getSource().getEntity() instanceof Player player)) {
            if (Handler.hascurio(player, Items.the_heart.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty() && stack.getItem() instanceof TheHeart theHeart) {
                                BundleContents contents = stack.get(DataComponents.BUNDLE_CONTENTS);
                                if (contents != null && !contents.isEmpty()) {
                                    Optional<ItemStack> itemStack = removeOneItemFromBundle(stack, player, contents);
                                    if (itemStack.isPresent()) {
                                        ItemStack in = itemStack.get();
                                        Collection<ItemEntity> drop = event.getDrops();
                                        for (ItemEntity entity : drop) {
                                            ItemStack i_stack = entity.getItem();
                                            if (i_stack.is(in.getItem())) {
                                                if (!(i_stack.getMaxStackSize() < 3)) {
                                                    i_stack.setCount(i_stack.getCount() * 3);
                                                    entity.setItem(i_stack);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });

            }
        }
    }
    public void appendHoverText(ItemStack p_150749_, Level p_150750_, List<Component> p_150751_, TooltipFlag p_150752_) {
        p_150751_.add(Component.translatable("item.the_heart.tool.string").withStyle(ChatFormatting.GOLD));
        p_150751_.add(Component.translatable("item.the_heart.tool.string.1").withStyle(ChatFormatting.GOLD));
        p_150751_.add(Component.translatable("item.the_heart.tool.string.2").withStyle(ChatFormatting.GOLD));
        p_150751_.add(Component.translatable(""));
    }

}
