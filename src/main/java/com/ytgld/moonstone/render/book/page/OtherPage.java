package com.ytgld.moonstone.render.book.page;

import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.render.book.MoonBookScreen;
import com.ytgld.moonstone.render.book.tool.AddBookPage;
import com.ytgld.moonstone.render.book.tool.RegisterBookPage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;

import java.util.List;

@AddBookPage
public class OtherPage implements RegisterBookPage {

    private Component component(){
        return Component.literal("");
    }


    @Override
    public void addPage(List<MoonBookScreen.CIBookGuiAdd> list) {
        addList(list,InitItems.blueamout.asItem(),new Vec2(offset(3),offset(3)),2,component(),listDungeon());
        addList(list,InitItems.redamout.asItem(),new Vec2(offset(3),offset(4)),2,component(),listCraft());
        addList(list,InitItems.greedamout.asItem(),new Vec2(offset(3),offset(5)),2,component(),listCraft());
        addList(list,InitItems.maxamout.asItem(),new Vec2(offset(1),offset(4)),2,component(),listCraft());

        addList(list,InitItems.greedcrystal.asItem(),new Vec2(offset(2),offset(6)),2,component(),listDungeon());
        addList(list,InitItems.biggreedcrystal.asItem(),new Vec2(offset(1),offset(6)),2,component(),listCraft());
        addList(list,InitItems.fortunecrystal.asItem(),new Vec2(offset(0),offset(6)),2,component(),listCraft());

        addList(list,InitItems.warcrystal.asItem(),new Vec2(offset(1),offset(7)),2,component(),listDungeon());
        addList(list,InitItems.bigwarcrystal.asItem(),new Vec2(offset(0),offset(7)),2,component(),listCraft());
        addList(list,InitItems.mayhemcrystal.asItem(),new Vec2(offset(-1),offset(7)),2,component(),listCraft());

        addList(list,InitItems.rage_crystal.asItem(),new Vec2(offset(0),offset(8)),2,component(),listDungeon());
        addList(list,InitItems.rage_crystal_big.asItem(),new Vec2(offset(-1),offset(8)),2,component(),listCraft());
        addBlood(list);
        addList(list,InitItems.rage_crystal_max.asItem(),new Vec2(offset(-2),offset(8)),2,component(),listCraft());
        addNecora(list);

    }

    private Component necora(){
        return Component.translatable("moonstone.book.loot.necora");
    }
    private Component dna(){
        return Component.translatable("moonstone.book.nda.main");
    }
    private List<Component> hasEqBooleanNecora(){
        return List.of(
                Component.translatable("moonstone.book.loot.has.2"),
                Component.translatable("moonstone.book.loot.has.3"),
                Component.translatable("moonstone.book.loot.clear")
        );
    }
    private void addNecora(List<MoonBookScreen.CIBookGuiAdd> list) {
        addList(list,InitItems.necora.asItem(),new Vec2(offset(-4),offset(8)),1,component(),listCraft());

        addList(list,InitItems.cytopathic_boost.asItem(),new Vec2(offset(-6),offset(8)),1,dna(),hasEqBooleanNecora());
        addList(list,InitItems.spliced_activation.asItem(),new Vec2(offset(-7),offset(8)),1,dna(),hasEqBooleanNecora());
        addDNASpace(list);
        addList(list,InitItems.thermo_necro.asItem(),new Vec2(offset(-8),offset(8)),1,dna(),hasEqBooleanNecora());

        addList(list,InitItems.WarmApproachable.asItem(),new Vec2(offset(-6),offset(6)),1,dna(),hasEqBooleanNecora());
        addList(list,InitItems.OceanAffinity.asItem(),new Vec2(offset(-7),offset(6)),1,dna(),hasEqBooleanNecora());
        addList(list,InitItems.EarthAffinity.asItem(),new Vec2(offset(-8),offset(6)),1,dna(),hasEqBooleanNecora());




    }

    private void addDNASpace(List<MoonBookScreen.CIBookGuiAdd> list) {
        addList(list,InitItems.calcareous.asItem(),new Vec2(offset(-7),offset(9)),1,component(),hasEqBooleanNecora());
        addList(list,InitItems.frontal_lobe.asItem(),new Vec2(offset(-7),offset(10)),1,component(),hasEqBooleanNecora());
        addList(list,InitItems.high_energy.asItem(),new Vec2(offset(-7),offset(11)),1,component(),hasEqBooleanNecora());
        addList(list,InitItems.surge.asItem(),new Vec2(offset(-7),offset(12)),1,component(),hasEqBooleanNecora());
    }


    private Component blood(){
        return Component.translatable("moonstone.book.loot.blood");
    }
    private void addBlood(List<MoonBookScreen.CIBookGuiAdd> list){
        addList(list,InitItems.blood_candle.asItem(),new Vec2(offset(-1),offset(9)),3,component(),listCraft());

        addList(list,InitItems.owner_blood_eye.asItem(),new Vec2(offset(-3),offset(10)),1,blood(),hasEqBoolean());
        addList(list,InitItems.owner_blood_attack_eye.asItem(),new Vec2(offset(-2),offset(10)),2,blood(),hasEqBoolean());
        addList(list,InitItems.owner_blood_speed_eye.asItem(),new Vec2(offset(-1),offset(10)),3,blood(),hasEqBoolean());
        addList(list,InitItems.owner_blood_boom_eye.asItem(),new Vec2(offset(0),offset(10)),3,blood(),hasEqBoolean());
        addList(list,InitItems.owner_blood_effect_eye.asItem(),new Vec2(offset(1),offset(10)),1,blood(),hasEqBoolean());
        addList(list,InitItems.owner_blood_earth.asItem(),new Vec2(offset(3),offset(10)),2,blood(),hasEqBoolean());
        addList(list,InitItems.owner_blood_vex.asItem(),new Vec2(offset(2),offset(10)),3,blood(),hasEqBoolean());

        addList(list,InitItems.the_blood_book.asItem(),new Vec2(offset(0),offset(9)),1,blood(),listCraft());

    }



}
