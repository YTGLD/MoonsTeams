package com.ytgld.moonstone.render.book.tool;


import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.book.MoonBookScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RegisterBookPage {
    void addPage(List<MoonBookScreen.CIBookGuiAdd> list);
    int color = Light.ARGB.color(255,200, 200, 200);
    int aInt = 24;
    default int offset(int mul){
        if (mul == 0) {
            return 0;
        }
        return aInt * mul;
    }
    default List<Component> listDungeon(){
        return List.of(
                Component.translatable("moonstone.book.loot.dungeon"),
                Component.translatable("moonstone.book.loot.clear")
        );
    }

    default List<Component> not(){
        return List.of(
                Component.translatable("moonstone.book.loot.not.1"),
                Component.translatable("moonstone.book.loot.clear")
        );
    }
    default List<Component> listCraft(){
        return List.of(
                Component.translatable("moonstone.book.loot.craft"),
                Component.translatable("moonstone.book.loot.clear")
        );
    }
    default List<Component> notHasDrop(){
        return List.of(
                Component.translatable("moonstone.book.loot.not"),
                Component.translatable("moonstone.book.loot.clear")
        );
    }
    default void  addList(List<MoonBookScreen.CIBookGuiAdd> list,
                          Item item,
                          Vec2 pos,
                          int value,
                          Component other,
                          List<Component> give
    ){
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        Set<Component> set = new HashSet<>();
        for (int i = 1; i <= value ; i++) {
            set.add(Component.translatable("moonstone.book." + name + "." + value));
        }
        if (other != null) {
            set.add(other);
        }
        list.add(new MoonBookScreen.CIBookGuiAdd(item, new Vec2(pos.x, pos.y),
                Component.translatable("item.moonstone." +  name),
                new ArrayList<>(set),
                color,
                color,
                MoonBookScreen.ThePage.BASE,
                color,give));
    }
}