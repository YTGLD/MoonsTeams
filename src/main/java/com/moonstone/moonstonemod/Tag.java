package com.moonstone.moonstonemod;

import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    public static final List<ItemStack> Mls = new ArrayList<>();
    public static final List<ItemStack> nightmare = new ArrayList<>();
    public static final List<ItemStack> doom = new ArrayList<>();
    public static final List<ItemStack> unCommon = new ArrayList<>();
    public static final List<ItemStack> Common = new ArrayList<>();
    public static final List<ItemStack> ectoplasm = new ArrayList<>();
    public static final List<ItemStack> necora = new ArrayList<>();
    public static final List<ItemStack> mob = new ArrayList<>();


    static {

        mob.add(new ItemStack(Items.dna.get()));
        mob.add(new ItemStack(Items.fungus.get()));
        mob.add(new ItemStack(Items.germ.get()));
        mob.add(new ItemStack(Items.parasite.get()));
        mob.add(new ItemStack(Items.virus.get()));
        mob.add(new ItemStack(Items.medicinebox.get()));
        mob.add(new ItemStack(Items.calcification.get()));
        mob.add(new ItemStack(Items.masticatory.get()));
        mob.add(new ItemStack(Items.polyphagia.get()));
        mob.add(new ItemStack(Items.quadriceps.get()));
        mob.add(new ItemStack(Items.reanimation.get()));



        necora.add(new ItemStack(Items.cell.get()));
       necora.add(new ItemStack(Items.adrenaline.get()));
       necora.add(new ItemStack(Items.cell_mummy.get()));
       necora.add(new ItemStack(Items.cell_boom.get()));
       necora.add(new ItemStack(Items.cell_calcification.get()));
       necora.add(new ItemStack(Items.cell_blood.get()));
       necora.add(new ItemStack(Items.air.get()));
       necora.add(new ItemStack(Items.motor.get()));
       necora.add(new ItemStack(Items.watergen.get()));
       necora.add(new ItemStack(Items.giant.get()));
       necora.add(new ItemStack(Items.necora.get()));
       necora.add(new ItemStack(Items.ambush.get()));
       necora.add(new ItemStack(Items.atpoverdose.get()));
       necora.add(new ItemStack(Items.autolytic.get()));
       necora.add(new ItemStack(Items.fermentation.get()));
       necora.add(new ItemStack(Items.putrefactive.get()));
       necora.add(new ItemStack(Items.regenerative.get()));

      ectoplasm.add(new ItemStack(Items.ectoplasmball.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmapple.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmcloub.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmcube.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmhorseshoe.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmprism.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmbattery.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmshild.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmstar.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmsoul.get()));
      ectoplasm.add(new ItemStack(Items.ectoplasmtree.get()));

      Mls.add(new ItemStack(Items.mbattery.get()));
      Mls.add(new ItemStack(Items.mblock.get()));
      Mls.add(new ItemStack(Items.mbottle.get()));
      Mls.add(new ItemStack(Items.mbox.get()));
      Mls.add(new ItemStack(Items.meye.get()));
      Mls.add(new ItemStack(Items.mkidney.get()));
      Mls.add(new ItemStack(Items.morb.get()));
      Mls.add(new ItemStack(Items.mring.get()));
      Mls.add(new ItemStack(Items.mshell.get()));
      Mls.add(new ItemStack(Items.brain.get()));
      Mls.add(new ItemStack(Items.mhead.get()));

        nightmare.add(new ItemStack(Items.nightmareanchor.get()));
        nightmare.add(new ItemStack(Items.nightmarecharm.get()));
        nightmare.add(new ItemStack(Items.nightmareeye.get()));
        nightmare.add(new ItemStack(Items.nightmaremoai.get()));
        nightmare.add(new ItemStack(Items.nightmarerotten.get()));
        nightmare.add(new ItemStack(Items.nightmarestone.get()));
        nightmare.add(new ItemStack(Items.nightmaretreasure.get()));
        nightmare.add(new ItemStack(Items.nightmarewater.get()));
        nightmare.add(new ItemStack(Items.nightmare_orb.get()));

       unCommon.add(new ItemStack(Items.book.get()));
       unCommon.add(new ItemStack(Items.the_heart.get()));
       unCommon.add(new ItemStack(Items.plague.get()));
       unCommon.add(new ItemStack(Items.maxamout.get()));
       unCommon.add(new ItemStack(Items.fortunecrystal.get()));
       unCommon.add(new ItemStack(Items.mayhemcrystal.get()));
       unCommon.add(new ItemStack(Items.soulbattery.get()));
       unCommon.add(new ItemStack(Items.soulcube.get()));
       unCommon.add(new ItemStack(Items.diemug.get()));
       unCommon.add(new ItemStack(Items.evilcandle.get()));
       unCommon.add(new ItemStack(Items.evilmug.get()));
       unCommon.add(new ItemStack(Items.obsidianring.get()));


       doom.add(new ItemStack(Items.magiceye.get()));
       doom.add(new ItemStack(Items.magicstone.get()));
       doom.add(new ItemStack(Items.nanocube.get()));
       doom.add(new ItemStack(Items.nanorobot.get()));
       doom.add(new ItemStack(Items.thedoomstone.get()));
       doom.add(new ItemStack(Items.thefruit.get()));
       doom.add(new ItemStack(Items.doomeye.get()));
       doom.add(new ItemStack(Items.doomswoud.get()));
       doom.add(new ItemStack(Items.wind.get()));
       doom.add(new ItemStack(Items.wind_and_rain.get()));



        Common.add(new ItemStack(Items.beacon.get()));
        Common.add(new ItemStack(Items.ectoplasmstone.get()));
        Common.add(new ItemStack(Items.twistedstone.get()));
        Common.add(new ItemStack(Items.badgeofthedead.get()));
        Common.add(new ItemStack(Items.battery.get()));
        Common.add(new ItemStack(Items.biggreedcrystal.get()));
        Common.add(new ItemStack(Items.bigwarcrystal.get()));
        Common.add(new ItemStack(Items.blackeorb.get()));
        Common.add(new ItemStack(Items.blueamout.get()));
        Common.add(new ItemStack(Items.greedamout.get()));
        Common.add(new ItemStack(Items.redamout.get()));
        Common.add(new ItemStack(Items.greedcrystal.get()));
        Common.add(new ItemStack(Items.warcrystal.get()));
        Common.add(new ItemStack(Items.whiteorb.get()));

    }
}
