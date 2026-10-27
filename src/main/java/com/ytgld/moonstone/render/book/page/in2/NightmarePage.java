package com.ytgld.moonstone.render.book.page.in2;


import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.book.MoonBookScreen;
import com.ytgld.moonstone.render.book.tool.AddBookPage;
import com.ytgld.moonstone.render.book.tool.RegisterBookPage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;

import java.util.List;
@AddBookPage
public class NightmarePage implements RegisterBookPage {
    private final int aInt = 24;
    private final int color = Light.ARGB.color(255,255, 50, 200);
    @Override
    public void addPage(List<MoonBookScreen.CIBookGuiAdd> list) {
        list.add(new MoonBookScreen.CIBookGuiAdd(InitItems.NightmareBaseItem_.asItem(), new Vec2(0, 0),
                Component.translatable("moonstone.book.nightmare_base.main"),
                List.of(
                        Component.translatable("moonstone.book.nightmare_base.1")
                ),
                color,
                color,
                MoonBookScreen.ThePage.BLACK,
                theCColor(InitItems.NightmareBaseItem_.asItem()),List.of(),true,true));
    }
    public static int theCColor(Item item){
        if (item instanceof ItemBase itemBase) {
            return itemBase.color();
        }
        return 0;
    }
}