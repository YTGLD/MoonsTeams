package com.ytgld.moonstone.render.book.page;

import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.render.book.MoonBookScreen;
import com.ytgld.moonstone.render.book.tool.AddBookPage;
import com.ytgld.moonstone.render.book.tool.RegisterBookPage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;

import java.util.List;

@AddBookPage
public class EctoplasmPage implements RegisterBookPage {

    private Component ectoplasm(){
        return Component.translatable("moonstone.book.ectoplasm.main");
    }


    @Override
    public void addPage(List<MoonBookScreen.CIBookGuiAdd> list) {
        addList(list,InitItems.ectoplasmball.asItem(),new Vec2(offset(1),offset(0)),2,ectoplasm(),notHasDrop());
        addList(list,InitItems.ectoplasmcloub.asItem(),new Vec2(offset(2),offset(0)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmcube.asItem(),new Vec2(offset(3),offset(0)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmprism.asItem(),new Vec2(offset(4),offset(0)),2,ectoplasm(),listCraft());

        addList(list,InitItems.ectoplasmapple.asItem(),new Vec2(offset(1),offset(1)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmbattery.asItem(),new Vec2(offset(3),offset(1)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmhorseshoe.asItem(),new Vec2(offset(2),offset(1)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmshild.asItem(),new Vec2(offset(4),offset(1)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmstar.asItem(),new Vec2(offset(4),offset(2)),2,ectoplasm(),listCraft());
        addList(list,InitItems.ectoplasmtree.asItem(),new Vec2(offset(4),offset(3)),2,ectoplasm(),listCraft());

    }

}
