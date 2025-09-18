package com.ytgld.seeking_immortals;

import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public class Handler {
    public static int getTagNumber(ItemStack must, String name){
        CompoundTag tag = must.getTag();
        if (tag != null){
            return tag.getInt(name);
        }
        return 0;
    }
    public static void addTagNumber(ItemStack must, String name, Player player,int giveNumber){
        CompoundTag tag = must.getTag();
        if (tag != null) {
            tag.putInt(name, tag.getInt(name) + giveNumber);
        }
    }
    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (CuriosApi.getCuriosInventory(entity).resolve().isPresent()) {
            if (CuriosApi.getCuriosInventory(entity).resolve().get().isEquipped(Items.immortal.get())) {
                if (curio instanceof nightmare) {
                    return false;
                }
            }
        }
        if (CuriosApi.getCuriosInventory(entity).resolve().isPresent()
                && !CuriosApi.getCuriosInventory(entity).resolve().get().isEquipped(Items.nightmare_base.get())) {
            if (curio instanceof SuperNightmare) {
                return false;
            }
        }
        return CuriosApi.getCuriosInventory(entity).resolve().isPresent()
                && CuriosApi.getCuriosInventory(entity).resolve().get().isEquipped(curio);
    }
}
