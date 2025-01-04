package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JeiText implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(MoonStoneMod.MODID, "jei_plugin");
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.ectoplasmstone"));
        registration.addIngredientInfo(new ItemStack(Items.twistedstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("moonstone.jei.twistedstone"));

        registration.addIngredientInfo(new ItemStack(Items.cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell").append(Component.translatable("moonstone.jei.cell")));
        registration.addIngredientInfo(new ItemStack(Items.giant.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.giant").append(Component.translatable("moonstone.jei.giant")));
        registration.addIngredientInfo(new ItemStack(Items.giant_nightmare.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.giant_nightmare").append(Component.translatable("moonstone.jei.giant_nightmare")));

        registration.addIngredientInfo(new ItemStack(Items.ambush.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ambush").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.atpoverdose.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.atpoverdose").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.autolytic.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.autolytic").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.fermentation.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.fermentation").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.putrefactive.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.putrefactive").append(Component.translatable("moonstone.jei.necora.all")));
        registration.addIngredientInfo(new ItemStack(Items.regenerative.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.regenerative").append(Component.translatable("moonstone.jei.necora.all")));

        registration.addIngredientInfo(new ItemStack(Items.air.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.air").append(Component.translatable("moonstone.jei.necora.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.motor.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.motor").append(Component.translatable("moonstone.jei.necora.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.watergen.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.watergen").append(Component.translatable("moonstone.jei.necora.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.slime.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.slime").append(Component.translatable("moonstone.jei.necora.treasure.all")));


        registration.addIngredientInfo(new ItemStack(Items.anaerobic_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.anaerobic_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));
        registration.addIngredientInfo(new ItemStack(Items.giant_boom_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.giant_boom_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));
        registration.addIngredientInfo(new ItemStack(Items.not_blood_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.not_blood_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));
        registration.addIngredientInfo(new ItemStack(Items.subspace_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.subspace_cell").append(Component.translatable("moonstone.jei.necora.giant_nightmare.all")));

        registration.addIngredientInfo(new ItemStack(Items.cell_mummy.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.adrenaline").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_boom.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_mummy").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_calcification.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_boom").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_blood.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_blood").append(Component.translatable("moonstone.jei.cell.all")));
        registration.addIngredientInfo(new ItemStack(Items.adrenaline.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_calcification").append(Component.translatable("moonstone.jei.cell.all")));

        registration.addIngredientInfo(new ItemStack(Items.nightmare_cube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_cube").append(Component.translatable("moonstone.jei.nightmare_cube")));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_head.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_head").append(Component.translatable("moonstone.jei.nightmare_head")));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_heart.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_heart").append(Component.translatable("moonstone.jei.nightmare_heart")));
        registration.addIngredientInfo(new ItemStack(Items.nightmare_orb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmare_orb").append(Component.translatable("moonstone.jei.nightmare_orb")));
        registration.addIngredientInfo(new ItemStack(Items.nightmareanchor.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmareanchor").append(Component.translatable("moonstone.jei.nightmareanchor")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarecharm.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarecharm").append(Component.translatable("moonstone.jei.nightmarecharm")));
        registration.addIngredientInfo(new ItemStack(Items.nightmareeye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmareeye").append(Component.translatable("moonstone.jei.nightmareeye")));
        registration.addIngredientInfo(new ItemStack(Items.nightmaremoai.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmaremoai").append(Component.translatable("moonstone.jei.nightmaremoai")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarerotten.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarerotten").append(Component.translatable("moonstone.jei.nightmarerotten")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarestone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarestone").append(Component.translatable("moonstone.jei.nightmarestone")));
        registration.addIngredientInfo(new ItemStack(Items.nightmaretreasure.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmaretreasure").append(Component.translatable("moonstone.jei.nightmaretreasure")));
        registration.addIngredientInfo(new ItemStack(Items.nightmarewater.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nightmarewater").append(Component.translatable("moonstone.jei.nightmarewater")));


        registration.addIngredientInfo(new ItemStack(Items.brain.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.brain").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mbattery.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mbattery").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mblock.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mblock").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mbottle.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mbottle").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mbox.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mbox").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.meye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.meye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mhead.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mhead").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mkidney.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mkidney").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.morb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.morb").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mring.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mring").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));
        registration.addIngredientInfo(new ItemStack(Items.mshell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.mshell").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maulice.all")));

        registration.addIngredientInfo(new ItemStack(Items.blood.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blood").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.blood")));

        registration.addIngredientInfo(new ItemStack(Items.bloodvirus.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bloodvirus").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus")));


        registration.addIngredientInfo(new ItemStack(Items.batgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.batgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.batskill.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.batskill").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.bloodgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bloodgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.botton.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.botton").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.catalyzer.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.catalyzer").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.flygene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.flygene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.heathgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.heathgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.ragegene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ragegene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));
        registration.addIngredientInfo(new ItemStack(Items.sleepgene.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.sleepgene").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.all")));

        registration.addIngredientInfo(new ItemStack(Items.bat_cell.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.bat_cell").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_blood_attack.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_blood_attack").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_desecrate.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_desecrate").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_doctor.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_doctor").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_fear.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_fear").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_harvest.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_harvest").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_immortal.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_immortal").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_not_do.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_not_do").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_rage.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_rage").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));
        registration.addIngredientInfo(new ItemStack(Items.cell_scientist.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.cell_scientist").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.bloodvirus.treasure.all")));


        registration.addIngredientInfo(new ItemStack(Items.ectoplasmball.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmball").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.ectoplasmball")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmcloub.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmcloub").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.ectoplasmcloub")));

        registration.addIngredientInfo(new ItemStack(Items.beacon.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.beacon").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmtree.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmtree").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmshild.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmshild").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmapple.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmapple").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmprism.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmprism").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmcube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmcube").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmstar.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmstar").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmsoul.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmsoul").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));

        registration.addIngredientInfo(new ItemStack(Items.ectoplasmsoul.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmsoul").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.ectoplasm.all")));

        registration.addIngredientInfo(new ItemStack(Items.soulcube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.soulcube").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.soulbattery.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.soulbattery").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.ectoplasmhorseshoe.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.ectoplasmhorseshoe").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));



        registration.addIngredientInfo(new ItemStack(Items.greedcrystal.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.greedcrystal").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.obsidianring.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.obsidianring").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.redamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.redamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.blackeorb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blackeorb").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.badgeofthedead.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.badgeofthedead").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.whiteorb.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.whiteorb").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.greedamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.greedamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.evilcandle.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.evilcandle").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.evilmug.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.evilmug").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.blueamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.blueamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.battery.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.battery").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.diemug.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.diemug").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));
        registration.addIngredientInfo(new ItemStack(Items.warcrystal.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.warcrystal").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.loot.all")));

        registration.addIngredientInfo(new ItemStack(Items.maxamout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.maxamout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.maxamout")));

        registration.addIngredientInfo(new ItemStack(Items.as_amout.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.as_amout").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.doomeye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.doomeye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.doomswoud.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.doomswoud").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.magiceye.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.magiceye").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.magicstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.magicstone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.million.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.million").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.nano_box.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nano_box").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.nanocube.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nanocube").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.nanorobot.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.nanorobot").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.thedoomstone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.thedoomstone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.thefruit.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.thefruit").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));
        registration.addIngredientInfo(new ItemStack(Items.wind.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.wind").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.nanodoom.all")));

        registration.addIngredientInfo(new ItemStack(Items.wind_and_rain.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.wind_and_rain").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.wind_and_rain")));


        registration.addIngredientInfo(new ItemStack(Items.pain_candle.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.pain_candle").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.pain.all")));
        registration.addIngredientInfo(new ItemStack(Items.pain_ring.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.pain_ring").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.pain.all")));
        registration.addIngredientInfo(new ItemStack(Items.the_pain_stone.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.the_pain_stone").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.pain.all")));


        registration.addIngredientInfo(new ItemStack(Items.calcification.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.calcification").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.calcification")));
        registration.addIngredientInfo(new ItemStack(Items.masticatory.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.masticatory").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.masticatory")));
        registration.addIngredientInfo(new ItemStack(Items.polyphagia.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.polyphagia").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.polyphagia")));
        registration.addIngredientInfo(new ItemStack(Items.quadriceps.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.quadriceps").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.quadriceps")));
        registration.addIngredientInfo(new ItemStack(Items.reanimation.get()), VanillaTypes.ITEM_STACK, Component.translatable("item.moonstone.reanimation").append(Component.literal(" : ")).append(Component.translatable("moonstone.jei.reanimation")));

    }
}
