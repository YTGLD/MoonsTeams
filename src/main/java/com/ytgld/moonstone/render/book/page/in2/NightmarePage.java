package com.ytgld.moonstone.render.book.page.in2;


import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.render.book.MoonBookScreen;
import com.ytgld.moonstone.render.book.tool.AddBookPage;
import com.ytgld.moonstone.render.book.tool.RegisterBookPage;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AddBookPage
public class NightmarePage implements RegisterBookPage {
    private static final int aInt = 24;
    private static final int color = Light.ARGB.color(255,255, 50, 200);
    @Override
    public void addPage(List<MoonBookScreen.CIBookGuiAdd> list) {
        addNightmareList(list,InitItems.NightmareBaseItem_.asItem(),new Vec2(0,0),List.of(Component.translatable("moonstone.book.nightmare_base.1")));


        addNightmareList(list,InitItems.flag_of_protest.asItem(),new Vec2(-1,1),List.of(Component.translatable("item.moonstone.flag_of_protest.book")));
        addNightmareList(list,InitItems.dead_drder.asItem(),new Vec2(-1,2),List.of(Component.translatable("item.moonstone.dead_drder.book")));
        addNightmareList(list,InitItems.defend_against_runestone.asItem(),new Vec2(-2,-1),List.of(Component.translatable("item.moonstone.defend_against_runestone.book")));
        addNightmareList(list,InitItems.revive_runestone.asItem(),new Vec2(-2,-2),List.of(Component.translatable("item.moonstone.revive_runestone.book")));
        addNightmareList(list,InitItems.strengthen_runestone.asItem(),new Vec2(-2,-3),List.of(Component.translatable("item.moonstone.strengthen_runestone.book")));
        addNightmareList(list,InitItems.blood_god.asItem(),new Vec2(2,-2),List.of(Component.translatable("item.moonstone.blood_god.book")));
        addNightmareList(list,InitItems.bone_or_god.asItem(),new Vec2(3,-2),List.of(Component.translatable("item.moonstone.bone_or_god.book")));
        addNightmareList(list,InitItems.lead.asItem(),new Vec2(4,-2),List.of(Component.translatable("item.moonstone.lead.book")));
        addNightmareList(list,InitItems.immortal.asItem(),new Vec2(5,-2),List.of(Component.translatable("item.moonstone.immortal.book")));


        addNightmare(list,InitItems.nightmare_base_black_eye.asItem(),new Vec2(0,1));
        addNightmare(list,InitItems.nightmare_base_black_eye_heart.asItem(),new Vec2(1,1));
        addNightmare(list,InitItems.nightmare_base_black_eye_eye.asItem(),new Vec2(2,1));
        addNightmare(list,InitItems.nightmare_base_black_eye_red.asItem(),new Vec2(3,1));
        addNightmare(list,InitItems.tricky_puppets.asItem(),new Vec2(4,1));

        addNightmare(list,InitItems.nightmare_base_fool.asItem(),new Vec2(0,2));
        addNightmare(list,InitItems.nightmare_base_fool_betray.asItem(),new Vec2(1,2));
        addNightmare(list,InitItems.nightmare_base_fool_bone.asItem(),new Vec2(2,2));
        addNightmare(list,InitItems.nightmare_base_fool_soul.asItem(),new Vec2(3,2));
        addNightmare(list,InitItems.apple.asItem(),new Vec2(4,2));

        addNightmare(list,InitItems.nightmare_base_insight.asItem(),new Vec2(0,3));
        addNightmare(list,InitItems.nightmare_base_insight_insane.asItem(),new Vec2(1,3));
        addNightmare(list,InitItems.ring.asItem(),new Vec2(2,3));
        addNightmare(list,InitItems.nightmare_base_insight_drug.asItem(),new Vec2(3,3));
        addNightmare(list,InitItems.nightmare_base_insight_collapse.asItem(),new Vec2(4,3));
        addNightmare(list,InitItems.hidden_blade.asItem(),new Vec2(5,3));

        addNightmare(list,InitItems.nightmare_base_redemption.asItem(),new Vec2(0,4));
        addNightmare(list,InitItems.hypocritical_self_esteem.asItem(),new Vec2(1,4));
        addNightmare(list,InitItems.nightmare_base_redemption_down_and_out.asItem(),new Vec2(2,4));
        addNightmare(list,InitItems.nightmare_base_redemption_degenerate.asItem(),new Vec2(3,4));
        addNightmare(list,InitItems.nightmare_base_redemption_deception.asItem(),new Vec2(4,4));


        addNightmareList(list,InitItems.rotten_gourd.asItem(),new Vec2(-1,5),List.of(
                Component.translatable("item.moonstone.rotten_gourd.book")
        ));
        addNightmare(list,InitItems.nightmare_base_reversal.asItem(),new Vec2(0,5));
        addNightmare(list,InitItems.candle.asItem(),new Vec2(1,5));
        addNightmare(list,InitItems.nightmare_base_reversal_card.asItem(),new Vec2(2,5));
        addNightmare(list,InitItems.nightmare_base_reversal_mysterious.asItem(),new Vec2(3,5));
        addNightmare(list,InitItems.nightmare_base_reversal_orb.asItem(),new Vec2(4,5));

        addNightmareList(list,InitItems.supreme_power.asItem(),new Vec2(-1,6),List.of(
                Component.translatable("item.moonstone.supreme_power.book")
        ));
        addNightmare(list,InitItems.nightmare_base_start.asItem(),new Vec2(0,6));
        addNightmare(list,InitItems.nightmare_base_start_egg.asItem(),new Vec2(1,6));
        addNightmare(list,InitItems.nightmare_base_start_pod.asItem(),new Vec2(2,6));
        addNightmare(list,InitItems.nightmare_base_start_power.asItem(),new Vec2(3,6));
        addNightmare(list,InitItems.wolf.asItem(),new Vec2(4,6));

        addNightmareList(list,InitItems.nightmare_clay.asItem(),new Vec2(-1,7),List.of(
                Component.translatable("item.moonstone.nightmare_clay.book")
        ));
        addNightmare(list,InitItems.nightmare_base_stone.asItem(),new Vec2(0,7));
        addNightmare(list,InitItems.end_bone.asItem(),new Vec2(1,7));
        addNightmare(list,InitItems.nightmare_base_stone_brain.asItem(),new Vec2(2,7));
        addNightmare(list,InitItems.nightmare_base_stone_meet.asItem(),new Vec2(3,7));
        addNightmare(list,InitItems.nightmare_base_stone_virus.asItem(),new Vec2(4,7));
        addNightmare(list,InitItems.NightmareVirus_.asItem(),new Vec2(5,7));

    }
    private static void  addNightmareList(List<MoonBookScreen.CIBookGuiAdd> list,
                                          Item item,
                                          Vec2 pos,
                                          List<Component> give
    ){
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        List<Component> components = new ArrayList<>(give);
        components.add(Component.literal(""));
        components.add(Component.translatable("moonstone.book.back"));
        list.add(new MoonBookScreen.CIBookGuiAdd(item, pos.scale(32),
                Component.translatable("item.moonstone." +  name),
                components,
                color,
                color,
                MoonBookScreen.ThePage.BLACK,
                theCColor(item),List.of(),true,true));
    }
    private static void  addNightmare(List<MoonBookScreen.CIBookGuiAdd> list,
                                      Item item,
                                      Vec2 pos
    ){
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        List<Component> translatable = List.of(Component.translatable(
                "moonstone.jei.item.moonstone."  + name
        ));
        List<Component> components = new ArrayList<>(translatable);
        components.add(Component.literal(""));
        components.add(Component.translatable("moonstone.book.back"));
        list.add(new MoonBookScreen.CIBookGuiAdd(item, pos.scale(32),
                Component.translatable("item.moonstone." +  name),
                components,
                color,
                color,
                MoonBookScreen.ThePage.BLACK,
                Light.ARGB.color(50, 255, 50, 200),
                List.of(),true,true));
    }
    public static int theCColor(Item item){
        if (item instanceof ItemBase itemBase) {
            return itemBase.color();
        }
        return 0;
    }
}